package Zim.linstener;

import Zim.common.SystemHelper;
import Zim.model.modelview.MatchedResult;
import Zim.mongo.MongoDao;
import Zim.task.NewImportDataTask;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Laxton-Joe on 2017/9/18.
 */
public class MatchRunnable implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(MatchRunnable.class);
    MongoDao mongoDao = null;

    @Override
    public void run() {
        while (true) {

            String matchTaskName = null;
            try {
                matchTaskName = SystemHelper.matchQueue.take();
                //query
                mongoDao = new MongoDao();
                BasicDBObject filter = new BasicDBObject();
                filter.append("importTask", matchTaskName);
                filter.append("status", 0);
                //更新 matched 时间
                BasicDBObject upBeginQuery = new BasicDBObject();

                upBeginQuery.put("matchBeginTime", new Date());
                upBeginQuery.put("status", "Matching");

                mongoDao.updateObject("ImportLog", new BasicDBObject("name", matchTaskName), new BasicDBObject("$set", upBeginQuery));

                FindIterable<Document> matchInfos = mongoDao.findQuery("ApplicantMaster", filter);

                for (Document matchInfo : matchInfos) {
                    //执行运算
//                    System.out.println("match:" + matchInfo.get("_id").toString());
                    HashSet<MatchedResult> matchArr = MapReduceFunc(matchInfo);
                    StringBuilder sb = new StringBuilder();
                    sb.append("empty.,");
                    if (matchArr != null && matchArr.size() > 0) {
                        sb = new StringBuilder();
                        for (MatchedResult mr : matchArr) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("probeId", matchInfo.get("_id").toString());
                            map.put("probeNumber", matchInfo.get("registrationNumber").toString());
                            map.put("referenceId", mr.getId());
                            map.put("referenceNumber", mr.getRegistrationNumber());
                            map.put("leven", mr.getLeven());
                            map.put("status", "Pending");
                            map.put("createdTime", new Date());
                            mongoDao.addObject(map, "Duplicate"); //插入到Duplicate
                            sb.append(mr.getId()).append(",");
                        }
                    }

                    //更新Applicant 状态
                    mongoDao.updateStatus(matchInfo.get("_id").toString(), "ApplicantMaster", "status", 1);
                    //日志
                    Map<String, Object> mapLog = new HashMap<>();
                    mapLog.put("applicantId", matchInfo.get("_id").toString());
                    mapLog.put("logEvent", String.format("Applicant Matched Status:Master.Reference:%s", sb.toString().substring(0, sb.toString().length() - 2)));
                    mapLog.put("logTime", new Date());

                    mongoDao.addObject(mapLog, "ApplicantLog");
                }


                //更新 matched 时间

                BasicDBObject upFinishQuery = new BasicDBObject();

                upFinishQuery.put("matchFinishTime", new Date());
                upFinishQuery.put("status", "Matched");

                mongoDao.updateObject("ImportLog", new BasicDBObject("name", matchTaskName), new BasicDBObject("$set", upFinishQuery));


                //删除matchTaskQueue 对象
                mongoDao.remove("MatchTaskQueue", new BasicDBObject("taskName", matchTaskName));
            } catch (Exception ex) {
                logger.error(ex.toString());
            } finally {


                if (mongoDao != null) {
                    mongoDao.close();
                }
            }
        }

    }

    private HashSet<MatchedResult> MapReduceFunc(Document matchInfo) {

        //插入到Duplicate
        HashSet<MatchedResult> results = new HashSet<>();
        LocalDate localBirth = SystemHelper.getUnLeapYearBirthDate((int) matchInfo.get("dateOfBirth"));
        StringBuilder sbMap = new StringBuilder();
        String fullName = matchInfo.get("forenames").toString() + " " + matchInfo.get("surname").toString();
        String id = matchInfo.get("_id").toString();
//        int gender = (int) matchInfo.get("gender");
        DB dbMongo = mongoDao.getDb();
        sbMap.append("function() {");
        sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", fullName));
        sbMap.append("var tgrSodEX= SoundEx(this.forenames+\" \"+this.surname);");
        sbMap.append(String.format("var leven= levenshtein(\"%s\",this.forenames+\" \"+this.surname);", fullName));
        sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
        sbMap.append(String.format("emit(\"%s\",{id:this._id,registrationNumber:this.registrationNumber,leven:leven});}}", id));


        String match_result = "match_result";
        DBObject dbQuery = new BasicDBObject();
        dbQuery.put("gender", matchInfo.get("gender"));
        dbQuery.put("status", 1);

        LocalDate startDate = localBirth.minusYears(10);
        LocalDate endDate = localBirth.plusYears(10);
        int iStart = Integer.parseInt(SystemHelper.LocalToString(startDate));
        int iEend = Integer.parseInt(SystemHelper.LocalToString(endDate));
        BasicDBObject birthQuery = new BasicDBObject();
        birthQuery.put("$gte", iStart);
        birthQuery.put("$lt", iEend);
        dbQuery.put("dateOfBirth", birthQuery);

        MapReduceCommand mrc = new MapReduceCommand(dbMongo.getCollection("ApplicantMaster"), sbMap.toString(), "function(key, values) { return {values:values};}", match_result, MapReduceCommand.OutputType.MERGE, dbQuery);

        MapReduceOutput mapReduceOutput = dbMongo.getCollection("ApplicantMaster").mapReduce(mrc);
        DBCollection resultColl = mapReduceOutput.getOutputCollection();
        DBObject idQuery = new BasicDBObject();

        idQuery.put("_id", id);
        List<DBObject> listResult = resultColl.find(idQuery).toArray();
        listResult.stream().filter(obj -> obj.keySet().contains("value")).forEach(obj -> {
            DBObject dbObject = (DBObject) obj.get("value");
            if (dbObject.keySet().contains("leven")) {
                results.add(new MatchedResult(dbObject));
            } else if (dbObject.keySet().contains("values")) {
                addMatchedResult(results, (BasicDBList) dbObject.get("values"));
            }
        });
        return results;
    }

    private void addMatchedResult(HashSet<MatchedResult> results, BasicDBList basicDBList) {
        for (Object obj : basicDBList) {
            DBObject item = (DBObject) obj;
            if (item.keySet().contains("leven")) {
                results.add(new MatchedResult(item));
            } else if (item.keySet().contains("values")) {
                addMatchedResult(results, (BasicDBList) item.get("values"));
            }
        }
    }
}
