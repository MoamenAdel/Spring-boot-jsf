package com.research.service.interfaces;

import java.util.Date;
import java.util.List;

import com.research.dto.employee.EmployeeDto;
import com.research.dto.project.PaymentRequestDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.PaymentRequest;
import com.research.entity.PaymentRequestParent;
import com.research.service.BaseService;

public interface PaymentRequestService extends BaseService<PaymentRequest> {

	List<PaymentRequestDto> calculatePaymentRequest(PaymentRequestParent paymentRequestParent, List<EmployeeDto> employeeDtos, Date startDate, Date endDate);
	
	List<PaymentRequestDto> calculatePaymentRequestByProject(ProjectDto projectDto, Date startDate, Date endDate);
}
