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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.controllers.OperatorController;
import com.epic951.data.entities.Operator;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OperatorControllerIntegrationTest {

	@Autowired
	private OperatorController operatorController;

	@Test
	public void testAddOperator() {

		// Create an Operator
		Operator zain = TestUtilities.createTestOperator(7479, "UAE", "Zain");

		// POST the new operator we just added and check the outcome
		ResponseEntity<String> outcome = operatorController.processAddOperator(zain);

		// Assert that the outcome is as expected
		assertThat(outcome.getBody(), is(equalTo("Success")));
	}

	@Test
	public void testAddOperatorWithoutName() {
		// Create an Operator
		Operator zain = TestUtilities.createTestOperator(7479, "UAE", null);

		// POST the new operator we just added and check the outcome
		ResponseEntity<String> outcome = operatorController.processAddOperator(zain);

		// Assert that the outcome is as expected
		assertThat(outcome.getBody(), is(equalTo("Failure")));
	}
}
