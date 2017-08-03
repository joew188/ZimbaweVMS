package Zim.controller;

import Zim.model.UploadHistory;
import Zim.model.User;
import Zim.model.modelview.PagingQuery;
import Zim.model.modelview.SysPagination;
import Zim.model.modelview.SysResult;
import Zim.service.UploadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/7/25.
 */
@RestController
public class UploadHistoryController {
    @Autowired
    UploadHistoryService uploadHistoryService;

    @CrossOrigin
    @RequestMapping(value = "/upload/list", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<UploadHistory> ApplicantQuery(@RequestBody PagingQuery request) {
        return uploadHistoryService.pageList(request);
    }


    @CrossOrigin
    @RequestMapping(value = "/upload/add", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> add
            (@RequestParam(value = "name", required = true) String name,
             @RequestParam(value = "fileSize", required = true) String fileSize,
             @RequestParam(value = "userId", required = true) String userId,
             @RequestParam(value = "userName", required = true) String userName) {
        SysResult<Boolean> result = new SysResult<Boolean>();
        Query query = new Query(Criteria.where("name").is(name));
        if (uploadHistoryService.getCollectionSize(query, UploadHistory.class) > 0) {
            result.setResult(false);
            result.setContent(false);
            result.setMessage("Same file name " + name + " exists ");
            return result;
        }
        try {

            UploadHistory uh = new UploadHistory();
            uh.setName(name);
            uh.setFileSize(fileSize);
            uh.setUploadTime(new Date());
            uh.setUserId(userId);
            uh.setUserName(userName);
            uploadHistoryService.add(uh);
            result.setContent(true);
            result.setResult(true);
        } catch (Exception ex) {
            result.setResult(false);
            result.setContent(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

}
