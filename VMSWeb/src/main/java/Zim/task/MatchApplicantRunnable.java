package Zim.task;

import Zim.common.SystemHelper;
import Zim.model.modelview.MatchedResult;
import Zim.mongo.MongoDBDaoImpl;
import com.mongodb.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Laxton-Joe on 2017/6/22.
 */
public class MatchApplicantRunnable implements Runnable {
    private String _applicantId;

    public MatchApplicantRunnable(String applicantId) {
        _applicantId = applicantId;
    }

    MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();

    @Override
    public void run() {
//        synchronized (this) {

            try {
                DBObject dbObj = mongoDBDaoImpl.findById("Applicant", _applicantId);
                if (dbObj != null) {

                    String mqId = dbObj.get("ID").toString();
                    int birthDay = (int) dbObj.get("DATEOFBIRTH");
                    String name = dbObj.get("SURNAME").toString();
                    int gender = Integer.parseInt(dbObj.get("GENDER").toString());
                    HashSet<MatchedResult> matchArr = MapReduce(mongoDBDaoImpl.getDb(SystemHelper.MONGODBSETTING_DB), mqId, name, gender, birthDay);
                    StringBuilder sb = new StringBuilder();
                    sb.append("empty.,");
                    if (matchArr != null && matchArr.size() > 0) {
                        //执行匹配
                        sb = new StringBuilder();
                        for (MatchedResult mr : matchArr) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("probeId", mqId);
                            map.put("referenceId", mr.getId());
                            map.put("leven", mr.getLeven());
                            map.put("status", "Pending");
                            map.put("createdTime", new Date());
                            mongoDBDaoImpl.addObject(map, "Duplicate"); //插入到Duplicate
                            sb.append(mr.getId() + ",");
                        }
                    }
                    //删除Queue
                  //  mongoDBDaoImpl.removeQueue(mqId);
                    //更新Applicant 状态
                    mongoDBDaoImpl.updateStatus(mqId, "Applicant", "status", 1);
                    //日志
                    Map<String, Object> mapLog = new HashMap<String, Object>();
                    mapLog.put("applicantId", mqId);
                    mapLog.put("LogEvent", String.format("Applicant Matched Status:Master.Reference:%s", sb.toString().substring(0, sb.toString().length() - 2)));
                    mapLog.put("LogTime", new Date());

                    mongoDBDaoImpl.addObject(mapLog, "ApplicantLog");

                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

//        }
    }

    private HashSet<MatchedResult> MapReduce(DB dbMongo, String key, String name, int gender, int dateOfBirth) {
        HashSet<MatchedResult> results = new HashSet<MatchedResult>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date dateBirth = null;
            LocalDate localBirth = null;
            try {
                dateBirth = sdf.parse(String.valueOf(dateOfBirth));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateBirth != null) {
                localBirth = SystemHelper.DateToLocal(dateBirth);
                if (localBirth.isLeapYear()) {
                    int monthOfBirth = localBirth.getMonthValue();
                    if (monthOfBirth == 2) {
                        int dayOfBirth = localBirth.getDayOfMonth();
                        if (monthOfBirth == 29) {
                            localBirth.minusDays(1);
                        }
                    }
                }
            }
            StringBuilder sbMap = new StringBuilder();
            sbMap.append("function() {");
            sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", name));
            sbMap.append("var tgrSodEX= SoundEx(this.surname);");
            sbMap.append(String.format("var leven= levenshtein(\"%s\",this.surname);", name));
            sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
            sbMap.append(String.format("emit(\"%s\",{id:this._id,leven:leven});}}", key));


            StringBuilder reduce = new StringBuilder();
            reduce.append("function(key, values) { return {values:values};}");
//            reduce.append("var responArr = new Array();");
//            reduce.append("var keyName=key.split(\":\")[1];");
//            reduce.append("var srcSodEX=SoundEx(keyName);");
//            reduce.append("for(var i in mapData) {");
//            reduce.append("var tgrSodEX= SoundEx(mapData[i].name);");
//            reduce.append("var leven= levenshtein(keyName,mapData[i].name);");
//            reduce.append("if(tgrSodEX==srcSodEX&&leven<4){");
//            reduce.append("responArr.push(mapData[i].id);}}");
//            reduce.append("return {match_id_list:responArr};}");

            String match_result = "match_result";

            DBObject dbQuery = new BasicDBObject();
            dbQuery.put("gender", gender);
            dbQuery.put("status", 1);

            LocalDate startDate = localBirth.minusYears(10);
            LocalDate endDate = localBirth.plusYears(10);
            int iStart = Integer.parseInt(SystemHelper.LocalToString(startDate));
            int iEend = Integer.parseInt(SystemHelper.LocalToString(endDate));
            BasicDBObject birthQuery = new BasicDBObject();
            birthQuery.put("$gte", iStart);
            birthQuery.put("$lt", iEend);
            dbQuery.put("dateOfBirth", birthQuery);

            MapReduceCommand mrc = new MapReduceCommand(dbMongo.getCollection("Applicant"), sbMap.toString(), reduce.toString(), match_result, MapReduceCommand.OutputType.REPLACE, dbQuery);

            MapReduceOutput mapReduceOutput = dbMongo.getCollection("Applicant").mapReduce(mrc);
            DBCollection resultColl = mapReduceOutput.getOutputCollection();
            List<DBObject> listResult = resultColl.find().toArray();
            for (DBObject obj : listResult) {//实际上只支持一个返回结果集合
                if (obj.keySet().contains("value")) {
                    DBObject _o1 = (DBObject) obj.get("value");
                    if (_o1.keySet().contains("id") && _o1.keySet().contains("leven")) {
                        results.add(new MatchedResult(_o1));
//                          dbIdsList = (BasicDBList) _o1.get("match_id_list");
                        // result = dbIdsList.toArray(new String[dbIdsList.size()]);
                    }
                    if (_o1.keySet().contains("values")) {
                        BasicDBList _oList = (BasicDBList) _o1.get("values");
                        for (Object o : _oList) {
                            BasicDBObject item = (BasicDBObject) o;
                            if (item.keySet().contains("id") && item.keySet().contains("leven")) {
                                results.add(new MatchedResult(item));
//                          dbIdsList = (BasicDBList) _o1.get("match_id_list");
                                // result = dbIdsList.toArray(new String[dbIdsList.size()]);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return results;
    }
}
