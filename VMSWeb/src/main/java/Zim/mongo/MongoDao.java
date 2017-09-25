package Zim.mongo;

import Zim.common.SystemHelper;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Created by Laxton-Joe on 2017/8/25.
 */
public class MongoDao {
    private MongoClient mongoClient = null;
    private MongoDatabase vmsMongo = null;
    private DB vmsDb = null;
//    private static MongoDao instance = new MongoDao();
//
//    public static MongoDao getInstance() {
//        return instance;
//    }


    public MongoDao() {
        if (mongoClient == null) {
            MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
            buide.connectionsPerHost(200);// 与目标数据库可以建立的最大链接数
            buide.connectTimeout(1000 * 60 * 20);// 与数据库建立链接的超时时间
            buide.maxWaitTime(100 * 60 * 5);// 一个线程成功获取到一个可用数据库之前的最大等待时间
            buide.threadsAllowedToBlockForConnectionMultiplier(100);
            buide.maxConnectionIdleTime(0);
            buide.maxConnectionLifeTime(0);
            buide.socketTimeout(0);
            buide.socketKeepAlive(true);
//            buide.writeConcern(WriteConcern.JOURNALED);
            MongoClientOptions myOptions = buide.build();
            //   MongoClientOptions options = MongoClientOptions.builder().writeConcern(WriteConcern.JOURNALED).build();
            MongoCredential credential = MongoCredential.createScramSha1Credential(SystemHelper.MONGODBSETTING_USER, SystemHelper.MONGODBSETTING_DB, SystemHelper.MONGODBSETTING_PWD.toCharArray());
            List<MongoCredential> credentials = new ArrayList<>();
            credentials.add(credential);
            mongoClient = new MongoClient(new ServerAddress(SystemHelper.MONGODBSETTING_HOST, SystemHelper.MONGODBSETTING_PORT), credentials, myOptions);
            vmsDb = mongoClient.getDB(SystemHelper.MONGODBSETTING_DB);
            vmsMongo = mongoClient.getDatabase(SystemHelper.MONGODBSETTING_DB);

            //  applicantPhotoGridFS = new GridFS(vmsDb, "ApplicantPhoto");
        }
    }


    public DB getDb() {
        return vmsDb;
    }


//    public DBObject insert(String collectionName, DBObject dbObject) {
//        DBObject result = null;
//
//        DBCollection dbCollection = vmsDb.getCollection(collectionName);
//        WriteResult wr = dbCollection.insert(dbObject, WriteConcern.JOURNALED);
//        if (wr != null) {
//            result = dbObject;
//        }
//        return result;
//    }

    public boolean insert(String collectionName, DBObject dbObject) {
        boolean result = false;
        DBCollection dbCollection = vmsDb.getCollection(collectionName);
        WriteResult wr = dbCollection.insert(dbObject, WriteConcern.JOURNALED);
        if (wr != null) {
            result = true;
        }
        return result;
    }

    public void insertMany(String collectionName, List<Document> documents) {

        MongoCollection<Document> dbCollection = vmsMongo.getCollection(collectionName);
        dbCollection.insertMany(documents);

    }

    public boolean tryInsertMany(String collectionName, List<Document> documents) {
        boolean result = true;
        try {
            MongoCollection<Document> dbCollection = vmsMongo.getCollection(collectionName);
            dbCollection.insertMany(documents);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean tryInsertOne(String collectionName, Document document) {
        boolean result = true;
        try {
            MongoCollection<Document> dbCollection = vmsMongo.getCollection(collectionName);
            dbCollection.insertOne(document);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public void insertOne(String collectionName, Document document) {

        MongoCollection<Document> dbCollection = vmsMongo.getCollection(collectionName);
        dbCollection.insertOne(document);

    }

    public boolean saveOne(String collectionName, DBObject dbObject) {
        boolean result = true;
        try {
            DBCollection dbCollection = vmsDb.getCollection(collectionName);
            WriteResult wr = dbCollection.save(dbObject, WriteConcern.JOURNALED);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean saveOne(String collectionName, Document document) {
        boolean result = true;
        try {
            MongoCollection<Document> dbCollection = vmsMongo.getCollection(collectionName);

            if (dbCollection.count(new BasicDBObject("_id", document.get("_id"))) > 0) {
                vmsMongo.getCollection(collectionName).replaceOne(new BasicDBObject("_id", document.get("_id")), document);
            } else {
                vmsMongo.getCollection(collectionName).insertOne(document);
            }


            //  vmsMongo.getCollection(collectionName).repl.updateOne(document)
            // WriteResult wr = dbCollection.save(dbObject, WriteConcern.JOURNALED);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

//    public boolean delete(String dbName, String collectionName, String keys, Object values) {
//        WriteResult writeResult = null;
//        DB db = mongoClient.getDB(dbName);
//        DBCollection dbCollection = db.getCollection(collectionName);
//        BasicDBObject doc = new BasicDBObject();
//        doc.put(keys, values);
//        writeResult = dbCollection.remove(doc);
//        if (writeResult.getN() > 0) {
//            System.out.println("删除数据成功!!!!");
//            return true;
//        }
//        return false;
//    }


//    public ArrayList<DBObject> find(String dbName, String collectionName, int num) {
//        int count = num;
//        ArrayList<DBObject> list = new ArrayList<DBObject>();
//        DB db = mongoClient.getDB(dbName);
//        DBCollection dbCollection = db.getCollection(collectionName);
//        DBCursor dbCursor = dbCollection.find();
//        if (num == -1) {
//            while (dbCursor.hasNext()) {
//                list.add(dbCursor.next());
//            }
//        } else {
//            while (dbCursor.hasNext()) {
//                if (count == 0) break;
//                list.add(dbCursor.next());
//                count--;
//            }
//        }
//        return list;
//    }

//    public boolean update(String dbName, String collectionName, DBObject oldValue, DBObject newValue) {
//        WriteResult writeResult = null;
//        DB db = mongoClient.getDB(dbName);
//        DBCollection dbCollection = db.getCollection(collectionName);
//        writeResult = dbCollection.update(oldValue, newValue);
//        if (writeResult.getN() > 0) {
//            System.out.println("数据更新成功");
//            return true;
//        }
//        return false;
//    }

    public boolean exist(String collectionName, Bson filter) {
        return vmsMongo.getCollection(collectionName).count(filter) > 0;
    }

    public DBObject findById(String collectionName, Object id) {
        return vmsDb.getCollection(collectionName).findOne(id);

    }

    public <T> FindIterable<T> findAll(String collectionName, Class<T> entityClass) {
        return vmsMongo.getCollection(collectionName).find(entityClass);
    }

    public FindIterable<org.bson.Document> findAll(String collectionName) {
        return vmsMongo.getCollection(collectionName).find();
    }


    public WriteResult addObject(Map<String, Object> map, String collectionName) {
        return vmsDb.getCollection(collectionName).save(new BasicDBObject(map));
    }

    public WriteResult insertObject(Map<String, Object> map, String collectionName) {
        return vmsDb.getCollection(collectionName).insert(new BasicDBObject(map));
    }

    public void close() {
        if (this.mongoClient != null) {
            mongoClient.close();
        }
    }


    public void updateStatus(Object id, String collectionName, String fieldName, Object status) {
        DBObject idQuery = new BasicDBObject();
        idQuery.put("_id", id);
        DBObject upQuery = new BasicDBObject(fieldName, status);
        Object updateResult = vmsDb.getCollection(collectionName).findAndModify(idQuery, new BasicDBObject("$set", upQuery));

    }

    public boolean updateObject(String collectionName, BasicDBObject idQuery, BasicDBObject updateObj) {
        // Object updateResult = vmsDb.getCollection(collectionName).findAndModify(idQuery, updateObj);
        boolean result = false;
        try {
            UpdateResult updateResult = vmsMongo.getCollection(collectionName).updateOne(idQuery, updateObj);
            result = updateResult.getModifiedCount() > 0;
        } catch (Exception ex) {
        }

        return result;

    }


    public String insertImg(byte[] fileByte, String collectionName) {
        String result = null;
        Document document = new Document();
        document.append("imgData", fileByte);
        if (tryInsertOne(collectionName, document)) {
            result = document.get("_id").toString();
        }
        return result;
    }


    public boolean remove(String collectionName, Bson filter) {
        boolean result = true;
        try {
            if (vmsMongo.getCollection(collectionName).count(filter) > 0) {
                DeleteResult deleteResult = vmsMongo.getCollection(collectionName).deleteMany(filter);
                result = deleteResult.getDeletedCount() > 0;
            }
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean removeOne(String collectionName, Bson filter) {
        boolean result = true;
        try {
            if (vmsMongo.getCollection(collectionName).count(filter) > 0) {
                DeleteResult deleteResult = vmsMongo.getCollection(collectionName).deleteOne(filter);
                result = deleteResult.getDeletedCount() > 0;
            }
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

//    public DBObject save(String collectionName, DBObject dbObj) {
//        DBObject result = null;
//        DBCollection dbCollection = vmsDb.getCollection(collectionName);
//        WriteResult wr = dbCollection.save(dbObj);
//        if (wr != null) {
//            result = dbObj;
//        }
//        return result;
//    }

    //region  FOR TEST
    public DBObject findQuery(String collectionName, DBObject query) {
        return vmsDb.getCollection(collectionName).findOne(query);
    }

    public <T> FindIterable<T> findQuery(String collectionName, BasicDBObject filter, Class<T> entityClass) {
        return vmsMongo.getCollection(collectionName).find(filter, entityClass);
    }

    public FindIterable<Document> findQuery(String collectionName, BasicDBObject filter) {
        return vmsMongo.getCollection(collectionName).find(filter);
    }
    //endregion
}
