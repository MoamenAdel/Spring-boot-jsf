package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.entity.Lfm;
import com.research.entity.Project;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.ProjectRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.LFMService;
import com.research.service.interfaces.ProjectService;
import com.research.service.interfaces.ProjectTypeService;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {

	@Autowired

	ProjectRepo projectRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	ProjectTypeService projectTypeService;
	@Autowired
	private LFMService lfmService;

	@Override
	public BaseRepository getBaseRepo() {
		return projectRepo;
	}

	@Override
	public ProjectDto addProject(ProjectDto projectDto) {
		Project project = new Project();
		mapper.map(projectDto, project);
		project.setId(null);
		project.setTypeId(projectTypeService.findByType(projectDto.getType()));
		project = save(project);
		Lfm lfm = new Lfm();
		lfm.setProjectId(project);
		lfmService.save(lfm);
		return mapper.map(project, projectDto.getClass());
	}

	@Override
	public List<ProjectDto> getAllProjects() {
		List<ProjectDto> projectsDto = new ArrayList<>();
		List<Project> projects = getAll();
		for(Project project : projects){
			ProjectDto projectDto = new ProjectDto();
			mapper.map(project, projectDto);
			projectDto.setType(project.getTypeId().getType());
			projectsDto.add(projectDto);
		}
		return projectsDto;
	}

	@Override
	public ProjectDto updateProject(ProjectDto projectDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectTypeDto> getProjectTypes() {
		return projectTypeService.getAllProjectTypes();
	}

	@Override
	public ProjectDto findOne(Long id) {
		Project project = projectRepo.findOne(id);
		return mapper.map(project, ProjectDto.class);
	}
	
	

}
