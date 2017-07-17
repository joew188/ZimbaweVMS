package Zim.task;

import Zim.common.SysConfigUtil;
import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.map.ExportLog;
import Zim.model.ImportLog;
import Zim.model.modelview.MatchedResult;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.*;

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
    private void saveTaskInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ImportLog.NAME, exportLog.getName());
        map.put(ImportLog.DEVICENAME, exportLog.getDeviceName());
        map.put(ImportLog.EXPORTDATETIME, exportLog.getExportDateTime().toGregorianCalendar().getTime());
        map.put(ImportLog.TOTAL, exportLog.getTotal());
        map.put(ImportLog.MALE, exportLog.getMale());
        map.put(ImportLog.FEMALE, exportLog.getFemale());
        map.put(ImportLog.STATUS, 0);
        map.put(ImportLog.BEGINTIME, new Date());
        map.put(ImportLog.FINISHTIME, null);
        map.put(ImportLog.SUCCESS, null);
        map.put(ImportLog.FAIL, null);
        map.put(ImportLog.GAP, null);
        mongoDBDaoImpl.addObject(map, "ImportLog");
    }

    /**
     * 更新任务信息，完成时间，导入统计。
     */
    private void updateTaskInfo() {

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
                saveTaskInfo();
                BlockingQueue<Applicant> buffer = new LinkedBlockingQueue<Applicant>(20);
                ConcurrentLinkedQueue<ImportResult> recordQueue = new ConcurrentLinkedQueue<>();
                AtomicInteger count = new AtomicInteger(fileQueue.size());
                AtomicInteger count2 = new AtomicInteger(fileQueue.size());
                String[] flag = {"working"};
                BlockingQueue<MatchInfo> matcherQueue = new LinkedBlockingQueue<>(20);

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
                    updateTaskInfo();
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
                    int intBirth = SystemHelper.DateToInt(applicant.getApplicantDemographic().getDateOfBirth());
                    if (intBirth > 0)
                        applicant.setDateOfBirth(intBirth);
                    applicant.setSurname(applicant.getApplicantDemographic().getSurname());
                    applicant.setGender(applicant.getApplicantDemographic().getGender() == 1);
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
        private BlockingQueue<MatchInfo> matcherQueue;
        private String[] flag;

        public Consumer(AtomicInteger count,
                        BlockingQueue<Applicant> buffer,
                        ConcurrentLinkedQueue<ImportResult> recordQueue,
                        BlockingQueue<MatchInfo> matcherQueue,
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
                    iResult.fileName = applicant.getGuid();
                    if (applicant.get_id() != "-1") {
                        mongoDBDaoImpl.insert(SystemHelper.MONGODBSETTING_DB, "Applicant", applicant.toDBObject());
                        importSuccess = true;
                    }

                } catch (Exception ex) {
                    importSuccess = false;
                    applicant = new Applicant();
                } finally {
                    count.getAndDecrement();
                    matcherQueue.add(MatchInfo.toMatchInfo(applicant, importSuccess));
                    iResult.ImportStatus = importSuccess;
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
        private BlockingQueue<MatchInfo> queue;

        public Matcher(AtomicInteger count, BlockingQueue<MatchInfo> queue) {
            this.count = count;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (count.get() > 0) {
                try {
                    MatchInfo matchInfo = queue.take();
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count.getAndDecrement();
                }
            }
        }


        private HashSet<MatchedResult> MapReduceFunc(MatchInfo matchInfo) {
            HashSet<MatchedResult> results = new HashSet<MatchedResult>();
            LocalDate localBirth = SystemHelper.getUnLeapYearBirthDate(matchInfo.getDateOfBirth());
            StringBuilder sbMap = new StringBuilder();
            String surName = matchInfo.getSurname();
            String guid = matchInfo.getGuid();
            boolean gender = matchInfo.getGender();
            DB dbMongo = mongoDBDaoImpl.getDb(SystemHelper.MONGODBSETTING_DB);
            sbMap.append("function() {");
            sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", surName));
            sbMap.append("var tgrSodEX= SoundEx(this.surname);");
            sbMap.append(String.format("var leven= levenshtein(\"%s\",this.surname);", surName));
            sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
            sbMap.append(String.format("emit(\"%s\",{id:this.guid,leven:leven});}}", guid));


            StringBuilder reduce = new StringBuilder();
            reduce.append("function(key, values) { return {values:values};}");


            String match_result = "match_result";

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

            MapReduceCommand mrc = new MapReduceCommand(dbMongo.getCollection("Applicant"), sbMap.toString(), reduce.toString(), match_result, MapReduceCommand.OutputType.REPLACE, dbQuery);

            MapReduceOutput mapReduceOutput = dbMongo.getCollection("Applicant").mapReduce(mrc);
            DBCollection resultColl = mapReduceOutput.getOutputCollection();
            List<DBObject> listResult = resultColl.find().toArray();
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
        private String fileName;
        private boolean ImportStatus;

        private int total;
        private int male;
        private int female;

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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getMale() {
            return male;
        }

        public void setMale(int male) {
            this.male = male;
        }

        public int getFemale() {
            return female;
        }

        public void setFemale(int female) {
            this.female = female;
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
