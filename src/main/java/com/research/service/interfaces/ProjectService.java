package com.research.service.interfaces;

import java.util.List;

import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.entity.Project;
import com.research.service.BaseService;

public interface ProjectService extends BaseService<Project> {
	ProjectDto addProject(ProjectDto projectDto);

	List<ProjectDto> getAllProjects();

	ProjectDto updateProject(ProjectDto projectDto);
	
	List<ProjectTypeDto> getProjectTypes();
	
	ProjectDto findOne(Long id);
}
