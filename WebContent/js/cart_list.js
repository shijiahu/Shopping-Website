function change_item_num(item_id, num) {
	$.ajax({
		"url" : "change_item_num.action",
		"type" : "post",
		"data" : {item_id : item_id,num : num},
		"dataType" : "json",
		"success" : function(data) {
			$("#product_num_" + item_id).html(num);
			$("#total_account").html((data.totalPrice).toFixed(2));
			$("#total_economy").html((data.totalSave).toFixed(2));
		}
	});
}
function txt_item_change_num(item_id) {
	return $("#txt_change_item_num_" + item_id).val();
}
function change_item_status(item_id,status){
	$.post("change_item_status.action",{item_id:item_id,status:status});
	location.reload();
}