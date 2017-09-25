//package Zim.task;
//
//import Zim.common.SysConfigUtil;
//import Zim.common.SystemHelper;
//import Zim.model.Applicant;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
///**
// * Created by Laxton-Joe on 2017/9/8.
// */
//public class Converter implements Runnable {
//    BlockingQueue<Applicant> applicantQueue;
//    ConcurrentLinkedQueue<String> fileQueue;
//    private String taskName;
//    private String xmlFilePath;
//
//    public Converter(BlockingQueue<Applicant> applicantQueue, ConcurrentLinkedQueue<String> fileQueue, String taskName) {
//        this.applicantQueue = applicantQueue;
//        this.fileQueue = fileQueue;
//        this.taskName = taskName;
//        this.xmlFilePath = SysConfigUtil.getSetting("import-scan-path") + "/" + taskName;
//    }
//
//    @Override
//    public void run() {
//        while (!fileQueue.isEmpty()) {
//            String fileName = fileQueue.poll();
//
//            Applicant applicant = null;
//            try {
//
//                File file = new File(xmlFilePath + File.separator + fileName);
//                InputStream fileStream = new FileInputStream(file);
//                JAXBContext jaxbContext = JAXBContext.newInstance(Applicant.class);
//                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                applicant = (Applicant) jaxbUnmarshaller.unmarshal(fileStream);
//                int intBirth = SystemHelper.DateToInt(applicant.getApplicantDemographic().getDateOfBirth());
//                if (intBirth > 0)
//                    applicant.setDateOfBirth(intBirth);
//                int intRegistration = SystemHelper.DateToInt(applicant.getEndCreateDatetime());
//                if (intRegistration > 0)
//                    applicant.setDateOfRegistration(intRegistration);
//                applicant.setImportTask(taskName);
//                applicant.setGender(applicant.getApplicantDemographic().getGender());
//                applicant.set_id(applicant.getGuid());
//
//            } catch (JAXBException e) {//xml 无效. 无法转成对象
//
//            } catch (FileNotFoundException e) {
//
//            } catch (Exception ex) {
//
//            } finally {
//                if (applicant != null) {
//                    try {
//                        applicantQueue.put(applicant);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }
//    }
//}
