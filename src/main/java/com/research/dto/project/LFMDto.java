package com.research.dto.project;

import java.util.ArrayList;
import java.util.Collection;

import com.research.dto.BaseDto;
import com.research.dto.project.ProjectDto;

public class LFMDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private ProjectDto projectDtoId;
	private Collection<TaskDTO> tasksDtoCollection;

	public ProjectDto getProjectDtoId() {
		return projectDtoId;
	}

	public void setProjectDtoId(ProjectDto projectDtoId) {
		this.projectDtoId = projectDtoId;
	}

	public Collection<TaskDTO> getTasksDtoCollection() {
		if (tasksDtoCollection == null) {
			tasksDtoCollection = new ArrayList<TaskDTO>();

		}
		return tasksDtoCollection;
	}

	public void setTasksDtoCollection(Collection<TaskDTO> tasksDtoCollection) {
		this.tasksDtoCollection = tasksDtoCollection;
	}

}
