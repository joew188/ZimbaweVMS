package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;
import Zim.model.MatchQueue;
import Zim.model.modelview.ApplicantMatchResult;
import Zim.model.modelview.ApplicantQuery;
import Zim.model.modelview.MatchedResult;
import Zim.model.modelview.SysPagination;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */

@Service
public class ApplicantService {

    //    @InitBinder
//        public void initBinder(WebDataBinder binder) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(true);
//        binder.registerCustomEditor(Date.class, newCustomDateEditor(dateFormat, true));
//    }
    private static String APPLICANT_COLLECTION = "Applicant";
    @Autowired
    MongoTemplate mongoTemplate;


    public Applicant find(String id) {
        return mongoTemplate.findById(id, Applicant.class);
    }

    public SysPagination<Applicant> pageList(ApplicantQuery appQuery) {

        SysPagination<Applicant> result = new SysPagination<>();
        try {
            Query query = new Query();
            if (appQuery.getOrderBy() != null && appQuery.getOrderBy().length() > 0) {
                if (appQuery.getOrderBy().toUpperCase().equals("DESC")) {
                    if (appQuery.getOrderByName() != null && appQuery.getOrderByName().length() > 0)
                        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, appQuery.getOrderByName())));
                } else {
                    if (appQuery.getOrderByName() != null && appQuery.getOrderByName().length() > 0)
                        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, appQuery.getOrderByName())));
                }
            }
            int skip = (appQuery.getCurrentPage() - 1) * appQuery.getPageSize();
            Criteria criteria = null;
            if (appQuery.getFilters() != null) {
                boolean isFirst = true;
                for (String key : appQuery.getFilters().keySet()) {
                    if (Applicant.getColumns().contains(key)) {
                        if (appQuery.getFilters().get(key).length() > 0) {
                            if (isFirst) {
                                if (key.equals("province") || key.equals("district")) {
                                    criteria = Criteria.where(key).is(appQuery.getFilters().get(key));
                                } else if (key.equals("dateOfBirth")) {
                                    criteria = Criteria.where(key).is(Integer.parseInt(appQuery.getFilters().get(key)));
                                } else {
                                    criteria = Criteria.where(key).regex(appQuery.getFilters().get(key), "i");
                                }
                                isFirst = false;
                            } else {
                                if (key.equals("province") || key.equals("district")) {
                                    criteria = criteria.and(key).is(appQuery.getFilters().get(key));
                                } else if (key.equals("dateOfBirth")) {
                                    criteria = criteria.and(key).is(Integer.parseInt(appQuery.getFilters().get(key)));
                                } else {
                                    criteria = criteria.and(key).regex(appQuery.getFilters().get(key), "i");
                                }
                            }
                        }
                    }
                }
                if (criteria != null) {
                    query.addCriteria(criteria);
                }

            }
            int count = mongoTemplate.find(query, Applicant.class).size();
            if (count > 0) {
                int totalPage = 0;//(int) (count / appQuery.getPageSize());
                if (count % appQuery.getPageSize() == 0) {
                    totalPage = (int) (count / appQuery.getPageSize());
                } else {
                    totalPage = ((int) (count / appQuery.getPageSize()) + 1);
                }
                result.setTotalRecord(count);//总共记录
                result.setTotalPage(totalPage);//总共页数
                result.setPageSize(appQuery.getPageSize());//每页记录
                result.setFilters(appQuery.getFilters());

                query.skip(skip);// 从那条记录开始
                query.limit(appQuery.getPageSize());// 取多少条记录
                List<Applicant> listData = mongoTemplate.find(query, Applicant.class);
                if (listData.size() > 0) {
                    result.setCurrentPage(appQuery.getCurrentPage());//当前页
                    for (Applicant a : listData) {
                        a.setPhoto("");
                    }
                    result.setItems(listData);//查询内容
                }
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }

        return result;
    }

    public List<String> addApplicant(Applicant applicant) {
        List<String> result = new ArrayList<>();
        if (mongoTemplate.findById(applicant.get_id(), Applicant.class) == null) {
            applicant.setStatus(0);
            applicant.setIndividualsId(java.util.UUID.randomUUID().toString());
            //保存applicant
            mongoTemplate.insert(applicant, "Applicant");

            mongoTemplate.insert(new ApplicantLog(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");

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
//            MapReduceOptions mrO=new MapReduceOptions();
//            mrO.outputCollection("map_reduce_result");
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
                }
                else if (_o1.keySet().contains("id"))
                {
                    String refId = (((LinkedHashMap) _o1).get("id")).toString();
                    int levenValue = ((int) (Double.parseDouble((((LinkedHashMap) _o1).get("leven")).toString())));

                    mongoTemplate.insert(new Duplicate(probeId, refId, levenValue));
                    result.add(refId);
                }
            }

            mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(probeId)), new Update().set("status", 1), Applicant.class);
        }
        return result;
    }
}
