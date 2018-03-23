package com.tarena.action.user;

import javax.servlet.http.HttpServletRequest;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.Cart;
import com.tarena.entity.User;
import com.tarena.util.MD5Util;
import com.tarena.util.ResponseInfo;

public class LoginAction {

	private String email;
	private String password;
	private User user;
	private String lastLoginIp;
	private ResponseInfo responseInfo;
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String execute() throws Exception {
		UserDAO userDAO = new UserDAOImpl();
		user = userDAO.findByEmail(email);
		user.setLastLoginTime(System.currentTimeMillis());
		responseInfo = new ResponseInfo();
		if (user != null) {
			if (MD5Util.encode(password).equals(user.getPassword())) {
				responseInfo.setAccept(true);
				responseInfo.setValue("登陆成功!");
				user.setLastLoginIp(lastLoginIp);
				userDAO.updateByLastLoginInfo(user);
				Cart cart = new Cart();
				cart.setUserId(user.getId());
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("cart", cart);
				return "success";
			} else {
				responseInfo.setAccept(false);
				responseInfo.setValue("用户名或密码错误!");
				return "error";
			}
		}else{
			responseInfo.setAccept(false);
			responseInfo.setValue("用户名或密码错误!");
			return "error";
		}

	}
}
