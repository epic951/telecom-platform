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

import com.epic951.business.controllers.OperatorController;
import com.epic951.business.exceptions.MissingRequiredValueException;
import com.epic951.business.exceptions.OperatorRequirementsViolationException;
import com.epic951.data.entities.Operator;
import com.epic951.utilities.HTTPUtilities;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OperatorControllerIntegrationTest {

	@Autowired
	private OperatorController operatorController;

	@Test
	public void testAddOperator()
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {

		// Create an Operator
		Operator zain = TestUtilities.createTestOperator(7479, "STC", "UAE", "", 1);

		// POST the new operator we just added and check the outcome
		ResponseEntity<String> outcome = operatorController.processAddOperator(zain);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo(HTTPUtilities.handleResponse("Success"))));
	}

	@Test
	public void testAddOperatorWithoutName()
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {
		// Create an Operator
		Operator zain = TestUtilities.createTestOperator(7479, null, "UAE", "", 1);

		// Assert that the faulty adding process fails and throws an exception as
		// expected
		assertThatThrownBy(() -> {
			operatorController.processAddOperator(zain);
		}).isInstanceOf(MissingRequiredValueException.class).hasMessageContaining("required field");
	}
}
