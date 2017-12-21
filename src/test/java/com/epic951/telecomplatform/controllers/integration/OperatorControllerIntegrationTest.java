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

import com.epic951.business.controllers.OperatorController;
import com.epic951.data.entities.Operator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OperatorControllerIntegrationTest {

	@Autowired
	private OperatorController operatorController;

	@Test
	public void testAddOperator() {

		// Create an Operator
		Operator vodafone = new Operator();
		vodafone.setOperatorCountry("EGY");
		vodafone.setOperatorName("Vodafone");

		// POST the new operator we just added and check the outcome
		String outcome = operatorController.processAddOperator(vodafone);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
	}
}
