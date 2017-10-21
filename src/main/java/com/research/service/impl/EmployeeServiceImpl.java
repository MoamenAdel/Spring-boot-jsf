package com.research.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.research.dto.employee.EmployeeDto;
import com.research.entity.Employee;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.employee.EmployeeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.EmployeeService;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	DozerBeanMapper mapper;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {

		Employee employee = new Employee();
		mapper.map(employeeDto, employee);
		employee.setId(null);
		employee = save(employee);
		return mapper.map(employee, employeeDto.getClass());

	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> employeesDto = new ArrayList<>();
		List<Employee> employees = getAll();
		if (employees == null) {
			throw new RuntimeException();
		}
		for (Employee employee : employees) {
			EmployeeDto employeeDto = new EmployeeDto();
			mapper.map(employee, employeeDto);
			employeesDto.add(employeeDto);
		}
		return employeesDto;

	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		Employee employee = employeeRepo.findOne(employeeDto.getId());
		if (employee == null) {
			throw new BusinessException();
		}
		mapper.map(employeeDto, employee);
		employeeRepo.save(employee);
		mapper.map(employee, employeeDto);
		return employeeDto;
	}

	@Override
	public EmployeeDto findOne(Long id) {
		Employee employee = employeeRepo.findOne(id);
		if (employee == null) {
			throw new RuntimeException();
		}
		return mapper.map(employee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getEmployeePage(int first, int pageSize) {
		List<EmployeeDto> employeesDto = new ArrayList<>();
		PageRequest request = new PageRequest(first, pageSize);
		Page<Employee> employeesPage = employeeRepo.findAll(request);
		List<Employee> employees = employeesPage.getContent();
		if (employees == null) {
			throw new RuntimeException();
		}
		for (Employee employee : employees) {
			EmployeeDto employeeDto = new EmployeeDto();
			mapper.map(employee, employeeDto);
			employeesDto.add(employeeDto);
		}

		Collections.reverse(employeesDto);
		return employeesDto;
	}

	@Override
	public BaseRepository getBaseRepo() {
		return employeeRepo;
	}

}
