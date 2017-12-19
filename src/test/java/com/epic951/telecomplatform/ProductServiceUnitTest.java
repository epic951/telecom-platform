package com.epic951.telecomplatform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductServiceUnitTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddProduct() {

		// Create a Product
		Product sms = new Product();
		sms.setProduct_name("Short Messaging Service");
		sms.setMin_price(100);
		sms.setMax_price(300);
		sms.setProduct_description("Casual messaging service used to exchange brief text-based messages");
		sms.setProduct_id(544);

		when(productRepository.save(any(Product.class))).thenReturn(sms);

		// Save the product
		Product p = productService.addProduct(null);

		// Verify the save
		assertNotNull(p);
		assertEquals(544, p.getProduct_id());
	}
}
