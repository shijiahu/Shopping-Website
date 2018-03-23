package com.tarena.action.user;

import java.io.IOException;

import javax.servlet.ServletException;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.User;
import com.tarena.util.Constant;
import com.tarena.util.EmailUtil;
import com.tarena.util.MD5Util;
import com.tarena.util.VerifyUtil;

/**
 *  业务处理类
 *  - (1) 主要负责业务的处理
 *  - (2) 内容包含:
 *  	-  编写相应的业务处理代码
 *		- 有必要时,调用DAO层代码
 *		- 在execute方法的最后,根据业务返回结果
 */
public class RegisterAction {
	private String email;
	private User user;

	public User getUser() {
		return user;
	}
	//控制器会调用set方法,将用户对象注入
	public void setUser(User user) {
		this.user = user;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * tomcat服务器将浏览器发送过来的http请求消息封装到request请求对象中;同时生成一个响应对象;
	 * 将request对象和response对象作为参数传递进来
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String execute()  {
		try {
			user.setPassword(MD5Util.encode(user.getPassword()));
			user.setUserIntegral(Constant.INTEGRAL_NORMAL);
			user.setEmailVerifyCode(VerifyUtil.randomUUID());
			user.setEmailVerify(false);
			user.setLastLoginTime(System.currentTimeMillis());
			UserDAO userDao = new UserDAOImpl();
			userDao.save(user);
//			request.getRequestDispatcher("/user/verify_form.jsp").forward(
//					request, response);
			new Thread() {
				public void run() {
					try {
						user = userDao.findByEmail(email);
						EmailUtil.sendEmail(user);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
//			request.getRequestDispatcher("/error.jsp").forward(request,
//					response);
			return "error";
		}

	}
}
