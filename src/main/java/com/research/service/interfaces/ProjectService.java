package com.research.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.StreamedContent;

import com.research.dto.project.DocsDTO;
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
	
	List<ProjectDto> getProjectPage(int first, int pageSize);

//	List<StreamedContent> getDocsPage(ProjectDto projectDto,int i, int pageSize) throws IOException;
	List<DocsDTO> getDocs(ProjectDto projectDto);
	
}
