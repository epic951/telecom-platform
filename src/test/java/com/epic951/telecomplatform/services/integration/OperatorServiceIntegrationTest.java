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

import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OperatorServiceIntegrationTest {

	@Autowired
	private OperatorService operatorService;

	@Test
	@Transactional
	public void testAddOperator() {

		// Create an Operator
		Operator orange = TestUtilities.createTestOperator(352, "STC", "UAE");

		// Test adding the operator
		Operator o = operatorService.addOrUpdateOperator(orange);

		// Verify the addition of the new operator and the integrity of the specified
		// data
		System.err.println(o.toString());
		assertNotNull(o);
		assertNotNull(o.getOperatorId());
		assertEquals("STC", o.getOperatorName());
		System.err.println(o.toString());
	}
}
