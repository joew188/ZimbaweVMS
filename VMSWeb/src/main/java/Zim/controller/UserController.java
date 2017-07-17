package Zim.controller;

import Zim.common.SystemHelper;
import Zim.model.User;
import Zim.model.modelview.*;
import Zim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/7/7.
 */

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    @ResponseBody
    public SysPagination<User> ApplicantQuery(@RequestBody ApplicantQuery request) {
        return userService.pageList(request);
    }


    @CrossOrigin
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> add(@RequestBody User user) {
        SysResult<Boolean> result = new SysResult<Boolean>();
        try {
            user.setPassword(SystemHelper.AES(user.getPassword()));
            userService.add(user);
            result.setContent(true);
            result.setResult(true);
        } catch (Exception ex) {
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> edit(@RequestBody User user) {
        SysResult<Boolean> result = new SysResult<>();
        result.setContent(false);
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> delete(@RequestBody SysQuery query) {
        SysResult<Boolean> result = new SysResult<>();
        result.setContent(false);
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<User> get(@RequestParam(value = "name", required = true) String name,
                               @RequestParam(value = "pwd", required = true) String pwd) {
        SysResult<User> result = new SysResult<>();
        try {
            String pwdAes = SystemHelper.AES(pwd);
            List<User> userList = userService.get(name, pwdAes);
            if (userList.size() > 0) {
                result.setContent(userList.get(0));
                result.setResult(true);
            } else {
                result.setResult(false);
            }
        } catch (Exception ex) {
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }

        return result;
    }

}
