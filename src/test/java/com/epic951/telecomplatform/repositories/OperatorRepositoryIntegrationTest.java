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

import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;

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
		Operator newOperator = new Operator();
		newOperator.setOperatorName("Orange");
		testEntityManager.persist(newOperator);

		// find and inserted record using repository class
		Operator foundOperator = operatorRepository.findByOperatorName("Orange");

		// Assertion
		assertThat(foundOperator.getOperatorName(), is(equalTo("Orange")));

	}
}
