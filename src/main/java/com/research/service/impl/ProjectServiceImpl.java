package com.research.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@Transactional
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
		// PageRequest request = new PageRequest(1, 1);
		// Page<Project> projectsPage = projectRepo.findAll(request);
		List<Project> projects = getAll();
		if (projects == null) {
			throw new RuntimeException();
		}
		for (Project project : projects) {
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
		if (project == null) {
			throw new RuntimeException();
		}
		return mapper.map(project, ProjectDto.class);
	}

	@Override
	public List<ProjectDto> getProjectPage(int first, int pageSize) {
		List<ProjectDto> projectsDto = new ArrayList<>();
		System.out.println("first : " + first + "  pageSize : " + pageSize);
		PageRequest request = new PageRequest(first, pageSize);
		Page<Project> projectsPage = projectRepo.findAll(request);
		List<Project> projects = projectsPage.getContent();
		if (projects == null) {
			throw new RuntimeException();
		}
		for (Project project : projects) {
			ProjectDto projectDto = new ProjectDto();
			mapper.map(project, projectDto);
			projectDto.setType(project.getTypeId().getType());
			projectsDto.add(projectDto);
		}

		Collections.reverse(projectsDto);
		return projectsDto;
	}

	@Override
	public long count() {
		return projectRepo.count();
	}

}
