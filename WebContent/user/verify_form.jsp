<%@page import="com.tarena.entity.User"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户注册 - 当当网</title>
<link href="../css/login.css" rel="stylesheet" type="text/css" />
<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.3.js" type="text/javascript"></script>
<script src="../js/email_verify.js"  type="text/javascript"></script>
</head>
<body>
	<c:choose>
		<c:when test="${user==null }">
			<c:redirect url="register_form.jsp"/>
		</c:when>
	</c:choose>
	<%@include file="../common/head1.jsp"%>

	<div class="login_step">
		注册步骤: 1.填写信息 > <span class="red_bold">2.验证邮箱</span> > 3.注册成功
	</div>
	<form action="register_ok.jsp" method="post">
		<div class="validate_email">
			<h2>
				${user.nickname }，感谢您注册当当网！现在请按以下步骤完成您的注册!
			</h2>
			<div class="look_email">
				<h4>第一步：查看您的电子邮箱</h4>
				<div class="mess reduce_h">
					我们给您发送了验证邮件，邮件地址为： <span class="red"><span id="lblEmail">${user.email }</span><span style="display: none" id="user_id">${user.id }</span><br/>
					</span> <span class="t1"> 请登录您的邮箱收信。<br /><br />    如果您没有收到?  <span id="send_email_msg"></span> <a href="javascript:" id="send_email_verify_code_again">再次发送</a></span>
				</div>
				<br /><br /><br/>
				<h4>第二步：点解邮件中的链接完成注册</h4>
				<div class="mess">
					<span class="t1">请点击您收到的验证邮件中的验证链接来完成注册的最后一步,或<a href="javascript:" id="verify_email_later">以后验证</a>。</span>
				</div>
			</div>
		</div>
	</form>
	<%@include file="../common/foot1.jsp"%>
</body>
</html>

