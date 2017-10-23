package com.research.service.interfaces;

import java.util.List;

import com.research.dto.project.EmployeeDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.Employee;
import com.research.service.BaseService;

public interface EmployeeService extends BaseService<Employee> {

	List<EmployeeDto> findByProjectId(ProjectDto projectDto);

}
