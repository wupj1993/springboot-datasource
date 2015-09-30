/*
 * Copyright (c) 2015 - 9 - 29  0 : 1 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import w.p.j.dao.UserMapper;
import w.p.j.daomian.User;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@Service
public class UserService {

	//	private static Logger log = LoggerFactory.getLogger(StudyService.class);

	@Resource
	private UserMapper userMapper;


	@Async
	public Future<Void> execute() {
		return new AsyncResult<Void>(null);
	}


	@Async
	public Future<User> findOne( String id ) {
		return new AsyncResult<User>(userMapper.findById(id));
	}


	@Async
	public Future<Void> addUser( String name ) {
		userMapper.insert(StringUtils.defaultString(name, "testname"), 10);
		return new AsyncResult<Void>(null);
	}

}
