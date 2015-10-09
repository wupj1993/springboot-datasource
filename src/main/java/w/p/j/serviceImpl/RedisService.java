/*
 * Copyright (c) 2015 - 10 - 9  6 : 2 :$second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by WPJ587 on 2015/10/9.
 */
@Service
public class RedisService{
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    ValueOperations<String, String> ops;
    public RedisService() {
        System.out.println("init " + stringRedisTemplate);

    }

    public String getByKey(String key) {
        ops = stringRedisTemplate.opsForValue();
        return ops.get(key);
    }
    public Integer put(String key,String value) {
        ops = stringRedisTemplate.opsForValue();
        int result= ops.append(key,value);
        return result;
    }



}


