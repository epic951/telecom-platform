package com.epic951.telecomplatform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductServiceIntegrationTest {

	@Autowired
	private ProductService productService;

	@Test
	public void testAddProduct() {

		// Create a product
		Product sms = new Product();
		sms.setProduct_name("Short Messaging Service");
		sms.setMin_price(100);
		sms.setMax_price(300);
		sms.setProduct_description("Casual messaging service used to exchange brief text-based messages");
		sms.setProduct_id(544);
		// Test adding the product
		Product p = productService.addProduct(sms);

		// Verify the addition of the new product and the integrity of the specified
		// data
		assertNotNull(p);
		assertNotNull(p.getProduct_id());
		assertEquals("Short Messaging Service", p.getProduct_name());
		System.err.println(p.toString() + "\n");
	}
}
