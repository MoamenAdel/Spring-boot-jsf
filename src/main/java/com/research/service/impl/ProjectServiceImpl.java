package com.research.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.research.dto.project.DocsDTO;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectTypeDto;
import com.research.entity.Docs;
import com.research.entity.Lfm;
import com.research.entity.Project;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.DocsRepository;
import com.research.repositories.project.ProjectRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.DocsService;
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
	@Autowired
	private Environment env;
	@Autowired
	DocsRepository docsRepo;

	@Override
	public BaseRepository getBaseRepo() {
		return projectRepo;
	}

	@Override
	public ProjectDto addProject(ProjectDto projectDto) {
		validateDto(projectDto);
		Project project = new Project();
		mapper.map(projectDto, project);
		project.setId(null);
		project.setTypeId(projectTypeService.findByType(projectDto.getType()));
		project = save(project);
		Lfm lfm = new Lfm();
		lfm.setProjectId(project);
		lfmService.save(lfm);
		File file = new File(env.getProperty("upload.path") + projectDto.getTitle());
		file.mkdirs();
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
		validateDto(projectDto);
		Project project = projectRepo.findOne(projectDto.getId());
		if (project == null) {
			throw new BusinessException();
		}
		mapper.map(projectDto, project);
		projectRepo.save(project);
		mapper.map(project, projectDto);
		return projectDto;
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

	private void validateDto(ProjectDto dto) {
		if (dto.getTitle() == null || dto.getTitle().equals("") || dto.getAbbreviation() == null
				|| dto.getAbbreviation().equals("") || dto.getApplicantName() == null
				|| dto.getApplicantName().equals("") || dto.getApplicantOrganization() == null
				|| dto.getApplicantOrganization().equals("") || dto.getBudget() == null || dto.getBudget() == 0
				|| dto.getType() == null || dto.getType().equals("") || dto.getSubmissionDate() == null) {
			throw new BusinessException();
		}
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		Calendar projectCal = new GregorianCalendar();
		projectCal.setTime(dto.getSubmissionDate());

		int yearDef = projectCal.get(Calendar.YEAR) - now.get(Calendar.YEAR);
		int diff = yearDef * 12 + projectCal.get(Calendar.MONTH) - now.get(Calendar.MONTH);

		// if (diff <= 0) {
		// throw new BusinessException();
		// }
	}

	// @Override
	// public List<StreamedContent> getDocsPage(ProjectDto project, int i, int
	// pageSize) throws IOException {
	// // TODO Auto-generated method stub
	// List<StreamedContent> streamedContents = new ArrayList<>();
	// Project project2 = getOne(project.getId());
	// for (Docs docs : project2.getDocsCollection()) {
	// streamedContents.add(n);
	//
	// }
	// return streamedContents;
	//
	// }

	@Override
	public List<DocsDTO> getDocs(ProjectDto projectDto) {
		// TODO Auto-generated method stub
		List<DocsDTO> docsDTOs = new ArrayList<>();
		Project project = projectRepo.findOne(projectDto.getId());
		// List<Docs> docses = (List<Docs>) project.getDocsCollection();
		// Iterator<Docs> docsIterator = project.getDocsCollection().iterator();
		List<Docs> docs = docsRepo.findByProjectId(project);
		for (Docs doc : docs)
			docsDTOs.add(mapper.map(doc, DocsDTO.class));

		return docsDTOs;
	}

}
