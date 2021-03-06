package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.research.dto.project.ProjectTypeDto;
import com.research.entity.ProjectTypes;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.ProjectTypeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.ProjectTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@Transactional
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
        List<ProjectTypes> projectTypes = getAll();
        for (ProjectTypes projectType : projectTypes) {
            ProjectTypeDto projectTypeDto = new ProjectTypeDto();
            mapper.map(projectType, projectTypeDto);
            projectTypeDtos.add(projectTypeDto);
        }

        return projectTypeDtos;
    }

    @Override
    public ProjectTypes findByType(String type) {
        ProjectTypes projectType = projectTypeRepo.findByType(type);
        if (projectType == null) {
            throw new BusinessException("", HttpStatus.BAD_REQUEST, 3);
        }
        return projectType;
    }

    @Override
    public List<ProjectTypeDto> getPage(int first, int pagesize) {
        List<ProjectTypeDto> projectTypeDtos = new ArrayList<>();
        PageRequest request = new PageRequest(first, pagesize);
        Page<ProjectTypes> projectTypes = projectTypeRepo.findAll(request);
        List<ProjectTypes> pts = projectTypes.getContent();
        if (pts == null) {
            throw new BusinessException("no data", HttpStatus.NOT_FOUND, 1);
        }
        for (ProjectTypes projectType : pts) {
            projectTypeDtos.add(mapper.map(projectType, ProjectTypeDto.class));
        }
        return projectTypeDtos;
    }

	


}
