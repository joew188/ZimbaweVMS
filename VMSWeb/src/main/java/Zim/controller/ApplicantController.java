package Zim.controller;

import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.ApplicantMaster;
import Zim.model.Duplicate;
import Zim.model.modelview.*;
import Zim.model.modelview.importLog.ImportGaps;
import Zim.model.modelview.req.PagingOneQuery;

import Zim.model.modelview.req.PagingPageQuery;
import Zim.model.modelview.res.EntityResponse;
import Zim.model.modelview.res.PageResponse;
import Zim.service.ApplicantLogService;
import Zim.service.ApplicantService;
import Zim.service.DuplicateService;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/2/17.
 */
@RestController
public class ApplicantController {
    private static Logger logger = LoggerFactory.getLogger(ApplicantController.class);
    @Autowired
    ApplicantService applicantService;
    @Autowired
    DuplicateService duplicateService;
    @Autowired
    ApplicantLogService applicantLogService;

    @CrossOrigin
    @RequestMapping(value = "/api/applicant", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<ApplicantMaster> listQuery(@RequestBody PagingPageQuery request) {
        return applicantService.pageList(request);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/applicant/pdf/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public List<ApplicantSimple> pdfListQuery(@PathVariable("pid") int pid) {
        return applicantService.pagePdfList(pid);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/applicant/find", method = RequestMethod.POST)
    @ResponseBody
    public EntityResponse<ApplicantDetails> findOneQuery(@RequestBody PagingOneQuery request) {
        EntityResponse<ApplicantDetails> result = new EntityResponse<>();
        try {
            Applicant applicant = applicantService.pageFineOne(result, request);
            if (applicant != null) {
                ApplicantDetails applicantDetails = new ApplicantDetails();
                List<Duplicate> duplicateList = duplicateService.getApplicantDuplicates(applicant.get_id());
                List<ApplicantLog> logList = applicantLogService.getLogList(applicant.get_id());
                applicantDetails.setApplicant(applicant);
                applicantDetails.setDuplicates(duplicateList);
                applicantDetails.setLogs(logList);
                result.setEntity(applicantDetails);
                result.setResult(true);
            }
        } catch (Exception e) {
            logger.error("/api/applicant/find," + e.toString());
            result.setResult(false);
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/photo/find", method = RequestMethod.GET)
    public void photoFind(HttpServletResponse response, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "col", required = true) String col) {
        try {
            byte[] result = applicantService.photoFind(id, col);
            byte[] decodeBase64 = Base64.decode(result);
            ByteArrayInputStream input = new ByteArrayInputStream(decodeBase64);
            BufferedImage imageF = ImageIO.read(input);
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(imageF, "png", os);
        } catch (Exception e) {

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/api/chart/gap", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<ImportGaps> gapQuery(@RequestBody PagingPageQuery request) {
        return applicantService.getApplicantGap(request);
    }

//    @CrossOrigin
//    @RequestMapping(value = "/api/applicant/pdf", method = RequestMethod.POST)
//    @ResponseBody
//    public PageResponse<ApplicantSimple> ApplicantSimpleQuery(@RequestBody PagingOrNotQuery request) {
//        return applicantService.pagePdfList(request);
//    }
//
//    @CrossOrigin
//    @RequestMapping(value = "/api/applicant/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public SysResult<ApplicantDetails> ApplicantQuery(@PathVariable("id") String applicantId) {
//        SysResult<ApplicantDetails> result = new SysResult<>();
//        try {
//            Applicant applicant = applicantService.findApplicant(applicantId);
//
//            if (applicant != null) {
//                ApplicantDetails details = new ApplicantDetails();
//                List<Duplicate> duplicateList = duplicateService.getApplicantDuplicates(applicantId);
//                List<ApplicantLog> logList = applicantLogService.getLogList(applicantId);
//                details.setApplicant(applicant);
//                details.setDuplicates(duplicateList);
//                details.setLogs(logList);
//                result.setContent(details);
//                result.setResult(true);
//            } else {
//                result.setMessage("not found record!");
//            }
//        } catch (Exception e) {
//            result.setMessage(e.getMessage());
//        }
//        return result;
//    }
}
