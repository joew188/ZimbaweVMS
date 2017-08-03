package Zim.task;

import Zim.common.SysConfigUtil;
import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.map.ExportLog;
import Zim.model.ImportLog;
import Zim.model.modelview.MatchedResult;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.*;
import org.bson.types.ObjectId;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Laxton-Joe on 2017/7/11.
 */
public class ImportTask implements Runnable {
    final static int importScanNum = Integer.parseInt(SysConfigUtil.getSetting("import-thread-num"));
    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
    final static ExecutorService service = Executors.newCachedThreadPool();


    public ImportTask(String exportFile) {
        ExportLog exportLog = SystemHelper.ImportInfo(new File(exportFile));

        this.exportLog = exportLog;
        this.xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + exportLog.getName();
    }

    private ExportLog exportLog;
    private String xmlFilePath;


    /**
     * 保存任务信息
     */
    private DBObject saveTaskInfo() {

        ImportLog importLog = new ImportLog();
        importLog.set_id(UUID.randomUUID().toString());
        importLog.setDeviceName(exportLog.getDeviceName());
        importLog.setName(exportLog.getName());
        importLog.setExportDateTime(exportLog.getExportDateTime());
        importLog.setExportTotal(exportLog.getTotal());
        importLog.setExportMale(exportLog.getMale());
        importLog.setExportFemale(exportLog.getFemale());
        importLog.setStatus(0);
        importLog.setImportBeginTime(new Date());
        importLog.setImportFinishTime(null);
        importLog.setImportTotal(0);
        importLog.setImportMale(0);
        importLog.setImportFemale(0);
        DBObject ilObj = mongoDBDaoImpl.insert(SystemHelper.MONGODBSETTING_DB, "ImportLog", importLog.toDBObject());
        return ilObj;
    }

    /**
     * 更新任务信息，完成时间，导入统计。
     *
     * @param importLog
     * @param recordQueue
     */
    private void updateTaskInfo(DBObject importLog, ConcurrentLinkedQueue<ImportResult> recordQueue) {
        int total = 0;
        int male = 0;
        int female = 0;
        for (ImportResult iResult : recordQueue) {
            if (iResult.ImportStatus) {
                total++;
                if (iResult.gender == 1) {
                    male++;
                }
                if (iResult.gender == 2) {
                    female++;
                }
            }
        }
        DBObject upQuery = new BasicDBObject();
        upQuery.put("importTotal", total);
        upQuery.put("importMale", male);
        upQuery.put("importFemale", female);
        upQuery.put("importFinishTime", new Date());
        upQuery.put("status", 1);
        DBObject idQuery = new BasicDBObject();
        //  idQuery.put("_id", new ObjectId(importLog.get("_id").toString()));
        idQuery.put("_id", importLog.get("_id").toString());
        mongoDBDaoImpl.updateObject(idQuery, "ImportLog", upQuery);
    }

    @Override
    public void run() {
        ConcurrentLinkedQueue<String> fileQueue = new ConcurrentLinkedQueue<String>();
        File directory = new File(this.xmlFilePath);
        if (directory.isDirectory()) {
            GenericExtFilter filter = new GenericExtFilter(".xml");
            String files[] = directory.list(filter);
            for (String fileName : files) {
                fileQueue.add(fileName);
            }
            if (!fileQueue.isEmpty()) {
                DBObject importLog = saveTaskInfo();
                BlockingQueue<Applicant> buffer = new LinkedBlockingQueue<Applicant>(100);
                ConcurrentLinkedQueue<ImportResult> recordQueue = new ConcurrentLinkedQueue<>();
                AtomicInteger count = new AtomicInteger(fileQueue.size());
                AtomicInteger count2 = new AtomicInteger(fileQueue.size());
                String[] flag = {"working"};
                ConcurrentLinkedQueue<MatchInfo> matcherQueue = new ConcurrentLinkedQueue<>();

                Producer producer = new Producer(fileQueue, buffer, this.xmlFilePath);
                Consumer consumer = new Consumer(count, buffer, recordQueue, matcherQueue, flag);
                Matcher matcher = new Matcher(count2, matcherQueue);
                for (int i = 0; i < importScanNum; i++) {
                    service.submit(producer);
                    service.submit(consumer);
                    service.submit(matcher);
                }

                //结束
                synchronized (flag) {
                    while (flag[0].equals("working")) {
                        try {
                            flag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //  System.out.println("任务完成");
                    updateTaskInfo(importLog, recordQueue);
                }
            }
        }
    }

    class Producer implements Runnable {
        private String xmlFilePath;
        ConcurrentLinkedQueue<String> fileQueue;
        BlockingQueue<Applicant> buffer;

        public Producer(ConcurrentLinkedQueue<String> fileQueue, BlockingQueue<Applicant> buffer, String xmlFilePath) {
            this.xmlFilePath = xmlFilePath;
            this.buffer = buffer;
            this.fileQueue = fileQueue;
        }

        @Override
        public void run() {
            while (!fileQueue.isEmpty()) {
                String fileName = fileQueue.poll();
                boolean produceSuccess = true;
                Applicant applicant = new Applicant();
                try {
                    File file = new File(xmlFilePath + File.separator + fileName);
                    InputStream fileStream = new FileInputStream(file);
                    JAXBContext jaxbContext = JAXBContext.newInstance(Applicant.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    applicant = (Applicant) jaxbUnmarshaller.unmarshal(fileStream);
                    //  applicant.getApplicantDemographic().setGenderBool(applicant.getApplicantDemographic().getGender().toLowerCase().equals("male"));
                    // if (null != applicant.getApplicantDemographic().getDateOfBirth()) {
                    int intBirth = SystemHelper.DateToInt(applicant.getApplicantDemographic().getDateOfBirth());
                    if (intBirth > 0)
                        applicant.setDateOfBirth(intBirth);
                    // }
                    //  if (null != applicant.getApplicantDemographic().getEndCreateDatetime()) {
                    int intRegistration = SystemHelper.DateToInt(applicant.getApplicantDemographic().getEndCreateDatetime());
                    if (intRegistration > 0)
                        applicant.setDateOfRegistration(intRegistration);

                    // }
                    applicant.setSurname(applicant.getApplicantDemographic().getSurname());
                    applicant.setGender(applicant.getApplicantDemographic().getGender());
                    applicant.set_id(applicant.getGuid());

                } catch (JAXBException e) {//xml 无效. 无法转成对象
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("FileName", fileName);
                    map.put("ExceptionType", "JAXBException");
                    map.put("Exception", e.toString());
                    map.put("FailTime", new Date());
                    mongoDBDaoImpl.addObject(map, "ApplicantProduceFailLog");
                    applicant = new Applicant();
                    applicant.set_id("-1");
                    applicant.setGuid(fileName);
                } catch (FileNotFoundException e) {
                    if (new File(fileName).exists()) {
                        fileQueue.add(fileName);
                        produceSuccess = false;//重新排除，不通知消费者。
                    }
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("FileName", fileName);
                    map.put("ExceptionType", "FileNotFoundException");
                    map.put("Exception", e.toString());
                    map.put("FailTime", new Date());
                    mongoDBDaoImpl.addObject(map, "ApplicantProduceFailLog");
                } catch (Exception ex) {
                    applicant = new Applicant();
                    applicant.set_id("-1");
                    applicant.setGuid(fileName);
                }
                if (produceSuccess) {
                    try {
                        buffer.put(applicant);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Consumer implements Runnable {

        private BlockingQueue<Applicant> buffer;
        private AtomicInteger count;
        private ConcurrentLinkedQueue<ImportResult> recordQueue;
        private ConcurrentLinkedQueue<MatchInfo> matcherQueue;
        private String[] flag;

        public Consumer(AtomicInteger count,
                        BlockingQueue<Applicant> buffer,
                        ConcurrentLinkedQueue<ImportResult> recordQueue,
                        ConcurrentLinkedQueue<MatchInfo> matcherQueue,
                        String[] flag) {
            this.buffer = buffer;
            this.count = count;
            this.recordQueue = recordQueue;
            this.flag = flag;
            this.matcherQueue = matcherQueue;
        }

        @Override
        public void run() {
            while (count.get() > 0) {
                boolean importSuccess = false;
                ImportResult iResult = new ImportResult();
                Applicant applicant = null;
                try {
                    applicant = buffer.take();

                    if (applicant.get_id() != "-1") {
                        if (mongoDBDaoImpl.isExist(SystemHelper.MONGODBSETTING_DB, "Applicant", "_id", applicant.get_id())) {
                            importSuccess = false;
                            applicant = new Applicant();
                        } else {
                            mongoDBDaoImpl.insert(SystemHelper.MONGODBSETTING_DB, "Applicant", applicant.toDBObject());
                            importSuccess = true;
                        }
                    }

                } catch (Exception ex) {
                    importSuccess = false;
                    applicant = new Applicant();
                } finally {
                    count.getAndDecrement();
                    matcherQueue.add(MatchInfo.toMatchInfo(applicant, importSuccess));
                    iResult.ImportStatus = importSuccess;
                    if (importSuccess) {
                        iResult.fileName = applicant.getGuid();
                        iResult.gender = applicant.getGender();
                    }
                    recordQueue.add(iResult);
                }
            }

            if (count.get() == 0) {
                synchronized (flag) {
                    flag[0] = "false";
                    flag.notify();
                }
            }
        }
    }

    class Matcher implements Runnable {
        private AtomicInteger count;
        private ConcurrentLinkedQueue<MatchInfo> queue;

        public Matcher(AtomicInteger count, ConcurrentLinkedQueue<MatchInfo> queue) {
            this.count = count;
            this.queue = queue;
        }

        @Override
        public void run() {
            synchronized (queue) {
                while (count.get() > 0) {

                    while (!queue.isEmpty()) {


                        try {
                            MatchInfo matchInfo = queue.poll();
                            if (matchInfo.isWaitMatch()) {
                                HashSet<MatchedResult> matchArr = MapReduceFunc(matchInfo);
                                StringBuilder sb = new StringBuilder();
                                sb.append("empty.,");
                                if (matchArr != null && matchArr.size() > 0) {
                                    sb = new StringBuilder();
                                    for (MatchedResult mr : matchArr) {
                                        Map<String, Object> map = new HashMap<String, Object>();
                                        map.put("probeId", matchInfo.getGuid());
                                        map.put("referenceId", mr.getId());
                                        map.put("leven", mr.getLeven());
                                        map.put("status", "Pending");
                                        map.put("createdTime", new Date());
                                        mongoDBDaoImpl.addObject(map, "Duplicate"); //插入到Duplicate
                                        sb.append(mr.getId() + ",");
                                    }
                                }

                                //更新Applicant 状态
                                mongoDBDaoImpl.updateStatus(matchInfo.get_id(), "Applicant", "status", 1);
                                //日志
                                Map<String, Object> mapLog = new HashMap<String, Object>();
                                mapLog.put("applicantId", matchInfo.getGuid());
                                mapLog.put("LogEvent", String.format("Applicant Matched Status:Master.Reference:%s", sb.toString().substring(0, sb.toString().length() - 2)));
                                mapLog.put("LogTime", new Date());

                                mongoDBDaoImpl.addObject(mapLog, "ApplicantLog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            count.getAndDecrement();

                        }
                    }
                }
            }
        }

        private HashSet<MatchedResult> MapReduceFunc(MatchInfo matchInfo) {

            //插入到Duplicate


            HashSet<MatchedResult> results = new HashSet<MatchedResult>();
            LocalDate localBirth = SystemHelper.getUnLeapYearBirthDate(matchInfo.getDateOfBirth());
            StringBuilder sbMap = new StringBuilder();
            String surName = matchInfo.getSurname();
            String guid = matchInfo.getGuid();
            int gender = matchInfo.getGender();
            DB dbMongo = mongoDBDaoImpl.getDb(SystemHelper.MONGODBSETTING_DB);
            sbMap.append("function() {");
            sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", surName));
            sbMap.append("var tgrSodEX= SoundEx(this.surname);");
            sbMap.append(String.format("var leven= levenshtein(\"%s\",this.surname);", surName));
            sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
            sbMap.append(String.format("emit(\"%s\",{id:this.guid,leven:leven});}}", guid));


            StringBuilder reduce = new StringBuilder();
            reduce.append("function(key, values) { return {values:values};}");


            String match_result = "match_result";//+matchInfo.getGuid();

            DBObject dbQuery = new BasicDBObject();
            dbQuery.put("gender", gender);
            dbQuery.put("status", 1);

            LocalDate startDate = localBirth.minusYears(10);
            LocalDate endDate = localBirth.plusYears(10);
            int iStart = Integer.parseInt(SystemHelper.LocalToString(startDate));
            int iEend = Integer.parseInt(SystemHelper.LocalToString(endDate));
            BasicDBObject birthQuery = new BasicDBObject();
            birthQuery.put("$gte", iStart);
            birthQuery.put("$lt", iEend);
            dbQuery.put("dateOfBirth", birthQuery);

            MapReduceCommand mrc = new MapReduceCommand(dbMongo.getCollection("Applicant"), sbMap.toString(), reduce.toString(), match_result, MapReduceCommand.OutputType.MERGE, dbQuery);

            MapReduceOutput mapReduceOutput = dbMongo.getCollection("Applicant").mapReduce(mrc);
            DBCollection resultColl = mapReduceOutput.getOutputCollection();
            DBObject idQuery = new BasicDBObject();

            idQuery.put("_id", matchInfo.getGuid());
            List<DBObject> listResult = resultColl.find(idQuery).toArray();
            for (DBObject obj : listResult) {
                if (obj.keySet().contains("value")) {
                    DBObject _o1 = (DBObject) obj.get("value");
                    if (_o1.keySet().contains("id") && _o1.keySet().contains("leven")) {
                        results.add(new MatchedResult(_o1));
                    }
                    if (_o1.keySet().contains("values")) {
                        BasicDBList _oList = (BasicDBList) _o1.get("values");
                        for (Object o : _oList) {
                            BasicDBObject item = (BasicDBObject) o;
                            if (item.keySet().contains("id") && item.keySet().contains("leven")) {
                                results.add(new MatchedResult(item));
                            }
                        }
                    }
                }
            }
            return results;
        }
    }

    class ImportResult {
        private String fileName;//导入xml文件名
        private boolean ImportStatus;//导入结果


        private int gender;//导入性别

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }


        public boolean isImportStatus() {
            return ImportStatus;
        }

        public void setImportStatus(boolean importStatus) {
            ImportStatus = importStatus;
        }


        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }

    public class GenericExtFilter implements FilenameFilter {
        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }


}
