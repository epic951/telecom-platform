package com.epic951.telecomplatform.service;

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

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TelecomServiceUnitTest {
	@Mock
	private TelecomServiceRepository telecomServiceRepository;

	@InjectMocks
	private TelecomServiceHandler telecomServiceHandler;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddTelecomService() {

		// Create a telecomservice
		TelecomService roaming = new TelecomService();
		roaming.setTelecomService_name("Roaming");
		roaming.setTelecomService_type(true); // Subscription
		roaming.setOperator_package_id(55);

		when(telecomServiceRepository.save(any(TelecomService.class))).thenReturn(roaming);

		TelecomService newService = telecomServiceHandler
				.addService(new TelecomService(0, "Video Calling", false, 55, 75, 211));
		// Verify mocked object is passed to the service no matter what is passed to
		// addservice method
		assertEquals("Roaming", newService.getTelecomService_name());
		System.err.println(newService.toString());
	}
}
