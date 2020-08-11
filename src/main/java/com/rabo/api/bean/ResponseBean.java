package com.rabo.api.bean;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rabo.api.common.StatementError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL)
	private String result;

	@JsonInclude(Include.NON_NULL)
	private List<StatementError> errorRecords;

}
