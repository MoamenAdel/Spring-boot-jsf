package com.research.dto;

abstract class TaskExpectedOutcomeDto extends BaseDto {
	private String expectation;

	public String getExpectation() {
		return expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

}
