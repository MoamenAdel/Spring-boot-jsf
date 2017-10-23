package com.research.service.interfaces;

import java.util.Date;
import java.util.List;

import com.research.dto.project.PaymentRequestDto;
import com.research.dto.project.PaymentRequestParentDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.PaymentRequestParent;
import com.research.service.BaseService;

public interface PaymentRequestParentService extends BaseService<PaymentRequestParent> {

	List<PaymentRequestParentDto> findAllByProject(Long id);

	PaymentRequestParentDto calculatePaymentRequest(ProjectDto projectDto,
			Date startDate, Date endDate);

}
