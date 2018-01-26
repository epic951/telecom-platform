package com.epic951.telecomplatform.controllers.integration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import com.epic951.business.exceptions.MissingRequiredValueException;
import com.epic951.business.exceptions.OperatorRequirementsViolationException;
import com.epic951.data.entities.Product;
import com.epic951.utilities.HTTPUtilities;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

	@Autowired
	private ProductController productController;

	@Test
	public void testAddProduct()
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {

		// Create a Product
		Product mms = TestUtilities.createTestProduct(6621, "Video-Messaging-Service", "Video-messaging-service", 435,
				121, 1);

		// POST the new product we just added and check the outcome
		ResponseEntity<String> outcome = productController.processAddProduct(mms);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo(HTTPUtilities.handleResponse("Success"))));
	}

	@Test
	public void testAddProductWithoutName()
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {

		Product streaming = TestUtilities.createTestProduct(65, null, "Youtube", 7, 88, 2);

		// Assert that the faulty adding process fails and throws an exception as
		// expected
		assertThatThrownBy(() -> {
			productController.processAddProduct(streaming);
		}).isInstanceOf(MissingRequiredValueException.class).hasMessageContaining("required field");
	}
}
