package com.research.dto.project;

import com.research.dto.BaseDto;

public class TasksExpectedOutcomesDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private String expectation;

	public TasksExpectedOutcomesDto(String expectation) {
		this.expectation = expectation;
	}

	public String getExpectation() {
		return expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

}