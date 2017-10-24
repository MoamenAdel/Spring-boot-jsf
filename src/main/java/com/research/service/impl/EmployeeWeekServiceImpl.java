package com.research.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.EmployeeWeekDto;
import com.research.entity.EmployeeWeek;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.EmployeeWeekRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.EmployeeWeekService;

@Service
public class EmployeeWeekServiceImpl extends BaseServiceImpl<EmployeeWeek> implements EmployeeWeekService {

	@Autowired
	private EmployeeWeekRepo employeeWeekRepo;
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public BaseRepository getBaseRepo() {
		return employeeWeekRepo;
	}

	@Override
	public List<EmployeeWeekDto> getEmployeeHoursWithin(Long id, Date startDate, Date endDate) {
		List<EmployeeWeek> employeeWeeks = employeeWeekRepo.findWithinDate(id, startDate, endDate);
		List<EmployeeWeekDto> employeeWeekDtos = new ArrayList<>();
		for (EmployeeWeek employeeWeek : employeeWeeks) {
			EmployeeWeekDto employeeWeekDto = new EmployeeWeekDto();
			mapper.map(employeeWeek, employeeWeekDto);
			employeeWeekDtos.add(employeeWeekDto);
		}
		return employeeWeekDtos;
	}

	@Override
	public List<EmployeeWeek> getEmployeeHoursWithin(List<EmployeeDto> employeeDtos, Date startDate, Date endDate) {
		// return employeeWeekRepo.findAllWithinDate(employeeDtos, startDate,
		// endDate);
		return null;
	}

	@Override
	public void addEmployeeWeek(EmployeeWeekDto employeeWeekDto) {
		EmployeeWeek employeeWeek = new EmployeeWeek();
		mapper.map(employeeWeekDto, employeeWeek);
		save(employeeWeek);
	}

}
