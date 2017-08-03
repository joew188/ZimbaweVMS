package Zim.controller;

import Zim.model.ImportLog;
import Zim.model.modelview.PagingQuery;
import Zim.model.modelview.SysPagination;
import Zim.model.modelview.importLog.ImportByDevice;
import Zim.model.modelview.importLog.ImportByDeviceRecord;
import Zim.service.ApplicantService;
import Zim.service.ImportLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@RestController
public class ImportLogController {
    @Autowired
    ImportLogService importLogService;
    @Autowired
    ApplicantService applicantService;

    @CrossOrigin
    @RequestMapping(value = "/import/list", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<ImportLog> ImportList(@RequestBody PagingQuery request) {
        return importLogService.pageList(request);
    }

    @CrossOrigin
    @RequestMapping(value = "/import/device", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<ImportByDevice> ImportDevice(@RequestBody PagingQuery request) {
        SysPagination<ImportByDevice> result = new SysPagination<>();
        try {
            Criteria criteria = new Criteria();
            for (String key : request.getFilters().keySet()) {
                if (request.getFilters().get(key).length() > 0) {
                    criteria = criteria.and(key).regex(request.getFilters().get(key), "i");
                }
            }
            GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{total:0,male:0,female:0}")
                    .reduceFunction("function(doc,prev){prev.total = prev.total +doc.exportTotal;prev.male = prev.male +doc.exportMale;prev.female = prev.female +doc.exportFemale;}");
            List<ImportByDevice> content = importLogService.getGroupStatic(criteria, groupBy, "ImportLog", ImportByDevice.class);
            String strDeviceName = "";
//            if (request.getFilters() != null) {
//                for (String key : request.getFilters().keySet()) {
//                    if (request.getFilters().get(key).length() > 0) {
//                        if (key.equals("deviceName")) {
//                            strDeviceName = request.getFilters().get(key);
//                        }
//                    }
//                }
//            }
            if (strDeviceName.length() > 0) {
                CharSequence cs = strDeviceName;
                content=   content.stream().filter(c -> c.getDeviceName().contains(cs)).collect(Collectors.toList());
            }
            int count = content.size();
            if (count > 0) {

                if (request.getOrderByName() != null && request.getOrderByName().length() > 0) {
                    if (request.getOrderBy() != null && request.getOrderBy().length() > 0) {
                        if (request.getOrderBy().toLowerCase().equals("desc")) {
                            switch (request.getOrderByName()) {
                                case "deviceName":
                                    content.sort((o1, o2) -> o2.getDeviceName().compareTo(o1.getDeviceName()));
                                    break;
                                case "total":
                                    content.sort((o1, o2) -> o2.getTotal() - o1.getTotal());
                                    break;
                                case "male":
                                    content.sort((o1, o2) -> o2.getMale() - o1.getMale());
                                    break;
                                case "female":
                                    content.sort((o1, o2) -> o2.getFemale() - o1.getFemale());
                                    break;
                            }
                            // content.sort((o1, o2) -> o1.getCount() - o2.getCount());
                        } else {
                            // content.sort((o1, o2) -> o2.getCount() - o1.getCount());
                            switch (request.getOrderByName()) {


                                case "deviceName":
                                    content.sort((o1, o2) -> o1.getDeviceName().compareTo(o2.getDeviceName()));
                                    break;
                                case "total":
                                    content.sort((o1, o2) -> o1.getTotal() - o2.getTotal());
                                    break;
                                case "male":
                                    content.sort((o1, o2) -> o1.getMale() - o2.getMale());
                                    break;
                                case "female":
                                    content.sort((o1, o2) -> o1.getFemale() - o2.getFemale());
                                    break;
                            }
                        }
                    }
                }

                int totalPage = 0;//(int) (count / appQuery.getPageSize());
                if (count % request.getPageSize() == 0) {
                    totalPage = (int) (count / request.getPageSize());
                } else {
                    totalPage = ((int) (count / request.getPageSize()) + 1);
                }
                result.setTotalRecord(count);//总共记录
                result.setTotalPage(totalPage);//总共页数
                result.setPageSize(request.getPageSize());//每页记录
                result.setFilters(request.getFilters());
                int beginIndex = (request.getCurrentPage() - 1) * request.getPageSize();
                int endIndex = beginIndex + request.getPageSize();
                if (endIndex > content.size()) {
                    endIndex = content.size();
                }
                beginIndex = (request.getCurrentPage() - 1) * request.getPageSize();
                List<ImportByDevice> listData = content.subList(beginIndex, endIndex);
                if (listData.size() > 0) {
                    result.setCurrentPage(request.getCurrentPage());//当前页
                    result.setItems(listData);//查询内容
                }
            }
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }

        return result;

    }

    @CrossOrigin
    @RequestMapping(value = "/import/record", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<ImportByDeviceRecord> ImportDeviceRecord(@RequestBody PagingQuery request) {
        SysPagination<ImportByDeviceRecord> result = new SysPagination<>();
        try {
            Criteria criteria = new Criteria();
            Criteria criteria2 = new Criteria();
            for (String key : request.getFilters().keySet()) {
                if (request.getFilters().get(key).length() > 0) {
                    criteria = criteria.and(key).regex(request.getFilters().get(key), "i");
                    criteria2 = criteria2.and(key).regex(request.getFilters().get(key), "i");
                }
            }




            GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{total:0,male:0,female:0}")
                    .reduceFunction("function(doc,prev){prev.total = prev.total +doc.exportTotal;prev.male = prev.male +doc.exportMale;prev.female = prev.female +doc.exportFemale;}");
            List<ImportByDeviceRecord> content = importLogService.getGroupStatic(criteria, groupBy, "ImportLog", ImportByDeviceRecord.class);


            GroupBy groupBy2 = GroupBy.key("deviceName").initialDocument("{recordTotal:0,recordMale:0,recordFemale:0}")
                    .reduceFunction("function(doc,prev){prev.recordTotal++;if(doc.gender==1){prev.recordMale++;}else if (doc.gender==2){prev.recordFemale++;}}");

            List<ImportByDeviceRecord> content2 = applicantService.getGroupStatic(criteria2, groupBy2, "Applicant", ImportByDeviceRecord.class);


            if (request.getOrderByName() != null && request.getOrderByName().length() > 0) {
                if (request.getOrderBy() != null && request.getOrderBy().length() > 0) {
                    if (request.getOrderBy().toLowerCase().equals("desc")) {
                        switch (request.getOrderByName()) {
                            case "deviceName":
                                content.sort((o1, o2) -> o2.getDeviceName().compareTo(o1.getDeviceName()));
                                break;
                            case "total":
                                content.sort((o1, o2) -> o2.getTotal() - o1.getTotal());
                                break;
                            case "male":
                                content.sort((o1, o2) -> o2.getMale() - o1.getMale());
                                break;
                            case "female":
                                content.sort((o1, o2) -> o2.getFemale() - o1.getFemale());
                                break;
                        }
                        // content.sort((o1, o2) -> o1.getCount() - o2.getCount());
                    } else {
                        // content.sort((o1, o2) -> o2.getCount() - o1.getCount());
                        switch (request.getOrderByName()) {


                            case "deviceName":
                                content.sort((o1, o2) -> o1.getDeviceName().compareTo(o2.getDeviceName()));
                                break;
                            case "total":
                                content.sort((o1, o2) -> o1.getTotal() - o2.getTotal());
                                break;
                            case "male":
                                content.sort((o1, o2) -> o1.getMale() - o2.getMale());
                                break;
                            case "female":
                                content.sort((o1, o2) -> o1.getFemale() - o2.getFemale());
                                break;
                        }
                    }
                }
            }




            int count = content.size();
            if (count > 0) {
                int totalPage = 0;//(int) (count / appQuery.getPageSize());
                if (count % request.getPageSize() == 0) {
                    totalPage = (int) (count / request.getPageSize());
                } else {
                    totalPage = ((int) (count / request.getPageSize()) + 1);
                }
                result.setTotalRecord(count);//总共记录
                result.setTotalPage(totalPage);//总共页数
                result.setPageSize(request.getPageSize());//每页记录
                result.setFilters(request.getFilters());
                int beginIndex = (request.getCurrentPage() - 1) * request.getPageSize();
                int endIndex = beginIndex + request.getPageSize();
                if (endIndex > content.size()) {
                    endIndex = content.size();
                }
                beginIndex = (request.getCurrentPage() - 1) * request.getPageSize();
                List<ImportByDeviceRecord> listData = content.subList(beginIndex, endIndex);
                if (listData.size() > 0) {
                    result.setCurrentPage(request.getCurrentPage());//当前页
                    for (ImportByDeviceRecord record : listData) {
                        for (ImportByDeviceRecord applicant : content2) {
                            if (record.getDeviceName().equals(applicant.getDeviceName())) {
                                record.setRecordTotal(applicant.getRecordTotal());
                                record.setRecordMale(applicant.getRecordMale());
                                record.setRecordFemale(applicant.getRecordFemale());
                                record.setTotalDisparity(record.getTotal() - applicant.getRecordTotal());
                                record.setMaleDisparity(record.getMale() - applicant.getRecordMale());
                                record.setFemaleDisparity(record.getFemale() - applicant.getRecordFemale());
                            }
                        }
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
}
