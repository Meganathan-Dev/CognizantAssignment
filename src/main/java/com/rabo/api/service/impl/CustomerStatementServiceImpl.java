package com.rabo.api.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rabo.api.bean.RequestBean;
import com.rabo.api.bean.ResponseBean;
import com.rabo.api.common.ResultConstants;
import com.rabo.api.common.StatementError;
import com.rabo.api.service.CustomerStatementService;

/**
 * The purpose of #CustomerStatementServiceImpl.java is logical processing the
 * given records and producing the valid #Response{status, statementError} .
 * 
 * @author Meganathan Prabakaran
 **/
@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	private final Logger logger = Logger.getLogger(CustomerStatementServiceImpl.class);

	/**
	 * Processing the records , checking the duplicate reference using HashMap and
	 * normal logic implement for incorrect balance
	 */
	@Override
	public ResponseBean validateCustomerStatement(List<RequestBean> requestBeans) throws Exception {
		logger.info("Process validateCustomerStatement  :: requestBean size --->" + requestBeans.size());
		List<StatementError> statementError = new ArrayList<>();
		String result = ResultConstants.SUCCESSFUL.toString();
		boolean isduplicate = false;
		boolean isIncorrectBalance = false;
		try {
			Map<Integer, Integer> duplicateReferenceNumberandAccountNumber = new HashMap<>();
			for (RequestBean requestBean : requestBeans) {
				int count = 1;
				if (null != duplicateReferenceNumberandAccountNumber.get(requestBean.getReference())) {
					isduplicate = true;
					StatementError stateError = new StatementError(requestBean.getReference(),
							requestBean.getAccountNumber());
					statementError.add(stateError);
					isIncorrectBalance = isIncorrectBalance ? isIncorrectBalance
							: validateIncorrectBalance(requestBean, statementError);
				} else {
					isIncorrectBalance = isIncorrectBalance ? isIncorrectBalance
							: validateIncorrectBalance(requestBean, statementError);
					duplicateReferenceNumberandAccountNumber.put(requestBean.getReference(), count);
				}
			}
			result = concludeResult(isduplicate, isIncorrectBalance);
		} catch (Exception e) {
			logger.info("Exception while performing the task validateCustomerStatement  :: <---" + e.getMessage());
			throw e;
		}
		logger.info("Complete validateCustomerStatement :: <---");
		return new ResponseBean(result, statementError);
	}

	/**Concluding the result based on duplicate and incorrect balance*/
	@Override
	public String concludeResult(boolean isduplicate, boolean isIncorrectBalance) {
		if (isIncorrectBalance && isduplicate) {
			return ResultConstants.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE.getValue();
		} else if (isIncorrectBalance) {
			return ResultConstants.INCORRECT_END_BALANCE.getValue();
		} else if (isduplicate) {
			return ResultConstants.DUPLICATE_REFERENCE.getValue();
		} else {
			return ResultConstants.SUCCESSFUL.getValue();
		}

	}

	/**validate the incorrect balance based on the given start balance and mutation */
	@Override
	public boolean validateIncorrectBalance(RequestBean requestBean, List<StatementError> statementError) {
		logger.info("Start validateIncorrectBalance :: --->  StartBalance :: " + requestBean.getStartBalance()
				+ " Mutation " + requestBean.getMutation() + " End Balance " + requestBean.getEndBalance());
		if (!checkBalance(requestBean.getStartBalance(), requestBean.getMutation(), requestBean.getEndBalance())) {
			StatementError stateError = new StatementError(requestBean.getReference(), requestBean.getAccountNumber());
			statementError.add(stateError);
			return true;
		}
		logger.info("Complete validateIncorrectBalance :: <---");
		return false;

	}

	/**Note : Rounding off decimal digits in order to avoid the decimal point error which leads to incorrect validation*/
	@Override
	public boolean checkBalance(Double startBalance, Double mutation, Double endBalance) {
		logger.info("After the addtion of  satrt balance and Mutation :: "
				+ BigDecimal.valueOf(startBalance + mutation).setScale(2, RoundingMode.HALF_UP).doubleValue());
		return endBalance == BigDecimal.valueOf(startBalance + mutation).setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}

}
