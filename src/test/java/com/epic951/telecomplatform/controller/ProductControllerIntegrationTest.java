package com.epic951.telecomplatform.controller;

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
		sms.setProduct_name("Short Messaging Service");
		sms.setMin_price(100);
		sms.setMax_price(300);
		sms.setProduct_description("Casual messaging service used to exchange brief text-based messages");
		sms.setProduct_id(544);
		// Product mms = new Product();
		// mms.setProduct_name("Multimedia Messaging Service");
		// mms.setMin_price(100);
		// mms.setMax_price(300);
		// mms.setProduct_description("Multimedia messaging service used to exchange
		// brief multimedia messages");
		// mms.setProduct_id(344);

		// POST the new product we just added and check the outcome
		String outcome = productController.processAddProduct(sms);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
	}

	@Test
	public void testAddProductWithoutName() {

		Product p = new Product();
		p.setProduct_id(1);
		p.setMin_price(15);
		
		// POST the new product we just added and check the outcome
		String outcome = productController.processAddProduct(p);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("failure")));
	}
}
