package com.epic951.telecomplatform.services.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductServiceIntegrationTest {

	@Autowired
	private ProductService productService;

	@Test
	@Transactional
	public void testAddProduct() {

		// Create a product
		Product sms = TestUtilities.createTestProduct("Short Messaging Service", 544,
				"Casual messaging service used to exchange brief text-based messages");

		// Test adding the product
		Product p = productService.addOrUpdateProduct(sms);

		// Verify the addition of the new product and the integrity of the specified
		// data
		assertNotNull(p);
		assertNotNull(p.getProductId());
		assertEquals("Short Messaging Service", p.getProductName());
		System.err.println(p.toString());
	}
}
