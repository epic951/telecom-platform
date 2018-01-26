package com.epic951.telecomplatform.services.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;
import com.epic951.utilities.TestUtilities;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TelecomServiceUnitTest {

	@Mock
	private TelecomServiceRepository mockedTelecomServiceRepository;

	@InjectMocks
	private TelecomServiceHandler mockedTelecomServiceHandler;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockedTelecomServiceRepository = mock(TelecomServiceRepository.class);
		mockedTelecomServiceHandler = new TelecomServiceHandler(mockedTelecomServiceRepository);
	}

	@Test
	public void testAddTelecomService() {

		// Create a telecomservice
		TelecomService roaming = TestUtilities.createTestTelecomService(16, "Roaming", "Etisalat", true, 14, 43, 76, "",
				1);
		TelecomService stickers = TestUtilities.createTestTelecomService(22, "Stickers", "Vodafone", false, 72, 30, 10,
				"", 1);
		System.err.println(roaming.toString() + "\n" + stickers.toString());

		// stub repository methods
		when(mockedTelecomServiceRepository.findByTelecomServiceId(Mockito.anyInt())).thenReturn(Optional.empty());
		when(mockedTelecomServiceRepository.findByTelecomServiceName(anyString())).thenReturn(Optional.empty());
		when(mockedTelecomServiceRepository.save(any(TelecomService.class))).thenReturn(roaming);

		// Verify mocked object is passed to the service no matter what is passed to
		// addservice method
		assertEquals(roaming, mockedTelecomServiceHandler.addOrUpdateService(stickers));
		assertEquals(roaming, mockedTelecomServiceHandler
				.addOrUpdateService(new TelecomService(334, "Call Keeper", false, 78, "Vodafone", 16, 88, " ", 0)));
	}

	@Test
	public void testDeleteTelecomService() {

		TelecomService roaming = TestUtilities.createTestTelecomService(16, "Roaming", "Etisalat", true, 14, 43, 76, "",
				1);

		// stub repository methods
		when(mockedTelecomServiceRepository.deleteByTelecomServiceName(anyString())).thenReturn(1);
		when(mockedTelecomServiceRepository.findByTelecomServiceId(Mockito.anyInt())).thenReturn(Optional.empty());
		when(mockedTelecomServiceRepository.findByTelecomServiceName(anyString())).thenReturn(Optional.empty());
		when(mockedTelecomServiceRepository.save(any(TelecomService.class))).thenReturn(roaming);

		TelecomService persisted = mockedTelecomServiceHandler.addOrUpdateService(roaming);
		int result = mockedTelecomServiceHandler.deleteService(persisted);
		assertEquals(1, result);
	}
}
