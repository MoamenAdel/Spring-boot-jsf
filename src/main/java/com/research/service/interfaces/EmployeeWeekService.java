package com.research.service.interfaces;

import java.util.Date;
import java.util.List;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.EmployeeWeekDto;
import com.research.entity.EmployeeWeek;
import com.research.service.BaseService;

public interface EmployeeWeekService extends BaseService<EmployeeWeek> {

	List<EmployeeWeekDto> getEmployeeHoursWithin(Long id, Date startDate, Date endDate);

	List<EmployeeWeek> getEmployeeHoursWithin(List<EmployeeDto> employeeDtos, Date startDate, Date endDate);

	void addEmployeeWeek(EmployeeWeekDto employeeWeekDto);

	List<EmployeeWeekDto> getWeeksByEmployeeId(long id);
}
