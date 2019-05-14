package com.group.bean;

import org.springframework.stereotype.Component;

@Component
public class ErrorBean {
private Integer code;
private String ErrorType,ErrorDetail=null;



public Integer getCode() {
	return code;
}

public void setCode(Integer code) {
	this.code = code;
}

public String getErrorType() {
	return ErrorType;
}

public void setErrorType(String errorType) {
	ErrorType = errorType;
}

public String getErrorDetail() {
	return ErrorDetail;
}

public void setErrorDetail(String errorDetail) {
	ErrorDetail = errorDetail;
}

}
