/*
 * Copyright (c) 2015 - 10 - 7  9 : 39 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.db;

import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by WPJ587 on 2015/10/7.
 */
@Aspect
@Component
public class ServiceMonitor {
    @Pointcut("execution(* w.p.j..*Service.*(..))")
    @AdviceName(value = "transactionManager")
    public void point(){

        System.out.println("pointcut");
    }
    @Before("execution(* w.p.j..*Controller.*(..))")
    public void before(){
        System.out.println("-----前置通知-------");
    }

}
