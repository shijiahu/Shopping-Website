$(function() {
	$("#txtEmail").keyup(check_email).blur(check_email);
	$("#txtNickName").keyup(check_nickname).blur(check_nickname);
	$("#txtPassword").keyup(check_password).blur(check_password);
	$("#txtPassword").keyup(check_repeat_password).blur(check_repeat_password);
	$("#txtRepeatPass").keyup(check_repeat_password)
			.blur(check_repeat_password);
	$("#txtRepeatPass").attr("disabled", true);
	$("#txtVerifyCode").keyup(check_verify_code).blur(check_verify_code);
	$("#imgVcode").attr("src", "verify_code");
	$("#number_info").click(function() {
		$("#imgVcode").attr("src", "verify_code?" + Math.random());
	});
	$("#f").submit(function() {
		if (ok) {
			return true;
		} else {
			$.each(checkFlag, function(key, val) {
				if (!val) {
					switch (key) {
					case "email":
						$("#txtEmail").attr("class", "submit_error");
						break;
					case "nickname":
						$("#txtNickName").attr("class", "submit_error");
						break;
					case "password":
						$("#txtPassword").attr("class", "submit_error");
						break;
					case "repeatPassword":
						$("#txtRepeatPass").attr("class", "submit_error");
						break;
					case "verifyCode":
						$("#txtVerifyCode").attr("class", "yzm_submit_error");
						break;
					}

				}
			});
			return false;
		}
	});
})
var ok = false;
var checkFlag = {
	"email" : false,
	"nickname" : false,
	"password" : false,
	"repeatPassword" : false,
	"verifyCode" : false
};
function setStatus(item) {
	switch (item) {
	case "email":
		checkFlag.email = true;
		break;
	case "nickname":
		checkFlag.nickname = true;
		break;
	case "password":
		checkFlag.password = true;
		break;
	case "repeat_password":
		checkFlag.repeatPassword = true;
		break;
	case "verify_code":
		checkFlag.verifyCode = true;
		break;
	}
	ok = true;
	$.each(checkFlag, function(key, val) {
		if (!val) {
			ok = false;
			return false;
		}
	});
}
function resetStatus(item) {
	switch (item) {
	case "email":
		checkFlag.email = false;
		break;
	case "nickname":
		checkFlag.nickname = false;
		break;
	case "password":
		checkFlag.password = false;
		break;
	case "repeat_password":
		checkFlag.repeatPassword = false;
		break;
	case "verify_code":
		checkFlag.verifyCode = false;
		break;
	}
	ok = false;
}
function check_email() {
	$("#txtEmail").attr("class", "text_input");
	$.ajax({
		"url" : "check_email.action",
		"type" : "post",
		"data" : "email=" + $("#txtEmail").val(),
		"dataType" : "json",
		"success" : function(data) {
			$("#emailValidMsg").html(data.value);
			if (data.accept) {
				setStatus("email");
			} else {
				resetStatus("email");
			}
		}
	});
}
function check_nickname() {
	$("#txtNickName").attr("class", "text_input");
	$.ajax({
		"url" : "check_nickname.action",
		"type" : "post",
		"data" : "nickname=" + $("#txtNickName").val(),
		"dataType" : "json",
		"success" : function(data) {
			$("#nickNameValidMsg").html(data.value);
			if (data.accept) {
				setStatus("nickname");
			} else {
				resetStatus("nickname");
			}
		}
	});
}
function check_password() {
	$("#txtPassword").attr("class", "text_input");
	$.ajax({
		"url" : "check_password.action",
		"type" : "post",
		"data" : "password=" + $("#txtPassword").val(),
		"dataType" : "json",
		"success" : function(data) {
			$("#passwordValidMsg").html(data.value);
			if (data.accept) {
				setStatus("password");
				$("#txtRepeatPass").attr("disabled", false);
			} else {
				resetStatus("password");
				$("#txtRepeatPass").val("");
				$("#txtRepeatPass").attr("disabled", true);
			}
		}
	});
}
function check_repeat_password() {
	$("#txtRepeatPass").attr("class", "text_input");
	$.ajax({
		"url" : "check_repeat_password.action",
		"type" : "post",
		"data" : "password=" + $("#txtPassword").val() + "&password1="
				+ $("#txtRepeatPass").val(),
		"dataType" : "json",
		"success" : function(data) {
			$("#repeatPassValidMsg").html(data.value);
			if (data.accept) {
				setStatus("repeat_password");
			} else {
				resetStatus("repeat_password");
			}
		}
	});
}
function check_verify_code() {
	$("#txtVerifyCode").attr("class", "yzm_input");
	$.ajax({
		"url" : "check_verify_code.action",
		"type" : "post",
		"data" : "verify_code=" + $("#txtVerifyCode").val(),
		"dataType" : "json",
		"success" : function(data) {
			$("#vcodeValidMsg").html(data.value);
			if (data.accept) {
				setStatus("verify_code");
				$("#number_info").hide();
			} else {
				resetStatus("verify_code");
				$("#number_info").show();
			}
		}
	});
}
