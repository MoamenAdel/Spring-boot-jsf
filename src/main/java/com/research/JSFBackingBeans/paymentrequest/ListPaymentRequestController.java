package com.research.JSFBackingBeans.paymentrequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.research.dto.project.PaymentRequestParentDto;
import com.research.dto.project.ProjectDto;
import com.research.entity.PaymentRequestParent;
import com.research.service.interfaces.PaymentRequestParentService;

@Component("ListPaymentRequestController")
@ManagedBean(name = "ListPaymentRequestController")
@Scope("view")
@ViewScoped
public class ListPaymentRequestController {

	@Autowired
	private PaymentRequestParentService paymentRequestParentService;
	private ProjectDto projectDto;
	private List<PaymentRequestParentDto> paymentRequestDtos = new ArrayList<>();
	private Date startDate;
	private Date endDate;
	private PaymentRequestParentDto paymentRequestParentDto;
        private ProjectDto temp;
	
	public ListPaymentRequestController() {
	}

	@PostConstruct
	public void init() {
		 temp = (ProjectDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("projectDto");
		if (temp != null){
			projectDto = temp;
		}
		paymentRequestDtos = paymentRequestParentService.findAllByProject(projectDto.getId());
//		if (paymentRequestDtos != null && !paymentRequestDtos.isEmpty()){
//			startDate = paymentRequestDtos.get(0).getEndDate();
//			for (PaymentRequestParentDto paymentRequestParentDto : paymentRequestDtos){
//				if (startDate.before(paymentRequestParentDto.getEndDate()))
//					startDate = paymentRequestParentDto.getEndDate();
//			}
//		} else {
//			// TODO get projectStartDate
//			startDate = new Date();
//		}
	}
	
	public String addPaymentRequest(){
		paymentRequestParentDto = paymentRequestParentService.calculatePaymentRequest(projectDto, startDate, endDate);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("requestParentDto", paymentRequestParentDto);
		return "View";
	}
	
	public String viewPaymentRequest(PaymentRequestParentDto requestParent){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("requestParentDto", requestParent);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("projectDto", temp);
		return "View";
	}

	public ProjectDto getProjectDto() {
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}

	public List<PaymentRequestParentDto> getPaymentRequestDtos() {
		return paymentRequestDtos;
	}

	public void setPaymentRequestDtos(
			List<PaymentRequestParentDto> paymentRequestDtos) {
		this.paymentRequestDtos = paymentRequestDtos;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PaymentRequestParentDto getPaymentRequestParentDto() {
		return paymentRequestParentDto;
	}

	public void setPaymentRequestParentDto(
			PaymentRequestParentDto paymentRequestParentDto) {
		this.paymentRequestParentDto = paymentRequestParentDto;
	}

}