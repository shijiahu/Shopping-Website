package com.tarena.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tarena.dao.OrderDAO;
import com.tarena.entity.Order;
import com.tarena.util.DBUtil;

public class OrderDAOImpl implements OrderDAO {

	private static final String SQL_FIND_ORDER_BY_ID = " Select *from d_order where id = ? ";
	private static final String SQL_SAVE_ORDER = "insert into d_order ( user_id,status,order_time,"
			+ "order_desc,total_price,receive_name,full_address,postal_code,mobile,phone ) values (?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void save(Order order) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_SAVE_ORDER,
				Statement.RETURN_GENERATED_KEYS);
		pst.setInt(1, order.getUserId());
		pst.setInt(2, order.getStatus());
		pst.setLong(3, order.getOrderTime());
		pst.setString(4, order.getOrderDesc());
		pst.setDouble(5, order.getTotalPrice());
		pst.setString(6, order.getReceiveName());
		pst.setString(7, order.getFullAddress());
		pst.setString(8, order.getPostalCode());
		pst.setString(9, order.getMobile());
		pst.setString(10, order.getPhone());
		pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		order.setId(rs.getInt("id"));
		DBUtil.close(conn);
	}

	@Override
	public Order findById(int id) throws Exception {
		Order order = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_FIND_ORDER_BY_ID);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			order = new Order();
			order.setId(rs.getInt("id"));
			order.setUserId(rs.getInt("user_id"));
			order.setStatus(rs.getInt("order_time"));
			order.setOrderTime(rs.getLong("order_time"));
			order.setOrderDesc(rs.getString("order_desc"));
			order.setTotalPrice(rs.getDouble("total_price"));
			order.setReceiveName(rs.getString("receive_name"));
			order.setFullAddress(rs.getString("full_address"));
			order.setPostalCode(rs.getString("postal_code"));
			order.setMobile(rs.getString("mobile"));
			order.setPhone(rs.getString("phone"));
		}
		DBUtil.close(conn);
		return order;
	}

}
