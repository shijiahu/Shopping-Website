package com.tarena.action.cart;

import javax.servlet.http.HttpServletRequest;

import com.tarena.entity.Cart;
import com.tarena.entity.ItemInCart;

public class ChangeItemStatusAction {
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute(){
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		int id = Integer.parseInt(request.getParameter("item_id"));
		int status = Integer.parseInt(request.getParameter("status"));
		for (ItemInCart item : cart.getItems()) {
			if(item.getProductId() == id){
				item.setDeleted(status);
				cart.setTotalPrice(cart.getTotalPrice()+(status==0?item.getAmount():(0-item.getAmount())));
				cart.setTotalSave(cart.getTotalSave()+(status==0?(item.getProductNum()*(item.getFixedPrice()-item.getDangPrice())):item.getProductNum()*(item.getDangPrice()-item.getFixedPrice())));
				break;
			}
		}
		request.getSession().setAttribute("cart", cart);
		return "success";
	}
}
