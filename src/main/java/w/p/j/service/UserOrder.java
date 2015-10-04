/*
 * Copyright (c) 2015 - 10 - 1  9 : 7 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.service;

import w.p.j.daomain.TbUserorder;

import java.util.List;

/**
 * Created by WPJ587 on 2015/10/1.
 */
public interface UserOrder extends IService<TbUserorder>{
    /**
     * 根据条件分页查询
     *
     * @param country
     * @param page
     * @param rows
     * @return
     */
    List<TbUserorder> selectByCountry(TbUserorder country, int page, int rows);
}
