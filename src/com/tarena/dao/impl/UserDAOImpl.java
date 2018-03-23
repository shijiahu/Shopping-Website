package com.tarena.dao.impl;

import java.sql.*;

import com.tarena.dao.UserDAO;
import com.tarena.entity.User;
import com.tarena.util.DBUtil;

/**
 * 用户DAO接口的实现类 负责将java对象和表中记录进行同步
 */
public class UserDAOImpl implements UserDAO {
	private static final String SQL_SAVE = "insert into d_user(email,nickname,password,"
			+ "user_integral,email_verify_code,is_email_verify,"
			+ "last_login_time,last_login_ip) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_BY_EMAIL = "select * from d_user where email = ?";
	private static final String SQL_FIND_BY_ID = "select * from d_user where  id = ?";
	private static final String SQL_FIND_BY_UUID = "select * from d_user where  email_verify_code = ?";
	private static final String SQL_UPTADE_EMAIL_VERIFY = "update d_user set is_email_verify = ? where id = ?";
	private static final String SQL_UPTADE_LAST_LOGIN_INFO="update d_user set last_login_time = ? ,last_login_ip=? where id = ?";
	/**
	 * 负责将传入的user对象,保存到d_user表中
	 */
	public void save(User user) throws Exception {

		// 1.借助DBUtil,取得一个数据库连接
		Connection conn = DBUtil.getConnection();

		// 2.使用连接,创建一个SQL预处理对象
		// 第二个参数含义:表中字段若有自动生成的值,执行后一起返回
		PreparedStatement pst = conn.prepareStatement(SQL_SAVE,
				Statement.RETURN_GENERATED_KEYS);

		// 3.向预处理对象中,设置占位符对应的参数
		// 设置参数时,要注意以表中字段类型为主导
		pst.setString(1, user.getEmail());
		pst.setString(2, user.getNickname());
		pst.setString(3, user.getPassword());
		pst.setInt(4, user.getUserIntegral());
		pst.setString(5, user.getEmailVerifyCode());
		pst.setString(6, user.isEmailVerify() ? "Y" : "N");
		pst.setLong(7, user.getLastLoginTime());
		pst.setString(8, user.getLastLoginIp());

		// 4.执行数据更新,得到结果集或受影响的记录条数
		pst.executeUpdate();

		// 5.遍历结果集,或根据受影响的条数再进一步处理
		ResultSet rs = pst.getGeneratedKeys(); // 得到数据库中自动生成的字段值
		rs.next(); // 将游标移动指向第一条记录
		int id = rs.getInt(1); // 取第一条记录中第一个字段的值
		user.setId(id); // 将id值保存到堆中的user对象

		// 6.关闭连接,释放资源
		DBUtil.close(conn);
	}

	public User findByEmail(String email) throws Exception {
		User user = null;
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_FIND_BY_EMAIL,
				Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, email);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setPassword(rs.getString("password"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setEmailVerify(rs.getString("is_email_verify").equals("Y") ? true : false);
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}
		DBUtil.close(conn);
		return user;
	}
	

	public User findByUUID(String uuid) throws Exception {
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_FIND_BY_UUID, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, uuid);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setPassword(rs.getString("password"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setEmailVerify(rs.getString("is_email_verify") == "Y" ? true : false);
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}
		DBUtil.close(conn);
		return user;
	}
	public User updateByEmailVerify(User user) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_UPTADE_EMAIL_VERIFY, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, user.isEmailVerify() ? "YES":"NO");
		pst.setInt(2, user.getId());
		pst.executeUpdate();
		DBUtil.close(conn);
		return user;
	}

	public User findById(String id) throws Exception {
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_FIND_BY_ID, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setPassword(rs.getString("password"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setEmailVerify(rs.getString("is_email_verify") == "Y" ? true : false);
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}
		DBUtil.close(conn);
		return user;
	}

	public User updateByLastLoginInfo(User user) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(SQL_UPTADE_LAST_LOGIN_INFO, Statement.RETURN_GENERATED_KEYS);
		pst.setLong(1, user.getLastLoginTime());
		pst.setString(2, user.getLastLoginIp());
		pst.setInt(3, user.getId());
		pst.executeUpdate();
		DBUtil.close(conn);
		return user;
	}

	
}
