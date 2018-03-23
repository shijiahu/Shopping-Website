package com.tarena.entity;

/*
 * 实体用户类(实体,POJO,Javabean)
 * 和库中d_user表相对应
 * 表明:d_user				类名:User
 * 字段:							属性/成员变量:
 * id								id
 * email							email
 * nickname					nickname
 * password					password
 * user_integral				userIntegral
 * email_verify_code		emailVerifyCode
 * is_email_verify			isEmailVerify
 * last_login_time			lastLoginTime
 * last_login_ip				lastLoginIp
 */
public class User implements java.io.Serializable{
	//序列化版本id
	private static final long serialVersionUID = -8933968130590874432L;
	
	//属性/成员变量
	private int id;
	private String email;
	private String nickname;
	private String password;
	private  int userIntegral;
	private String emailVerifyCode;
	private boolean isEmailVerify;
	private long lastLoginTime;
	private String lastLoginIp;
	
	//无参构造方法
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//成员方法
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(int userIntegral) {
		this.userIntegral = userIntegral;
	}
	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}
	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}
	public boolean isEmailVerify() {
		return isEmailVerify;
	}
	public void setEmailVerify(boolean isEmailVerify) {
		this.isEmailVerify = isEmailVerify;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
}
