package com.research.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.research.entity.TasksExpectedOutcomes;

public class TaskDto extends BaseDto {
	private Date startDate;
	private Date endDate;
	private Integer duration;
	private List<TasksExpectedOutcomesDto> tasksExpectedOutcomesCollection;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public List<TasksExpectedOutcomesDto> getTasksExpectedOutcomesCollection() {
		return tasksExpectedOutcomesCollection;
	}

	public void setTasksExpectedOutcomesCollection(List<TasksExpectedOutcomesDto> tasksExpectedOutcomesCollection) {
		this.tasksExpectedOutcomesCollection = tasksExpectedOutcomesCollection;
	}

}
