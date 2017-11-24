package com.research.service.impl;

import java.text.SimpleDateFormat;
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
		if (employeeWeekDto.getWeek1() > 8) {
			employeeWeekDto.setWeek1(8);
		}
		if (employeeWeekDto.getWeek2() > 8) {
			employeeWeekDto.setWeek2(8);
		}
		if (employeeWeekDto.getWeek3() > 8) {
			employeeWeekDto.setWeek3(8);
		}
		if (employeeWeekDto.getWeek4() > 8) {
			employeeWeekDto.setWeek4(8);
		}

		mapper.map(employeeWeekDto, employeeWeek);
		save(employeeWeek);
	}

	@Override
	public List<EmployeeWeekDto> getWeeksByEmployeeId(long id) {
		List<EmployeeWeek> weeks = employeeWeekRepo.getWeeksByEmployeeId(id);
		List<EmployeeWeekDto> res = new ArrayList<>();
		for (EmployeeWeek w : weeks) {
			EmployeeWeekDto ewd = new EmployeeWeekDto();
			mapper.map(w, ewd);
			new SimpleDateFormat("MMM yyyy").format(ewd.getMonth());
			ewd.setFormatedMonth(new SimpleDateFormat("MMM yyyy").format(ewd.getMonth()));
			ewd.setAvg(new Double(ewd.getWeek1() + ewd.getWeek2() + ewd.getWeek3() + ewd.getWeek4()) / 4);
			res.add(ewd);
		}
		return res;

	}

}
