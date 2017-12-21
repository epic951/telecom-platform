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
		TelecomService newTelecomService = new TelecomService();
		newTelecomService.setTelecomServiceName("GPS Tracking");
		testEntityManager.persist(newTelecomService);

		// find and inserted record using repository class
		TelecomService foundTelecomService = telecomServiceRepository.findByTelecomServiceName("GPS Tracking");

		// Assertion
		assertThat(foundTelecomService.getTelecomServiceName(), is(equalTo("GPS Tracking")));

	}
}
