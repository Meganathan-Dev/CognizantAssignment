package com.rabo.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabo.api.bean.RequestBean;
import com.rabo.api.bean.ResponseBean;
import com.rabo.api.common.StatementError;
import com.rabo.api.service.CustomerStatementService;

/**RaboBankControllerTests.java is used to testing the Spring MVC Controller in a legacy way of testing . 
 *However this module helps to ascertain the @WebMVCTest , @MockBean Concepts. 
 *The mature model of testing is Spring integration testing ,that is implemented in RaboBankCustomerStatementIntegrationTest.java
 *@author Meganathan Prabakaran**/
@WebMvcTest
public class RaboBankCustomerStatementServiceTests {
	private final Logger logger = Logger.getLogger(RaboBankCustomerStatementServiceTests.class);
	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private CustomerStatementService customerStateService;

	/** verify the given request is Successful */
	
	@Test
	public void verifySuccessfull() throws Exception {
		logger.info("Start verifySuccessfull ::--->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing1", 50.00, 20.00, 70.00);
		RequestBean reqbean2 = new RequestBean(1132112, "NQ980THGBPZPM4330H", "Testing1", 50.00, 20.00, 70.00);

		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		List<StatementError> stateError = new ArrayList<StatementError>();
		when(customerStateService.validateCustomerStatement(requestBeans))
				.thenReturn(new ResponseBean("Successful", stateError));

		mockmvc.perform(MockMvcRequestBuilders.post("/validateReport").content(asJsonString(requestBeans))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").value("Successful"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorRecords").isEmpty());
		logger.info("Complete verifySuccessfull :: <---");

	}

	/** verify the given request is duplicaterefernceNumber */
	@Test
	public void duplicateReferenceNumber() throws Exception {
		logger.info("Start duplicateReferenceNumber ::--->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing1", 50.00, 20.00, 70.00);
		RequestBean reqbean2 = new RequestBean(1132111, "NQ980THGBPZPM4330H", "Testing1", 50.00, 20.00, 70.00);

		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		List<StatementError> stateError = new ArrayList<StatementError>();
		StatementError statementError = new StatementError(1132111, "NQ980THGBPZPM4330H");
		stateError.add(statementError);

		when(customerStateService.validateCustomerStatement(requestBeans))
				.thenReturn(new ResponseBean("Duplicate_reference", stateError));

		mockmvc.perform(MockMvcRequestBuilders.post("/validateReport").content(asJsonString(requestBeans))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").value("Duplicate_reference"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorRecords[0].reference").value(1132111));
		logger.info("Complete duplicateReferenceNumber ::<---");
	}

	/** verify the given request is incorrectBalance */
	@Test
	public void incorrectBalance() throws Exception {
		logger.info("Start incorrectBalance ::--->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing1", 50.00, 20.00, 70.00);
		RequestBean reqbean2 = new RequestBean(1132112, "NQ980THGBPZPM4330H", "Testing1", 50.00, 20.00, 70.00);

		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		List<StatementError> stateError = new ArrayList<StatementError>();
		StatementError statementError = new StatementError(1132112, "NQ980THGBPZPM4330H");
		stateError.add(statementError);

		when(customerStateService.validateCustomerStatement(requestBeans))
				.thenReturn(new ResponseBean("Incorrect_End_Balance", stateError));

		mockmvc.perform(MockMvcRequestBuilders.post("/validateReport").content(asJsonString(requestBeans))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").value("Incorrect_End_Balance"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorRecords[0].reference").value(1132112));
		logger.info("Complete incorrectBalance :: <---");
	}

	/** verify the given request is duplicate reference and incorrectBalance */
	@Test
	public void duplicateNumIncorrectBalance() throws Exception {
		logger.info("Start duplicateNumIncorrectBalance :: --->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing1", 50.00, 20.00, 70.00);
		RequestBean reqbean2 = new RequestBean(1132111, "NQ980THGBPZPM4330H", "Testing1", 50.00, 30.00, 70.00);
		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		List<StatementError> stateError = new ArrayList<StatementError>();
		StatementError statementError = new StatementError(1132111, "NQ980THGBPZPM4330H");
		stateError.add(statementError);
		when(customerStateService.validateCustomerStatement(requestBeans))
				.thenReturn(new ResponseBean("Duplicate_Reference_Incorrect_End_Balance", stateError));

		mockmvc.perform(MockMvcRequestBuilders.post("/validateReport").content(asJsonString(requestBeans))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.result").value("Duplicate_Reference_Incorrect_End_Balance"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorRecords[0].reference").value(1132111));
		logger.info("Complete duplicateNumIncorrectBalance :: <---");
	}
	/**converting the list into JSON */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
