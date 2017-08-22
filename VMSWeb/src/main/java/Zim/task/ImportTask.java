package Zim.task;

import Zim.common.SysConfigUtil;
import Zim.common.SystemHelper;
import Zim.linstener.FileListener;
import Zim.model.Applicant;
import Zim.map.ExportLog;
import Zim.model.ImportLog;
import Zim.model.modelview.MatchedResult;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(FileListener.class);
    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
    final static ExecutorService service = Executors.newCachedThreadPool();
    private AtomicInteger m_importTotal = new AtomicInteger(1);
    private AtomicInteger m_importMale = new AtomicInteger(1);
    private AtomicInteger m_importFemale = new AtomicInteger(1);

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
//        importLog.set_id(UUID.randomUUID().toString());
        importLog.setDeviceName(exportLog.getDeviceName());
        importLog.setName(exportLog.getName());
        importLog.setExportDateTime(exportLog.getExportDateTime());
        importLog.setExportTotal(exportLog.getTotal());
        importLog.setExportMale(exportLog.getMale());
        importLog.setExportFemale(exportLog.getFemale());

        importLog.setLastSerialNumber(exportLog.getLastSerialNumber());
        importLog.setFirstSerialNumber(exportLog.getFirstSerialNumber());
        importLog.setKitTotal(exportLog.getKitTotal());
        importLog.setNameOfOperator(exportLog.getNameOfOperator());
        importLog.setIdNumberOfOperator(exportLog.getIdNumberOfOperator());
        importLog.setOperatorGuid(exportLog.getOperatorGuid());

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
     * @param
     */
    private void updateTaskInfo(DBObject idQuery) {
        DBObject upQuery = new BasicDBObject();
        upQuery.put("importFinishTime", new Date());
        upQuery.put("status", 1);
        mongoDBDaoImpl.updateObject(idQuery, "ImportLog", upQuery);
    }

    @Override
    public void run() {
        logger.error("Import Task Run:" + xmlFilePath);
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
                // ConcurrentLinkedQueue<ImportResult> recordQueue = new ConcurrentLinkedQueue<>();

                DBObject importLogQuery = new BasicDBObject();

                importLogQuery.put("_id", importLog.get("_id"));


                AtomicInteger count = new AtomicInteger(fileQueue.size());
                AtomicInteger count2 = new AtomicInteger(fileQueue.size());
                String[] flag = {"working"};
                ConcurrentLinkedQueue<MatchInfo> matcherQueue = new ConcurrentLinkedQueue<>();

                Producer producer = new Producer(fileQueue, buffer, this.xmlFilePath);
                Consumer consumer = new Consumer(count, buffer, importLogQuery, matcherQueue, flag);
                Matcher matcher = new Matcher(count2, matcherQueue);
                for (int i = 0; i < importScanNum; i++) {
                    service.submit(producer);
                    service.submit(consumer);
                    service.submit(matcher);
                }

                //   结束
                synchronized (flag) {
                    while (flag[0].equals("working")) {
                        try {
                            flag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    updateTaskInfo(importLogQuery);
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
                    int intBirth = SystemHelper.DateToInt(applicant.getApplicantDemographic().getDateOfBirth());
                    if (intBirth > 0)
                        applicant.setDateOfBirth(intBirth);
                    int intRegistration = SystemHelper.DateToInt(applicant.getEndCreateDatetime());
                    if (intRegistration > 0)
                        applicant.setDateOfRegistration(intRegistration);
                    applicant.setFullName(applicant.getApplicantDemographic().getForenames() + " " + applicant.getApplicantDemographic().getSurname());
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
        private DBObject importLogQuery;
        private ConcurrentLinkedQueue<MatchInfo> matcherQueue;
        private String[] flag;

        public Consumer(AtomicInteger count,
                        BlockingQueue<Applicant> buffer,
                        DBObject importLogQuery,
                        ConcurrentLinkedQueue<MatchInfo> matcherQueue,
                        String[] flag) {
            this.buffer = buffer;
            this.count = count;
            this.importLogQuery = importLogQuery;
            this.flag = flag;
            this.matcherQueue = matcherQueue;
        }

        @Override
        public void run() {
            while (count.get() > 0) {
                boolean importSuccess = false;
                Applicant applicant = null;
                try {
                    applicant = buffer.take();

                    if (applicant.get_id() != "-1") {
                        if (mongoDBDaoImpl.isExist(SystemHelper.MONGODBSETTING_DB, "Applicant", "guid", applicant.getGuid())) {
                            importSuccess = false;
                            applicant = new Applicant();
                        } else {
                            mongoDBDaoImpl.insert(SystemHelper.MONGODBSETTING_DB, "Applicant", applicant.toDBObject(mongoDBDaoImpl));
                            importSuccess = true;
                        }
                    }

                } catch (Exception ex) {
                    importSuccess = false;
                    applicant = new Applicant();
                } finally {
                    count.getAndDecrement();
                    matcherQueue.add(MatchInfo.toMatchInfo(applicant, importSuccess));

                    if (importSuccess) {
                        DBObject upQuery = new BasicDBObject();
                        upQuery.put("importTotal", m_importTotal.getAndAdd(1));
                        if (applicant.getGender() == 1) {
                            upQuery.put("importMale", m_importMale.getAndAdd(1));
                        } else {
                            upQuery.put("importFemale", m_importFemale.getAndAdd(1));
                        }
                        mongoDBDaoImpl.updateObject(importLogQuery, "ImportLog", upQuery);
                    }
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
            String fullName = matchInfo.getFullName();
            String guid = matchInfo.getGuid();
            int gender = matchInfo.getGender();
            DB dbMongo = mongoDBDaoImpl.getDb(SystemHelper.MONGODBSETTING_DB);
            sbMap.append("function() {");
            sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", fullName));
            sbMap.append("var tgrSodEX= SoundEx(this.fullName);");
            sbMap.append(String.format("var leven= levenshtein(\"%s\",this.fullName);", fullName));
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
            listResult.stream().filter(obj -> obj.keySet().contains("value")).forEach(obj -> {
                DBObject dbObject = (DBObject) obj.get("value");
                if (dbObject.keySet().contains("id") && dbObject.keySet().contains("leven")) {
                    results.add(new MatchedResult(dbObject));
                } else {
                    if (dbObject.keySet().contains("values")) {
                        addMatchedResult(results, (BasicDBList) dbObject.get("values"));
                    }
                }
            });
            return results;
        }

        private void addMatchedResult(HashSet<MatchedResult> results, BasicDBList basicDBList) {
            for (Object obj : basicDBList) {
                DBObject item = (DBObject) obj;
                if (item.keySet().contains("id") && item.keySet().contains("leven")) {
                    results.add(new MatchedResult(item));
                } else {
                    if (item.keySet().contains("values")) {
                        addMatchedResult(results, (BasicDBList) item.get("values"));
                    }
                }
            }
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
