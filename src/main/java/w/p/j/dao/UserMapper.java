/*
 * Copyright (c) 2015 - 9 - 29  0 : 0 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.dao;

import org.apache.ibatis.annotations.Param;
import w.p.j.daomian.User;

public interface UserMapper {

	//@Select("select `id`, `name`, `age` from `user` where `id` = #{id}")
	User findById(@Param("id") String id);


	//@Insert("insert into user(`name`, `age`) values(#{name}, #{age})")
	void insert(@Param("name") String name, @Param("age") int age);
}
