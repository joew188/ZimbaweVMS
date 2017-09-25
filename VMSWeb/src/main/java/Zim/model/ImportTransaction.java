package Zim.model;

import Zim.mongo.MongoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Laxton-Joe on 2017/8/25.
 */

@Document(collection = "ImportTransaction")
public class ImportTransaction {
    private ObjectId _id;
    private String guid;
    private String status;
    //// 0 initial, 1 applied,2 done,3 canceling,4 canceled
    private MongoDao mongoDao;
    private volatile boolean isInserted = false;


//    public ImportTransaction(ObjectId id, String guid, MongoDao mongoDao) {
//        this.setGuid(guid);
//        this.setStatus("begin");
//        this.mongoDao = mongoDao;
//        this.set_id(id);
//    }

    public ImportTransaction(ObjectId transactionId, String guid, MongoDao mongoDao) {
        this.set_id(transactionId);
        this.setGuid(guid);
        this.setStatus("init");
        this.mongoDao = mongoDao;
        init();
    }


//    public void commit() {
//
//
//        BasicDBObject idQuery = new BasicDBObject();
//        idQuery.put("_id", this.getGuid());
//
//        BasicDBObject upObj = new BasicDBObject();
//        DBObject upStatus = new BasicDBObject("status", 0);
//        upObj.append("$set", upStatus);
//        DBObject upTran = new BasicDBObject("transactionId", this.get_id());
//        upObj.append("$unset", upTran);
//
//
//        //更新 applicant master 的状态
//        boolean commitMasterResult = mongoDao.updateObject("ApplicantMaster", idQuery, upObj);
//
//        boolean commitApplicantResult = mongoDao.updateObject("Applicant", new BasicDBObject("_id", this.getGuid()), new BasicDBObject("$unset", new BasicDBObject("transactionId", this.get_id())));
//
//        BasicDBObject idTranQuery = new BasicDBObject();
//        idQuery.put("_id", this.get_id());
//        idQuery.put("status", 0);
//        boolean commitTranResult = mongoDao.updateObject("ImportTransaction", idTranQuery, new BasicDBObject("$set", new BasicDBObject("status", 1)));
//
//        if (commitMasterResult && commitApplicantResult && commitTranResult) {
//            mongoDao.updateObject("ImportTransaction", new BasicDBObject("_id", this.get_id()), new BasicDBObject("$set", new BasicDBObject("status", 2)));
//        } else {
//            rollBack();
//        }
//    }

    public void rollBack() {

        org.bson.Document filter = new org.bson.Document("transactionId", this.get_id());
        try {
            if (this.isInserted) {
                //删除applicant
                mongoDao.removeOne("Applicant", filter);
                //删除master
                mongoDao.removeOne("ApplicantMaster", filter);
                mongoDao.remove("FingerprintImage", filter);
                mongoDao.remove("FingerprintWSQ", filter);
                mongoDao.remove("FingerprintTemplate", filter);
                mongoDao.remove("ApplicantPhotos", filter);
            }
            //更新 ImportTransaction的状态 canceling
            mongoDao.updateObject("ImportTransaction", new BasicDBObject("_id", this.get_id()),
                    new BasicDBObject("$set", new BasicDBObject("status", "canceled")));

        } catch (Exception ex) {

        }
//        if (rollApplicantResult || rollMasterResult || rollTranResult) {
//            mongoDao.updateObject("ImportTransaction", new BasicDBObject("_id", this.get_id()), new BasicDBObject("$set", new BasicDBObject("status", "canceled")));
//        }
    }

    public void dispose() {
        DBObject transactionObj = mongoDao.findById("ImportTransaction", this.get_id());
        if (transactionObj != null) {
//            if (transactionObj.get("status").equals(2) || transactionObj.get("status").equals(4)) {
            mongoDao.remove("ImportTransaction", new BasicDBObject("_id", this.get_id()));
//            }
        }
    }

    private boolean init() {
        boolean result = false;
        try {
            org.bson.Document document = new org.bson.Document();
            document.append("_id", this.get_id());
            document.append("guid", this.getGuid());
            document.append("status", this.getStatus());
            result = mongoDao.tryInsertOne("ImportTransaction", document);

        } catch (Exception ex) {
            result = false;
        }
        return result;
    }


    public boolean commit() {
        boolean result = true;
        try {
            BasicDBObject idQuery = new BasicDBObject();
            idQuery.put("_id", this.getGuid());

            BasicDBObject upObj = new BasicDBObject();
            DBObject upStatus = new BasicDBObject("status", 0);
            upObj.append("$set", upStatus);
            DBObject upTran = new BasicDBObject("transactionId", this.get_id());
            upObj.append("$unset", upTran);


            //更新 applicant master 的状态
            mongoDao.updateObject("ApplicantMaster", idQuery, upObj);


            BasicDBObject idTranQuery = new BasicDBObject();
            idTranQuery.put("_id", this.get_id());

            mongoDao.updateObject("ImportTransaction", idTranQuery, new BasicDBObject("$set", new BasicDBObject("status", "commited")));
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }


    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isInserted() {
        return isInserted;
    }

    public void setInserted(boolean inserted) {
        isInserted = inserted;
    }
}
