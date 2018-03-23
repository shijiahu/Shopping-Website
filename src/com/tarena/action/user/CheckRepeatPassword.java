package com.tarena.action.user;


import com.tarena.util.ResponseInfo;

public class CheckRepeatPassword {
	private String password;
	private String repeatPassword;
	private ResponseInfo responseInfo;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public String extcute(){
		boolean isPasswordEquals = password.equals(repeatPassword);
		responseInfo = new ResponseInfo();
		if (isPasswordEquals && !password.equals("")) {
			responseInfo.setAccept(true);
			responseInfo.setValue("<img src='../images/label3.gif' alt='正确' />");
			setResponseInfo(responseInfo);
		} else if (repeatPassword.equals("")) {
			responseInfo.setAccept(false);
			responseInfo.setValue("<p>请再次输入您的密码。</p>");
			setResponseInfo(responseInfo);
		} else {
			responseInfo.setAccept(false);
			responseInfo.setValue("<span style='color: red'>两次输入的密码不一致!</span>");
			setResponseInfo(responseInfo);
		}
		return "success";
	}
	
}
