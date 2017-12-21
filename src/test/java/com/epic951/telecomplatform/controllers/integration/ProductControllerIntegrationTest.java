package com.epic951.telecomplatform.controllers.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.controllers.ProductController;
import com.epic951.data.entities.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

	@Autowired
	private ProductController productController;
	
	@Test
	public void testAddProduct() {

		// Create a Product
		Product sms = new Product();
		sms.setProductName("Short Messaging Service");
		sms.setMinPrice(100);
		sms.setMaxPrice(300);
		sms.setProductDescription("Casual messaging service used to exchange brief text-based messages");
		sms.setProductId(544);

		// POST the new product we just added and check the outcome
		String outcome = productController.processAddProduct(sms);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
	}

	@Test
	public void testAddProductWithoutName() {

		Product p = new Product();
		p.setProductId(1);
		p.setMinPrice(15);
		
		// POST the new product we just added and check the outcome
		String outcome = productController.processAddProduct(p);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("failure")));
	}
}
