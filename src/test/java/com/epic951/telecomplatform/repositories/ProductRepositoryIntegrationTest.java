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
		Product newProduct = new Product();
		newProduct.setProductName("Mobile Internet");
		testEntityManager.persist(newProduct);

		// find and inserted record using repository class
		Product foundProduct = productRepository.findByProductName("Mobile Internet");

		// Assertion
		assertThat(foundProduct.getProductName(), is(equalTo("Mobile Internet")));

	}

}
