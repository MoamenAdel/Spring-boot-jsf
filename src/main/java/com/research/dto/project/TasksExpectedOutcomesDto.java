package com.research.dto.project;

import com.research.dto.BaseDto;

public class TasksExpectedOutcomesDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private String expectation;
	private TaskDTO taskDTO;

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setTaskDTO(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
	}

	public TasksExpectedOutcomesDto() {
	}
	
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