/*
 * Copyright (c) 2015 - 10 - 9  5 : 2 :$second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import w.p.j.serviceImpl.RedisService;
import w.p.j.serviceImpl.UserOrderImpl;

/**
 * 测试redis缓存
 */
@Controller
@RequestMapping("/redis")
public class CacheController {
    @Autowired
    RedisService redisService;
    @Autowired
    UserOrderImpl userOrder;
    @RequestMapping("/test")
    public String test() {
        System.out.println(redisService);
        redisService.getByKey("name");
        redisService.put("people",userOrder.selectByKey(1).toString());
        return "index";
    }
}
