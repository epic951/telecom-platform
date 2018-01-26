package com.epic951.telecomplatform.services.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;
import com.epic951.utilities.TestUtilities;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OperatorServiceUnitTest {

	@Mock
	private OperatorRepository mockedOperatorRepository;

	@InjectMocks
	private OperatorService mockedOperatorService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockedOperatorRepository = mock(OperatorRepository.class);
		mockedOperatorService = new OperatorService(mockedOperatorRepository);
	}

	@Test
	public void testAddOperator() {

		// Create an Operator
		Operator etisalat = TestUtilities.createTestOperator(3, "EGY", "Etisalat", "", 1);
		Operator vodafone = TestUtilities.createTestOperator(5, "EG", "Vodafone", "", 1);

		// stub repository methods
		when(mockedOperatorRepository.findByOperatorId(Mockito.anyInt())).thenReturn(Optional.empty());
		when(mockedOperatorRepository.findByOperatorName(anyString())).thenReturn(Optional.empty());
		when(mockedOperatorRepository.save(any(Operator.class))).thenReturn(etisalat);

		// Verify mocked object is passed to the service no matter what is passed to
		// addoperator method
		assertEquals(etisalat, mockedOperatorService.addOrUpdateOperator(vodafone));
		assertEquals(etisalat, mockedOperatorService.addOrUpdateOperator(new Operator(99, "Orange", "KSA", " ", 1)));
	}

	@Test
	public void testDeleteByOperatorName() {

		// Create an Operator
		Operator etisalat = TestUtilities.createTestOperator(3, "EGY", "Etisalat", "", 1);

		// stub repository methods
		when(mockedOperatorRepository.deleteByOperatorName(anyString())).thenReturn(1);
		when(mockedOperatorRepository.findByOperatorId(Mockito.anyInt())).thenReturn(Optional.empty());
		when(mockedOperatorRepository.findByOperatorName(anyString())).thenReturn(Optional.empty());
		when(mockedOperatorRepository.save(any(Operator.class))).thenReturn(etisalat);

		Operator persisted = mockedOperatorService.addOrUpdateOperator(etisalat);
		int result = mockedOperatorService.deleteOperator(persisted);
		assertEquals(1, result);
	}

}
