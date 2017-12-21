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

import com.epic951.business.controllers.TelecomServiceController;
import com.epic951.data.entities.TelecomService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TelecomServiceIntegrationTest {

	@Autowired
	private TelecomServiceController telecomServiceController;

	@Test
	public void testAddService() {
		// Create a service
		TelecomService gpsTracking = new TelecomService();
		gpsTracking.setTelecomServiceName("GPS Tracking");
		gpsTracking.setTelecomServiceType(true);

		// POST the new service we just added and check the outcome
		String outcome = telecomServiceController.processAddService(gpsTracking);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
	}
}
