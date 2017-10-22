package com.research.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.research.dto.project.ProjectEmployeesDto;
import com.research.entity.ProjectEmployees;
import com.research.service.BaseService;

public interface ProjectEmployeeService extends BaseService<ProjectEmployees> {
	
	ProjectEmployeesDto addProjectEmployee(ProjectEmployeesDto ProjectEmployeeDto);

	List<ProjectEmployeesDto> getSelectedProjectEmployeesByProjectId(Long projectId);

}
