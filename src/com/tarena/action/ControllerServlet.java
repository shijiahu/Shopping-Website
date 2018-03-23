package com.tarena.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.tarena.action.cart.AddToCartAction;
import com.tarena.action.cart.CartSettleAction;
import com.tarena.action.cart.ChangeItemNumAction;
import com.tarena.action.cart.ChangeItemStatusAction;
import com.tarena.action.main.NewProductsAction;
import com.tarena.action.user.CheckEmailVerifyCodeAction;
import com.tarena.action.user.CheckNicknameAction;
import com.tarena.action.user.CheckPasswordAction;
import com.tarena.action.user.CheckRepeatPasswordAction;
import com.tarena.action.user.CheckVerifyCode;
import com.tarena.action.user.SendVerifyEmailAction;
import com.tarena.action.user.ValidateEmailAction;
import com.tarena.action.user.LoginAction;
import com.tarena.action.user.RegisterAction;
import com.tarena.entity.Cart;
import com.tarena.entity.User;
import com.tarena.util.Constant;
import com.tarena.util.EmailUtil;

/**
 * MVC开发模式中的控制器 (1) 根据请求路径调用对应的处理流程 (2) 在控制器中主要负责: -
 * 取得页面中的参数,有必要时封装成对象一起注入到action处理类中 - 调用业务处理类中的execute方法 - 根据返回值转向到对应的页面
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		System.out.println(action);
		switch (action) {
		case "/regist":
			regist(request, response);
			break;
		case "/login":
			try {
				login(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case "/check_email":
			checkEmail(request, response);
			break;
		case "/check_nickname":
			checkNickname(request, response);
			break;
		case "/check_password":
			checkPassword(request, response);
			break;
		case "/check_repeat_password":
			checkRepeatPassword(request, response);
			break;
		case "/check_verify_code":
			checkVerifyCode(request, response);
			break;
		case "/email_verify":
			try {
				checkEmailVerifyCode(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/send_verify_email":
			try {
				sendVerifyEmail(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/new_products":
			newProducts(request, response);
			break;
		case "/add_to_cart":
			addToCart(request,response);
			break;
		case "/change_item_num":
			changeItemNum(request,response);
			break;
		case "/change_item_status":
			changeItemStatus(request, response);
			break;
		case "/cart_settle":
			try {
				cartSettle(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegisterAction registerAction = new RegisterAction();
		registerAction.setEmail(request.getParameter("user.email").trim());
		User user = new User();
		user.setEmail(request.getParameter("user.email").trim());
		user.setNickname(request.getParameter("user.nickname").trim());
		user.setPassword(request.getParameter("user.password").trim());
		user.setLastLoginIp(request.getRemoteAddr());
		registerAction.setUser(user);
		String result = registerAction.execute();
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher(
				result.equals("success") ? "/user/verify_form.jsp"
						: Constant.SYSTEM_ERROR_INFO)
				.forward(request, response);

	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		LoginAction loginAction = new LoginAction();
		loginAction.setEmail(request.getParameter("email").trim());
		loginAction.setPassword(request.getParameter("password").trim());
		loginAction.setLastLoginIp(request.getRemoteAddr());
		loginAction.setRequest(request);
		PrintWriter out = response.getWriter();
		if (loginAction.execute().equals("success")) {
			System.out.println("success");
			out.print(JSONObject.fromObject(loginAction.getResponseInfo()));
		} else {
			out.print(JSONObject.fromObject(loginAction.getResponseInfo()));
		}
		out.close();
	}

	private void checkEmail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ValidateEmailAction validateEmailAction = new ValidateEmailAction();
		validateEmailAction.setEmail(request.getParameter("email"));
		PrintWriter out = response.getWriter();
		out.print(validateEmailAction.execute().equals("success") ? JSONObject
				.fromObject(validateEmailAction.getResponseInfo())
				: Constant.SYSTEM_ERROR_INFO);
		out.close();
	}

	private void checkNickname(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CheckNicknameAction checkNicknameAction = new CheckNicknameAction();

		checkNicknameAction
				.setNickname(request.getParameter("nickname").trim());
		PrintWriter out = response.getWriter();
		out.print(checkNicknameAction.extcute().equals("success") ? JSONObject
				.fromObject(checkNicknameAction.getResponseInfo())
				: Constant.SYSTEM_ERROR_INFO);
		out.close();
	}

	private void checkPassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CheckPasswordAction checkPasswordAction = new CheckPasswordAction();
		checkPasswordAction
				.setPassword(request.getParameter("password").trim());
		PrintWriter out = response.getWriter();
		out.print(checkPasswordAction.extcute().equals("success") ? JSONObject
				.fromObject(checkPasswordAction.getResponseInfo())
				: Constant.SYSTEM_ERROR_INFO);
		out.close();
	}

	private void checkRepeatPassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CheckRepeatPasswordAction checkRepeatPasswordAction = new CheckRepeatPasswordAction();
		checkRepeatPasswordAction.setPassword(request.getParameter("password")
				.trim());
		checkRepeatPasswordAction.setRepeatPassword(request.getParameter(
				"password1").trim());
		PrintWriter out = response.getWriter();
		out.print(checkRepeatPasswordAction.extcute().equals("success") ? JSONObject
				.fromObject(checkRepeatPasswordAction.getResponseInfo())
				: Constant.SYSTEM_ERROR_INFO);
		out.close();
	}

	private void checkVerifyCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CheckVerifyCode checkVerifyCode = new CheckVerifyCode();
		checkVerifyCode.setVerifyCode(request.getSession()
				.getAttribute("verifyCode").toString());
		checkVerifyCode.setTxtVerifyCode(request.getParameter("verify_code")
				.trim());
		PrintWriter out = response.getWriter();
		out.print(checkVerifyCode.extcute().equals("success") ? JSONObject
				.fromObject(checkVerifyCode.getResponseInfo())
				: Constant.SYSTEM_ERROR_INFO);
		out.close();
	}

	private void checkEmailVerifyCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			Exception {
		CheckEmailVerifyCodeAction checkEmailVerifyCodeAction = new CheckEmailVerifyCodeAction();
		checkEmailVerifyCodeAction.setId(request.getParameter("id"));
		checkEmailVerifyCodeAction.setUuid(request.getParameter("uuid"));
		if (checkEmailVerifyCodeAction.extcute().equals("success")) {
			request.getSession().setAttribute("user",
					checkEmailVerifyCodeAction.getUser());
			request.getRequestDispatcher("register_ok.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher(Constant.SYSTEM_ERROR_INFO).forward(
					request, response);
		}
	}

	private void sendVerifyEmail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SendVerifyEmailAction sendVerifyEmailAction = new SendVerifyEmailAction();
		sendVerifyEmailAction.setEmail(request.getParameter("email"));
		PrintWriter out = response.getWriter();
		sendVerifyEmailAction.execute();
		out.print(JSONObject.fromObject(sendVerifyEmailAction.getResponseInfo()));
		out.close();
	}
	
	private void newProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		NewProductsAction newProductsAction = new NewProductsAction();
		newProductsAction.setRequest(request);
		if (newProductsAction.execute().equals("success")) {
			request.getRequestDispatcher("/main/new.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	private void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		AddToCartAction addToCartAction = new AddToCartAction();
		addToCartAction.setRequest(request);
		PrintWriter out = response.getWriter();
		addToCartAction.execute();
		out.print(JSONObject.fromObject(addToCartAction.getResponseInfo()));
		out.close();
	}
	private void changeItemNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ChangeItemNumAction changeItemNumAction = new ChangeItemNumAction();
		changeItemNumAction.setRequest(request);
		changeItemNumAction.execute();
		PrintWriter out = response.getWriter();
		Properties properties = new Properties();
		properties.put("totalPrice", ((Cart)request.getSession().getAttribute("cart")).getTotalPrice());
		properties.put("totalSave", ((Cart)request.getSession().getAttribute("cart")).getTotalSave());
		out.print(JSONObject.fromObject(properties));
		out.close();
	}
	private void changeItemStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ChangeItemStatusAction changeItemStatusAction = new ChangeItemStatusAction();
		changeItemStatusAction.setRequest(request);
		changeItemStatusAction.execute();

	}
	
	private void cartSettle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		CartSettleAction cartSettleAction = new CartSettleAction();
		cartSettleAction.setRequest(request);
		String result = cartSettleAction.execute();
		PrintWriter out = response.getWriter();
		if(result.equals("success")){
			request.getRequestDispatcher("../order/order_info.jsp").forward(request, response);
		}else{
			out.print("<script>alert('您的购物车为空,请先购物！');location.href='../main/main.jsp'</script>");
			out.close();

		}
	}
}
