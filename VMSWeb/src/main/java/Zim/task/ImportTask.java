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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.Marker;
//import org.slf4j.MarkerFactory;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.*;
//import java.time.LocalDate;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Created by Laxton-Joe on 2017/7/11.
// */
//public class ImportTask implements Runnable {
//    final static int importScanNum = Integer.parseInt(SysConfigUtil.getSetting("import-thread-num"));
//    private static Logger logger = LoggerFactory.getLogger(ImportTask.class);
//    private Marker notifyAdmin = MarkerFactory.getMarker("NOTIFY_ADMIN");
//    private MongoDao mongoDao = new MongoDao();
//    private ExecutorService service = Executors.newCachedThreadPool();
//
//
//    public ImportTask(String exportFile) {
//        ExportLog exportLog = SystemHelper.ImportInfo(new File(exportFile));
//        this.exportLog = exportLog;
//        this.xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + exportLog.getName();
//    }
//
//    private ExportLog exportLog;
//    private String xmlFilePath;
//
//    /**
//     * 保存任务信息
//     */
//    private Document saveTaskInfo() {
//
//        ImportLog importLog = new ImportLog();
////        importLog.set_id(UUID.randomUUID().toString());
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
//
//        //   DBObject ilObj = importLog.toDBObject();
//        Document document = importLog.toDocument();
//        if (!mongoDao.insertOne("ImportLog", document)) {
//            document = null;
//        }
//        return document;
//    }
//
//    /**
//     * 更新任务信息，完成时间，导入统计。
//     *
//     * @param
//     */
//    private void updateTaskInfo() {
//        BasicDBObject upQuery = new BasicDBObject();
//        upQuery.put("importFinishTime", new Date());
//        upQuery.put("status", 1);
//        mongoDao.updateObject("ImportLog", new BasicDBObject("name", exportLog.getName()), new BasicDBObject("$set", upQuery));
//    }
//
//    @Override
//    public void run() {
////        logger.error("Import Task Run:" + xmlFilePath);
//        ConcurrentLinkedQueue<String> fileQueue = new ConcurrentLinkedQueue<String>();
//        File directory = new File(this.xmlFilePath);
//        if (directory.isDirectory()) {
//            GenericExtFilter filter = new GenericExtFilter(".xml");
//            String files[] = directory.list(filter);
//            for (String fileName : files) {
//                fileQueue.add(fileName);
//            }
//            if (!fileQueue.isEmpty()) {
//                Document importLog = saveTaskInfo();
//                if (importLog != null) {
//                    BlockingQueue<Applicant> applicantQueue = new LinkedBlockingQueue<Applicant>(20);
//                    BlockingQueue<ApplicantMaster> masterQueue = new LinkedBlockingQueue<>(30);
//                    BlockingQueue<MatchInfo> matcherQueue = new LinkedBlockingQueue<>(30);
//                    // ConcurrentLinkedQueue<ImportResult> recordQueue = new ConcurrentLinkedQueue<>();
//
//
//                    AtomicInteger count = new AtomicInteger(fileQueue.size());
//                    AtomicInteger count2 = new AtomicInteger(fileQueue.size());
//                    AtomicInteger count3 = new AtomicInteger(fileQueue.size());
//                    String[] flag = {"working"};
//
//                    Producer producer = new Producer(fileQueue, applicantQueue, this.xmlFilePath);
//                    Consumer consumer = new Consumer(count, applicantQueue, masterQueue);
//                    Updater updater = new Updater(count2, masterQueue, matcherQueue, flag);
//                    AMatcher matcher = new AMatcher(count3, matcherQueue);
//
//                    for (int i = 0; i < 2; i++) {
//                        service.submit(producer);
//                        service.submit(updater);
//                    }
//                    for (int i = 0; i < importScanNum; i++) {
//                        service.submit(consumer);
//                    }
//
//                    Thread matcherThread = new Thread(matcher);
//                    matcherThread.start();
//
//
//                    //   结束
//                    synchronized (flag) {
//                        while (flag[0].equals("working")) {
//                            try {
//                                flag.wait();
//                            } catch (InterruptedException e) {
//                                Thread.currentThread().interrupt();
//                                logger.error(notifyAdmin, e.toString() + "137");//,
//                            }
//                        }
//                        updateTaskInfo();
//
//
//                        try {
//                            matcherThread.join();
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                        service.shutdownNow();
//
//
//                        mongoDao.close();
//                    }
//                }
//            }
//        }
//    }
//
//    class Producer implements Runnable {
//        private String xmlFilePath;
//        ConcurrentLinkedQueue<String> fileQueue;
//        BlockingQueue<Applicant> applicantQueue;
//
//        public Producer(ConcurrentLinkedQueue<String> fileQueue, BlockingQueue<Applicant> applicantQueue, String xmlFilePath) {
//            this.xmlFilePath = xmlFilePath;
//            this.applicantQueue = applicantQueue;
//            this.fileQueue = fileQueue;
//        }
//
//        @Override
//        public void run() {
//            while (!fileQueue.isEmpty()) {
//                String fileName = fileQueue.poll();
//                boolean produceSuccess = true;
//                Applicant applicant = new Applicant();
//                try {
//                    //                 System.out.println("begin produce:" + fileName + "," + SystemHelper.getDateString(new Date()));
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
//                    applicant.setImportTask(exportLog.getName());
//                    applicant.setGender(applicant.getApplicantDemographic().getGender());
//                    applicant.set_id(applicant.getGuid());
//                    //                  System.out.println("end produce:" + applicant.get_id() + "," + SystemHelper.getDateString(new Date()));
//                } catch (JAXBException e) {//xml 无效. 无法转成对象
//                    applicant.setPoisonPill(true);
//                    logger.error(notifyAdmin, e.toString() + "181");//,
//                } catch (FileNotFoundException e) {
//                    if (new File(fileName).exists()) {
//                        fileQueue.add(fileName);
//                        produceSuccess = false;//重新排队，不通知消费者。
//                    }
//                    logger.error(notifyAdmin, e.toString() + "187");//,
//                } catch (Exception ex) {
//                    applicant.setPoisonPill(true);
//                    logger.error(notifyAdmin, ex.toString() + "190");//,
//                } finally {
//                    if (produceSuccess) {
//                        try {
//                            applicantQueue.put(applicant);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                            logger.error(notifyAdmin, e.toString() + "196");//,
//                        }
//                    }
//                }
//
//            }
//        }
//    }
//
//    class Consumer implements Runnable {
//
//        private BlockingQueue<Applicant> buffer;
//        private AtomicInteger count;
//        private BlockingQueue<ApplicantMaster> splitQueue;
//
//        public Consumer(AtomicInteger count, BlockingQueue<Applicant> buffer, BlockingQueue<ApplicantMaster> splitQueue) {
//            this.buffer = buffer;
//            this.count = count;
//            this.splitQueue = splitQueue;
//        }
//
//        @Override
//        public void run() {
//            while (count.get() > 0) {
//                ApplicantMaster master = new ApplicantMaster();
//                boolean insertApplicant = false;
//                try {
//                    Applicant applicant = buffer.take();
//                    master = applicant.toMaster();
//                    //                  System.out.println("begin Consumer:" + master.get_id() + "," + SystemHelper.getDateString(new Date()));
//
//                    if (!applicant.isPoisonPill()) {
//                      //  insertApplicant = mongoDao.insertOne("Applicant", applicant.toDocument(mongoDao));
//                    }
//                    //                 System.out.println("end Consumer:" + master.get_id() + "," + SystemHelper.getDateString(new Date()));
//                } catch (InterruptedException iex) {
//                    Thread.currentThread().interrupt();
//                } catch (Exception ex) {
//                    master.setPoisonPill(true);
//                    logger.error(notifyAdmin, ex.toString() + "230");//,
//                } finally {
//                    count.getAndDecrement();
//                    try {
//                        master.setPoisonPill(!insertApplicant);
//                        splitQueue.put(master);
//
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }
//    }
//
//    class Updater implements Runnable {
//        BlockingQueue<MatchInfo> matcherQueue;
//        BlockingQueue<ApplicantMaster> splitQueue;
//        private AtomicInteger count;
//        private String[] flag;
//
//        public Updater(AtomicInteger count, BlockingQueue<ApplicantMaster> splitQueue, BlockingQueue<MatchInfo> matcherQueue, String[] flag) {
//            this.count = count;
//            this.matcherQueue = matcherQueue;
//            this.splitQueue = splitQueue;
//            this.flag = flag;
//        }
//
//        @Override
//        public void run() {
//            while (count.get() > 0) {
//                MatchInfo matchInfo = new MatchInfo();
//                boolean insertMaster = false;
//                try {
//                    ApplicantMaster master = splitQueue.take();
//                    //              System.out.println("begin Updater:" + matchInfo.get_id() + "," + SystemHelper.getDateString(new Date()));
//
//                    matchInfo = master.toMatchInfo();
//                    if (!master.isPoisonPill()) {
//                        insertMaster = mongoDao.insertOne("ApplicantMaster", master.toDocument());
//                    }
//                    //            System.out.println("end Updater:" + matchInfo.get_id() + "," + SystemHelper.getDateString(new Date()));
//                } catch (InterruptedException iex) {
//                    // Restore the interrupted status
//                    Thread.currentThread().interrupt();
//                } catch (Exception ex) {
//                    matchInfo.setPoisonPill(true);
//                    logger.error(notifyAdmin, ex.toString() + "271");//,
//                } finally {
//                    count.getAndDecrement();
//                    try {
//                        matchInfo.setPoisonPill(!insertMaster);
//                        matcherQueue.put(matchInfo);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//            if (count.get() == 0) {
//                synchronized (flag) {
//                    flag[0] = "false";
//                    flag.notify();
//                }
//            }
//        }
//    }
//
//    class AMatcher implements Runnable {
//        private AtomicInteger count;
//        private BlockingQueue<MatchInfo> queue;
//
//        public AMatcher(AtomicInteger count, BlockingQueue<MatchInfo> queue) {
//            this.count = count;
//            this.queue = queue;
//        }
//
//        @Override
//        public void run() {
//            synchronized (queue) {
//                while (count.get() > 0) {
//                    try {
//                        MatchInfo matchInfo = queue.take();
//                        if (!matchInfo.isPoisonPill()) {
//                            HashSet<MatchedResult> matchArr = MapReduceFunc(matchInfo);
//                            StringBuilder sb = new StringBuilder();
//                            sb.append("empty.,");
//                            if (matchArr != null && matchArr.size() > 0) {
//                                sb = new StringBuilder();
//                                for (MatchedResult mr : matchArr) {
//                                    Map<String, Object> map = new HashMap<String, Object>();
//                                    map.put("probeId", matchInfo.get_id());
//                                    map.put("probeNumber", matchInfo.getRegistrationNumber());
//                                    map.put("referenceId", mr.getId());
//                                    map.put("referenceNumber", mr.getRegistrationNumber());
//                                    map.put("leven", mr.getLeven());
//                                    map.put("status", "Pending");
//                                    map.put("createdTime", new Date());
//                                    mongoDao.addObject(map, "Duplicate"); //插入到Duplicate
//                                    sb.append(mr.getId() + ",");
//                                }
//                            }
//
//                            //更新Applicant 状态
//                            mongoDao.updateStatus(matchInfo.get_id(), "ApplicantMaster", "status", 1);
//                            //日志
//                            Map<String, Object> mapLog = new HashMap<String, Object>();
//                            mapLog.put("applicantId", matchInfo.get_id());
//                            mapLog.put("logEvent", String.format("Applicant Matched Status:Master.Reference:%s", sb.toString().substring(0, sb.toString().length() - 2)));
//                            mapLog.put("logTime", new Date());
//
//                            mongoDao.addObject(mapLog, "ApplicantLog");
//                        }
//                    } catch (InterruptedException iex) {
//                        // Restore the interrupted status
//                        Thread.currentThread().interrupt();
//                    } catch (Exception ex) {
//                        logger.error(notifyAdmin, ex.toString() + "338");//,
//                    } finally {
//                        count.getAndDecrement();
//
//                    }
//                }
//            }
//
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
//    public class GenericExtFilter implements FilenameFilter {
//        private String ext;
//
//        public GenericExtFilter(String ext) {
//            this.ext = ext;
//        }
//
//        public boolean accept(File dir, String name) {
//            return (name.endsWith(ext));
//        }
//    }
//}
