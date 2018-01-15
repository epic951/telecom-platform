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

import com.epic951.business.controllers.TelecomServiceController;
import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;
import com.epic951.utilities.HTTPUtilities;
import com.epic951.utilities.TestUtilities;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TelecomServiceController.class, secure = false)
public class TelecomServiceUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TelecomServiceHandler telecomServiceHandler;

	@InjectMocks
	private TelecomServiceController telecomServiceController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddProduct() throws Exception {

		// setup mock product returned from the mocked service component
		TelecomService mockTelecomService = TestUtilities.createTestTelecomService(87, "Zain", "Stickers", false, 6363,
				858, 326);

		when(telecomServiceHandler.addOrUpdateService(Mockito.isA(TelecomService.class)))
				.thenReturn(mockTelecomService);

		// simulate the form bean that would POST from the web page
		TelecomService newTelecomService = TestUtilities.createTestTelecomService(0, null, null, false, 0, 0, 0);

		// simulate the form submit (POST)
		mockMvc.perform(post("/api/addservice", newTelecomService).header("Origin", "")
				.header("Host", "telecom-platform-backend.herokuapp.com").content(this.json(newTelecomService))
				.contentType(HTTPUtilities.JSON_CONTENT_TYPE)).andDo(print()).andExpect(status().isOk()).andReturn();
		System.err.println(newTelecomService.toString());
		System.err.println(this.json(newTelecomService));

	}

	private String json(Object o) throws IOException {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		jsonConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}