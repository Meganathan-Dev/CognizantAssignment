package com.rabo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabo.api.bean.RequestBean;
import com.rabo.api.bean.ResponseBean;
import com.rabo.api.service.CustomerStatementService;

/**
 * RaboBankCustomerStatementApplicationTests.java is used to test the service
 * layer methods .
 * @author Meganathan Prabakaran
 **/
@SpringBootTest
class RaboBankCustomerStatementApplicationTests {
	private final Logger logger = Logger.getLogger(RaboBankCustomerStatementApplicationTests.class);
	@Autowired
	CustomerStatementService customerService;
	
	/** Testing whether the response bean  produce the result as Duplicate_reference*/
	@Test
	public void validateCustomerStatementDuplicateReference() {
		logger.info("start validateCustomerStatementDuplicateReference ::---->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing1", 50.00, 20.00, 70.00);
		RequestBean reqbean2 = new RequestBean(1132111, "NQ980THGBPZPM8420H", "Testing1", 50.00, 20.00, 70.00);
		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		try {
			ResponseBean responseBean = customerService.validateCustomerStatement(requestBeans);
			assertEquals("Duplicate_reference", responseBean.getResult());

		} catch (Exception e) {
			logger.error(e.getCause());
		}
		logger.info("complete validateCustomerStatementDuplicateReference ::<----");

	}
	/** Testing whether the response bean  produce the result as Incorrect_End_Balance*/
	@Test
	public void validateCustomerStatementIncorrectBalance() {
		logger.info("start validateCustomerStatementIncorrectBalance ::---->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing2", 50.00, 20.00, 29.00);
		RequestBean reqbean2 = new RequestBean(1132112, "NQ980THGBPZPM4230H", "Testing2", 50.00, 20.00, 30.00);
		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		try {
			ResponseBean responseBean = customerService.validateCustomerStatement(requestBeans);
			assertEquals("Incorrect_End_Balance", responseBean.getResult());

		} catch (Exception e) {
			logger.error(e.getCause());
		}
		logger.info("complete validateCustomerStatementIncorrectBalance ::<----");
	}
	/** Testing whether the response bean  produce the result as Duplicate_Reference_Incorrect_End_Balance*/
	@Test
	public void validateCustomerStatementDuplicateReferenceAndIncorrectBalance() {
		logger.info("Start validateCustomerStatementDuplicateReferenceAndIncorrectBalance ::---->");
		RequestBean reqbean1 = new RequestBean(1132111, "NQ980THGBPZPM8220H", "Testing3", 50.00, 20.00, 70.00);
		RequestBean reqbean2 = new RequestBean(1132111, "NQ980THGBPZPM4230H", "Testing3", 50.00, 20.00, 30.00);
		List<RequestBean> requestBeans = new ArrayList<>();
		requestBeans.add(reqbean1);
		requestBeans.add(reqbean2);
		try {
			ResponseBean responseBean = customerService.validateCustomerStatement(requestBeans);
			assertEquals("Duplicate_Reference_Incorrect_End_Balance", responseBean.getResult());

		} catch (Exception e) {
			logger.error(e.getCause());
		}
		logger.info("Complete validateCustomerStatementDuplicateReferenceAndIncorrectBalance ::---->");
	}

}
