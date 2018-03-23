package com.tarena.dao;

import com.tarena.entity.User;

/**
 * 用户类的数据访问对象接口
 */
public interface UserDAO {

	public void save(User user) throws Exception;

	public User findByEmail(String email) throws Exception;

	public User findByUUID(String uuid) throws Exception;
	
	public User findById(String id) throws Exception;
	public User updateByEmailVerify(User user) throws Exception;
	public User updateByLastLoginInfo(User user) throws Exception;
}
