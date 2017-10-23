package com.research.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.PaymentRequestDto;
import com.research.dto.project.PaymentRequestParentDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.PaymentRequestParent;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.PaymentRequestParentRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.EmployeeService;
import com.research.service.interfaces.PaymentRequestParentService;
import com.research.service.interfaces.PaymentRequestService;
import com.research.service.interfaces.ProjectService;

@Transactional
@Service
public class PaymentRequestParentServiceImpl extends
		BaseServiceImpl<PaymentRequestParent> implements
		PaymentRequestParentService {

	@Autowired
	private PaymentRequestParentRepo paymentRequestParentRepo;
	@Autowired
	private DozerBeanMapper mapper;
	@Autowired
	private PaymentRequestService paymentRequestService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;
	
	@Override
	public BaseRepository getBaseRepo() {
		return paymentRequestParentRepo;
	}

	@Override
	public List<PaymentRequestParentDto> findAllByProject(Long id) {
		List<PaymentRequestParent> paymentRequestParents = paymentRequestParentRepo.findByProjectId(id);
		List<PaymentRequestParentDto> paymentRequestParentDtos = new ArrayList<>();
		
		for (PaymentRequestParent paymentRequestParent : paymentRequestParents){
			PaymentRequestParentDto paymentRequestParentDto = mapper.map(paymentRequestParent, PaymentRequestParentDto.class);
			paymentRequestParentDtos.add(paymentRequestParentDto);
		}
		return paymentRequestParentDtos;
	}

	@Override
	public PaymentRequestParentDto calculatePaymentRequest(
			ProjectDto projectDto, Date startDate, Date endDate) {
		List<EmployeeDto> employeeDtos = employeeService.findByProjectId(projectDto);
		// TODO employees is empty
		PaymentRequestParent paymentRequestParent = new PaymentRequestParent();
		paymentRequestParent.setStartDate(startDate);
		paymentRequestParent.setEndDate(endDate);
		paymentRequestParent.setProject(projectService.getOne(projectDto.getId()));
		this.save(paymentRequestParent);
		List<PaymentRequestDto> paymentRequestDto = paymentRequestService.calculatePaymentRequest(paymentRequestParent, employeeDtos, startDate, endDate);
		PaymentRequestParentDto paymentRequestParentDto = mapper.map(paymentRequestParent, PaymentRequestParentDto.class);
		return paymentRequestParentDto;		
	}

}
