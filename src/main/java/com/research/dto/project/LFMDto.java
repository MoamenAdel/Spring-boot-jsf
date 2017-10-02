package com.research.dto.project;

import java.util.Collection;

import com.research.dto.BaseDto;

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

}
