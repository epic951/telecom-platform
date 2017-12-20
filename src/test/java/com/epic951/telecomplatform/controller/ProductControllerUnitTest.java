package com.epic951.telecomplatform.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	private MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddProduct() throws Exception {

		// setup mock product returned from the mocked service component
		Product mockProduct = new Product();
		mockProduct.setProduct_name("MMS");

		when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);

		// simulate the form bean that would POST from the web page
		Product newProduct = new Product();
		newProduct.setProduct_name("Voice Calls");
		newProduct.setProduct_id(218);

		// simulate the form submit (POST)
		mockMvc.perform(
				post("/test/addproduct", newProduct).content(this.json(newProduct)).contentType(contentType))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		// System.err.println(newProduct.toString());
		// mockMvc.perform(post("/test/addproduct",
		// newProduct)).andExpect(status().isOk()).andReturn();
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.jsonConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}