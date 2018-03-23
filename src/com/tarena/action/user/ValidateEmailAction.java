package com.tarena.action.user;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.User;
import com.tarena.util.ResponseInfo;

public class ValidateEmailAction {
	private String email;
	private ResponseInfo responseInfo;
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}
	public String execute(){
		
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			boolean isEmailMatched = Pattern.compile(check).matcher(email).matches();
			UserDAO userDAO = new UserDAOImpl();
			User user = userDAO.findByEmail(email);
			responseInfo = new ResponseInfo();
			if (user != null) {
				responseInfo.setAccept(false);
				responseInfo.setValue("<span style='color: red'>邮箱被占用!</span>");
				setResponseInfo(responseInfo);
			} else if (isEmailMatched) {
				responseInfo.setAccept(true);
				responseInfo.setValue("<img src='../images/label3.gif' alt='正确' />");
				setResponseInfo(responseInfo);
			} else if (email.equals("")) {
				responseInfo.setAccept(false);
				responseInfo.setValue("<p>请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。</p>");
				setResponseInfo(responseInfo);
			} else if (!email.equals("")) {
				responseInfo.setAccept(false);
				responseInfo.setValue("<span style='color: red'>请输入正确的邮箱！</span>");
				setResponseInfo(responseInfo);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
