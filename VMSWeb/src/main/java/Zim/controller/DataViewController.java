package Zim.controller;

import Zim.common.SystemHelper;
import Zim.model.Applicant;
import Zim.service.ApplicantService;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Laxton-Joe on 2017/7/21.
 */
@Controller
public class DataViewController {
    @Autowired
    ApplicantService applicantService;

    @RequestMapping(value = {"/data/{id}"}, method = RequestMethod.GET)
    public String Index(@PathVariable("id") String id, ModelMap modelMap) {

        modelMap.addAttribute("applicantId", id);
        return "DataView/index";

    }

    @ResponseBody
    @RequestMapping(value = "/applicant/{id}", method = RequestMethod.GET)
    public List<List<String>> delete(@PathVariable("id") String id) {
        List<List<String>> applicant = new ArrayList<>();

        Applicant app = applicantService.find(id);
        DBObject dbObject = app.toDBObject();
        Iterator entries = dbObject.toMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();

            if (key.equals("applicantCompliance")
                    || key.equals("applicantFingerprint")
                    || key.equals("applicantPhoto")
                    || key.equals("applicantDemographic")) {

                addChildWord(key.replace("applicant", ""), (DBObject) entry.getValue(), applicant);
            } else {
                String value = "";
                if (entry.getValue() != null) {
                    if (entry.getValue().getClass().getName().equals("java.util.Date")) {
                        value = SystemHelper.getDateString((Date) entry.getValue());
                    } else {
                        value = String.valueOf(entry.getValue());
                    }
                }
                List<String> content = new ArrayList<>();
                content.add("applicant " + key + ":" + value);
                applicant.add(content);
            }
        }
        return applicant;
    }

    private void addChildWord(String prefix, DBObject dbObject, List<List<String>> container) {

        Iterator entries = dbObject.toMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();


            String value = "";
            if (entry.getValue() != null) {
                if (entry.getValue().getClass().getName() == "java.util.Date") {
                    value = SystemHelper.getDateString((Date) entry.getValue());
                } else {
                    value = String.valueOf(entry.getValue());
                }
            }
            List<String> content = new ArrayList<>();
            content.add(prefix + " " + key + ":" + value);
            container.add(content);

        }
    }

}
