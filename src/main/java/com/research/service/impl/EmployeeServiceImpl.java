package com.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.EmployeeDto;
import com.research.dto.project.ProjectDto;
import com.research.dto.project.ProjectEmployeesDto;
import com.research.entity.Employee;
import com.research.entity.ProjectEmployees;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.EmployeeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.EmployeeService;
import com.research.service.interfaces.ProjectEmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ProjectEmployeeService projectEmployeeService;
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public BaseRepository getBaseRepo() {
		return employeeRepo;
	}

	@Override
	public List<EmployeeDto> findByProjectId(ProjectDto projectDto) {
		List<ProjectEmployees> projectEmployees = projectEmployeeService.findByProjectId(projectDto.getId());
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		for (ProjectEmployees projectEmployee : projectEmployees){
			Employee employee = projectEmployee.getEmployeeId();
			EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

}
