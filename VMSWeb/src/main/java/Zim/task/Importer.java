//package Zim.task;
//
//import Zim.model.Applicant;
//import Zim.model.ApplicantMaster;
//
//import java.util.concurrent.BlockingQueue;
//
///**
// * Created by Laxton-Joe on 2017/9/8.
// */
//public class Importer implements Runnable {
//    BlockingQueue<Applicant> applicantQueue;
//    BlockingQueue<ApplicantMaster> masterQueue;
//
//    public Importer(BlockingQueue<Applicant> applicantQueue, BlockingQueue<ApplicantMaster> masterQueue) {
//        this.applicantQueue = applicantQueue;
//        this.masterQueue = masterQueue;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            ApplicantMaster master = null;
//            try {
//
//                Applicant applicant = applicantQueue.take();
//                if (applicant.isPoisonPill()) {
//                    master = new ApplicantMaster(true);
//                    break;
//                }
//                master = applicant.toMaster();
//                System.out.println(Thread.currentThread().getId() + "--Importer:" + applicant.get_id());
//                //Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (master != null) {
//                    try {
//                        masterQueue.put(master);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//
//        }
//    }
//}
