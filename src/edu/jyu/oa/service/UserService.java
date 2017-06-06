package edu.jyu.oa.service;

import edu.jyu.oa.base.DaoSupport;
import edu.jyu.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * 验证用户名与密码，如果正确就返回这个用户，否则返回null
	 * 
	 * @param loginName
	 * @param password 明文密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
