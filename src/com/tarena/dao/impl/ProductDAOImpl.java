package com.tarena.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dao.ProductDAO;
import com.tarena.entity.Product;
import com.tarena.util.DBUtil;

public class ProductDAOImpl implements ProductDAO {
	private final static String SQL_INSERT = "insert into d_product(product_name,description,add_time,fixed_price,dang_price,keywords,has_deleted,product_pic)"
			+ "values(?,?,?,?,?,?,?,?)";
	// limit 起始记录,结束记录; //0,8 取前8条记录
	private static final String SQL_FIND_NEW_PRODUCTS = "select * from d_product order by add_time desc limit ?,?";

	//Plus
	private static final String SQL_FIND_BY_PRODUCT_ID = "select * from d_product where id = ?";
	
	public void save(Product product) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_INSERT,
				Statement.RETURN_GENERATED_KEYS);
		// ResultSet rs=pst.executeQuery();
		pst.setString(1, product.getProductName());
		pst.setString(2, product.getDescription());
		pst.setLong(3, product.getAddTime());
		pst.setDouble(4, product.getFixedPrice());
		pst.setDouble(5, product.getDangPrice());
		pst.setString(6, product.getKeywords());
		// pst.setInt(7,product.getHasDeleted());
		pst.setString(8, product.getProductPic());
		pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		product.setId(id);
		DBUtil.close(conn);
	}


	public List<Product> findNewProducts(int size) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_FIND_NEW_PRODUCTS);
		pst.setInt(1, 0);
		pst.setInt(2, size);
		ResultSet rs =  pst.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while(rs.next()){
			Product product  = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setDescription(rs.getString("description"));
			product.setAddTime(rs.getLong("add_time"));
			product.setFixedPrice(rs.getDouble("fixed_price"));
			product.setDangPrice(rs.getDouble("dang_price"));
			product.setKeywords(rs.getString("keywords"));
			product.setHasDeleted(rs.getInt("has_deleted")==0 ? false:true);
			product.setProductPic(rs.getString("product_pic"));
			list.add(product);
		}
		DBUtil.close(conn);
		return list;
	}

	@Override
	public Product findByProductId(int ProductId) throws Exception {
		Product product = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_FIND_BY_PRODUCT_ID);
		pst.setInt(1, ProductId);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setDescription(rs.getString("description"));
			product.setAddTime(rs.getLong("add_time"));
			product.setFixedPrice(rs.getDouble("fixed_price"));
			product.setDangPrice(rs.getDouble("dang_price"));
			product.setKeywords(rs.getString("keywords"));
			product.setHasDeleted(rs.getInt("has_deleted")==0 ? false:true);
			product.setProductPic(rs.getString("product_pic"));
		}
		DBUtil.close(conn);
		return product;
	}
}
