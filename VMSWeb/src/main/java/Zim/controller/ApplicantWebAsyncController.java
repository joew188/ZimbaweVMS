package Zim.controller;

import Zim.model.Applicant;
import Zim.service.ApplicantService;
import Zim.service.DuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
@RestController
public class ApplicantWebAsyncController {
    @Autowired
    ApplicantService applicantService;

    @RequestMapping(value = "/WebAsyncApplicant", method = RequestMethod.POST)
    @ResponseBody
    public WebAsyncTask<List<String>> addApplicant(@RequestBody Applicant applicant) {
        Callable<List<String>> callable = new Callable<List<String>>() {
            public List<String> call() throws Exception {
             //   System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
                 List<String> result=applicantService.addApplicant(applicant);
               // List<String> result = new ArrayList<>();
              //  result.add("test");
                return result;
            }
        };
        return new WebAsyncTask(callable);
    }

    @RequestMapping(value = "/longtimetask", method = RequestMethod.GET)
    public WebAsyncTask longTimeTask() {
        System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
        Callable<ModelAndView> callable = new Callable<ModelAndView>() {
            public ModelAndView call() throws Exception {
                Thread.sleep(3000); //假设是一些长时间任务
                ModelAndView mav = new ModelAndView("longtimetask");
                mav.addObject("result", "执行成功");
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
                return mav;
            }
        };
        return new WebAsyncTask(callable);
    }


    @RequestMapping("/custom-timeout-handling")
    @ResponseBody

    public WebAsyncTask<String> callableWithCustomTimeoutHandling() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Callable result";
            }
        };
        return new WebAsyncTask<String>(callable);
    }

}
