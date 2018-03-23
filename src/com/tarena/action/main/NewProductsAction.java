package com.tarena.action.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tarena.dao.ProductDAO;
import com.tarena.dao.impl.ProductDAOImpl;
import com.tarena.entity.Product;

public class NewProductsAction {
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() {
		ProductDAO productDAO = new ProductDAOImpl();
		try {
			List<Product> products = productDAO.findNewProducts(8);
			request.setAttribute("products", products);
			return "success";
		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}
	}
}
