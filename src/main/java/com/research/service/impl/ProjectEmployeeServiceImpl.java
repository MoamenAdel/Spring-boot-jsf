package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.research.dto.project.ProjectEmployeesDto;
import com.research.entity.ProjectEmployees;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.ProjectEmployeeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.ProjectEmployeeService;

@Service
@Transactional
public class ProjectEmployeeServiceImpl extends
		BaseServiceImpl<ProjectEmployees> implements ProjectEmployeeService {
	@Autowired
	ProjectEmployeeRepo projectEmployeeRepo;
	@Autowired
	DozerBeanMapper mapper;

	@Override
	public BaseRepository getBaseRepo() {
		return projectEmployeeRepo;
	}

	@Override
	public ProjectEmployeesDto addProjectEmployee(
			ProjectEmployeesDto ProjectEmployeeDto) {

		ProjectEmployees temp = mapper.map(ProjectEmployeeDto,
				ProjectEmployees.class);
		save(temp);
		return null;
	}

	@Override
	public List<ProjectEmployeesDto> getSelectedProjectEmployeesByProjectId(
			Long projectId) {
		List<ProjectEmployees> projectEmployees = new ArrayList<ProjectEmployees>();
		List<ProjectEmployeesDto> projectEmployeesDto = new ArrayList<ProjectEmployeesDto>();
		projectEmployees = projectEmployeeRepo
				.getSelectedProjectEmployeesByProjectId(projectId);
		for (ProjectEmployees pe : projectEmployees) {
			ProjectEmployeesDto tempPED = new ProjectEmployeesDto();

			tempPED = mapper.map(pe, ProjectEmployeesDto.class);
			projectEmployeesDto.add(tempPED);
		}
		return projectEmployeesDto;
	}

	@Override
	public List<ProjectEmployees> findByProjectId(Long id) {
		return projectEmployeeRepo.findByProjectId(id);
	}

}
