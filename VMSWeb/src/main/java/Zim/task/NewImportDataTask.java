package Zim.task;


import Zim.common.SysConfigUtil;
import Zim.common.SystemHelper;
import Zim.model.*;
import Zim.model.map.ExportLog;
import Zim.mongo.MongoDao;
import com.mongodb.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Laxton-Joe on 2017/9/7.
 */
public class NewImportDataTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(NewImportDataTask.class);
    final static int importThreadNum = Integer.parseInt(SysConfigUtil.getSetting("import-thread-num"));
    private ExportLog exportLog;
    private MongoDao mongoDao = new MongoDao();

    public NewImportDataTask(String path) {
        this.exportLog = SystemHelper.ImportInfo(new File(path));
    }

    @Override
    public void run() {
        ConcurrentLinkedQueue<String> fileQueue = new ConcurrentLinkedQueue<>();
        String xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + exportLog.getName();
        File directory = new File(xmlFilePath);
        if (directory.isDirectory()) {
            String[] files = directory.list(new ImportExtFilter(".xml"));
            Collections.addAll(fileQueue, files);
            if (!fileQueue.isEmpty()) {
                Document importLog = saveTaskInfo();
                if (importLog != null) {
                    try {

                        BlockingQueue<Applicant> applicantQueue = new LinkedBlockingQueue<>(100);

                        Thread threadConverter = new Thread(new Converter(applicantQueue, fileQueue, exportLog.getName()));
                        threadConverter.start();


                        List<Thread> threadList = new ArrayList<>();
                        for (int i = 0; i < importThreadNum; ++i) {
                            threadList.add(new Thread(new Importer(applicantQueue)));
                        }
                        for (Thread thread : threadList) {
                            thread.start();
                        }


                        threadConverter.join();

                        for (int i = 0; i < importThreadNum; ++i) {
                            applicantQueue.put(new Applicant(true));
                        }
//                        executor.shutdown();

                        for (Thread thread : threadList) {
                            thread.join();
                        }
//                        threadImporter.join();
                        // ImporterThread.join();
                        updateTaskInfo("importFinishTime", "Imported");
//                        Thread threadMatcher = new Thread(new Matcher(matchQueue));
//                        threadMatcher.setPriority(Thread.MIN_PRIORITY);
//                        updateTaskInfo("matchBeginTime", "Matching");
//                        threadMatcher.start();
//                        threadMatcher.join();
//                        updateTaskInfo("matchFinishTime", "Matched");


                        if (mongoDao.tryInsertOne("MatchTaskQueue", new MatchTaskQueue(exportLog.getName()).toDocument())) {
                            SystemHelper.matchQueue.put(exportLog.getName());
                        }

                        mongoDao.close();


                    } catch (Exception ex) {
                        logger.error(ex.toString());
                    }
                }
            }
        }
    }

    class Converter implements Runnable {
        BlockingQueue<Applicant> applicantQueue;
        ConcurrentLinkedQueue<String> fileQueue;
        private String taskName;
        private String xmlFilePath;

        public Converter(BlockingQueue<Applicant> applicantQueue, ConcurrentLinkedQueue<String> fileQueue, String taskName) {
            this.applicantQueue = applicantQueue;
            this.fileQueue = fileQueue;
            this.taskName = taskName;
            this.xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + taskName;
        }

        @Override
        public void run() {
            while (!fileQueue.isEmpty()) {
                String fileName = fileQueue.poll();

                Applicant applicant = null;
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
                    applicant.setImportTask(taskName);
                    applicant.setGender(applicant.getApplicantDemographic().getGender());
                    applicant.set_id(applicant.getGuid());

                } catch (JAXBException | FileNotFoundException e) {//xml 无效. 无法转成对象
                    logger.error(e.toString());
                } catch (Exception ex) {
                    logger.error(ex.toString());
                } finally {
                    if (applicant != null) {
                        try {
                            applicantQueue.put(applicant);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    class Importer implements Runnable {
        BlockingQueue<Applicant> applicantQueue;
//        BlockingQueue<MatchInfo> matchQueue;

        //        public Importer(BlockingQueue<Applicant> applicantQueue) {
//            this.applicantQueue = applicantQueue;
//        }
        public Importer(BlockingQueue<Applicant> applicantQueue) {
            this.applicantQueue = applicantQueue;
//            this.matchQueue = matchQueue;
        }

        @Override
        public void run() {
            while (true) {
//                MatchInfo matchInfo = null;
                boolean isCommitSuccess = false;
                Applicant applicant = null;
                ImportTransaction iTran = null;

                try {
                    applicant = applicantQueue.take();
                    if (applicant.isPoisonPill()) {
                        break;
                    }
                    ObjectId transactionId = new ObjectId();
                    iTran = new ImportTransaction(transactionId, applicant.get_id(), mongoDao);
                    Map<String, List<Document>> documentMap = applicant.toDocument(transactionId);
                    boolean importApplicantSuccess = false;

                    try {
                        mongoDao.insertOne("Applicant", documentMap.get("Applicant").get(0));
                        if (documentMap.keySet().contains("ApplicantPhotos")) {
                            mongoDao.insertMany("ApplicantPhotos", documentMap.get("ApplicantPhotos"));
                        }
                        if (documentMap.keySet().contains("FingerprintImage")) {
                            mongoDao.insertMany("FingerprintImage", documentMap.get("FingerprintImage"));
                        }
                        if (documentMap.keySet().contains("FingerprintWSQ")) {
                            mongoDao.insertMany("FingerprintWSQ", documentMap.get("FingerprintWSQ"));
                        }
                        if (documentMap.keySet().contains("FingerprintTemplate")) {
                            mongoDao.insertMany("FingerprintTemplate", documentMap.get("FingerprintTemplate"));
                        }
                        iTran.setInserted(true);
                        importApplicantSuccess = true;
                    } catch (MongoWriteException ex) {
                        if (ex.getError().getCode() == 11000) {
                            if (ex.getMessage().contains("_id_ dup")) {
                                importApplicantSuccess = true;
                            }
                        }
                    } catch (Exception ex) {
                        importApplicantSuccess = false;
                        logger.error(ex.toString());
                    }


                    boolean importMasterSuccess = false;

                    if (importApplicantSuccess) {
                        if (mongoDao.tryInsertOne("ApplicantMaster", documentMap.get("ApplicantMaster").get(0))) {
                            iTran.setInserted(true);
                            importMasterSuccess = true;
                        }
                    }
                    if (importApplicantSuccess && importMasterSuccess) {
                        isCommitSuccess = iTran.commit();
                    }
                } catch (Exception e) {
                    logger.error(e.toString());
                    isCommitSuccess = false;
                } finally {
                    if (!isCommitSuccess) {
                        if (applicant != null && iTran != null) {

                            iTran.rollBack();

                        }
                    }
                }
            }
        }
    }


//    class Matcher implements Runnable {
//        BlockingQueue<MatchInfo> matchQueue;
//        private AtomicInteger poisonPillcount = new AtomicInteger(0);
//
//        public Matcher(BlockingQueue<MatchInfo> matchQueue) {
//            this.matchQueue = matchQueue;
//        }
//
//        @Override
//        public void run() {
//            synchronized (matchQueue) {
//                while (true) {
//                    try {
//                        MatchInfo matchInfo = matchQueue.take();
//                        if (matchInfo.isPoisonPill()) {
//                            poisonPillcount.getAndIncrement();
//                            if (poisonPillcount.get() == importThreadNum) {
//                                break;
//                            }
//                            continue;
//                        }
//
//                        HashSet<MatchedResult> matchArr = MapReduceFunc(matchInfo);
//                        StringBuilder sb = new StringBuilder();
//                        sb.append("empty.,");
//                        if (matchArr != null && matchArr.size() > 0) {
//                            sb = new StringBuilder();
//                            for (MatchedResult mr : matchArr) {
//                                Map<String, Object> map = new HashMap<String, Object>();
//                                map.put("probeId", matchInfo.get_id());
//                                map.put("probeNumber", matchInfo.getRegistrationNumber());
//                                map.put("referenceId", mr.getId());
//                                map.put("referenceNumber", mr.getRegistrationNumber());
//                                map.put("leven", mr.getLeven());
//                                map.put("status", "Pending");
//                                map.put("createdTime", new Date());
//                                mongoDao.addObject(map, "Duplicate"); //插入到Duplicate
//                                sb.append(mr.getId() + ",");
//                            }
//                        }
//
//                        //更新Applicant 状态
//                        mongoDao.updateStatus(matchInfo.get_id(), "ApplicantMaster", "status", 1);
//                        //日志
//                        Map<String, Object> mapLog = new HashMap<String, Object>();
//                        mapLog.put("applicantId", matchInfo.get_id());
//                        mapLog.put("logEvent", String.format("Applicant Matched Status:Master.Reference:%s", sb.toString().substring(0, sb.toString().length() - 2)));
//                        mapLog.put("logTime", new Date());
//
//                        mongoDao.addObject(mapLog, "ApplicantLog");
//
//                    } catch (InterruptedException iex) {
//                        // Restore the interrupted status
//                        Thread.currentThread().interrupt();
//                    } catch (Exception ex) {
//                        // logger.error(notifyAdmin, ex.toString() + "338");//,
//                    } finally {
//                        //  count.getAndDecrement();
//
//                    }
//                }
//            }
//        }
//
//        private HashSet<MatchedResult> MapReduceFunc(MatchInfo matchInfo) {
//
//            //插入到Duplicate
//            HashSet<MatchedResult> results = new HashSet<MatchedResult>();
//            LocalDate localBirth = SystemHelper.getUnLeapYearBirthDate(matchInfo.getDateOfBirth());
//            StringBuilder sbMap = new StringBuilder();
//            String fullName = matchInfo.getFullName();
//            String id = matchInfo.get_id();
//            int gender = matchInfo.getGender();
//            DB dbMongo = mongoDao.getDb();
//            sbMap.append("function() {");
//            sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", fullName));
//            sbMap.append("var tgrSodEX= SoundEx(this.forenames+\" \"+this.surname);");
//            sbMap.append(String.format("var leven= levenshtein(\"%s\",this.forenames+\" \"+this.surname);", fullName));
//            sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
//            sbMap.append(String.format("emit(\"%s\",{id:this._id,registrationNumber:this.registrationNumber,leven:leven});}}", id));
//
//
//            StringBuilder reduce = new StringBuilder();
//            reduce.append("function(key, values) { return {values:values};}");
//
//            String match_result = "match_result";
//            DBObject dbQuery = new BasicDBObject();
//            dbQuery.put("gender", gender);
//            dbQuery.put("status", 1);
//
//            LocalDate startDate = localBirth.minusYears(10);
//            LocalDate endDate = localBirth.plusYears(10);
//            int iStart = Integer.parseInt(SystemHelper.LocalToString(startDate));
//            int iEend = Integer.parseInt(SystemHelper.LocalToString(endDate));
//            BasicDBObject birthQuery = new BasicDBObject();
//            birthQuery.put("$gte", iStart);
//            birthQuery.put("$lt", iEend);
//            dbQuery.put("dateOfBirth", birthQuery);
//
//            MapReduceCommand mrc = new MapReduceCommand(dbMongo.getCollection("ApplicantMaster"), sbMap.toString(), reduce.toString(), match_result, MapReduceCommand.OutputType.MERGE, dbQuery);
//
//            MapReduceOutput mapReduceOutput = dbMongo.getCollection("ApplicantMaster").mapReduce(mrc);
//            DBCollection resultColl = mapReduceOutput.getOutputCollection();
//            DBObject idQuery = new BasicDBObject();
//
//            idQuery.put("_id", matchInfo.get_id());
//            List<DBObject> listResult = resultColl.find(idQuery).toArray();
//            listResult.stream().filter(obj -> obj.keySet().contains("value")).forEach(obj -> {
//                DBObject dbObject = (DBObject) obj.get("value");
//                if (dbObject.keySet().contains("leven")) {
//                    results.add(new MatchedResult(dbObject));
//                } else if (dbObject.keySet().contains("values")) {
//                    addMatchedResult(results, (BasicDBList) dbObject.get("values"));
//                }
//            });
//            return results;
//        }
//
//        private void addMatchedResult(HashSet<MatchedResult> results, BasicDBList basicDBList) {
//            for (Object obj : basicDBList) {
//                DBObject item = (DBObject) obj;
//                if (item.keySet().contains("leven")) {
//                    results.add(new MatchedResult(item));
//                } else if (item.keySet().contains("values")) {
//                    addMatchedResult(results, (BasicDBList) item.get("values"));
//                }
//            }
//        }
//    }

//    class Extractor implements Runnable {
//
//        private BlockingQueue<ApplicantMaster> masterQueue;
//        BlockingQueue<MatchInfo> matchQueue;
//
//        public Extractor(BlockingQueue<ApplicantMaster> masterQueue, BlockingQueue<MatchInfo> matchQueue) {
//            this.masterQueue = masterQueue;
//            this.matchQueue = matchQueue;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                MatchInfo matchInfo = null;
//                try {
//
//                    ApplicantMaster master = masterQueue.take();
//                    if (master.isPoisonPill()) {
//                        matchInfo = new MatchInfo(true);
//                        break;
//                    }
//
//                    boolean insertMaster = mongoDao.insertOne("ApplicantMaster", master.toDocument());
//                    if (insertMaster) {
//                        matchInfo = master.toMatchInfo();
//                    }
//                    //   System.out.println(Thread.currentThread().getId() + "--Extractor:" + master.get_id());
//                    //Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (matchInfo != null) {
//                        try {
//                            matchQueue.put(matchInfo);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }
//            }
//        }
//    }

    private Document saveTaskInfo() {

//        ImportLog importLog = new ImportLog();
//
//        importLog.setDeviceName(exportLog.getDeviceName());
//        importLog.setName(exportLog.getName());
//        importLog.setExportDateTime(exportLog.getExportDateTime());
//        importLog.setExportTotal(exportLog.getTotal());
//        importLog.setExportMale(exportLog.getMale());
//        importLog.setExportFemale(exportLog.getFemale());
//
//        importLog.setLastSerialNumber(exportLog.getLastSerialNumber());
//        importLog.setFirstSerialNumber(exportLog.getFirstSerialNumber());
//        importLog.setKitTotal(exportLog.getKitTotal());
//        importLog.setNameOfOperator(exportLog.getNameOfOperator());
//        importLog.setIdNumberOfOperator(exportLog.getIdNumberOfOperator());
//        importLog.setOperatorGuid(exportLog.getOperatorGuid());
//
//        importLog.setStatus(0);
//        importLog.setImportBeginTime(new Date());
//        importLog.setImportFinishTime(null);
//        importLog.setImportTotal(0);
//        importLog.setImportMale(0);
//        importLog.setImportFemale(0);


        org.bson.Document document = new org.bson.Document();

        document.append("_id", exportLog.getName());
        document.append("name", exportLog.getName());
        document.append("deviceName", exportLog.getDeviceName());
        document.append("exportDateTime", exportLog.getExportDateTime());
        document.append("exportTotal", exportLog.getTotal());
        document.append("exportMale", exportLog.getMale());
        document.append("exportFemale", exportLog.getFemale());
        document.append("lastSerialNumber", exportLog.getLastSerialNumber());
        document.append("firstSerialNumber", exportLog.getFirstSerialNumber());
        document.append("kitTotal", exportLog.getKitTotal());
        document.append("nameOfOperator", exportLog.getNameOfOperator());
        document.append("idNumberOfOperator", exportLog.getIdNumberOfOperator());
        document.append("operatorGuid", exportLog.getOperatorGuid());


        document.append("importBeginTime", new Date());
        document.append("importFinishTime", null);
        document.append("matchBeginTime", null);
        document.append("matchFinishTime", null);

        document.append("status", "Importing");
//        document.append("mportTotal", 0);
//        document.append("mportMale", 0);
//        document.append("mportFemale", 0);
//        document.append("matchedTotal", 0);


        // DBObject ilObj = importLog.toDBObject();
//        Document document = importLog.toDocument();
        if (!mongoDao.saveOne("ImportLog", document)) {
            document = null;
        }
        return document;
    }

    private void updateTaskInfo(String fieldName, String status) {
        BasicDBObject upQuery = new BasicDBObject();

        upQuery.put(fieldName, new Date());
        upQuery.put("status", status);

        mongoDao.updateObject("ImportLog", new BasicDBObject("name", exportLog.getName()), new BasicDBObject("$set", upQuery));
    }
}