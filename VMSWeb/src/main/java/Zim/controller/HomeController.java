package Zim.controller;

import Zim.linstener.TestDataImport;
import Zim.service.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
@Controller
public class HomeController {

    @Autowired
    ApplicantService service;
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        // logger.error("I am programming.");

        Marker notifyAdmin = MarkerFactory.getMarker("NOTIFY_ADMIN");
        logger.error(notifyAdmin,
                "This is a serious an error requiring the admin's attention");//,
             //   new Exception("Just testing"));
        //    model.put("title", "title");
        // model.put("msg", "helloWorldService");
//        Applicant user=new Applicant();
//        user.setId("123");
//        user.setSurname("test");
//        user.setGender("femal");
//        service.saveApplicant(user);
        return "Home/index";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public String drop() {
        service.drop();
        return "Home/index";
    }

    @RequestMapping(value = "/service/start/{begin}", method = RequestMethod.GET)
    public String start(@PathVariable("begin") int begin) {

        new Thread(new TestDataImport(begin)).start();

        return "Home/index";
    }

}
