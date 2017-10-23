package com.research.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.project.EmployeeDto;
import com.research.dto.project.EmployeeWeekDto;
import com.research.dto.project.PaymentRequestDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.Employee;
import com.research.entity.EmployeeWeek;
import com.research.entity.PaymentRequest;
import com.research.entity.PaymentRequestParent;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.PaymentRequestRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.EmployeeService;
import com.research.service.interfaces.EmployeeWeekService;
import com.research.service.interfaces.PaymentRequestService;

@Service
public class PaymentRequestServiceImpl extends BaseServiceImpl<PaymentRequest> implements
		PaymentRequestService {

	@Autowired
	private PaymentRequestRepo paymentRequestRepo;
	@Autowired
	private EmployeeWeekService employeeWeekService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public BaseRepository getBaseRepo() {
		return paymentRequestRepo;
	}

	@Override
	public List<PaymentRequestDto> calculatePaymentRequest(PaymentRequestParent paymentRequestParent,
			List<EmployeeDto> employeeDtos, Date startDate, Date endDate) {
		if (endDate.after(new Date()) || startDate.after(endDate)){
			throw new BusinessException();
		}
		Calendar start = new GregorianCalendar();
		start.setTime(startDate);
		Calendar end = new GregorianCalendar();
		end.setTime(endDate);
		
		int yearDef = end.get(end.YEAR) - start.get(start.YEAR);
		int months = 12 * yearDef + end.get(end.MONTH) - start.get(start.MONTH);
		int weeks = months * 4;
//		List<EmployeeWeek> employeeWeeks = employeeWeekService.getEmployeeHoursWithin(employeeDtos, startDate, endDate);
		List<PaymentRequestDto> paymentRequestsDtos = new ArrayList<>();
		for (EmployeeDto employeeDto : employeeDtos){
			// TODO implement the employee week service
			Employee employee = employeeService.getOne(employeeDto.getId());
			List<EmployeeWeekDto> employeeWeekDtos = employeeWeekService.getEmployeeHoursWithin(employeeDto.getId(), startDate, endDate);
			int hoursWorked = 0;
			for (EmployeeWeekDto employeeWeekDto : employeeWeekDtos){
				hoursWorked += employeeWeekDto.getWeek1() + employeeWeekDto.getWeek2() 
						+ employeeWeekDto.getWeek3() + employeeWeekDto.getWeek4();
			}
			Double participation = (double)hoursWorked / (double)(months * 8);  
			// TODO change the fixed 8 number 
			Double total = employeeDto.getMonthlyIncentive() * participation * months;
			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setEmployee(employee);
			paymentRequest.setEmployeeName(employee.getName());
			paymentRequest.setHoursPerWeek(hoursWorked / weeks);
			paymentRequest.setMonthlyIncentive(employeeDto.getMonthlyIncentive());
			paymentRequest.setNumberOfMonths(months);
			paymentRequest.setParticipation(participation);
			paymentRequest.setRole(employeeDto.getPosition());
			paymentRequest.setTotal(total);
			paymentRequest.setPaymentRequestParent(paymentRequestParent);
			this.save(paymentRequest);
			PaymentRequestDto paymentRequestDto = mapper.map(paymentRequest, PaymentRequestDto.class);
			paymentRequestsDtos.add(paymentRequestDto);
		}
		
		return paymentRequestsDtos;
	}
	
	public List<PaymentRequestDto> calculatePaymentRequestByProject(ProjectDto projectDto, Date startDate, Date endDate){
//		List<EmployeeDto> employeeDtos = employeeService.findByProjectId(projectDto);
//		List<PaymentRequestDto> paymentRequestDtos =  this.calculatePaymentRequest(employeeDtos, startDate, endDate);
//		
		throw new UnsupportedOperationException();
	}

}
