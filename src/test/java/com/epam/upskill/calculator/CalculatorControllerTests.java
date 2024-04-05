package com.epam.upskill.calculator;

import static org.junit.Assert.assertEquals;

import com.epam.upskill.calculator.controllers.CalculatorController;
import com.epam.upskill.calculator.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	
	//thi method test the add operation of 1 + 1 
	@Test
	public void addPositiveOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": 1, \"operand2\": 1}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/add").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"\",\"error\":false,\"result\":2}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(expectedResult, response.getContentAsString());
	}
	
	@Test
	public void addNegativeOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": -1, \"operand2\": -1}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/add").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"\",\"error\":false,\"result\":-2}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(expectedResult, response.getContentAsString());
	}
	
	@Test
	public void addNullValueOperatorTest() throws Exception{

		String jsonBody = "{\"operand1\": null, \"operand2\": null}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/add").accept(
				MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"Error: One of the operands is not valid\",\"error\":true,\"result\":null}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		assertEquals(expectedResult, response.getContentAsString());

	}

	@Test
	public void divideZeroOperatorTest() throws Exception{
		String jsonBody = "{\"operand1\": 10, \"operand2\": 0}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/divide")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"Error: Can't divide by zero\",\"error\":true,\"result\":null}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(expectedResult, response.getContentAsString());
	}
	
	@Test
	public void negativeDivisionOperatorTest() throws Exception{
		String jsonBody = "{\"operand1\": -10, \"operand2\": 2}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Constants.CALCULATOR_PATH + "/divide")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expectedResult = "{\"message\":\"\",\"error\":false,\"result\":-5}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(expectedResult, response.getContentAsString());
	}

}
