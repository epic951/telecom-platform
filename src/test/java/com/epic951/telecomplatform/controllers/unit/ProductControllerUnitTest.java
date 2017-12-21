package com.epic951.telecomplatform.controllers.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.epic951.business.controllers.ProductController;
import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;
import com.epic951.utilities.HTTPUtilities;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddProduct() throws Exception {

		// setup mock product returned from the mocked service component
		Product mockProduct = new Product();
		mockProduct.setProductName("MMS");
		mockProduct.setProductId(99);

		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(mockProduct);

		// simulate the form bean that would POST from the web page
		Product newProduct = new Product();
		newProduct.setProductName("Voice Calls");
		newProduct.setProductId(218);

		// simulate the form submit (POST)
		mockMvc.perform(post("/test/addproduct", newProduct).content(this.json(newProduct))
				.contentType(HTTPUtilities.JSON_CONTENT_TYPE)).andDo(print()).andExpect(status().isOk()).andReturn();
		System.err.println(newProduct.toString());
		System.err.println(this.json(newProduct));

		mockMvc.perform(get("/lol")).andExpect(status().isNotFound());

		// System.err.println(newProduct.toString());
		// mockMvc.perform(post("/test/addproduct",
		// newProduct)).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	private String json(Object o) throws IOException {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		jsonConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}