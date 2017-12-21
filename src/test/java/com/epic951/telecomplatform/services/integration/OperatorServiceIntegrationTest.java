package com.epic951.telecomplatform.services.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)

public class OperatorServiceIntegrationTest {

	@Autowired
	private OperatorService operatorService;

	@Test
	public void testAddOperator() {

		// Create an Operator
		Operator etisalat = new Operator();
		etisalat.setOperatorCountry("EG");
		etisalat.setOperatorId(3);
		etisalat.setOperatorName("Etisalat");

		// Test adding the operator
		Operator o = operatorService.addOperator(etisalat);

		// Verify the addition of the new operator and the integrity of the specified
		// data
		assertNotNull(o);
		assertNotNull(o.getOperatorId());
		assertEquals("Etisalat", o.getOperatorName());
		System.err.println(o.toString());
	}
}
