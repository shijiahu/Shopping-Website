package com.tarena.action.user;


import com.tarena.util.ResponseInfo;

public class CheckVerifyCode {
	private String txtVerifyCode;
	private String verifyCode;
	private ResponseInfo responseInfo;
	public String getTxtVerifyCode() {
		return txtVerifyCode;
	}
	public void setTxtVerifyCode(String txtVerifyCode) {
		this.txtVerifyCode = txtVerifyCode;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public String extcute(){
		boolean isVerifyCodeEquals = txtVerifyCode
				.equalsIgnoreCase(verifyCode);
		responseInfo = new ResponseInfo();
		if (isVerifyCodeEquals && !txtVerifyCode.equals("")) {
			responseInfo.setAccept(true);
			responseInfo.setValue("<img src='../images/label3.gif' alt='正确' />");
			setResponseInfo(responseInfo);
		} else if (txtVerifyCode.equals("")) {
			responseInfo.setAccept(false);
			responseInfo.setValue("<span id='vcodeValidMsg'>请输入图片中的四个字母。</span>");
			setResponseInfo(responseInfo);
		} else {
			responseInfo.setAccept(false);
			responseInfo.setValue("<span style='color: red'>请输入正确的验证码！</span>");
			setResponseInfo(responseInfo);
		}
		return "success";
	}
}
