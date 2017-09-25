//package Zim.task;
//
//import Zim.model.Applicant;
//import Zim.model.ApplicantMaster;
//import Zim.model.MatchInfo;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Created by Laxton-Joe on 2017/9/8.
// */
//public class Extractor implements Runnable {
//
//    private BlockingQueue<ApplicantMaster> masterQueue;
//    BlockingQueue<MatchInfo> matchQueue;
//
//    public Extractor(BlockingQueue<ApplicantMaster> masterQueue, BlockingQueue<MatchInfo> matchQueue) {
//        this.masterQueue = masterQueue;
//        this.matchQueue = matchQueue;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            MatchInfo matchInfo = null;
//            try {
//
//                ApplicantMaster master = masterQueue.take();
//                if (master.isPoisonPill()) {
//                    matchInfo = new MatchInfo(true);
//                    break;
//                }
//                matchInfo = master.toMatchInfo();
//                System.out.println(Thread.currentThread().getId() + "--Extractor:" + master.get_id());
//                //Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (matchInfo != null) {
//                try {
//                    matchQueue.put(matchInfo);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    }
//}
