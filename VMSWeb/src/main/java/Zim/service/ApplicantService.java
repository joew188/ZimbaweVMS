package Zim.service;

import Zim.linstener.MatchRunnable;
import Zim.model.*;
import Zim.model.modelview.*;
import Zim.model.modelview.importLog.ImportGaps;
import Zim.model.modelview.req.BasicPagingQuery;
import Zim.model.modelview.req.PagingOneQuery;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.EntityResponse;
import Zim.model.modelview.res.PageResponse;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */

@Service
public class ApplicantService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(ApplicantService.class);

    private Class getPhotoClass(String col) {
        Class entityClass = null;
        switch (col) {
            case "ApplicantPhotos":
                entityClass = ApplicantPhotos.class;
                break;

            case "FingerprintImage":
                entityClass = FingerprintImage.class;
                break;
            case "FingerprintTemplate":
                entityClass = FingerprintTemplate.class;
                break;
            case "FingerprintWSQ":
                entityClass = FingerprintWSQ.class;
                break;
        }
        return entityClass;
    }

    public String photoFindOne(String id) {
        String result = "";
        Class entityClass = getPhotoClass("ApplicantPhotos");
        Object objResult = mongoTemplate.findById(new ObjectId(id), entityClass);
        if (objResult != null) {
            result = new String(((BinaryEntity) objResult).getImgData());
        }
        return result;
    }

    public byte[] photoFind(String id, String col) {
        byte[] result = null;
        Class entityClass = getPhotoClass(col);
        Object objResult = mongoTemplate.findById(new ObjectId(id), entityClass);
        if (objResult != null) {
            result = ((BinaryEntity) objResult).getImgData();
        }
        return result;
    }

    public void drop() {
        mongoTemplate.dropCollection(Applicant.class);
        mongoTemplate.dropCollection(ApplicantLog.class);
        mongoTemplate.dropCollection(Duplicate.class);
        mongoTemplate.dropCollection(ImportLog.class);
        mongoTemplate.dropCollection(ApplicantPhotos.class);
        mongoTemplate.dropCollection(MatchTaskQueue.class);
        mongoTemplate.dropCollection(FingerprintImage.class);
        mongoTemplate.dropCollection(FingerprintTemplate.class);
        mongoTemplate.dropCollection(FingerprintWSQ.class);
        mongoTemplate.dropCollection(ApplicantMaster.class);
        mongoTemplate.dropCollection(ImportTransaction.class);
        mongoTemplate.dropCollection("match_result");

    }

    public PageResponse<ApplicantMaster> pageList(PagingPageQuery pagingQuery) {

        PageResponse<ApplicantMaster> result = new PageResponse<>();
        try {
            DBObject fieldObject = new BasicDBObject();
            fieldObject.put("_id", true);
            fieldObject.put("registrationNumber", true);
            fieldObject.put("surname", true);
            fieldObject.put("provinceName", true);
            fieldObject.put("districtName", true);
            fieldObject.put("dateOfBirth", true);
            fieldObject.put("gender", true);
            fieldObject.put("forenames", true);
            fieldObject.put("status", true);
            BasicDBObject queryObject = new BasicDBObject();
            Query query = new BasicQuery(queryObject, fieldObject);

            sortQuery(pagingQuery, query);

            setCriteria(pagingQuery, query);

            long total = mongoTemplate.count(query, ApplicantMaster.class);
            if (total > 0) {
                setPageQuery(pagingQuery, query);
                List<ApplicantMaster> listData = mongoTemplate.find(query, ApplicantMaster.class);
                result.setItems(listData);
            }
            setPagePaging(result, pagingQuery, total);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            logger.error(e.toString());
        }
        return result;
    }

    public Applicant pageFineOne(EntityResponse<ApplicantDetails> response, PagingOneQuery pagingOneQuery) {
        Applicant applicant = null;
        ApplicantMaster master;
        try {
            Query query = new Query();
            sortQuery(pagingOneQuery, query);

            setCriteria(pagingOneQuery, query);

            long total = mongoTemplate.count(query, ApplicantMaster.class);
            if (total > 0) {
                setPageQuery(pagingOneQuery, query);
                List<ApplicantMaster> listData = mongoTemplate.find(query, ApplicantMaster.class);
                if (listData.size() > pagingOneQuery.getRecordIndex()) {
                    master = listData.get(pagingOneQuery.getRecordIndex());
                    if (pagingOneQuery.getRecordId() != null && pagingOneQuery.getRecordId().length() > 0) {
                        if (!master.get_id().equals(pagingOneQuery.getRecordId())) {
                            master = null;
                        }
                    }
                    if (master != null) {
                        applicant = mongoTemplate.findById(master.get_id(), Applicant.class);
                        applicant.merge(master);
                    }
                }
            }
            setFindOnePaging(response, pagingOneQuery, total);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return applicant;
    }

//    public PageResponse<ApplicantSimple> pagePdfList(PagingOrNotQuery pageOrNotQuery) {
//
//        PageResponse<ApplicantSimple> result = new PageResponse<>();
//        try {
//            DBObject fieldObject = new BasicDBObject();
//            fieldObject.put("_id", true);
//            fieldObject.put("applicantDemographic.idNumber", true);
//            fieldObject.put("fullName", true);
//            fieldObject.put("gender", true);
//            fieldObject.put("applicantPhoto.photoArray", true);
//            fieldObject.put("applicantDemographic.dateOfBirthText", true);
//            fieldObject.put("applicantDemographic.provinceName", true);
//            fieldObject.put("applicantDemographic.districtName", true);
//            fieldObject.put("applicantDemographic.constituencyName", true);
//            fieldObject.put("applicantDemographic.wardName", true);
//            fieldObject.put("applicantDemographic.surburb", true);
//            fieldObject.put("applicantDemographic.streetName", true);
//            DBObject queryObject = new BasicDBObject();
//            Query query = new BasicQuery(queryObject, fieldObject);
//            //  PagingQuery pagingQuery = pageOrNotQuery.toPagingQuery();
//            if (pageOrNotQuery.isPaging()) {
//                sortQuery(pageOrNotQuery, query);
//            }
//            if (pageOrNotQuery.getFilters() != null) {
//                setCriteria(pageOrNotQuery, query);
//            }
//            long total = mongoTemplate.count(query, Applicant.class);
//            if (total > 0) {
//                if (pageOrNotQuery.isPaging()) {
//                    setPaging(result, pageOrNotQuery, query, total);
//                }
//                List<Applicant> listData = mongoTemplate.find(query, Applicant.class);
//                if (pageOrNotQuery.isPaging()) {
//                    result.setCurrentPage(pageOrNotQuery.getCurrentPage());//当前页
//                }
//                List<ApplicantSimple> content = new ArrayList<>();
//                for (Applicant applicant : listData) {
//                    ApplicantSimple simple = new ApplicantSimple();
//                    simple.setName(applicant.getFullName());
//                    simple.set_id(applicant.get_id());
//                    simple.setDateOfBirthText(applicant.getApplicantDemographic().getDateOfBirthText());
//                    simple.setIdNumber(applicant.getApplicantDemographic().getIdNumber());
//                    String photoStr = "";
//                    if (applicant.getApplicantPhoto().getPhotoArray() != null && applicant.getApplicantPhoto().getPhotoArray().length() > 0) {
//                        photoStr = photoFindOne(applicant.getApplicantPhoto().getPhotoArray(), "PersonPhoto");
//                    }
//                    simple.setPhoto(photoStr);
//                    simple.setAddress(applicant.getApplicantDemographic().getProvinceName() + " "
//                            + applicant.getApplicantDemographic().getDistrictName() + " "
//                            + applicant.getApplicantDemographic().getConstituencyName() + " "
//                            + applicant.getApplicantDemographic().getWardName() + " "
//                            + applicant.getApplicantDemographic().getSurburb() + " "
//                            + applicant.getApplicantDemographic().getStreetName()
//                    );
//                    simple.setGender(applicant.getGender() == 1 ? "Male" : "Female");
//                    content.add(simple);
//                }
//                result.setItems(content);
//            }
//            result.setResult(true);
//        } catch (Exception e) {
//            result.setResult(false);
//        }
//        return result;
//    }

    public List<ApplicantSimple> pagePdfList(int pid) {
        List<ApplicantSimple> result = new ArrayList<>();
        try {
            DBObject fieldObject = new BasicDBObject();
            fieldObject.put("_id", true);
            fieldObject.put("applicantDemographic.idNumber", true);
            fieldObject.put("fullName", true);
            fieldObject.put("gender", true);
            fieldObject.put("applicantPhoto.photoArray", true);
            fieldObject.put("applicantDemographic.dateOfBirthText", true);
            fieldObject.put("applicantDemographic.provinceName", true);
            fieldObject.put("applicantDemographic.districtName", true);
            fieldObject.put("applicantDemographic.constituencyName", true);
            fieldObject.put("applicantDemographic.wardName", true);
            fieldObject.put("applicantDemographic.surburb", true);
            fieldObject.put("applicantDemographic.streetName", true);
            DBObject queryObject = new BasicDBObject();
            Query query = new BasicQuery(queryObject, fieldObject);
            Criteria criteria = new Criteria();

            criteria.and("pollingStationId").is(pid);
            query.addCriteria(criteria);
            long total = mongoTemplate.count(query, Applicant.class);
            if (total > 0) {

                List<Applicant> listData = mongoTemplate.find(query, Applicant.class);


                for (Applicant applicant : listData) {
                    ApplicantSimple simple = new ApplicantSimple();
                    simple.setName(applicant.getApplicantDemographic().getForenames() + " " + applicant.getApplicantDemographic().getSurname());
                    simple.set_id(applicant.get_id());
                    simple.setDateOfBirthText(applicant.getApplicantDemographic().getDateOfBirthText());
                    simple.setIdNumber(applicant.getApplicantDemographic().getIdNumber());
                    String photoStr = "";
                    if (applicant.getApplicantPhoto().getPhotoArray() != null && applicant.getApplicantPhoto().getPhotoArray().length() > 0) {
                        photoStr = photoFindOne(applicant.getApplicantPhoto().getPhotoArray());
                    }
                    simple.setPhoto(photoStr);
                    simple.setAddress(applicant.getApplicantDemographic().getProvinceName() + " "
                            + applicant.getApplicantDemographic().getDistrictName() + " "
                            + applicant.getApplicantDemographic().getConstituencyName() + " "
                            + applicant.getApplicantDemographic().getWardName() + " "
                            + applicant.getApplicantDemographic().getSurburb() + " "
                            + applicant.getApplicantDemographic().getStreetName()
                    );
                    simple.setGender(applicant.getGender() == 1 ? "Male" : "Female");
                    result.add(simple);
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return result;
    }

    @Override
    public void setCriteria(BasicPagingQuery pagingQuery, Query query) {
        Criteria criteria = new Criteria();
        for (String key : pagingQuery.getFilters().keySet()) {
            if (pagingQuery.getFilters().get(key).length() > 0) {
                if (key.equals("dateOfBirth") || key.equals("gender") || key.equals("status") || key.equals("pollingStationId")) {
                    criteria = criteria.and(key).is(Integer.parseInt(pagingQuery.getFilters().get(key)));
                } else {
                    String keyValue = pagingQuery.getFilters().get(key);

                    criteria = criteria.and(key).regex(keyValue, "i");
                }
            }
        }
        query.addCriteria(criteria);
    }

//    public List<String> addApplicant(Applicant applicant) {
//        List<String> result = new ArrayList<>();
//        try {
//            Applicant findApplicant = mongoTemplate.findById(applicant.get_id(), Applicant.class);
//            if (findApplicant == null || findApplicant.getStatus() == 0) {
//                if (findApplicant == null) {
//                    applicant.setStatus(Byte.parseByte("0"));
//
//                    //保存applicant
//                    mongoTemplate.insert(applicant, "Applicant");
//
//                    mongoTemplate.insert(new ApplicantLog(applicant.get_id(), "Applicant Created Status:Unmatched."), "ApplicantLog");
//                }
//                String probeId = applicant.get_id();
//              //  String name = applicant.getFullName();
//                int gender = applicant.getGender();
//                List<Integer> birthDistance = SystemHelper.getBetweenDate(applicant.getDateOfBirth());
//
//                StringBuilder sbMap = new StringBuilder();
//                sbMap.append("function() {");
//                sbMap.append(String.format("var srcSodEX=SoundEx(\"%s\");", name));
//                sbMap.append("var tgrSodEX= SoundEx(this.fullName);");
//                sbMap.append(String.format("var leven= levenshtein(\"%s\",this.fullName);", name));
//                sbMap.append("if (srcSodEX == tgrSodEX&&leven<4){");
//                sbMap.append(String.format("emit(\"%s\",{id:this._id,leven:leven});}}", probeId));
//
//                StringBuilder reduce = new StringBuilder();
//                reduce.append("function(key, values) { return {values:values};}");
//
//                Query query = new Query(Criteria.where("gender").is(gender).and("status").is(1).and("dateOfBirth").gt(birthDistance.get(0)).lt(birthDistance.get(1)));
//
//                MapReduceResults<ApplicantMatchResult> matchArr = mongoTemplate.mapReduce(query, "Applicant", sbMap.toString(), reduce.toString(), ApplicantMatchResult.class);
//
//                for (ApplicantMatchResult matched : matchArr) {
//                    HashMap _o1 = (HashMap) matched.getValue();
//                    if (_o1.keySet().contains("values")) {
//                        ArrayList _oList = (ArrayList) _o1.get("values");
//                        for (Object o : _oList) {
//                            String refId = (((LinkedHashMap) o).get("id")).toString();
//                            int levenValue = ((int) (Double.parseDouble((((LinkedHashMap) o).get("leven")).toString())));
//                            Duplicate dupMatched = new Duplicate(probeId, refId, levenValue);
//                            mongoTemplate.insert(dupMatched);
//                            result.add(refId);
//                        }
//                    } else if (_o1.keySet().contains("id")) {
//                        String refId = (_o1.get("id")).toString();
//                        int levenValue = ((int) (Double.parseDouble((_o1.get("leven")).toString())));
//
//                        mongoTemplate.insert(new Duplicate(probeId, refId, levenValue));
//                        result.add(refId);
//                    }
//                }
//
//                mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(probeId)), new Update().set("status", 1), Applicant.class);
//            } else {
//                result = getApplicantDuplicatesIdList(applicant.get_id());
//            }
//        } catch (Exception ex) {
//            System.out.print(ex.getMessage());
//        }
//        return result;
//    }

//    public List<String> getApplicantDuplicatesIdList(String applicantId) {
//        List<String> result = new ArrayList<>();
//        Query query = new Query();
//        Criteria criteria = Criteria.where("probeId").is(applicantId);
//
//        query.addCriteria(criteria);
//        List<Duplicate> listDup = mongoTemplate.find(query, Duplicate.class);
//        for (Duplicate dup : listDup) {
//            result.add(dup.getReferenceId());
//        }
//        return result;
//    }

    public PageResponse<ImportGaps> getApplicantGap(PagingPageQuery pagingQuery) {
        PageResponse<ImportGaps> result = new PageResponse<>();

        DBObject query = new BasicDBObject();
        if (pagingQuery.getFilters() != null && pagingQuery.getFilters().containsKey("deviceName")) {
            String params = pagingQuery.getFilters().get("deviceName").toString();
            Pattern pattern = Pattern.compile(params, Pattern.CASE_INSENSITIVE);
            query.put("deviceName", pattern);
        }
        List listDevice = mongoTemplate.getCollection("ApplicantMaster").distinct("deviceName", query);


        int count = listDevice.size();
        //分页
        if (count > 0) {
            long totalPage = 0;
            if (count % pagingQuery.getPageSize() == 0) {
                totalPage = count / pagingQuery.getPageSize();
            } else {
                totalPage = (count / pagingQuery.getPageSize() + 1);
            }
            result.setTotalRecord(count);//总共记录
            result.setTotalPage(totalPage);//总共页数
            result.setPageSize(pagingQuery.getPageSize());//每页记录
            result.setFilters(pagingQuery.getFilters());
            long beginIndex = (pagingQuery.getCurrentPage() - 1) * pagingQuery.getPageSize();
            long endIndex = beginIndex + pagingQuery.getPageSize();
            if (endIndex > count) {
                endIndex = count;
            }
            if (beginIndex < count) {
                listDevice.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
                List<String> selDeviceName = listDevice.subList((int) beginIndex, (int) endIndex);
                if (selDeviceName.size() > 0) {
                    result.setCurrentPage(pagingQuery.getCurrentPage());//当前页


                    GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{sortNums:null,gaps:null,,beginIndex:0,endIndex:0}")
                            .reduceFunction("function(doc,prev){if(prev.sortNums==null){prev.sortNums=new Array();}prev.sortNums.push( doc.sortNumber);}")
                            .finalizeFunction("function(prev){prev.sortNums.sort(sortNumber);var gaps=new Array();for(var i=0;i<prev.sortNums.length;i++){if(i>0){var gapNum=(prev.sortNums[i]-prev.sortNums[i-1]);if(gapNum>1){var gap={gap:gapNum-1,start:prev.sortNums[i-1],end:prev.sortNums[i]};gaps.push(gap);}}else{if(prev.sortNums[0]>1){ var gap={gap:prev.sortNums[0]-1,start:0,end:prev.sortNums[0]};gaps.push(gap);}}}prev.beginIndex=prev.sortNums[0];prev.endIndex=prev.sortNums[prev.sortNums.length-1];prev.gaps=gaps;delete prev.sortNums;}");

                    List<ImportGaps> content = getGroupStatic(Criteria.where("deviceName").in(selDeviceName), groupBy, "ApplicantMaster", ImportGaps.class);
                    content.sort((o1, o2) -> o1.getDeviceName().compareTo(o2.getDeviceName()));
                    result.setItems(content);//查询内容
                }
            }
            result.setResult(true);
        }
        return result;
    }

//    public List getApplicantDistinct(String fieldName, DBObject query) {
//        return getDistinct("Applicant", fieldName, query);
//    }
//    public Applicant findApplicant(String id) {
//        Applicant applicant = mongoTemplate.findById(id, Applicant.class);
//        ApplicantMaster master = mongoTemplate.findById(id, ApplicantMaster.class);
//        applicant.merge(master);
//        return applicant;
//    }
}
