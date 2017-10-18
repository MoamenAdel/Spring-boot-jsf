package com.research.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.entity.PaymentRequest;
import com.research.repositories.BaseRepository;
import com.research.repositories.project.PaymentRequestRepo;
import com.research.service.BaseServiceImpl;
import com.research.service.interfaces.PaymentRequestService;

@Service
public class PaymentRequestServiceImpl extends BaseServiceImpl<PaymentRequest> implements
		PaymentRequestService {

	@Autowired
	private PaymentRequestRepo paymentRequestRepo;

	@Override
	public BaseRepository getBaseRepo() {
		return paymentRequestRepo;
	}

}
