package com.rabo.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rabo.api.bean.RequestBean;
import com.rabo.api.bean.ResponseBean;
import com.rabo.api.service.CustomerStatementService;

/**
 * The purpose of #CustomerStatementController.java is accepting the customer
 * statement records and process the internal details. The output is
 * ResponseEntity of Responsebean which contains the status of the given
 * records.
 * 
 * @author Meganathan Prabakaran
 **/
@Validated
@RestController
public class CustomerStatementController {

	private final Logger logger = Logger.getLogger(CustomerStatementController.class);
	@Autowired
	CustomerStatementService customerService;

	/**
	 * Accepting the given records.json and validating through @valid annotation .
	 * Thereafter calling the service to process the values.
	 */
	@PostMapping(value = "/validateReport", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> validateCustomerReport(@RequestBody @NotEmpty List<@Valid RequestBean> requestBeans) {
		logger.info("Start processing the customer report :: validateCustomerReport ::--->");
		ResponseBean responseBean = null;
		try {
			responseBean = customerService.validateCustomerStatement(requestBeans);
		} catch (Exception e) {
			logger.error("Exception in validateCustomerReport ::-->"+e.getMessage());
		}
		logger.info("End processing the customer report :: validateCustomerReport ::<---");
		return new ResponseEntity<>(responseBean, HttpStatus.OK);

	}
}
