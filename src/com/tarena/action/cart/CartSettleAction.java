package com.tarena.action.cart;

import javax.servlet.http.HttpServletRequest;

import com.tarena.entity.Cart;
import com.tarena.entity.ItemInCart;
import com.tarena.util.ResponseInfo;

public class CartSettleAction {
	private HttpServletRequest request;
	private ResponseInfo responseInfo;
	
	
	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	 public String execute(){
		 Cart cart = (Cart)request.getSession().getAttribute("cart");
		 responseInfo = new ResponseInfo();
		 int itemNum = 0;
		 for (ItemInCart item : cart.getItems()) {
			if(item.getDeleted() == 0){
				itemNum++;
			}
		}
		 if(itemNum==0){
			 responseInfo.setAccept(false);
			 responseInfo.setValue("您的购物车为空,请先购物!");
			 return "error";
		 }
		 responseInfo.setAccept(true);
		 return "success";
	 }
}
