package com.epic951.telecomplatform.services.unit;

import static org.junit.Assert.assertEquals;
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
		sms.setProductName("Silent");
		sms.setProductDescription("messages");
		sms.setProductId(44);

		when(productRepository.save(any(Product.class))).thenReturn(sms);

		Product newProduct = productService.addProduct(new Product(55, "rrrr", "ttt", 5, 66));

		// Verify mocked object is passed to the service no matter what is passed to
		// addproduct method
		assertEquals("Silent", newProduct.getProductName());
		System.err.println(newProduct.toString());
	}
}
