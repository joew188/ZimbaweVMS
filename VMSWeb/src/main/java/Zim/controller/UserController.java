package Zim.controller;

import Zim.common.SystemHelper;
import Zim.model.User;
import Zim.model.modelview.*;
import Zim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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
    public SysPagination<User> ApplicantQuery(@RequestBody PagingQuery request) {
        return userService.pageList(request);
    }


    @CrossOrigin
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> add(@RequestBody User user) {
        SysResult<Boolean> result = new SysResult<Boolean>();
        Query query = new Query(Criteria.where("name").is(user.getName()));
        if (userService.getCollectionSize(query, User.class) > 0) {
            result.setResult(false);
            result.setContent(false);
            result.setMessage("Same user name " + user.getName() + " exists ");
            return result;
        }
        try {
            user.setPassword(SystemHelper.AES(user.getPassword()));
            userService.add(user);
            result.setContent(true);
            result.setResult(true);
        } catch (Exception ex) {
            result.setResult(false);
            result.setContent(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> edit(@RequestBody User user) {
        SysResult<Boolean> result = new SysResult<>();
        if (userService.findById(user.get_id()) != null) {

            Query query = new Query(Criteria.where("name").is(user.getName()));
            List<User> nameUsers = userService.getCollection(query, User.class);
            if (nameUsers.size() > 0) {
                for (User findUser : nameUsers) {
                    if (!findUser.get_id().equals(user.get_id())) {
                        result.setResult(false);
                        result.setContent(false);
                        result.setMessage("Same user name " + user.getName() + " exists ");
                        return result;
                    }
                }
            }


            try {
                user.setPassword(SystemHelper.AES(user.getPassword()));
                userService.edit(user);
                result.setContent(true);
                result.setResult(true);
            } catch (NoSuchAlgorithmException e) {
                result.setContent(false);
                result.setResult(false);
            }
        } else {
            result.setContent(false);
            result.setResult(false);
            result.setMessage("user id " + user.get_id() + " not found");
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<Boolean> delete(@RequestParam("userId") String userId) {
        SysResult<Boolean> result = new SysResult<>();
        User user = userService.findById(userId);
        if (user != null) {
            try {
                userService.delete(user);
                result.setContent(true);
                result.setResult(true);
            } catch (Exception e) {
                result.setContent(false);
                result.setResult(false);
            }
        } else {
            result.setContent(false);
            result.setResult(false);
            result.setMessage("user id " + userId + " not found");
        }
        return result;
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
