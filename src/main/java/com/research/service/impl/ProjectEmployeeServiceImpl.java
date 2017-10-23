package com.research.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.ProjectDto;
import com.research.entity.ProjectEmployees;
import com.research.repositories.BaseRepository;
import com.research.repositories.ProjectEmployeeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.ProjectEmployeeService;

@Service
public class ProjectEmployeeServiceImpl extends BaseServiceImpl<ProjectEmployees> implements ProjectEmployeeService{

	@Autowired
	private ProjectEmployeeRepo projectEmployeeRepo; 
	
	@Override
	public BaseRepository getBaseRepo() {
		return projectEmployeeRepo;
	}

	@Override
	public List<ProjectEmployees> findByProjectId(Long id) {
		return projectEmployeeRepo.findByProjectrId(id);
	}

}
