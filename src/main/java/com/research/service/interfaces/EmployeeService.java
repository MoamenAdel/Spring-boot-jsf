package com.research.service.interfaces;

import java.util.List;
import com.research.dto.employee.EmployeeDto;
import com.research.entity.Employee;
import com.research.service.BaseService;

public interface EmployeeService extends BaseService<Employee> {
	EmployeeDto addEmployee(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmployees();

	EmployeeDto updateEmployee(EmployeeDto employeeDto);

	EmployeeDto findOne(Long id);

	List<EmployeeDto> getEmployeePage(int first, int pageSize);


}
