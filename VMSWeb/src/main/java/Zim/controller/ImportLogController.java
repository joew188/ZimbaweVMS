package Zim.controller;

import Zim.model.ImportLog;
import Zim.model.modelview.ApplicantQuery;
import Zim.model.modelview.SysPagination;
import Zim.model.modelview.importLog.ImportByDevice;
import Zim.model.modelview.importLog.ImportGaps;
import Zim.service.ImportLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/12.
 */
@RestController
public class ImportLogController {
    @Autowired
    ImportLogService importLogService;

    @CrossOrigin
    @RequestMapping(value = "/import/list", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<ImportLog> ImportList(@RequestBody ApplicantQuery request) {
        return importLogService.pageList(request);
    }

    @CrossOrigin
    @RequestMapping(value = "/import/device", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<ImportByDevice> ImportDevice(@RequestBody ApplicantQuery request) {
        SysPagination<ImportByDevice> result = new SysPagination<>();
        try {
            Criteria criteria = new Criteria();
            GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{total:0,male:0,female:0}")
                    .reduceFunction("function(doc,prev){prev.total = prev.total +doc.total;prev.male = prev.male +doc.male;prev.female = prev.female +doc.female;}");
            List<ImportByDevice> content = importLogService.getDeviceStatic(criteria, groupBy);
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
    @RequestMapping(value = "/import/gap", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<ImportGaps> ImportGaps(@RequestBody ApplicantQuery request) {
        SysPagination<ImportGaps> result = new SysPagination<>();
        try {
            Criteria criteria = new Criteria();
            GroupBy groupBy = GroupBy.key("deviceName").initialDocument("{gaps:null}")
                    .reduceFunction("function(doc,prev){if(prev.gaps==null){ prev.gaps = doc.gaps;}else{ prev.gaps = prev.gaps.concat(doc.gaps);}}");
            List<ImportGaps> content = importLogService.getGapStatic(criteria, groupBy);
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
                List<ImportGaps> listData = content.subList(beginIndex, endIndex);
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
}
