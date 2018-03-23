<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登录 - 当当网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/login.css" rel="stylesheet" type="text/css" />
<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.3.js" type="text/javascript"></script>
<script type="text/javascript">$(function(){
	$("#btnSignCheck").click(function(){
		$.ajax({
			"url" : "login.action",
			"type" : "post",
			"data" : "email=" + $("#txtEmail").val()+"&password="+$("#txtPassword").val(),
			"dataType" : "json",
			"success" : function(data) {
				$("#login_msg").html(data.value);
				if(data.accept){
					location.href="../main/main.jsp";
				}
			}
		});
	})
})
</script>
</head>
<body>


	<%@include file="../common/head1.jsp"%>

	<div class="enter_part">

		<%@include file="../common/introduce.jsp"%>

		<div class="enter_in">
			<div class="bj_top"></div>
			<div class="center">
				<div style="height: 30px; padding: 5px; color: red"
					id="divErrorMssage"></div>
				<div class="main">
					<h3>登录当当网</h3>

					<form method="post"  id="ctl00">
						<ul>
							<li><span>请输入Email地址：</span> <input type="text" name="email"
								id="txtEmail" class="textbox" /></li>
							<li><span class="blank">密码：</span> <input type="password"
								name="password" id="txtPassword" class="textbox" /></li>
							<li><input type="button" id="btnSignCheck"
								class="button_enter" value="登 录" /> <a href="#">忘记密码？</a></li>
						</ul>
						<input type="hidden" name="uri" value="${uri}" /> 
						<span id="login_msg" class="notice"></span>
					</form>
				</div>
				<div class="user_new">
					<p>您还不是当当网用户？</p>
					<p class="set_up">
						<a href="register_form.jsp">创建一个新用户&gt;&gt;</a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../common/foot1.jsp"%>

</body>
</html>

