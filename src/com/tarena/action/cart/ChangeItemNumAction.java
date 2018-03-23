package com.tarena.action.cart;

import javax.servlet.http.HttpServletRequest;

import com.tarena.entity.Cart;
import com.tarena.entity.ItemInCart;
import com.tarena.util.ResponseInfo;

public class ChangeItemNumAction {
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
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		System.out.println(request.getParameter("num"));
		for (ItemInCart item : cart.getItems()) {
			if (Integer.parseInt(request.getParameter("item_id"))==item.getProductId()) {
				int gap = Integer.parseInt(request.getParameter("num"))-item.getProductNum();
				item.setProductNum(Integer.parseInt(request.getParameter("num")));
				item.setAmount(Integer.parseInt(request.getParameter("num"))*item.getDangPrice());
				System.out.println(Integer.parseInt(request.getParameter("num"))*item.getDangPrice());
				cart.setTotalPrice(cart.getTotalPrice()+gap*item.getDangPrice());
				cart.setTotalSave(cart.getTotalSave()+gap*(item.getFixedPrice()-item.getDangPrice()));
			}
		}
		request.getSession().setAttribute("cart", cart);
		responseInfo = new ResponseInfo();
		responseInfo.setAccept(true);
		responseInfo.setValue("修改成功!");
		return "success";
	}
}
