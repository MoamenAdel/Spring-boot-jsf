package com.research.JSFBackingBeans.paymentrequest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.JSFBackingBeans.lazydatamodels.PaymentRequestLazyDataModel;
import com.research.dto.project.PaymentRequestDto;
import com.research.dto.project.PaymentRequestParentDto;
import com.research.service.interfaces.PaymentRequestService;

@Component("ViewPaymentRequestController")
@Scope("view")
@ManagedBean(name = "ViewPaymentRequestController")
@ViewScoped
public class ViewPaymentRequestController {

	@Autowired
	private PaymentRequestService paymentRequestService;
	@Autowired
	private BeanFactory factory;
	private PaymentRequestLazyDataModel model;
	private PaymentRequestParentDto requestParentDto;
	
	public ViewPaymentRequestController() {
	}
	
	@PostConstruct
	public void init() {
		PaymentRequestParentDto temp = (PaymentRequestParentDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("requestParentDto");
		model = factory.getBean(PaymentRequestLazyDataModel.class, requestParentDto);
		if (temp != null){
			requestParentDto = temp;
			model = factory.getBean(PaymentRequestLazyDataModel.class, requestParentDto);
			model.setRowCount(paymentRequestService.count().intValue());
		} else {
			
		}
	}

	public PaymentRequestParentDto getRequestParentDto() {
		return requestParentDto;
	}

	public void setRequestParentDto(PaymentRequestParentDto requestParentDto) {
		this.requestParentDto = requestParentDto;
	}

	public PaymentRequestLazyDataModel getModel() {
		return model;
	}

	public void setModel(PaymentRequestLazyDataModel model) {
		this.model = model;
	}

}
