package com.rabo.api.service;

import java.util.List;

import com.rabo.api.bean.RequestBean;
import com.rabo.api.bean.ResponseBean;
import com.rabo.api.common.StatementError;

public interface CustomerStatementService  {

	public ResponseBean validateCustomerStatement(List<RequestBean> requestBeans) throws Exception;
	public String concludeResult(boolean isduplicate, boolean isIncorrectBalance);
	public boolean validateIncorrectBalance(RequestBean requestBean, List<StatementError> statementError);
	public boolean checkBalance(Double startBalance, Double mutation, Double endBalance);
}
