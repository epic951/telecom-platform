package com.epic951.telecomplatform.controllers.integration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.business.controllers.TelecomServiceController;
import com.epic951.business.exceptions.MissingRequiredValueException;
import com.epic951.business.exceptions.OperatorRequirementsViolationException;
import com.epic951.data.entities.TelecomService;
import com.epic951.utilities.HTTPUtilities;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TelecomServiceIntegrationTest {

	@Autowired
	private TelecomServiceController telecomServiceController;

	@Test
	public void testAddService()
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {
		// Create a service
		TelecomService gpsTracking = TestUtilities.createTestTelecomService(92934, "Zain", "GPS-tracking", false, 89,
				6585, 799, 1);

		// POST the new service we just added and check the outcome
		ResponseEntity<String> outcome = telecomServiceController.processAddService(gpsTracking);

		// Assert that the outcome is as expected
		assertThat(outcome, is(equalTo(HTTPUtilities.handleResponse("Success"))));
	}

	@Test
	public void testAddServiceWithoutTelecomServiceName()
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {
		// Create a service
		TelecomService gpsTracking = TestUtilities.createTestTelecomService(1, "Zain", null, false, 8, 9, 4, 1);

		// Assert that the faulty adding process fails and throws an exception as
		// expected
		assertThatThrownBy(() -> {
			telecomServiceController.processAddService(gpsTracking);
		}).isInstanceOf(MissingRequiredValueException.class)
				.hasMessageContaining("Neither the Telecom Service name or the Operator name can be empty");
	}
}
