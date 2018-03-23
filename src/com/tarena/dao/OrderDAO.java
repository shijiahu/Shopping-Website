package com.tarena.dao;

import com.tarena.entity.Order;

public interface OrderDAO {
	
	public void save(Order order) throws Exception;
	
	public Order findById(int id) throws Exception;
	
}
