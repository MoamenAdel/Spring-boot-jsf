package com.research.JSFBackingBeans.lazydatamodels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.PaymentRequestDto;
import com.research.dto.project.PaymentRequestParentDto;
import com.research.service.interfaces.PaymentRequestService;

@Component
@Scope("prototype")
public class PaymentRequestLazyDataModel extends LazyDataModel<PaymentRequestDto>{

	@Autowired
	private PaymentRequestService paymentRequestService;
	private PaymentRequestParentDto paymentRequestDto;
	
	public PaymentRequestLazyDataModel(PaymentRequestParentDto paymentRequestDto) {
		if (paymentRequestDto != null){
			this.paymentRequestDto = paymentRequestDto;
		}
	}
	
	@Override
	public List<PaymentRequestDto> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return paymentRequestService.getAllByParentId(paymentRequestDto.getId(), first/pageSize, pageSize);
	}
}
