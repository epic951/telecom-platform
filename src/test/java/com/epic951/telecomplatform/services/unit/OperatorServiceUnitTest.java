package com.epic951.telecomplatform.services.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OperatorServiceUnitTest {

	@Mock
	private OperatorRepository operatorRepository;

	@InjectMocks
	private OperatorService operatorService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddOperator() {

		// Create an Operator
		Operator etisalat = new Operator();
		etisalat.setOperatorCountry("EG");
		etisalat.setOperatorId(3);
		etisalat.setOperatorName("Etisalat");

		when(operatorRepository.save(any(Operator.class))).thenReturn(etisalat);

		Operator newOperator = operatorService.addOperator(new Operator(2, "Vodafone", "EGY"));
		// Verify mocked object is passed to the service no matter what is passed to
		// addoperator method
		assertEquals("Etisalat", newOperator.getOperatorName());
		System.err.println(newOperator.toString());
	}
}
