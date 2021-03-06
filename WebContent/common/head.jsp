<%@page import="com.tarena.entity.User"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="../css/book_head090107.css" rel="stylesheet" type="text/css" />
<div class="head_container">
	<div class="head_welcome">
	<c:choose>
	<c:when test="${user != null }">
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span> <span
				class="head_welcome_text"> <span class="little_n"> | <a
					href="#"  id="mydangdang"  class="head_black12a">我的当当</a> | <a
					href="#"  id="helpcenter"  class="head_black12a" target="_blank">帮助</a>
					|
			</span>
			</span>
			<div class="cart gray4012">
				<a href="../cart/cart_list.jsp">购物车</a>
			</div>
		</div>
		</c:when>
	</c:choose>
		<span class="head_toutext" id="logininfo"> <b>
				${user.nickname }您好，欢迎光临当当网 </b> <c:choose>
				<c:when test="${user != null }">
				[&nbsp;<a href="" class="b">登出</a>&nbsp;]
					</c:when>
				<c:otherwise>
			[&nbsp;<a href='../user/login_form.jsp' class='b'>登录</a>|<a
						href='../user/register_form.jsp' class='b'>注册</a>&nbsp;]
			</c:otherwise>
			</c:choose>


		</span>
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a href="#"  id="backtobook"><img
					src="../images/booksaleimg/book_logo.gif" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b"></div>
		</div>
	</div>
	<div class="head_search_div"></div>
	<div class="head_search_bg"></div>
</div>
