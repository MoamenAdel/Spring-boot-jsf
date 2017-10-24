package com.research.service.interfaces;

import java.util.List;

import org.primefaces.model.UploadedFile;

import com.research.dto.employee.EmployeeDto;
import com.research.entity.Employee;
import com.research.service.BaseService;
import com.research.dto.project.ProjectDto;

public interface EmployeeService extends BaseService<Employee> {
	EmployeeDto addEmployee(EmployeeDto employeeDto, UploadedFile cv, UploadedFile certificate, UploadedFile criminalStatus);

	List<EmployeeDto> getAllEmployees();

	List<EmployeeDto> getAutoCompleteEmployees(String name);

	EmployeeDto updateEmployee(EmployeeDto employeeDto);

	EmployeeDto findOne(Long id);

	List<EmployeeDto> getEmployeePage(int first, int pageSize);

	List<EmployeeDto> findByProjectId(ProjectDto projectDto);
}
