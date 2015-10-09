/*
 * Copyright (c) 2015 - 9 - 29  0 : 3 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import w.p.j.daomain.TbUserorder;
import w.p.j.serviceImpl.UserOrderImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WPJ587 on 2015/9/29.
 */
@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger logger = LoggerFactory
            .getLogger(MainController.class);

    @Resource
  private   UserOrderImpl userOrderImpl;
    @Resource
    private CacheManager cacheManager;

    @RequestMapping
    @ResponseBody
    public String index() {
        return "Hello! Spring Boot~";
    }


    @RequestMapping(value = {"/user/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    public TbUserorder findOne(@PathVariable("id") int id) throws Exception {
        TbUserorder tbUserorder=  userOrderImpl.selectByKey(id);
        return tbUserorder;
    }

    @RequestMapping("/velocity")
    public String velocity(TbUserorder tbUserorder,Model model,@RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "3") int rows) {
        List<TbUserorder> user =userOrderImpl.selectByCountry(tbUserorder,page,rows);
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/showtime")
    public String showTime() {
        return "show";
    }
    @RequestMapping("/orderCache/{id}")
    public String detail(@PathVariable("id")int id,ModelMap map){
       TbUserorder tbUserorder= userOrderImpl.selectByKey(id);
        map.addAttribute("order", tbUserorder);
        logger.info("order-----"+tbUserorder.toString());
        return "orderDetail";
    }
    @RequestMapping("/order/{id}")
    public String detailNoCache(@PathVariable("id")int id,ModelMap map){
        TbUserorder tbUserorder= userOrderImpl.selectByKey(id);
        map.addAttribute("order", tbUserorder);
        logger.error("tb--"+tbUserorder);
        return "orderDetail";
    }

}
