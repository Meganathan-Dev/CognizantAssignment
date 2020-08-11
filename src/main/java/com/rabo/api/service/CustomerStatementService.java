package com.rabo.api.service;

import java.util.List;

import com.rabo.api.bean.RequestBean;
import com.rabo.api.bean.ResponseBean;

public interface CustomerStatementService  {

	public ResponseBean validateCustomerStatement(List<RequestBean> requestBeans) throws Exception;
}
