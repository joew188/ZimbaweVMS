package Zim.controller;

import Zim.model.Duplicate;
import Zim.model.modelview.DuplicateAction;
import Zim.model.modelview.req.PagingQuery;
import Zim.model.modelview.SysResult;
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
    public PageResponse<Duplicate> ApplicantQuery(@RequestBody PagingQuery query) {
        return duplicateService.pageList(query);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/duplicate/action", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<String> DuplicateAction(@RequestBody DuplicateAction action) {
        SysResult<String> result = new SysResult<String>();
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

    @CrossOrigin
    @RequestMapping(value = "/api/duplicate/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SysResult<Duplicate> DuplicateById(@PathVariable("id") String duplicateId) {
        SysResult<Duplicate> result = new SysResult<>();
        try {
            Duplicate content = duplicateService.find(duplicateId);
            if (content != null) {
                result.setResult(true);
                result.setContent(content);
            } else {
                result.setMessage("not found record!");
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

