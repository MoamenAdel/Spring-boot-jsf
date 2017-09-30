package com.research.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.ProjectTypeDto;
import com.research.entity.ProjectTypes;
import com.research.repositories.BaseRepository;
import com.research.repositories.ProjectTypeRepo;
import com.research.service.ProjectTypeService;
@Service
public class ProjectTypeServiceImpl extends BaseServiceImpl<ProjectTypes> implements ProjectTypeService {

	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	ProjectTypeRepo projectTypeRepo;

	@Override
	public BaseRepository getBaseRepo() {
		return projectTypeRepo;
	}

	@Override
	public ProjectTypeDto addProjectType(ProjectTypeDto projectTypeDto) {
		ProjectTypes projectTypes = save(mapper.map(projectTypeDto, ProjectTypes.class));
		return mapper.map(projectTypes, ProjectTypeDto.class);
	}

	@Override
	public List<ProjectTypeDto> getAllProjectTypes() {
		List<ProjectTypeDto> projectTypeDtos = new ArrayList<>();
		mapper.map(getAll(), projectTypeDtos);
		return projectTypeDtos;
	}

}
