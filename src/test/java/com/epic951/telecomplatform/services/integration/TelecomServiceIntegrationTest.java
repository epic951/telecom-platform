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

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class TelecomServiceIntegrationTest {

	@Autowired
	private TelecomServiceHandler telecomServiceHandler;

	@Test
	@Transactional
	public void testAddTelecomService() {

		// Create a telecomservice
		TelecomService roaming = TestUtilities.createTestTelecomService(5, "Roaming", "Zain", false, 77, 23, 75, 1);

		// Test adding the telecomservice
		TelecomService t = telecomServiceHandler.addOrUpdateService(roaming);

		// Verify the addition of the new telecomservice and the integrity of the
		// specified data
		assertNotNull(t);
		assertNotNull(t.getTelecomServiceId());
		assertEquals("Roaming", t.getTelecomServiceName());
		System.err.println(t.toString());
	}

}
