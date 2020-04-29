package com.mooc.house.web.controller;

import com.mooc.house.biz.service.UserService;
import com.mooc.house.common.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("hello/name")
    public String hlleo(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();

        User user = users.get(0);
        modelMap.put("user", user);
        return "hello";
    }

    @RequestMapping("/index")
    public String index() {
        return "homepage/index";
    }
}
