package com.epic951.telecomplatform.services.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;
import com.epic951.utilities.TestUtilities;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductServiceUnitTest {

	@Mock
	private ProductRepository mockedProductRepository;

	@InjectMocks
	private ProductService mockedProductService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockedProductRepository = mock(ProductRepository.class);
		mockedProductService = new ProductService(mockedProductRepository);
		// mockedProductService = mock(ProductService.class,
		// Mockito.RETURNS_DEEP_STUBS);
	}

	@Test
	public void testFindProduct() {
		Product sms = TestUtilities.createTestProduct(44, "Silent", "messages", 23, 63, 1);
		Product mms = TestUtilities.createTestProduct(55, "Loud", "video", 23, 12, 1);
		List<Product> products = new ArrayList<>();
		products.add(sms);
		products.add(mms);
		when(mockedProductRepository.findAll()).thenReturn(products);

		assertEquals(mockedProductService.findProduct(sms), sms);
	}

	@Test
	public void testAddProduct() {

		// Create a Product
		Product sms = TestUtilities.createTestProduct(44, "Silent", "messages", 2, 6, 1);
		Product mms = TestUtilities.createTestProduct(55, "Loud", "video", 2342, 12, 1);

		// stub repository methods
		when(mockedProductRepository.findByProductId(Mockito.anyInt())).thenReturn(Optional.empty());
		when(mockedProductRepository.findByProductName(Mockito.anyString())).thenReturn(Optional.empty());
		when(mockedProductRepository.save(any(Product.class))).thenReturn(sms);

		// Verify mocked object is passed to the service no matter what is passed to
		// addproduct method
		assertEquals(sms, mockedProductService.addOrUpdateProduct(mms));
		assertEquals(sms, mockedProductService.addOrUpdateProduct(new Product(7723, "firebolt", "dd", 44, 33, " ", 0)));
	}

	@Test
	public void testDeleteByProductName() {
		// Create a Product
		Product sms = TestUtilities.createTestProduct(44, "Silent", "messages", 12, 52, 1);

		// stub repository methods
		when(mockedProductRepository.deleteByProductName(anyString())).thenReturn(1);
		when(mockedProductRepository.findByProductId(Mockito.anyInt())).thenReturn(Optional.empty());
		when(mockedProductRepository.findByProductName(Mockito.anyString())).thenReturn(Optional.empty());
		when(mockedProductRepository.save(any(Product.class))).thenReturn(sms);

		Product persisted = mockedProductService.addOrUpdateProduct(sms);
		int result = mockedProductService.deleteProductByProductName(persisted.getProductName());
		assertEquals(1, result);
	}
}
