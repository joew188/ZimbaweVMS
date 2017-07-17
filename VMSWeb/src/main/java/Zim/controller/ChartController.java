package Zim.controller;

import Zim.model.modelview.SysResult;
import Zim.model.modelview.chart.*;
import Zim.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;


/**
 * Created by Laxton-Joe on 2017/6/8.
 */
@RestController
public class ChartController {
//    public HashMap provinceMap = new HashMap();
//    public HashMap districtMap = new HashMap();
//    public HashMap constituencyMap = new HashMap();

//    public ChartController() {
//        MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();
//        List<DBObject> aProvince = mongoDBDaoImpl.find("vms", "Province", -1);
//        for (DBObject doj : aProvince) {
//            provinceMap.put(doj.get("_id"), doj.get("name"));
//        }
//        List<DBObject> aDistrict = mongoDBDaoImpl.find("vms", "District", -1);
//        for (DBObject doj : aDistrict) {
//            districtMap.put(doj.get("_id"), doj.get("name"));
//        }
//        List<DBObject> aConstituency = mongoDBDaoImpl.find("vms", "Constituency", -1);
//        for (DBObject doj : aConstituency) {
//            constituencyMap.put(doj.get("_id"), doj.get("name"));
//        }
//    }

    @Autowired
    ApplicantService applicantService;

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/province")
    @ResponseBody
    public List<ApplicantOfProvince> ApplicantOfProvince(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                         @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfProvince> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("province", "provinceName").initialDocument("{count:0,distinct:0,duplicate:0}")
                .reduceFunction("function(doc,prev){switch(doc.status){default:prev.duplicate++;break; case 1:prev.distinct++;break;}prev.count++;}");
        List<ApplicantOfProvince> content = applicantService.getProvinceStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfProvince>() {
                    @Override
                    public int compare(ApplicantOfProvince o1, ApplicantOfProvince o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }
//        for (ApplicantOfProvince aop : content) {
//            if (provinceMap.get(aop.getProvince()) != null) {
//                aop.setProvinceName(provinceMap.get(aop.getProvince()).toString());
//            }
//        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/province/avg")
    @ResponseBody
    public List<ApplicantOfProvinceAvg> ApplicantOfProvinceAvg(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {

        SysResult<ApplicantOfProvinceAvg> result = new SysResult<>();

        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("province", "provinceName").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg = Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfProvinceAvg> content = applicantService.getProvinceAvgStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfProvinceAvg>() {
                    @Override
                    public int compare(ApplicantOfProvinceAvg o1, ApplicantOfProvinceAvg o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }
        return content;
    }


    /**
     * 查找录入居民数量最多的前10名操作员，或者录入数量最少的后10名操作员
     *
     * @param topSize
     * @param sortStr
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/operator")
    @ResponseBody
    public List<ApplicantOfOperator> ApplicantOfOperator(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                         @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfOperator> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("operatorGuid", "operatorName").initialDocument("{count:0}")
                .reduceFunction("function(doc,prev){prev.count++;}");
        List<ApplicantOfOperator> content = applicantService.getOperatorStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfOperator>() {
                    @Override
                    public int compare(ApplicantOfOperator o1, ApplicantOfOperator o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }

        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/operator/avg")
    @ResponseBody
    public List<ApplicantOfOperatorAvg> ApplicantOfOperatorAvg(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfOperatorAvg> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("operatorGuid", "operatorName").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg =Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfOperatorAvg> content = applicantService.getOperatorStaticAvg(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfOperatorAvg>() {
                    @Override
                    public int compare(ApplicantOfOperatorAvg o1, ApplicantOfOperatorAvg o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }

        return content;
    }


    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/device")
    @ResponseBody
    public List<ApplicantOfDevice> ApplicantOfDevice(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                     @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfDevice> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{count:0}")
                .reduceFunction("function(doc,prev){prev.count++;}");
        List<ApplicantOfDevice> content = applicantService.getDeviceStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfDevice>() {
                    @Override
                    public int compare(ApplicantOfDevice o1, ApplicantOfDevice o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/device/avg")
    @ResponseBody
    public List<ApplicantOfDeviceAvg> ApplicantOfDeviceAvg(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                           @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfDeviceAvg> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg = Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfDeviceAvg> content = applicantService.getDeviceAvgStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfDeviceAvg>() {
                    @Override
                    public int compare(ApplicantOfDeviceAvg o1, ApplicantOfDeviceAvg o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/date")
    @ResponseBody
    public List<ApplicantOfDate> ApplicantOfDate(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                 @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfDate> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("dateOfRegistration").initialDocument("{count:0}")
                .reduceFunction("function(doc,prev){prev.count++;}");
        List<ApplicantOfDate> content = applicantService.getDateStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfDate>() {
                    @Override
                    public int compare(ApplicantOfDate o1, ApplicantOfDate o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/date/avg")
    @ResponseBody
    public List<ApplicantOfDateAvg> ApplicantOfDateAvg(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                       @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {
        SysResult<ApplicantOfDateAvg> result = new SysResult<>();
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("dateOfRegistration").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg = Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfDateAvg> content = applicantService.getDateAvgStatic(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ApplicantOfDateAvg>() {
                    @Override
                    public int compare(ApplicantOfDateAvg o1, ApplicantOfDateAvg o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/constituency/{districtId}")
    @ResponseBody
    public List<ConstituencyStatistic> StatisticByConstituency(@PathVariable("districtId") int districtId) {
        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        criteria.and("district").is(districtId);
        GroupBy groupBy = GroupBy.key("province", "provinceName", "district", "districtName", "constituency", "constituencyName").initialDocument("{count:0,male:0,female:0,age0_20:0,age20_40:0,age40_60:0,age60_80:0,age80:0}")
                .reduceFunction("function(doc,prev){  var age = getAge(doc.dateOfBirth);if(doc.gender==0){ prev.male++;}else{prev.female++;}if(age<21){prev.age0_20++;}else if(age>20&&age<41){ prev.age20_40++;}else if(age>40&&age<61){ prev.age40_60++;}else if(age>60&&age<81){prev.age60_80++;}else if (age>80){ prev.age80++;}prev.count++;}");
        List<ConstituencyStatistic> content = applicantService.getStaticByConstituency(criteria, groupBy);
//
//        for (ConstituencyStatistic aop : content) {
//            if (provinceMap.get(aop.getProvince()) != null) {
//                aop.setProvinceName(provinceMap.get(aop.getProvince()).toString());
//            }
//            if (districtMap.get(aop.getDistrict()) != null) {
//                aop.setDistrictName(districtMap.get(aop.getDistrict()).toString());
//            }
//            if (constituencyMap.get(aop.getConstituency()) != null) {
//                aop.setProvinceName(constituencyMap.get(aop.getConstituency()).toString());
//            }
//        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/district/{provinceId}")
    @ResponseBody
    public List<DistrictStatistic> StatisticByDistrict(@PathVariable("provinceId") int provinceId) {

        SysResult<DistrictStatistic> result = new SysResult<>();

        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        criteria.and("province").is(provinceId);
        GroupBy groupBy = GroupBy.key("province", "provinceName", "district", "districtName").initialDocument("{count:0,male:0,female:0,age0_20:0,age20_40:0,age40_60:0,age60_80:0,age80:0}")
                .reduceFunction("function(doc,prev){  var age = getAge(doc.dateOfBirth);if(doc.gender==0){ prev.male++;}else{prev.female++;}if(age<21){prev.age0_20++;}else if(age>20&&age<41){ prev.age20_40++;}else if(age>40&&age<61){ prev.age40_60++;}else if(age>60&&age<81){prev.age60_80++;}else if (age>80){ prev.age80++;}prev.count++;}");
        List<DistrictStatistic> content = applicantService.getStaticByDistrict(criteria, groupBy);

        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/province")
    @ResponseBody
    public List<ProvinceStatistic> StatisticByProvince(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                       @RequestParam(value = "sort", required = false, defaultValue = "") String sortStr) {

        SysResult<ProvinceStatistic> result = new SysResult<>();

        Criteria criteria = new Criteria();
        criteria.and("status").gt(0);
        GroupBy groupBy = GroupBy.key("province", "provinceName").initialDocument("{count:0,male:0,female:0,age0_20:0,age20_40:0,age40_60:0,age60_80:0,age80:0}")
                .reduceFunction("function(doc,prev){  var age = getAge(doc.dateOfBirth);if(doc.gender==0){ prev.male++;}else{prev.female++;}if(age<21){prev.age0_20++;}else if(age>20&&age<41){ prev.age20_40++;}else if(age>40&&age<61){ prev.age40_60++;}else if(age>60&&age<81){prev.age60_80++;}else if (age>80){ prev.age80++;}prev.count++;}");
        List<ProvinceStatistic> content = applicantService.getStaticByProvince(criteria, groupBy);
        if (sortStr.length() > 0) {
            if (sortStr.equals("DESC") || sortStr.equals("ASC")) {
                content.sort(new Comparator<ProvinceStatistic>() {
                    @Override
                    public int compare(ProvinceStatistic o1, ProvinceStatistic o2) {

                        if (sortStr.equals("DESC")) {
                            return o1.getCount() - o2.getCount();
                        } else {
                            return o2.getCount() - o1.getCount();
                        }
                    }
                });
            }
        }
        if (topSize > 0) {
            if (content.size() > topSize) {
                content = content.subList(0, topSize);
            }
        }

        return content;
    }
}
    