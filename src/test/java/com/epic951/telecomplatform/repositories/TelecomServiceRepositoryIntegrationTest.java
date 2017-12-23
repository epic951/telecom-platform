package com.epic951.telecomplatform.repositories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TelecomServiceRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private TelecomServiceRepository telecomServiceRepository;

	@Test
	public void testFindByTelecomServiceName() {

		// setup data scenario
		TelecomService newTelecomService = TestUtilities.createTestTelecomService(265, "Zain", "LTE", false, 636, 8585,
				324);
		TelecomService persisted = testEntityManager.merge(newTelecomService);
		System.err.println(persisted.toString());

		// find an inserted record using repository class
		TelecomService foundTelecomService = telecomServiceRepository
				.findByTelecomServiceName(persisted.getTelecomServiceName()).get();

		// Assertion
		assertThat(foundTelecomService.getTelecomServiceName(), is(equalTo("LTE")));
	}

	@Test
	public void testDeleteByTelecomServiceName() {

		// setup data scenario
		TelecomService newTelecomService = TestUtilities.createTestTelecomService(8335, "Zain", "GPRS", false, 732, 32,
				74);
		testEntityManager.merge(newTelecomService);

		Integer result = telecomServiceRepository.deleteByTelecomServiceName("GPRS");

		// Assertion
		System.err.println(result);
		assertThat("1", is(equalTo(String.valueOf(result))));
	}
}
