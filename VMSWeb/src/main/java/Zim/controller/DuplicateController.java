package Zim.controller;

import Zim.model.Duplicate;
import Zim.model.modelview.DuplicateAction;
import Zim.model.modelview.req.PagingOneQuery;

import Zim.model.modelview.SysResult;
import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.EntityResponse;
import Zim.model.modelview.res.PageResponse;
import Zim.service.DuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */

@RestController
public class DuplicateController {
    @Autowired
    DuplicateService duplicateService;

    @CrossOrigin
    @RequestMapping(value = "/api/duplicate", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<Duplicate> ApplicantQuery(@RequestBody PagingPageQuery query) {
        return duplicateService.pageList(query);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/duplicate/find", method = RequestMethod.POST)
    @ResponseBody
    public EntityResponse<Duplicate> ApplicantQuery(@RequestBody PagingOneQuery pagingOneQuery) {
        EntityResponse<Duplicate> result = new EntityResponse<>();
        try {
            Duplicate duplicate = duplicateService.pageFineOne(result, pagingOneQuery);
            if (duplicate != null) {
                result.setEntity(duplicate);
                result.setResult(true);
            } else {
                result.setResult(false);
            }
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/duplicate/action", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<String> DuplicateAction(@RequestBody DuplicateAction action) {
        SysResult<String> result = new SysResult<>();
        try {
            String actionResult = duplicateService.action(action);
            if (actionResult.length() > 0) {
                result.setMessage(actionResult);
            } else {
                result.setResult(true);
                String nextId = duplicateService.getNextDuplicate();
                result.setMessage(nextId);
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }

//    @CrossOrigin
//    @RequestMapping(value = "/api/duplicate/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public SysResult<Duplicate> DuplicateById(@PathVariable("id") String duplicateId) {
//        SysResult<Duplicate> result = new SysResult<>();
//        try {
//            Duplicate content = duplicateService.find(duplicateId);
//            if (content != null) {
//                result.setResult(true);
//                result.setContent(content);
//            } else {
//                result.setMessage("not found record!");
//            }
//        } catch (Exception e) {
//            result.setMessage(e.getMessage());
//        }
//        return result;
//    }
}

