//package Zim.thread;
//
//import Zim.map.Record;
//import Zim.model.Applicant;
//import Zim.model.ApplicantLog;
//import Zim.mongo.MongoDBDaoImpl;
//import Zim.task.MatchApplicantRunnable;
//import com.mongodb.WriteResult;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.BlockingQueue;
//
///**
// * Created by Laxton-Joe on 2017/6/22.
// */
//public class Consume implements Runnable {
//    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
//    private Record _record;
//
//    public Consume(Record record) {
//        _record = record;
//    }
//
//    @Override
//    public void run() {
//        String fileName = "";
//        try {
//
//            Applicant applicant = Applicant.toApplicant(_record);
//            if (mongoDBDaoImpl.findById("Applicant", applicant.get_id()) == null) {
//                WriteResult wApplicantResult = mongoDBDaoImpl.addObject(applicant.toMap(java.util.UUID.randomUUID().toString()), "Applicant");
//                if (wApplicantResult.getN() > 0) {
//                    mongoDBDaoImpl.addObject(ApplicantLog.toLogMap(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");
//                    new MatchApplicantRunnable(fileName).run();
//                }
//            } else {
//                System.out.println("AAAAAAAAAAA");
//            }
//
//
//        } catch (Exception e) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("fileId", fileName);
//
//            mongoDBDaoImpl.addObject(map, "ApplicantImportFailLog");
//        }
//    }
//}
