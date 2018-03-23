package test;

import java.util.List;

import org.junit.Test;

import com.tarena.dao.ProductDAO;
import com.tarena.dao.impl.ProductDAOImpl;
import com.tarena.entity.Product;

public class ProductDAOTest {
	
	@Test
	public void testFindNewProducts(){
		
		ProductDAO productDAO = new ProductDAOImpl();
		try {
			List<Product> products = productDAO.findNewProducts(8);
			System.out.println(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
