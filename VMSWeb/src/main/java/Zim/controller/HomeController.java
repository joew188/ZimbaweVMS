package Zim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
@Controller
public class HomeController {
    //    @Autowired
//    ApplicantService service;
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
//    logger.info("I am programming.");

    //    model.put("title", "title");
       // model.put("msg", "helloWorldService");
//        Applicant user=new Applicant();
//        user.setId("123");
//        user.setSurname("test");
//        user.setGender("femal");
//        service.saveApplicant(user);
        return "Home/index";
    }
}
