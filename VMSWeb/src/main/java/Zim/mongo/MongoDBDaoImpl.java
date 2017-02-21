package Zim.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

import Zim.common.SystemHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */
public class MongoDBDaoImpl implements MongoDBDao {
    private MongoClient mongoClient = null;
    private DB vmsDb = null;
    private static final MongoDBDaoImpl mongoDBDaoImpl = new MongoDBDaoImpl();// 饿汉式单例模式
    private static final MongoDBDaoImpl mongoDBDaoImpl2 = new MongoDBDaoImpl();// 饿汉式单例模式
    private MongoDBDaoImpl() {
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
            MongoClientOptions myOptions = buide.build();
            mongoClient = new MongoClient(new ServerAddress(SystemHelper.MONGODBSETTING_HOST, SystemHelper.MONGODBSETTING_PORT), myOptions);
            vmsDb = mongoClient.getDB(SystemHelper.MONGODBSETTING_DB);
        }
    }

    public static MongoDBDaoImpl getMongoDBDaoImpl() {
        return mongoDBDaoImpl;
    }
    public static MongoDBDaoImpl mongoDBDaoImpl2() {
        return mongoDBDaoImpl2;
    }


    @Override
    public DB getDb(String dbName) {
        return mongoClient.getDB(dbName);
    }

    @Override
    public DBCollection getCollection(String dbName, String collectionName) {
        DB db = mongoClient.getDB(dbName);
        return db.getCollection(collectionName);
    }

    @Override
    public boolean insert(String dbName, String collectionName, String keys, Object values) {
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        long num = dbCollection.count();
        BasicDBObject doc = new BasicDBObject();
        doc.put(keys, values);
        dbCollection.insert(doc);
        if (dbCollection.count() - num > 0) {
            System.out.println("添加数据成功！！！");
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String dbName, String collectionName, String keys, Object values) {
        WriteResult writeResult = null;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        BasicDBObject doc = new BasicDBObject();
        doc.put(keys, values);
        writeResult = dbCollection.remove(doc);
        if (writeResult.getN() > 0) {
            System.out.println("删除数据成功!!!!");
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<DBObject> find(String dbName, String collectionName, int num) {
        int count = num;
        ArrayList<DBObject> list = new ArrayList<DBObject>();
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        DBCursor dbCursor = dbCollection.find();
        if (num == -1) {
            while (dbCursor.hasNext()) {
                list.add(dbCursor.next());
            }
        } else {
            while (dbCursor.hasNext()) {
                if (count == 0) break;
                list.add(dbCursor.next());
                count--;
            }
        }
        return list;
    }

    @Override
    public boolean update(String dbName, String collectionName, DBObject oldValue, DBObject newValue) {
        WriteResult writeResult = null;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        writeResult = dbCollection.update(oldValue, newValue);
        if (writeResult.getN() > 0) {
            System.out.println("数据更新成功");
            return true;
        }
        return false;
    }

    @Override
    public boolean isExit(String dbName, String collectionName, String key, Object value) {
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        BasicDBObject doc = new BasicDBObject();
        doc.put(key, value);
        if (dbCollection.count(doc) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public DBObject findById(String collectionName, String id) {
        return vmsDb.getCollection(collectionName).findOne(id);
    }

    @Override
    public WriteResult addObject(Map<String, Object> map, String collectionName) {
        return vmsDb.getCollection(collectionName).save(new BasicDBObject(map));
    }


    public void close() {
        if (this.mongoClient != null) {
            mongoClient.close();
        }
    }

    public Boolean IsQueue() {

        return vmsDb.getCollection("MatchQueue").find().count() > 0;
    }

    public DBObject getQueue() {

        return vmsDb.getCollection("MatchQueue").findOne();
    }
    public void removeQueue(String _id) {
        vmsDb.getCollection("MatchQueue").remove(new BasicDBObject("_id", _id));

    }
    public void updateStatus(String _id, String collectionName, String fieldName, Object status) {

        BasicDBObject idQuery = new BasicDBObject();
        idQuery.put("_id", _id);

        BasicDBObject upQuery = new BasicDBObject(fieldName, status);
        Object updateResult = vmsDb.getCollection(collectionName).findAndModify(idQuery, new BasicDBObject("$set", upQuery));


    }
}
