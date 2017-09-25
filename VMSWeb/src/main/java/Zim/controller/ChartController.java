package Zim.controller;

import Zim.common.CustomerAggregationOperation;
import Zim.common.SystemHelper;
import Zim.model.modelview.chart.*;
import Zim.service.ChartService;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/6/8.
 */
@RestController
public class ChartController {
    @Autowired
    ChartService chartService;

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/province")
    @ResponseBody
    public List ApplicantOfProvince(@RequestParam(value = "top", required = false, defaultValue = "0") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "asc") String sortStr) {
        List<AggregationOperation> listOperations = new ArrayList<>();
        listOperations.add(Aggregation.match(Criteria.where("status").gt(0)));
        listOperations.add(Aggregation.project("provinceId", "provinceName"));
        listOperations.add(Aggregation.group("provinceId", "provinceName").count().as("count"));
        listOperations.add(Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"));
        if (topSize > 0) {
            listOperations.add(Aggregation.limit(topSize));
        }
        Aggregation aggregation = Aggregation.newAggregation(listOperations);
        return chartService.getAggregation(aggregation, ApplicantOfProvince.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/province/avg")
    @ResponseBody
    public List ApplicantOfProvinceAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("provinceId", "provinceName").andExpression("(endCreateDatetime-beginCreateDatetime)/60000").as("total"),
                Aggregation.group("provinceId", "provinceName").count().as("count").sum("total").as("total").avg("total").as("avg"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfProvinceAvg.class);
    }


    /**
     * 查找录入居民数量最多的前10名操作员，或者录入数量最少的后10名操作员
     *
     * @param topSize record size
     * @param sortStr desc|asc
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/operator")
    @ResponseBody
    public List ApplicantOfOperator(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("operatorGuid", "operatorName"),
                Aggregation.group("operatorGuid", "operatorName").count().as("count"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfOperator.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/operator/avg")
    @ResponseBody
    public List ApplicantOfOperatorAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("operatorGuid", "operatorName").andExpression("(endCreateDatetime-beginCreateDatetime)/60000").as("total"),
                Aggregation.group("operatorGuid", "operatorName").count().as("count").sum("total").as("total").avg("total").as("avg"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfOperatorAvg.class);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/device")
    @ResponseBody
    public List ApplicantOfDevice(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("deviceName"),
                Aggregation.group("deviceName").count().as("count"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfDevice.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/device/avg")
    @ResponseBody
    public List ApplicantOfDeviceAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("deviceName").andExpression("(endCreateDatetime-beginCreateDatetime)/60000").as("total"),
                Aggregation.group("deviceName").count().as("count").sum("total").as("total").avg("total").as("avg"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfDeviceAvg.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/date")
    @ResponseBody
    public List<ApplicantOfDate> ApplicantOfDate(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("dateOfRegistration"),
                Aggregation.group("dateOfRegistration").count().as("count"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfDate.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/applicant/date/avg")
    @ResponseBody
    public List ApplicantOfDateAvg(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").gt(0)),
                Aggregation.project("dateOfRegistration").andExpression("(endCreateDatetime-beginCreateDatetime)/60000").as("total"),
                Aggregation.group("dateOfRegistration").count().as("count").sum("total").as("total").avg("total").as("avg"),
                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
                Aggregation.limit(topSize));
        return chartService.getAggregation(aggregation, ApplicantOfDateAvg.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/constituency/{districtId}")
    @ResponseBody
    public List<ConstituencyStatistic> StatisticByConstituency(@PathVariable("districtId") int districtId) {
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName", "districtId", "districtName", "constituencyId", "constituencyName")
                .initialDocument("{count:0,male:0,female:0,age18_19:0,age20_24:0,age25_29:0,age30_34:0,age35_39:0,age40_44:0,age45_49:0,age50_54:0,age55_59:0,age60_64:0,age65_69:0,age70_74:0,age75_79:0,age80_84:0,age85_89:0,age90_94:0,age95_99:0,age100:0}")
                .reduceFunction("function(doc,prev){var ageStage = getAgeStage(doc.dateOfBirth);if(doc.gender==1){ prev.male++;}else{prev.female++;}switch(ageStage){ case 'age18_19': prev.age18_19++; break; case 'age20_24': prev.age20_24++; break; case 'age25_29': prev.age25_29++; break; case 'age29_34': prev.age29_34++; break; case 'age35_39': prev.age35_39++; break; case 'age40_44': prev.age40_44++; break; case 'age45_49': prev.age45_49++; break; case 'age50_54': prev.age50_54++; break; case 'age55_59': prev.age55_59++; break; case 'age60_64': prev.age60_64++; break; case 'age65_69': prev.age65_69++; break; case 'age70_74': prev.age70_74++; break; case 'age75_79': prev.age75_79++; break; case 'age80_84': prev.age80_84++; break; case 'age85_89': prev.age85_89++; break; case 'age90_94': prev.age90_94++; break; case 'age95_99': prev.age95_99++; break;case 'age100': prev.age100++; break;}prev.count++;}");
        return chartService.getGroupStatic(Criteria.where("status").gt(0).and("districtId").is(districtId), groupBy, "ApplicantMaster", ConstituencyStatistic.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/district/{provinceId}")
    @ResponseBody
    public List<DistrictStatistic> StatisticByDistrict(@PathVariable("provinceId") int provinceId) {
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName", "districtId", "districtName")
                .initialDocument("{count:0,male:0,female:0,age18_19:0,age20_24:0,age25_29:0,age30_34:0,age35_39:0,age40_44:0,age45_49:0,age50_54:0,age55_59:0,age60_64:0,age65_69:0,age70_74:0,age75_79:0,age80_84:0,age85_89:0,age90_94:0,age95_99:0,age100:0}")
                .reduceFunction("function(doc,prev){var ageStage = getAgeStage(doc.dateOfBirth);if(doc.gender==1){ prev.male++;}else{prev.female++;}switch(ageStage){ case 'age18_19': prev.age18_19++; break; case 'age20_24': prev.age20_24++; break; case 'age25_29': prev.age25_29++; break; case 'age29_34': prev.age29_34++; break; case 'age35_39': prev.age35_39++; break; case 'age40_44': prev.age40_44++; break; case 'age45_49': prev.age45_49++; break; case 'age50_54': prev.age50_54++; break; case 'age55_59': prev.age55_59++; break; case 'age60_64': prev.age60_64++; break; case 'age65_69': prev.age65_69++; break; case 'age70_74': prev.age70_74++; break; case 'age75_79': prev.age75_79++; break; case 'age80_84': prev.age80_84++; break; case 'age85_89': prev.age85_89++; break; case 'age90_94': prev.age90_94++; break; case 'age95_99': prev.age95_99++; break;case 'age100': prev.age100++; break;}prev.count++;}");
        return chartService.getGroupStatic(Criteria.where("status").gt(0).and("provinceId").is(provinceId), groupBy, "ApplicantMaster", DistrictStatistic.class);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/statistics/province")
    @ResponseBody
    public List StatisticByProvince(@RequestParam(value = "top", required = false, defaultValue = "10") int topSize, @RequestParam(value = "sort", required = false, defaultValue = "desc") String sortStr) {
//        int dateNow = SystemHelper.DateToInt(new Date());
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.match(Criteria.where("status").gt(0)),
//                Aggregation.project("provinceId", "provinceName").andExpression("([0]-dateOfBirth)/10000", dateNow).as("age"),
//                Aggregation.group("provinceId", "provinceName").count().as("count"),
//                Aggregation.sort(SystemHelper.getSortDirection(sortStr), "count"),
//                Aggregation.limit(topSize));
//        List<ProvinceStatistic> contentTest = chartService.getAggregation(aggregation, ProvinceStatistic.class);
        GroupBy groupBy = GroupBy.key("provinceId", "provinceName").initialDocument("{count:0,male:0,female:0,age18_19:0,age20_24:0,age25_29:0,age30_34:0,age35_39:0,age40_44:0,age45_49:0,age50_54:0,age55_59:0,age60_64:0,age65_69:0,age70_74:0,age75_79:0,age80_84:0,age85_89:0,age90_94:0,age95_99:0,age100:0}")
                .reduceFunction("function(doc,prev){var ageStage = getAgeStage(doc.dateOfBirth);if(doc.gender==1){ prev.male++;}else{prev.female++;}switch(ageStage){ case 'age18_19': prev.age18_19++; break; case 'age20_24': prev.age20_24++; break; case 'age25_29': prev.age25_29++; break; case 'age29_34': prev.age29_34++; break; case 'age35_39': prev.age35_39++; break; case 'age40_44': prev.age40_44++; break; case 'age45_49': prev.age45_49++; break; case 'age50_54': prev.age50_54++; break; case 'age55_59': prev.age55_59++; break; case 'age60_64': prev.age60_64++; break; case 'age65_69': prev.age65_69++; break; case 'age70_74': prev.age70_74++; break; case 'age75_79': prev.age75_79++; break; case 'age80_84': prev.age80_84++; break; case 'age85_89': prev.age85_89++; break; case 'age90_94': prev.age90_94++; break; case 'age95_99': prev.age95_99++; break;case 'age100': prev.age100++; break;}prev.count++;}");
        List<ProvinceStatistic> content = chartService.getGroupStatic(Criteria.where("status").gt(0), groupBy, "ApplicantMaster", ProvinceStatistic.class);
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