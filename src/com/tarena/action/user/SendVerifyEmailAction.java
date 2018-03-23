package com.tarena.action.user;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.User;
import com.tarena.util.EmailUtil;
import com.tarena.util.ResponseInfo;

public class SendVerifyEmailAction {

	private String email;
	private User user;
	private ResponseInfo responseInfo;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}

	public String execute() throws Exception {
		UserDAO userDao = new UserDAOImpl();
		user = userDao.findByEmail(email);
		responseInfo = new ResponseInfo();
		if (user != null) {
			try {
				EmailUtil.sendEmail(user);
				responseInfo.setAccept(true);
				responseInfo.setValue("发送成功！");
				return "success";
			} catch (Exception e) {
				responseInfo.setAccept(false);
				responseInfo.setValue("发送失败，请稍后再试！");
				return "error";
			}
		} else {
			responseInfo.setAccept(false);
			responseInfo.setValue("发送失败，请稍后再试！");
			return "error";
		}
	}

}
