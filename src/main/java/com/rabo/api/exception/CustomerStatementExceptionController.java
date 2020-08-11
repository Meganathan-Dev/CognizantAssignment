package com.rabo.api.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rabo.api.bean.ResponseBean;
import com.rabo.api.common.ResultConstants;
import com.rabo.api.common.StatementError;

/**
 * The purpose of #CustomerStatementExceptionController.java is handling the exception
 * which appear during the validation and runtime process . 
 * @author Meganathan Prabakaran
 **/
@ControllerAdvice
public class CustomerStatementExceptionController {
	
	private final Logger logger = Logger.getLogger(CustomerStatementExceptionController.class);
	/**Handling validation exception {HttpMessageNotReadableException,ConstraintViolationException} and runtime exception {InternalServerException} */
	@ExceptionHandler({ InternalServerException.class, HttpMessageNotReadableException.class,ConstraintViolationException.class })
	public ResponseEntity<ResponseBean> exception(Exception exception) {
		ResponseBean responseBean = null;
		ResponseEntity<ResponseBean> responseEntity = null;
		List<StatementError> statementError = new ArrayList<>();
		if (exception instanceof InternalServerException) {
			logger.info("Internal service exception "+exception.getMessage());
			responseBean = new ResponseBean(ResultConstants.INTERNAL_SERVER_ERROR.getValue(), statementError);
			responseEntity = new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (exception instanceof HttpMessageNotReadableException||exception instanceof ConstraintViolationException) {
			logger.info("HttpMessageReadable or Constraintviolation  exception "+exception.getMessage());
			responseBean = new ResponseBean(ResultConstants.BAD_REQUEST.getValue(), statementError);
			responseEntity = new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);

		}
		return responseEntity;

	}

}
