package com.epic951.telecomplatform.controllers.unit;

import static org.mockito.Mockito.when;
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

import com.epic951.business.controllers.OperatorController;
import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;
import com.epic951.utilities.HTTPUtilities;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OperatorController.class, secure = false)
public class OperatorControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OperatorService operatorService;

	@InjectMocks
	private OperatorController operatorController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddOperator() throws Exception {
		// setup mock product returned from the mocked service component
		Operator mockOperator = TestUtilities.createTestOperator(353, "UAE", "Zain");

		when(operatorService.addOrUpdateOperator(Mockito.isA(Operator.class))).thenReturn(mockOperator);

		// simulate the form bean that would POST from the web page
		Operator newOperator = TestUtilities.createTestOperator(0, null, null);

		// simulate the form submit (POST)
		mockMvc.perform(post("/addoperator", newOperator).content(this.json(newOperator))
				.contentType(HTTPUtilities.JSON_CONTENT_TYPE)).andDo(print()).andExpect(status().isOk()).andReturn();
		System.err.println(newOperator.toString());
		System.err.println(this.json(newOperator));

	}

	private String json(Object o) throws IOException {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		jsonConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
