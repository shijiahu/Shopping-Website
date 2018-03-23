package com.tarena.action.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tarena.dao.ProductDAO;
import com.tarena.dao.impl.ProductDAOImpl;
import com.tarena.entity.Cart;
import com.tarena.entity.ItemInCart;
import com.tarena.entity.Product;
import com.tarena.entity.User;
import com.tarena.util.ResponseInfo;

public class AddToCartAction {
	private ItemInCart item;
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
	
	public ItemInCart getItem() {
		return item;
	}

	public void setItem(ItemInCart item) {
		this.item = item;
	}

	public String execute(){
		responseInfo = new ResponseInfo();
		ProductDAO productDAO = new ProductDAOImpl();
		try {
			Product product = productDAO.findByProductId(Integer.parseInt(request.getParameter("id")));
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(cart==null){
				cart=new Cart();
				cart.setUserId(((User)request.getSession().getAttribute("user")).getId());
			}
			item = new ItemInCart();
			item.setProductId(product.getId());
			item.setProductName(product.getProductName());
			item.setProductNum(1);
			item.setFixedPrice(product.getFixedPrice());
			item.setDangPrice(product.getDangPrice());
			item.setProductIntenvory(product.getProductInventory());
			item.setAmount(product.getDangPrice());
			item.setDeleted(0);
			List<ItemInCart> items = cart.getItems();
			if(items==null){
				items = new ArrayList<ItemInCart>();
			}
			for (ItemInCart itemInCart : items) {
				if (itemInCart.getProductId() == item.getProductId()) {
					itemInCart.setProductNum(itemInCart.getProductNum()+1);
					itemInCart.setAmount(itemInCart.getAmount()+itemInCart.getDangPrice());
					cart.setTotalPrice(cart.getTotalPrice()+itemInCart.getDangPrice());
					cart.setTotalSave(cart.getTotalSave()+itemInCart.getFixedPrice()-itemInCart.getDangPrice());
					request.getSession().setAttribute("cart", cart);
					responseInfo.setAccept(true);
					responseInfo.setValue("添加成功!");
					return "success";
				}
			}
			
			items.add(item);
			cart.setTotalPrice(item.getDangPrice()+cart.getTotalPrice());
			cart.setTotalSave(cart.getTotalSave()+item.getFixedPrice()-item.getDangPrice());
			cart.setItems(items);
			responseInfo.setAccept(true);
			responseInfo.setValue("添加成功!");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			
			responseInfo.setAccept(false);
			responseInfo.setValue("添加失败!");
			return "error";
		}
	}
}
