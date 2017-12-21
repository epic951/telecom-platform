package com.epic951.telecomplatform.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TelecomServiceIntegrationTest {

	@Autowired
	private TelecomServiceHandler telecomServiceHandler;

	@Test
	public void testAddTelecomService() {

		// Create a telecomservice
		TelecomService roaming = new TelecomService();
		roaming.setTelecomService_name("Roaming");
		roaming.setTelecomService_type(true); // Subscription
		roaming.setOperator_package_id(55);

		// Test adding the telecomservice
		TelecomService t = telecomServiceHandler.addService(roaming);

		// Verify the addition of the new telecomservice and the integrity of the
		// specified data
		assertNotNull(t);
		assertNotNull(t.getTelecomService_id());
		assertEquals("Roaming", t.getTelecomService_name());
		System.err.println(t.toString());
	}

}
