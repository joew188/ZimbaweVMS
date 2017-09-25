//package Zim.task;
//
//import Zim.model.MatchInfo;
//
//import java.util.concurrent.BlockingQueue;
//
///**
// * Created by Laxton-Joe on 2017/9/8.
// */
//public class Matcher implements Runnable {
//    BlockingQueue<MatchInfo> matchQueue;
//
//    public Matcher(BlockingQueue<MatchInfo> matchQueue) {
//        this.matchQueue = matchQueue;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            MatchInfo matchInfo = null;
//            try {
//
//                matchInfo = matchQueue.take();
//                if (matchInfo.isPoisonPill()) {
//
//                    break;
//                }
//
//                System.out.println(Thread.currentThread().getId() + "--Matcher:" + matchInfo.get_id());
//                //Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//}
