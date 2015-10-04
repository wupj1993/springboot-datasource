/*
 * Copyright (c) 2015 - 10 - 1  9 : 35 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import w.p.j.serviceImpl.UserOrderImpl;

/**
 * Created by WPJ587 on 2015/10/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class TestUserOrder {
    @Autowired
    UserOrderImpl userOrder;
    @Test
    public void select(){
//        userOrder.findAllUserOrder();

    }
}
