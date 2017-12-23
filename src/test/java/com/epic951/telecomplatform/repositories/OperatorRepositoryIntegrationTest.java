package com.epic951.telecomplatform.repositories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic951.data.entities.Operator;
import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.OperatorRepository;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OperatorRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private OperatorRepository operatorRepository;

	@Test
	public void testFindByOperatorName() {

		// setup data scenario
		Operator newOperator = TestUtilities.createTestOperator(10, "UAE", "Zain");
		Operator persisted = testEntityManager.merge(newOperator);
		System.err.println(persisted.toString());

		// find an inserted record using repository class
		Operator foundOperator = operatorRepository.findByOperatorName(persisted.getOperatorName()).get();

		// Assertion
		assertThat(foundOperator.getOperatorName(), is(equalTo("Zain")));
	}

	@Test
	public void testDeleteByOperatorName() {

		// setup data scenario
		Operator newOperator = TestUtilities.createTestOperator(36363, "UAE", "Zain");
		testEntityManager.merge(newOperator);

		Integer result = operatorRepository.deleteByOperatorName("Zain");

		// Assertion
		System.err.println(result);
		assertThat("1", is(equalTo(String.valueOf(result))));
	}
}
