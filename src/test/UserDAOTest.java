package test;

import org.junit.Test;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.User;
import com.tarena.util.Constant;

public class UserDAOTest {
	
	@Test
	public void testSave(){
		
		UserDAO userDAO = new UserDAOImpl();
		
		User user = new User();
		user.setEmail("trilindan@gmail.com");
		user.setNickname("Jeremy");
		user.setPassword("1234567");
		user.setUserIntegral(Constant.INTEGRAL_NORMAL);
		user.setEmailVerifyCode("1dfe-2345-6789-0abc-defg");
		user.setEmailVerify(false);
		user.setLastLoginTime(System.currentTimeMillis());
		user.setLastLoginIp("127.0.0.1");
		
		try {
			userDAO.save(user);
			System.out.println("id:" + user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存用户出错!");
		}
	}
}
