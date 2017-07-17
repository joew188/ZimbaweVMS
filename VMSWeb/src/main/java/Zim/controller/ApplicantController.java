package Zim.controller;

import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;
import Zim.model.modelview.ApplicantDetails;
import Zim.model.modelview.ApplicantQuery;
import Zim.model.modelview.SysPagination;
import Zim.model.modelview.SysResult;
import Zim.service.ApplicantLogService;
import Zim.service.ApplicantService;
import Zim.service.DuplicateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */
@RestController
public class ApplicantController {

    @Autowired
    ApplicantService applicantService;
    @Autowired
    DuplicateService duplicateService;
    @Autowired
    ApplicantLogService applicantLogService;

    @CrossOrigin
    @RequestMapping(value = "/api/applicant", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<Applicant> ApplicantQuery(@RequestBody ApplicantQuery request) {
        return applicantService.pageList(request);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/applicant/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SysResult<ApplicantDetails> ApplicantQuery(@PathVariable("id") String applicantId) {
        SysResult<ApplicantDetails> result = new SysResult<>();
        try {
            Applicant applicant = applicantService.find(applicantId);

            if (applicant != null) {
                ApplicantDetails details = new ApplicantDetails();
                List<Duplicate> duplicateList = duplicateService.getApplicantDuplicates(applicantId);
                List<ApplicantLog> logList = applicantLogService.getLogList(applicantId);
                details.setApplicant(applicant);
                details.setDuplicates(duplicateList);
                details.setLogs(logList);
                result.setContent(details);
                result.setResult(true);
            } else {
                result.setMessage("not found record!");
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
