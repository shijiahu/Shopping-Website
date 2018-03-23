package com.tarena.action.user;

import java.util.regex.Pattern;


import com.tarena.util.ResponseInfo;

public class CheckPasswordAction {

	private String password;
	private ResponseInfo responseInfo;
	
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

	public String extcute(){
		password = getPassword();
		String check = "^[0-9 | A-Z | a-z]{6,20}+$";
		Pattern regex = Pattern.compile(check);
		boolean isPasswordMatched = regex.matcher(password).matches();
		responseInfo = new ResponseInfo();
		if (isPasswordMatched) {
			responseInfo.setAccept(true);
			responseInfo.setValue("<img src='../images/label3.gif' alt='正确' />");
			setResponseInfo(responseInfo);
		} else if (password.equals("")) {
			responseInfo.setAccept(false);
			responseInfo.setValue("<p>您的密码可以由大小写英文字母、数字组成，长度6－20位。</p>");
			setResponseInfo(responseInfo);
		} else {
			responseInfo.setAccept(false);
			responseInfo.setValue("<span style='color: red'>请输入规范的密码!</span>");
			setResponseInfo(responseInfo);
		}
		return "success";
	}
	
	
}
