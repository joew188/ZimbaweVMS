package Zim.controller;

import Zim.model.modelview.chart.*;
import Zim.service.ApplicantService;
import Zim.service.DuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Laxton-Joe on 2017/6/8.
 */
@RestController
public class ChartController {
    @Autowired
    ApplicantService applicantService;

    @Autowired
    DuplicateService duplicateService;

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/province")
    @ResponseBody
    public List<ApplicantOfProvince> ApplicantOfProvince(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize,
                                                         @RequestParam(value = "sort", required = false, defaultValue = "asc") String sortStr) {
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName").initialDocument("{count:0,distinct:0,duplicate:0}")
                .reduceFunction("function(doc,prev){switch(doc.status){default:prev.duplicate++;break; case 1:prev.distinct++;break;}prev.count++;}");
        List<ApplicantOfProvince> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfProvince.class);
        if (sortStr.toLowerCase().equals("desc")) {
            content.sort((o1, o2) -> o2.getProvinceName().compareTo(o1.getProvinceName()));
        } else {
            content.sort((o1, o2) -> o1.getProvinceName().compareTo(o2.getProvinceName()));
        }

        if (content.size() > topSize && topSize > 0) {
            content = content.subList(0, topSize);
        }

        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/province/avg")
    @ResponseBody
    public List<ApplicantOfProvinceAvg> ApplicantOfProvinceAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg = Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfProvinceAvg> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfProvinceAvg.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }

        if (content.size() > topSize) {
            content = content.subList(0, topSize);
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
    public List<ApplicantOfOperator> ApplicantOfOperator(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                         @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("operatorGuid", "operatorName").initialDocument("{count:0}").reduceFunction("function(doc,prev){prev.count++;}");
        List<ApplicantOfOperator> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfOperator.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }

        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }

        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/operator/avg")
    @ResponseBody
    public List<ApplicantOfOperatorAvg> ApplicantOfOperatorAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("operatorGuid", "operatorName").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg =Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfOperatorAvg> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfOperatorAvg.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }

        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }

        return content;
    }


    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/device")
    @ResponseBody
    public List<ApplicantOfDevice> ApplicantOfDevice(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                     @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{count:0}")
                .reduceFunction("function(doc,prev){prev.count++;}");
        List<ApplicantOfDevice> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfDevice.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }
        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/device/avg")
    @ResponseBody
    public List<ApplicantOfDeviceAvg> ApplicantOfDeviceAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                           @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg = Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfDeviceAvg> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfDeviceAvg.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }
        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }

        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/date")
    @ResponseBody
    public List<ApplicantOfDate> ApplicantOfDate(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                 @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("dateOfRegistration").initialDocument("{count:0}")
                .reduceFunction("function(doc,prev){prev.count++;}");
        List<ApplicantOfDate> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfDate.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }
        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/date/avg")
    @ResponseBody
    public List<ApplicantOfDateAvg> ApplicantOfDateAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                       @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        GroupBy groupBy = GroupBy.key("dateOfRegistration").initialDocument("{count:0,total:0}")
                .reduceFunction("function(doc,prev){prev.total+=Math.ceil((doc.endCreateDatetime.getTime() -doc.beginCreateDatetime.getTime()) / 60000);prev.count++;}")
                .finalizeFunction("function(out){ out.avg = Math.ceil(out.total/out.count*100)/100}");
        List<ApplicantOfDateAvg> content = applicantService.getGroupStatic(groupBy, "Applicant", ApplicantOfDateAvg.class);

        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }
        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/constituency/{districtId}")
    @ResponseBody
    public List<ConstituencyStatistic> StatisticByConstituency(@PathVariable("districtId") int districtId) {
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName", "districtId", "districtName", "constituencyId", "constituencyName").initialDocument("{count:0,male:0,female:0,age18_19:0,age20_24:0,age25_29:0,age30_34:0,age35_39:0,age40_44:0,age45_49:0,age50_54:0,age55_59:0,age60_64:0,age65_69:0,age70_74:0,age75_79:0,age80_84:0,age85_89:0,age90_94:0,age95_99:0,age100:0}")
                .reduceFunction("function(doc,prev){  var age = getAge(doc.dateOfBirth);if(doc.gender==1){ prev.male++;}else{prev.female++;}if(age>17&&age<20){ prev.age18_19++;}else if(age>19&&age<25){ prev.age20_24++;}else if(age>24&&age<30){prev.age25_29++;}else if(age>30&&age<35){prev.age29_34++;}else if(age>34&&age<40){prev.age35_39++;}else if(age>39&&age<45){prev.age40_44++;}else if(age>44&&age<50){prev.age45_49++;}else if(age>49&&age<55){prev.age50_54++;}else if(age>54&&age<60){prev.age55_59++;}else if(age>59&&age<65){prev.age60_64++;}else if(age>64&&age<70){prev.age65_69++;}else if(age>69&&age<75){prev.age70_74++;}else if(age>74&&age<80){prev.age75_79++;}else if(age>79&&age<85){prev.age80_84++;}else if(age>84&&age<90){prev.age85_89++;}else if(age>89&&age<95){prev.age90_94++;}else if(age>94&&age<100){prev.age95_99++;}else if (age>99){ prev.age100++;}prev.count++;}");
        List<ConstituencyStatistic> content = applicantService.getGroupStatic(Criteria.where("districtId").is(districtId), groupBy, "Applicant", ConstituencyStatistic.class);
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/district/{provinceId}")
    @ResponseBody
    public List<DistrictStatistic> StatisticByDistrict(@PathVariable("provinceId") int provinceId) {
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName", "districtId", "districtName").initialDocument("{count:0,male:0,female:0,age18_19:0,age20_24:0,age25_29:0,age30_34:0,age35_39:0,age40_44:0,age45_49:0,age50_54:0,age55_59:0,age60_64:0,age65_69:0,age70_74:0,age75_79:0,age80_84:0,age85_89:0,age90_94:0,age95_99:0,age100:0}")
                .reduceFunction("function(doc,prev){  var age = getAge(doc.dateOfBirth);if(doc.gender==1){ prev.male++;}else{prev.female++;}if(age>17&&age<20){ prev.age18_19++;}else if(age>19&&age<25){ prev.age20_24++;}else if(age>24&&age<30){prev.age25_29++;}else if(age>30&&age<35){prev.age29_34++;}else if(age>34&&age<40){prev.age35_39++;}else if(age>39&&age<45){prev.age40_44++;}else if(age>44&&age<50){prev.age45_49++;}else if(age>49&&age<55){prev.age50_54++;}else if(age>54&&age<60){prev.age55_59++;}else if(age>59&&age<65){prev.age60_64++;}else if(age>64&&age<70){prev.age65_69++;}else if(age>69&&age<75){prev.age70_74++;}else if(age>74&&age<80){prev.age75_79++;}else if(age>79&&age<85){prev.age80_84++;}else if(age>84&&age<90){prev.age85_89++;}else if(age>89&&age<95){prev.age90_94++;}else if(age>94&&age<100){prev.age95_99++;}else if (age>99){ prev.age100++;}prev.count++;}");
        List<DistrictStatistic> content = applicantService.getGroupStatic(Criteria.where("provinceId").is(provinceId), groupBy, "Applicant", DistrictStatistic.class);
        return content;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/province")
    @ResponseBody
    public List<ProvinceStatistic> StatisticByProvince(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize,
                                                       @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {

        GroupBy groupBy = GroupBy.key("provinceId", "provinceName").initialDocument("{count:0,male:0,female:0,age18_19:0,age20_24:0,age25_29:0,age30_34:0,age35_39:0,age40_44:0,age45_49:0,age50_54:0,age55_59:0,age60_64:0,age65_69:0,age70_74:0,age75_79:0,age80_84:0,age85_89:0,age90_94:0,age95_99:0,age100:0}")
                .reduceFunction("function(doc,prev){  var age = getAge(doc.dateOfBirth);if(doc.gender==1){ prev.male++;}else{prev.female++;}if(age>17&&age<20){ prev.age18_19++;}else if(age>19&&age<25){ prev.age20_24++;}else if(age>24&&age<30){prev.age25_29++;}else if(age>30&&age<35){prev.age29_34++;}else if(age>34&&age<40){prev.age35_39++;}else if(age>39&&age<45){prev.age40_44++;}else if(age>44&&age<50){prev.age45_49++;}else if(age>49&&age<55){prev.age50_54++;}else if(age>54&&age<60){prev.age55_59++;}else if(age>59&&age<65){prev.age60_64++;}else if(age>64&&age<70){prev.age65_69++;}else if(age>69&&age<75){prev.age70_74++;}else if(age>74&&age<80){prev.age75_79++;}else if(age>79&&age<85){prev.age80_84++;}else if(age>84&&age<90){prev.age85_89++;}else if(age>89&&age<95){prev.age90_94++;}else if(age>94&&age<100){prev.age95_99++;}else if (age>99){ prev.age100++;}prev.count++;}");
        List<ProvinceStatistic> content = applicantService.getGroupStatic(groupBy, "Applicant", ProvinceStatistic.class);
        if (sortStr.length() > 0) {
            if (sortStr.toLowerCase().equals("desc")) {
                content.sort((o1, o2) -> o2.getCount() - o1.getCount());
            } else {
                content.sort((o1, o2) -> o1.getCount() - o2.getCount());
            }
        }

        if (content.size() > topSize) {
            content = content.subList(0, topSize);
        }

        return content;
    }
}