//package Zim.task;
//
//import Zim.common.SysConfigUtil;
//import Zim.common.SystemHelper;
//import Zim.model.*;
//import Zim.model.map.ExportLog;
//import Zim.model.modelview.MatchedResult;
//import Zim.mongo.MongoDao;
//import com.mongodb.*;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.time.LocalDate;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Created by Laxton-Joe on 2017/9/7.
// */
//public class NewImportTask implements Runnable {
//    final static int importThreadNum = Integer.parseInt(SysConfigUtil.getSetting("import-thread-num"));
//    private ExportLog exportLog;
//    private MongoDao mongoDao = new MongoDao();
//
//    public NewImportTask(String path) {
//        this.exportLog = SystemHelper.ImportInfo(new File(path));
//    }
//
//    @Override
//    public void run() {
//        ConcurrentLinkedQueue<String> fileQueue = new ConcurrentLinkedQueue<String>();
//        String xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + exportLog.getName();
//        File directory = new File(xmlFilePath);
//        if (directory.isDirectory()) {
//            String[] files = directory.list(new ImportExtFilter(".xml"));
//            Collections.addAll(fileQueue, files);
//            if (!fileQueue.isEmpty()) {
//                Document importLog = saveTaskInfo();
//                if (importLog != null) {
//                    try {
//
//                        BlockingQueue<Applicant> applicantQueue = new LinkedBlockingQueue<>(20);
//                        BlockingQueue<MatchInfo> matchQueue = new LinkedBlockingQueue<>();
//                        Thread threadConverter = new Thread(new Converter(applicantQueue, fileQueue, exportLog.getName()));
//                        threadConverter.start();
////                        Thread ImporterThread=   new Thread(new Importer(applicantQueue, matchQueue));
////                        ImporterThread.start();
//
//
//                        List<Thread> threadList = new ArrayList<>();
//                        for (int i = 0; i < importThreadNum; ++i) {
//                            threadList.add(new Thread(new Importer(applicantQueue, matchQueue)));
//                        }
//                        for (Thread thread : threadList) {
//                            thread.start();
//                        }
//
////                        for (Thread thread : threadList) {
////                            thread.join();
////                        }
//
//                        threadConverter.join();
//
//                        for (int i = 0; i < importThreadNum; ++i) {
//                            applicantQueue.put(new Applicant(true));
//                        }
////                        executor.shutdown();
//
//                        for (Thread thread : threadList) {
//                            thread.join();
//                        }
////                        threadImporter.join();
//                        // ImporterThread.join();
//                        updateTaskInfo("importFinishTime");
//
//                        Thread threadMatcher = new Thread(new Matcher(matchQueue));
//                        threadMatcher.start();
//                        threadMatcher.join();
//                        updateTaskInfo("matcherFinishTime");
//                        mongoDao.close();
//
//
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        }
//    }
//
//    class Converter implements Runnable {
//        BlockingQueue<Applicant> applicantQueue;
//        ConcurrentLinkedQueue<String> fileQueue;
//        private String taskName;
//        private String xmlFilePath;
//
//        public Converter(BlockingQueue<Applicant> applicantQueue, ConcurrentLinkedQueue<String> fileQueue, String taskName) {
//            this.applicantQueue = applicantQueue;
//            this.fileQueue = fileQueue;
//            this.taskName = taskName;
//            this.xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + taskName;
//        }
//
//        @Override
//        public void run() {
//            while (!fileQueue.isEmpty()) {
//                String fileName = fileQueue.poll();
//
//                Applicant applicant = null;
//                try {
//
//                    File file = new File(xmlFilePath + File.separator + fileName);
//                    InputStream fileStream = new FileInputStream(file);
//                    JAXBContext jaxbContext = JAXBContext.newInstance(Applicant.class);
//                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                    applicant = (Applicant) jaxbUnmarshaller.unmarshal(fileStream);
//                    int intBirth = SystemHelper.DateToInt(applicant.getApplicantDemographic().getDateOfBirth());
//                    if (intBirth > 0)
//                        applicant.setDateOfBirth(intBirth);
//                    int intRegistration = SystemHelper.DateToInt(applicant.getEndCreateDatetime());
//                    if (intRegistration > 0)
//                        applicant.setDateOfRegistration(intRegistration);
//                    applicant.setImportTask(taskName);
//                    applicant.setGender(applicant.getApplicantDemographic().getGender());
//                    applicant.set_id(applicant.getGuid());
//
//                } catch (JAXBException e) {//xml 无效. 无法转成对象
//
//                } catch (FileNotFoundException e) {
//
//                } catch (Exception ex) {
//
//                } finally {
//                    if (applicant != null) {
//                        try {
//                            applicantQueue.put(applicant);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    class Importer implements Runnable {
//        BlockingQueue<Applicant> applicantQueue;
//        BlockingQueue<MatchInfo> matchQueue;
//
//        public Importer(BlockingQueue<Applicant> applicantQueue, BlockingQueue<MatchInfo> matchQueue) {
//            this.applicantQueue = applicantQueue;
//            this.matchQueue = matchQueue;
//        }
//
//        //        @Override
////        public void run() {
////            while (true) {
////                Applicant applicant = null;
////                MatchInfo matchInfo = null;
////                try {
////                    applicant = applicantQueue.take();
////                    if (applicant.isPoisonPill()) {
////                        matchInfo = new MatchInfo(true);
////                        break;
////                    }
////                   // ConcurrentHashMap<String, List<Document>> documentMap = applicant.toDocument();
////                    Thread.sleep(13000);
////                    boolean importSuccess = true;
////
////                    if (importSuccess) {
////                        matchInfo = applicant.toMatchInfo();
////                    }
////
////                } catch (Exception e) {
////
////                } finally {
////                    if (matchInfo != null) {
////                        try {
////
////                            matchQueue.put(matchInfo);
////
////                        } catch (InterruptedException e) {
////                            Thread.currentThread().interrupt();
////                        }
////                    }
////                }
////            }
////        }
//        @Override
//        public void run() {
//            while (true) {
//                MatchInfo matchInfo = null;
//                Applicant applicant = null;
//                ImportTransaction iTran = null;
//                try {
//                    applicant = applicantQueue.take();
//                    if (applicant.isPoisonPill()) {
//                        matchInfo = new MatchInfo(true);
//                        break;
//                    }
//                    ObjectId transactionId=new ObjectId();
//                    iTran = new ImportTransaction(transactionId,applicant.get_id(), mongoDao);
//                    Map<String, List<Document>> documentMap = applicant.toDocument(transactionId);
//                    boolean importSuccess = true;
//                    for (String col : documentMap.keySet()) {
//                        switch (col) {
//                            case "CompliancePhoto":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertMany(col, documentMap.get(col));
//                                }
//                                break;
//                            case "FingerprintImage":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertMany(col, documentMap.get(col));
//                                }
//                                break;
//                            case "FingerprintTemplate":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertMany(col, documentMap.get(col));
//                                }
//                                break;
//                            case "FingerprintWSQ":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertMany(col, documentMap.get(col));
//                                }
//                                break;
//                            case "PersonPhoto":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertMany(col, documentMap.get(col));
//                                }
//                                break;
//                            case "Applicant":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertOne(col, documentMap.get(col).get(0));
//                                }
//                                break;
//                            case "ApplicantMaster":
//                                if (importSuccess) {
//                                    importSuccess = mongoDao.insertOne(col, documentMap.get(col).get(0));
//                                }
//                                break;
//                        }
//                    }
//                    if (importSuccess) {
//
//                        matchInfo = applicant.toMatchInfo();
//                        // System.out.println(Thread.currentThread().getId() + "--Importer success:" + matchInfo.get_id());
//                        iTran.commit();
//
//                    } else {
//                        System.out.println(Thread.currentThread().getId() + "--Importer:" + applicant.get_id());
//                    }
//                    // System.out.println(Thread.currentThread().getId() + "--Importer:" + applicant.get_id());
//                    //Thread.sleep(1000);
//                } catch (Exception e) {
//                    matchInfo = null;
//                } finally {
//                    if (matchInfo != null) {
//                        try {
//
//                            matchQueue.put(matchInfo);
//
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    } else {
//                        if (applicant != null && iTran != null) {
//                            iTran.rollBack();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    class Matcher implements Runnable {
//        final BlockingQueue<MatchInfo> matchQueue;
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
//                        System.out.println(Thread.currentThread().getId() + "--Matcher:" + matchInfo.get_id());
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
//
////    class Extractor implements Runnable {
////
////        private BlockingQueue<ApplicantMaster> masterQueue;
////        BlockingQueue<MatchInfo> matchQueue;
////
////        public Extractor(BlockingQueue<ApplicantMaster> masterQueue, BlockingQueue<MatchInfo> matchQueue) {
////            this.masterQueue = masterQueue;
////            this.matchQueue = matchQueue;
////        }
////
////        @Override
////        public void run() {
////            while (true) {
////                MatchInfo matchInfo = null;
////                try {
////
////                    ApplicantMaster master = masterQueue.take();
////                    if (master.isPoisonPill()) {
////                        matchInfo = new MatchInfo(true);
////                        break;
////                    }
////
////                    boolean insertMaster = mongoDao.insertOne("ApplicantMaster", master.toDocument());
////                    if (insertMaster) {
////                        matchInfo = master.toMatchInfo();
////                    }
////                    //   System.out.println(Thread.currentThread().getId() + "--Extractor:" + master.get_id());
////                    //Thread.sleep(1000);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                } finally {
////                    if (matchInfo != null) {
////                        try {
////                            matchQueue.put(matchInfo);
////                        } catch (InterruptedException e) {
////                            Thread.currentThread().interrupt();
////                        }
////                    }
////                }
////            }
////        }
////    }
//
//    private Document saveTaskInfo() {
//
//        ImportLog importLog = new ImportLog();
//        importLog.set_id(exportLog.getName());
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
//        importLog.setStatus("Importing");
//        importLog.setImportBeginTime(new Date());
//        importLog.setImportFinishTime(null);
//        importLog.setMatchBeginTime(null);
//        importLog.setMatchFinishTime(null);
//        importLog.setImportTotal(0);
//        importLog.setImportMale(0);
//        importLog.setImportFemale(0);
//        importLog.setMatchedTotal(0);
//        // DBObject ilObj = importLog.toDBObject();
//        Document document = importLog.toDocument();
//        if (!mongoDao.insertOne("ImportLog", document)) {
//            document = null;
//        }
//        return document;
//    }
//
//    private void updateTaskInfo(String fieldName) {
//        BasicDBObject upQuery = new BasicDBObject();
//        upQuery.put(fieldName, new Date());
//        upQuery.put("status", 1);
//
//        mongoDao.updateObject("ImportLog", new BasicDBObject("name", exportLog.getName()), new BasicDBObject("$set", upQuery));
//    }
//}