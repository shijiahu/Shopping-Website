package com.tarena.action.user;

import java.util.regex.Pattern;

import com.tarena.util.ResponseInfo;

public class CheckNicknameAction {
	private String nickname;
	private ResponseInfo responseInfo;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public String extcute(){
		String check = "^[A-Za-z0-9\u4e00-\u9fa5_]{4,20}+$";
		Pattern regex = Pattern.compile(check);
		boolean isNicknameMatched = regex.matcher(nickname).matches();
		responseInfo = new ResponseInfo();
		if (isNicknameMatched) {
			responseInfo.setAccept(true);
			responseInfo.setValue("<img src='../images/label3.gif' alt='正确' />");
			setResponseInfo(responseInfo);
		} else if (nickname.equals("")) {
			responseInfo.setAccept(false);
			responseInfo.setValue("<p>您的昵称可以由小写英文字母、中文、数字组成，</p><p>长度4－20个字符，一个汉字为两个字符。</p>");
			setResponseInfo(responseInfo);
		} else {
			responseInfo.setAccept(false);
			responseInfo.setValue("<span style='color: red'>请输入正确的用户名!</span>");
			setResponseInfo(responseInfo);
		}
		return "success";
	}
}
