package com.research.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{
	private String describtion;
	private HttpStatus httpStatus;
	private Integer ecode;
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Integer getEcode() {
		return ecode;
	}
	public void setEcode(Integer ecode) {
		this.ecode = ecode;
	}
	public BusinessException( String describtion, HttpStatus httpStatus, Integer ecode) {
		super(describtion);
		this.describtion=describtion;
		this.httpStatus = httpStatus;
		this.ecode = ecode;
	}
	

}
