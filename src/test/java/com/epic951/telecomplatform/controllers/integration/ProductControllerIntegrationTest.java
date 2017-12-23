package com.epic951.telecomplatform.controllers.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.controllers.ProductController;
import com.epic951.data.entities.Product;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

	@Autowired
	private ProductController productController;

	@Test
	public void testAddProduct() {

		// Create a Product
		Product mms = TestUtilities.createTestProduct("Video-Messaging-Service", 6621, "Video-messaging-service");

		// POST the new product we just added and check the outcome
		String outcome = productController.processAddProduct(mms);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
	}

	@Test
	public void testAddProductWithoutName() {

		Product streaming = TestUtilities.createTestProduct(null, 9238, "Youtube");

		// POST the new product we just added and check the outcome
		String outcome = productController.processAddProduct(streaming);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("failure")));
	}
}
