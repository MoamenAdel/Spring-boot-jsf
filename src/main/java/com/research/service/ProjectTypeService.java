package com.research.service;

import java.util.List;

import com.research.dto.ProjectTypeDto;
import com.research.entity.ProjectTypes;

public interface ProjectTypeService extends BaseService<ProjectTypes> {
	ProjectTypeDto addProjectType(ProjectTypeDto projectTypeDto);
	List<ProjectTypeDto> getAllProjectTypes();

}
