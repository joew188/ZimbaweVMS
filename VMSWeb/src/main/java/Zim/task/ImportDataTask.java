//package Zim.task;
//
//import Zim.common.SysConfigUtil;
//import Zim.common.SystemHelper;
//import Zim.model.Applicant;
//import Zim.model.MatchInfo;
//import Zim.model.map.ExportLog;
//import Zim.mongo.MongoDao;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.Collections;
//import java.util.concurrent.*;
//
///**
// * Created by Laxton-Joe on 2017/9/12.
// */
//public class ImportDataTask implements Runnable {
//    final static int importThreadNum = Integer.parseInt(SysConfigUtil.getSetting("import-thread-num"));
//    private ExportLog exportLog;
//    private MongoDao mongoDao = new MongoDao();
//
//    public ImportDataTask(String path) {
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
//                ArrayBlockingQueue<Applicant> applicantQueue = new ArrayBlockingQueue<>(20);
//                ArrayBlockingQueue<MatchInfo> matchQueue = new ArrayBlockingQueue<>(20);
//
//                Thread threadProducer1 = new Thread(new Producer(fileQueue, applicantQueue, exportLog.getName()));
//                threadProducer1.start();
//
////                Thread threadConsumer1 = new Thread(new Consumer(applicantQueue, matchQueue));
////                threadConsumer1.start();
////                Thread threadConsumer2 = new Thread(new Consumer(applicantQueue, matchQueue));
////                threadConsumer2.start();
////                Thread threadConsumer3 = new Thread(new Consumer(applicantQueue, matchQueue));
////                threadConsumer3.start();
//
//                ExecutorService executor = Executors.newCachedThreadPool();
//                for (int i = 0; i < importThreadNum; ++i) {
//                    executor.execute(new Consumer(applicantQueue, matchQueue));
//                    executor.execute(new Matcher(matchQueue));
//                }
//
////                List<Thread> threadList = new ArrayList<>();
////                for (int i = 0; i < importThreadNum; ++i) {
////                    threadList.add(new Thread(new Consumer(applicantQueue, matchQueue)));
////                }
////                for (Thread thread : threadList) {
////                    thread.start();
////                }
//
//
////                Thread threadMatcher1 = new Thread(new Matcher(matchQueue));
////                threadMatcher1.start();
//
//                try {
//                    threadProducer1.join();
//
//                    for (int i = 0; i < importThreadNum; ++i) {
//                        applicantQueue.put(new Applicant(true));
//                    }
//                    executor.shutdown();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
//
//    class Producer implements Runnable {
//        ConcurrentLinkedQueue<String> fileQueue;
//        ArrayBlockingQueue<Applicant> applicantQueue;
//        private String taskName;
//        private String xmlFilePath;
//
//        public Producer(ConcurrentLinkedQueue<String> fileQueue, ArrayBlockingQueue<Applicant> applicantQueue, String taskName) {
//            this.fileQueue = fileQueue;
//            this.applicantQueue = applicantQueue;
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
//    class Consumer implements Runnable {
//        ArrayBlockingQueue<Applicant> applicantQueue;
//        ArrayBlockingQueue<MatchInfo> matchQueue;
//
//        public Consumer(ArrayBlockingQueue<Applicant> applicantQueue, ArrayBlockingQueue<MatchInfo> matchQueue) {
//            this.applicantQueue = applicantQueue;
//            this.matchQueue = matchQueue;
//        }
//
//        @Override
//        public void run() {
////            synchronized (applicantQueue) {
//                while (true) {
//                    try {
//                        Applicant applicant = applicantQueue.take();
//
//                        if (applicant.isPoisonPill()) {
//                            //System.out.println(Thread.currentThread().getId() + "--Consumer:isPoisonPill");
//                            matchQueue.put(new MatchInfo(true));
//                            break;
//                        }
//                        Thread.sleep(3000);
//                        matchQueue.put(applicant.toMatchInfo());
//                        //System.out.println(Thread.currentThread().getId() + "--Consumer:" + page.getName());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
////            }
//        }
//    }
//
//    class Matcher implements Runnable {
//        ArrayBlockingQueue<MatchInfo> matchQueue;
//
//        public Matcher(ArrayBlockingQueue<MatchInfo> matchQueue) {
//            this.matchQueue = matchQueue;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                MatchInfo matchInfo = null;
//                try {
//                    matchInfo = matchQueue.take();
//
//                    if (matchInfo.isPoisonPill()) {
//                        System.out.println(Thread.currentThread().getId() + "--Matcher:isPoisonPill");
//                        break;
//                    }
//
//                    System.out.println(Thread.currentThread().getId() + "--Matcher:" + matchInfo.get_id());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
