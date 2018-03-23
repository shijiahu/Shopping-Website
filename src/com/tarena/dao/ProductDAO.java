package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Product;

public interface ProductDAO {

	/**
	 * 查询前n个最新上架的商品
	 */
	public List<Product> findNewProducts(int size) throws Exception;
	public void save(Product product) throws Exception;

	
	//Plus
	public Product findByProductId(int ProductId) throws Exception;
}
