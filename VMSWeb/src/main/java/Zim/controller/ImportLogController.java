package Zim.controller;

import Zim.model.ImportLog;
import Zim.model.modelview.importLog.ImportByDevice;
import Zim.model.modelview.importLog.ImportByDeviceRecord;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.PageResponse;
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
    public PageResponse<ImportLog> ImportList(@RequestBody PagingPageQuery request) {
        return importLogService.pageList(request);
    }

    @CrossOrigin
    @RequestMapping(value = "/import/device", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<ImportByDevice> ImportDevice(@RequestBody PagingPageQuery request) {
        return importLogService.groupByDevice(request);
    }

    @CrossOrigin
    @RequestMapping(value = "/import/record", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<ImportByDeviceRecord> ImportDeviceRecord(@RequestBody PagingPageQuery request) {
        return importLogService.groupByDeviceRecord(request);
    }

}
