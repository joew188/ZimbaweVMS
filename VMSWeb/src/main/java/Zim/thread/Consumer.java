package Zim.thread;

import Zim.map.Record;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.WriteResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Laxton-Joe on 2017/6/22.
 */
public class Consumer implements Runnable {
    static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
    private BlockingQueue<Record> _buffer;
    private ConcurrentLinkedQueue<String> matcherQueue;

    public Consumer(BlockingQueue<Record> buffer, ConcurrentLinkedQueue<String> matcherQueue) {
        _buffer = buffer;
        this.matcherQueue = matcherQueue;
    }

    @Override
    public void run() {

        while (true) {

            String applicantId = "";
            try {
                Record rm = _buffer.take();
                applicantId = rm.getId();
                Applicant applicant = null;// Applicant.toApplicant(rm);
                if (mongoDBDaoImpl.findById("Applicant", applicant.get_id()) == null) {
                    WriteResult wApplicantResult = null;//mongoDBDaoImpl.addObject(applicant.toMap(java.util.UUID.randomUUID().toString()), "Applicant");
                    if (wApplicantResult.getN() > 0) {
                        mongoDBDaoImpl.addObject(ApplicantLog.toLogMap(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");
//                        synchronized (_buffer) {
//                            new MatchApplicantRunnable(applicantId).run();
//                        }
                        matcherQueue.add(applicantId);
                    }
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("file", applicantId);

                    mongoDBDaoImpl.addObject(map, "ApplicantSameFailLog");
                }
            } catch (InterruptedException e) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("file", applicantId);
                map.put("Exception", e.getMessage());
                mongoDBDaoImpl.addObject(map, "ApplicantConsumerFailLog");
            }

        }
    }
}
