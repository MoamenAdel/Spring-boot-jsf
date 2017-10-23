package com.research.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.dto.project.ProjectDto;
import com.research.entity.ProjectEmployees;
import com.research.service.BaseService;

public interface ProjectEmployeeService extends BaseService<ProjectEmployees> {

	@Query("SELECT pe FROM ProjectEmployees WHERE projectId.id = ?")
	List<ProjectEmployees> findByProjectId(Long id);

}
