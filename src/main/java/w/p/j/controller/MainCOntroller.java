/*
 * Copyright (c) 2015 - 9 - 29  0 : 3 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import w.p.j.daomian.User;
import w.p.j.service.UserService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by WPJ587 on 2015/9/29.
 */
@Controller
@RequestMapping("/")
public class MainCOntroller {


    @Autowired
    UserService userService;


    @RequestMapping
    @ResponseBody
    public String index() {
        return "Hello! Spring Boot~";
    }


    @RequestMapping(value = {"/user/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    public User findOne(@PathVariable("id") String id) throws Exception {
        Future<User> res = userService.findOne(id);
        return res.get(1000, TimeUnit.MILLISECONDS);
    }


    @RequestMapping(value = {"/user"}, method = {RequestMethod.GET})
    @ResponseBody
    public String register(@RequestParam("name") String name) {
        userService.addUser(name);
        return "success";
    }


    @RequestMapping("/velocity")
    public String velocity(Model model) {
        Future<User> res = userService.findOne(1 + "");
        User user = new User();
        System.out.println("res--->" + res);
        try {
            System.out.println("res--->" + res.get().getAge());

            user.setAge(res.get().getAge());
            user.setId(res.get().getId());
            user.setName(res.get().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/showtime")
    public String showTime() {
        return "show";
    }

}
