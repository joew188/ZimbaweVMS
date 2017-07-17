package Zim.service;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;
import Zim.model.modelview.*;
import Zim.model.modelview.chart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */

@Service
public class ApplicantService {
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
                    if (SystemHelper.getApplicantColumns().contains(key)) {
                        if (appQuery.getFilters().get(key).length() > 0) {
                            if (isFirst) {
                                if (key.equals("applicantDemographic.province") || key.equals("applicantDemographic.district")) {
                                    criteria = Criteria.where(key).is(appQuery.getFilters().get(key));
                                } else if (key.equals("dateOfBirth") || key.equals("gender") || key.equals("status")) {
                                    if (appQuery.getFilters().get(key).length() > 0) {
                                        criteria = Criteria.where(key).is(Integer.parseInt(appQuery.getFilters().get(key)));
                                    }
                                } else {
                                    criteria = Criteria.where(key).regex(appQuery.getFilters().get(key), "i");
                                }
                                isFirst = false;
                            } else {
                                if (key.equals("applicantDemographic.province") || key.equals("applicantDemographic.district")) {
                                    criteria = criteria.and(key).is(appQuery.getFilters().get(key));
                                } else if (key.equals("applicantDemographic.dateOfBirth") || key.equals("applicantDemographic.gender") || key.equals("status")) {
                                    if (appQuery.getFilters().get(key).length() > 0) {
                                        criteria = criteria.and(key).is(Integer.parseInt(appQuery.getFilters().get(key)));
                                    }
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
                boolean gender = applicant.getGender();
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
        for (Duplicate dup : listDup
                ) {
            result.add(dup.getReferenceId());
        }
        return result;
    }

    public List<ApplicantOfProvince> getProvinceStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfProvince> result = new ArrayList<>();
        Iterator<ApplicantOfProvince> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfProvince.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfProvince) iteratorResult.next());
        }
        return result;
    }

    public List<ProvinceStatistic> getStaticByProvince(Criteria criteria, GroupBy groupBy) {
        List<ProvinceStatistic> result = new ArrayList<>();
        Iterator<ProvinceStatistic> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ProvinceStatistic.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ProvinceStatistic) iteratorResult.next());

        }
        return result;
    }

    public List<DistrictStatistic> getStaticByDistrict(Criteria criteria, GroupBy groupBy) {
        List<DistrictStatistic> result = new ArrayList<>();
        Iterator<DistrictStatistic> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, DistrictStatistic.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((DistrictStatistic) iteratorResult.next());

        }
        return result;
    }

    public List<ConstituencyStatistic> getStaticByConstituency(Criteria criteria, GroupBy groupBy) {
        List<ConstituencyStatistic> result = new ArrayList<>();
        Iterator<ConstituencyStatistic> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ConstituencyStatistic.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ConstituencyStatistic) iteratorResult.next());

        }
        return result;
    }

    public List<ApplicantOfProvinceAvg> getProvinceAvgStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfProvinceAvg> result = new ArrayList<>();
        Iterator<ApplicantOfProvinceAvg> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfProvinceAvg.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfProvinceAvg) iteratorResult.next());
        }
        return result;
    }

    public List<ApplicantOfOperator> getOperatorStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfOperator> result = new ArrayList<>();
        Iterator<ApplicantOfOperator> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfOperator.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfOperator) iteratorResult.next());
        }
        return result;
    }

    public List<ApplicantOfOperatorAvg> getOperatorStaticAvg(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfOperatorAvg> result = new ArrayList<>();
        Iterator<ApplicantOfOperatorAvg> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfOperatorAvg.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfOperatorAvg) iteratorResult.next());
        }
        return result;
    }

    public List<ApplicantOfDevice> getDeviceStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfDevice> result = new ArrayList<>();
        Iterator<ApplicantOfDevice> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfDevice.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfDevice) iteratorResult.next());
        }
        return result;
    }

    public List<ApplicantOfDeviceAvg> getDeviceAvgStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfDeviceAvg> result = new ArrayList<>();
        Iterator<ApplicantOfDeviceAvg> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfDeviceAvg.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfDeviceAvg) iteratorResult.next());
        }
        return result;
    }

    public List<ApplicantOfDate> getDateStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfDate> result = new ArrayList<>();
        Iterator<ApplicantOfDate> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfDate.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfDate) iteratorResult.next());
        }
        return result;
    }

    public List<ApplicantOfDateAvg> getDateAvgStatic(Criteria criteria, GroupBy groupBy) {
        List<ApplicantOfDateAvg> result = new ArrayList<>();
        Iterator<ApplicantOfDateAvg> iteratorResult = mongoTemplate.group(criteria, "Applicant", groupBy, ApplicantOfDateAvg.class).iterator();
        while (iteratorResult.hasNext()) {
            result.add((ApplicantOfDateAvg) iteratorResult.next());
        }
        return result;
    }
}
