/*
 * Copyright (c) 2015 - 10 - 9  5 : 4 :$second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import w.p.j.App;

/**
 * Created by WPJ587 on 2015/10/9.
 * 操作Redis的工具类
 */
@Service
public class RedisTemplateUtils {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public  void test(){
        System.out.println(stringRedisTemplate);
        ValueOperations<String, String> ops =stringRedisTemplate.opsForValue();
        String key = "spring.boot.redis.test";
        if (!stringRedisTemplate.hasKey(key)) {
            ops.set(key, "foo");
        }
        System.out.println("缓存"+ops.get("name"));

        System.out.println("Found key " + key + ", value=" + ops.get(key));
    }

}
