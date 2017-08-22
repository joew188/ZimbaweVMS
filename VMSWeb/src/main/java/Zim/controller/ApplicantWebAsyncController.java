package Zim.controller;

import Zim.model.Applicant;
import Zim.model.Area;
import Zim.model.modelview.ApplicantMatchResult;
import Zim.model.modelview.SysResult;
import Zim.service.ApplicantService;
import Zim.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
@RestController
public class ApplicantWebAsyncController {
    @Autowired
    ApplicantService applicantService;

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "/WebAsyncApplicant", method = RequestMethod.POST)
    @ResponseBody
    public WebAsyncTask<List<String>> addApplicant(@RequestBody Applicant applicant) {
        Callable<List<String>> callable = new Callable<List<String>>() {
            public List<String> call() throws Exception {

                List<String> result = applicantService.addApplicant(applicant);
                // List<String> result = new ArrayList<>();
                //  result.add("test");
                return result;
            }
        };
        return new WebAsyncTask(callable);
    }

    @RequestMapping(value = "/longtimetask", method = RequestMethod.POST)
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


    @RequestMapping(value = "/WebAsyncApplicant/Add", method = RequestMethod.POST)
    @ResponseBody

    public WebAsyncTask<SysResult<ApplicantMatchResult>> addSimplyApplicant(HttpServletRequest request) {
        System.out.println(request.getParameter("id"));
        Callable<SysResult<ApplicantMatchResult>> callable = new Callable<SysResult<ApplicantMatchResult>>() {
            public SysResult<ApplicantMatchResult> call() throws Exception {
                Set<String> setKeys = request.getParameterMap().keySet();
                SysResult<ApplicantMatchResult> result = new SysResult<ApplicantMatchResult>();
                try {
                    if (setKeys.contains("id") && setKeys.contains("name") && setKeys.contains("birth")) {
                        ApplicantMatchResult content = new ApplicantMatchResult();

                        Applicant applicant = new Applicant();
                        String name = request.getParameter("name");
                        String id = request.getParameter("id");
                        String birth = request.getParameter("birth");
                        String gender = request.getParameter("gender");
//                        applicant.setSurname(name);
                        applicant.set_id(id);
                        applicant.setDateOfBirth(Integer.parseInt(birth));
                        //   applicant.setGender(Integer.parseInt(gender));
                        List<String> listMatched = applicantService.addApplicant(applicant);

                        content.set_id(id);
                        if (listMatched.size() > 0) {
                            content.setValue(listMatched);
                        }
                        result.setContent(content);
                        result.setResult(true);
                    } else {
                        result.setResult(false);
                        result.setMessage("Invalid parameter!");
                    }

                } catch (Exception e) {
                    result.setResult(false);
                    result.setMessage(e.getMessage());
                }

                return result;
            }
        };
        //  return new WebAsyncTask(callable);
        WebAsyncTask asyncTask = new WebAsyncTask(10000, callable);
        asyncTask.onTimeout(
                new Callable<SysResult<ApplicantMatchResult>>() {
                    public SysResult<ApplicantMatchResult> call() throws Exception {
                        SysResult<ApplicantMatchResult> result = new SysResult<ApplicantMatchResult>();
                        result.setMessage("time out");
                        result.setResult(false);
                        return result;
                    }
                }
        );
        return asyncTask;
    }

    @ResponseBody
    @RequestMapping(value = "/WebAsyncApplicant/New", method = RequestMethod.POST)
    public DeferredResult<SysResult<ApplicantMatchResult>> deferredAddSimplyApplicant(HttpServletRequest request) {

        Applicant applicant = new Applicant();
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String birth = request.getParameter("birth");
        String gender = request.getParameter("gender");
//        applicant.setSurname(name);
        applicant.set_id(id);
        applicant.setDateOfBirth(Integer.parseInt(birth));
        //  applicant.setGender(Integer.parseInt(gender));


        DeferredResult<SysResult<ApplicantMatchResult>> deferredResult = new DeferredResult<SysResult<ApplicantMatchResult>>(10000L);


        LongTimeAsyncCallService longTimeAsyncCallService = new LongTimeAsyncCallService(applicant);
        longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
            @Override
            public void callback(List<String> result) {
                SysResult<ApplicantMatchResult> matchResult = new SysResult<ApplicantMatchResult>();
                ApplicantMatchResult content = new ApplicantMatchResult();
                content.set_id(id);
                if (result.size() > 0) {
                    content.setValue(result);
                }
                matchResult.setContent(content);
                matchResult.setResult(true);
                deferredResult.setResult(matchResult);
            }

        });

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                SysResult<ApplicantMatchResult> timeoutResult = new SysResult<ApplicantMatchResult>();
                timeoutResult.setMessage("time out");
                timeoutResult.setResult(false);

                deferredResult.setResult(timeoutResult);
            }
        });

        return deferredResult;
    }

    @ResponseBody
    @RequestMapping(value = "/WebAsync/Area", method = RequestMethod.POST)
    public DeferredResult<SysResult<String>> importArea(HttpServletRequest request) {

        Area area = new Area();
        String areaId = request.getParameter("areaId");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String streetName = request.getParameter("streetName");
        String standNumber = request.getParameter("standNumber");
        String streetNumber = request.getParameter("streetNumber");
        String surburb = request.getParameter("surburb");
        String stationCode = request.getParameter("stationCode");
        String localAuthority = request.getParameter("localAuthority");
        String parentAreaID = request.getParameter("parentAreaID");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");


        area.setAreaId(Integer.valueOf(areaId));
        area.setName(String.valueOf(name));
        area.setType(Integer.valueOf(type));
        if (streetName.length() > 0)
            area.setStreetName(streetName);
        else
            area.setStreetName(null);

        if (standNumber.length() > 0)
            area.setStandNumber(standNumber);
        else
            area.setStandNumber(null);

        if (streetNumber.length() > 0)
            area.setStreetNumber(streetNumber);
        else
            area.setStreetNumber(null);

        if (surburb.length() > 0)
            area.setSurburb(surburb);
        else
            area.setSurburb(null);

        if (stationCode.length() > 0)
            area.setStationCode(stationCode);
        else
            area.setStationCode(null);

        if (localAuthority.length() > 0)
            area.setLocalAuthority(localAuthority);
        else
            area.setLocalAuthority(null);


        area.setParentAreaID(Integer.valueOf(parentAreaID));

        if (lat.length() > 0)
            area.setLat(lat);
        else
            area.setLat(null);

        if (lng.length() > 0)
            area.setLng(lng);
        else
            area.setLng(null);


        DeferredResult<SysResult<String>> deferredResult = new DeferredResult<SysResult<String>>(10000L);


        InsertAreaAsyncCallService asyncCallService = new InsertAreaAsyncCallService(area);
        asyncCallService.makeRemoteCallAndUnknownWhenFinish(new InsertAreaCallback() {
            @Override
            public void callback(String result) {
                SysResult<String> matchResult = new SysResult<String>();

                matchResult.setContent(name);
                matchResult.setResult(true);
                deferredResult.setResult(matchResult);
            }

        });

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                SysResult<String> timeoutResult = new SysResult<String>();
                timeoutResult.setMessage("time out");
                timeoutResult.setResult(false);

                deferredResult.setResult(timeoutResult);
            }
        });

        return deferredResult;
    }


    public interface LongTermTaskCallback {
        void callback(List<String> result);
    }

    public class LongTimeAsyncCallService {
        private final int CorePoolSize = 4;
        private final int NeedSeconds = 3;
        private final Applicant applicant;

        private Random random = new Random();
        private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);

        public LongTimeAsyncCallService(Applicant applicant) {
            this.applicant = applicant;
        }

        public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback task) {

            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    List<String> listMatched = applicantService.addApplicant(applicant);
                    task.callback(listMatched);
                }
            }, 0, TimeUnit.SECONDS);
        }
    }

    public interface InsertAreaCallback {
        void callback(String result);
    }

    public class InsertAreaAsyncCallService {
        private final int CorePoolSize = 4;
        private final int NeedSeconds = 3;
        private final Area area;

        private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);

        public InsertAreaAsyncCallService(Area area) {
            this.area = area;
        }

        public void makeRemoteCallAndUnknownWhenFinish(InsertAreaCallback task) {

            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    String msg = areaService.addArea(area);
                    task.callback(msg);
                }
            }, 0, TimeUnit.SECONDS);
        }
    }
}
