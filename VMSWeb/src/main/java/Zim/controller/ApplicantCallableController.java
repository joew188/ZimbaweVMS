package Zim.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
public class ApplicantCallableController {
    @RequestMapping("CallableApplicant")
    public Callable<List<String>> call(HttpServletRequest req, HttpServletResponse res) throws Exception {
        return new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                List<String> result = new ArrayList<>();
                return result;
            }
        };
    }
}
