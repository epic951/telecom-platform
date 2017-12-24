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

import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testFindByProductName() {

		// setup data scenario
		Product newProduct = TestUtilities.createTestProduct("Mobile Internet", 9706, "LTE service", 43, 23);
		Product persisted = testEntityManager.merge(newProduct);

		// find and inserted record using repository class
		Product foundProduct = productRepository.findByProductName(persisted.getProductName()).get();

		// Assertion
		assertThat(foundProduct.getProductName(), is(equalTo("Mobile Internet")));
	}

	@Test
	public void testDeleteByProductName() {

		// setup data scenario
		Product newProduct = TestUtilities.createTestProduct("Smartphone", 3453, "device", 234, 12);
		testEntityManager.merge(newProduct);

		Integer result = productRepository.deleteByProductName("Smartphone");

		// Assertion
		System.err.println(result);
		assertThat("1", is(equalTo(String.valueOf(result))));
	}

}
