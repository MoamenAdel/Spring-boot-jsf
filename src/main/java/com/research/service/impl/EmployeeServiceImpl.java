package com.research.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.Employee;
import com.research.entity.ProjectEmployees;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.employee.EmployeeRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.EmployeeService;
import com.research.service.interfaces.ProjectEmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements
		EmployeeService {

	@Autowired
	private Environment env;
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	private ProjectEmployeeService projectEmployeeService;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto, UploadedFile cv,
			UploadedFile certificate, UploadedFile criminalStatus) {

		Employee employee = new Employee();
		mapper.map(employeeDto, employee);
		employee.setId(null);
		employee = save(employee);
		try (InputStream cvStream = cv.getInputstream();
				InputStream certificateStream = certificate.getInputstream();
				InputStream statusStream = criminalStatus.getInputstream();) {
			String cvPath = env.getProperty("upload.path") + "/" + "employees/" + employeeDto.getName() + employee.getId() + "/cv/" + cv.getFileName();
			String certificatePath = env.getProperty("upload.path") + "/" + "employees/" + employeeDto.getName() + employee.getId() + "/certificate/" + certificate.getFileName();
			String statusPath = env.getProperty("upload.path") + "/" + "employees/" + employeeDto.getName() + employee.getId() + "/CriminalStatus/" + criminalStatus.getFileName();
			FileUtils.copyInputStreamToFile(cvStream, new File(cvPath));
			FileUtils.copyInputStreamToFile(certificateStream, new File(certificatePath));
			FileUtils.copyInputStreamToFile(statusStream, new File(statusPath));
			employee.setCv(cvPath);
			employee.setCertificate(certificatePath);
			employee.setCriminalStatus(statusPath);
			employee = save(employee);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@Override
	public List<EmployeeDto> getAutoCompleteEmployees(String name) {
		List<Employee> emps = employeeRepo.getAutoCompleteEmployees(name);
		List<EmployeeDto> empsDto = new ArrayList<EmployeeDto>();
		for (Employee e : emps) {
			EmployeeDto edto = new EmployeeDto();
			mapper.map(e, edto);
			empsDto.add(edto);
		}
		return empsDto;
	}

	@Override
	public List<EmployeeDto> findByProjectId(ProjectDto projectDto) {
		List<ProjectEmployees> projectEmployees = projectEmployeeService
				.findByProjectId(projectDto.getId());
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		for (ProjectEmployees projectEmployee : projectEmployees) {
			Employee employee = projectEmployee.getEmployeeId();
			EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

}
