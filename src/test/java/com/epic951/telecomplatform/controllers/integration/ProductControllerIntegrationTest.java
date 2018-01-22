package com.epic951.telecomplatform.controllers.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
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
	public void testAddProduct() throws JSONException {

		// Create a Product
		Product mms = TestUtilities.createTestProduct(6621, "Video-Messaging-Service", "Video-messaging-service", 435,
				121, 1);

		// POST the new product we just added and check the outcome
		ResponseEntity<String> outcome = productController.processAddProduct(mms);

		// Assert that the outcome is as expected
		assertThat(outcome.getBody(), is(equalTo("Success")));
	}

	@Test
	public void testAddProductWithoutName() throws JSONException {

		Product streaming = TestUtilities.createTestProduct(65, null, "Youtube", 7, 88, 2);

		// POST the new product we just added and check the outcome
		ResponseEntity<String> outcome = productController.processAddProduct(streaming);
		// Assert that the outcome is as expected
		assertThat(outcome.getBody(), is(equalTo("Failure")));
	}
}
