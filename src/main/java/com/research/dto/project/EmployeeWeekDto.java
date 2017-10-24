package com.research.dto.project;

import java.util.Date;

import com.research.dto.BaseDto;
import com.research.dto.employee.EmployeeDto;

public class EmployeeWeekDto extends BaseDto {
	
	private Integer week1;
	private Integer week2;
	private Integer week3;
	private Integer week4;
	private Date month;
	private EmployeeDto employeeDto;
	private Long employeeId;

	public Integer getWeek1() {
		return week1;
	}

	public void setWeek1(Integer week1) {
		this.week1 = week1;
	}

	public Integer getWeek2() {
		return week2;
	}

	public void setWeek2(Integer week2) {
		this.week2 = week2;
	}

	public Integer getWeek3() {
		return week3;
	}

	public void setWeek3(Integer week3) {
		this.week3 = week3;
	}

	public Integer getWeek4() {
		return week4;
	}

	public void setWeek4(Integer week4) {
		this.week4 = week4;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
