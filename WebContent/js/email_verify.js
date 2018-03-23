$(function() {
	$("#send_email_verify_code_again").click(function(){
		$("#send_email_verify_code_again").hide();
		$("#send_email_msg").html("发送中…");
		$.ajax({
			"url" : "send_verify_email.action",
			"type" : "post",
			"data" : "email=" + $("#lblEmail").html(),
			"dataType" : "json",
			"success" : function(data) {
				
				$("#send_email_msg").html(data.value);
			}
		});
	});
	$("#verify_email_later").click(function(){
		window.location = "register_ok.jsp";
	});
})