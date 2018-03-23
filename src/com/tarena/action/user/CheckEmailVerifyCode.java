package com.tarena.action.user;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.User;

public class CheckEmailVerifyCode {
	private User user;
	private String uuid;
	private String id;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String extcute() throws Exception{
		UserDAO userDao = new UserDAOImpl();
		user = userDao.findById(id);
		if(uuid.equals(user.getEmailVerifyCode())) {
			user.setEmailVerify(true);
			setUser(user);
			userDao.updateByEmailVerify(user);
			return "success";
		} else {
			return "error";
		}
	}
	
}
