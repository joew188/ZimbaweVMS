package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;
import Zim.model.ImportLog;
import Zim.model.modelview.*;
import Zim.model.modelview.chart.*;
import Zim.model.modelview.importLog.ImportByDeviceRecord;
import Zim.model.modelview.importLog.ImportGaps;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */

@Service
public class ApplicantService extends BaseService {
    @Autowired
    MongoTemplate mongoTemplate;

    public Applicant find(String id) {
        return mongoTemplate.findById(id, Applicant.class);
    }

    public void drop() {
        mongoTemplate.dropCollection(Applicant.class);
        mongoTemplate.dropCollection(ApplicantLog.class);
        mongoTemplate.dropCollection(Duplicate.class);
        mongoTemplate.dropCollection(ImportLog.class);
        mongoTemplate.dropCollection("match_result");
    }

    public SysPagination<Applicant> pageList(PagingQuery appQuery) {

        SysPagination<Applicant> result = new SysPagination<>();
        try {
            DBObject fieldObject = new BasicDBObject();
            fieldObject.put("_id", true);
            fieldObject.put("surname", true);
            fieldObject.put("provinceName", true);
            fieldObject.put("districtName", true);
            fieldObject.put("dateOfBirth", true);
            fieldObject.put("gender", true);
            fieldObject.put("applicantDemographic.forenames", true);
            fieldObject.put("status", true);
            DBObject queryObject = new BasicDBObject();
            Query query = new BasicQuery(queryObject, fieldObject);

            sortQuery(appQuery, query);
            if (appQuery.getFilters() != null) {
                setCriteria(appQuery, query);
            }
            int total = mongoTemplate.find(query, Applicant.class).size();
            if (total > 0) {
                setPaging(result, appQuery, query, total);
                List<Applicant> listData = mongoTemplate.find(query, Applicant.class);
                result.setCurrentPage(appQuery.getCurrentPage());//当前页
                result.setItems(listData);
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }

    @Override
    public void setCriteria(PagingQuery pagingQuery, Query query) {
        Criteria criteria = new Criteria();
        for (String key : pagingQuery.getFilters().keySet()) {
            if (pagingQuery.getFilters().get(key).length() > 0) {
                if (key.equals("dateOfBirth") || key.equals("gender") || key.equals("status")) {
                    criteria = criteria.and(key).is(Integer.parseInt(pagingQuery.getFilters().get(key)));
                } else {
                    String keyValue = pagingQuery.getFilters().get(key);
                    if (key.equals("personName")) {
                        key  = "applicantDemographic.forenames";
                    }
                    criteria = criteria.and(key).regex(keyValue, "i");
                }
            }
        }
        query.addCriteria(criteria);
    }

    public List<String> addApplicant(Applicant applicant) {
        List<String> result = new ArrayList<>();
        try {
            Applicant findApplicant = mongoTemplate.findById(applicant.get_id(), Applicant.class);
            if (findApplicant == null || findApplicant.getStatus() == 0) {
                if (findApplicant == null) {
                    applicant.setStatus(Short.parseShort("0"));

                    //保存applicant
                    mongoTemplate.insert(applicant, "Applicant");

                    mongoTemplate.insert(new ApplicantLog(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");
                }
                String probeId = applicant.get_id();
                String name = applicant.getSurname();
                int gender = applicant.getGender();
                List<Integer> birthDistance = SystemHelper.getBetweenDate(applicant.getDateOfBirth());

                StringBuilder sbMap = new StringBuilder();
                sbMap.append("function() {");
                sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", name));
                sbMap.append("var tgrSodEX= SoundEx(this.surname);");
                sbMap.append(String.format("var leven= levenshtein(\"%s\",this.surname);", name));
                sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
                sbMap.append(String.format("emit(\"%s\",{id:this._id,leven:leven});}}", probeId));

                StringBuilder reduce = new StringBuilder();
                reduce.append("function(key, values) { return {values:values};}");

                Query query = new Query(Criteria.where("gender").is(gender).and("status").is(1).and("dateOfBirth").gt(birthDistance.get(0)).lt(birthDistance.get(1)));

                MapReduceResults<ApplicantMatchResult> matchArr = mongoTemplate.mapReduce(query, "Applicant", sbMap.toString(), reduce.toString(), ApplicantMatchResult.class);

                for (ApplicantMatchResult matched : matchArr) {
                    HashMap _o1 = (HashMap) matched.getValue();
                    if (_o1.keySet().contains("values")) {
                        ArrayList _oList = (ArrayList) _o1.get("values");
                        for (Object o : _oList) {
                            String refId = (((LinkedHashMap) o).get("id")).toString();
                            int levenValue = ((int) (Double.parseDouble((((LinkedHashMap) o).get("leven")).toString())));
                            Duplicate dupMatched = new Duplicate(probeId, refId, levenValue);
                            mongoTemplate.insert(dupMatched);
                            result.add(refId);
                        }
                    } else if (_o1.keySet().contains("id")) {
                        String refId = (((LinkedHashMap) _o1).get("id")).toString();
                        int levenValue = ((int) (Double.parseDouble((((LinkedHashMap) _o1).get("leven")).toString())));

                        mongoTemplate.insert(new Duplicate(probeId, refId, levenValue));
                        result.add(refId);
                    }
                }

                mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(probeId)), new Update().set("status", 1), Applicant.class);
            } else {
                result = getApplicantDuplicatesIdList(applicant.get_id());
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return result;
    }

    public List<String> getApplicantDuplicatesIdList(String applicantId) {
        List<String> result = new ArrayList<>();
        Query query = new Query();
        Criteria criteria = Criteria.where("probeId").is(applicantId);

        query.addCriteria(criteria);
        List<Duplicate> listDup = mongoTemplate.find(query, Duplicate.class);
        for (Duplicate dup : listDup) {
            result.add(dup.getReferenceId());
        }
        return result;
    }
}
