package com.research.repositories.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.research.entity.PaymentRequest;
import com.research.repositories.BaseRepository;

public interface PaymentRequestRepo extends BaseRepository<PaymentRequest> {

	@Query("SELECT pr FROM PaymentRequest pr WHERE pr.paymentRequestParent.id = ?")
	Page<PaymentRequest> findByParentId(Long id, Pageable request);

	@Query("SELECT count(*) FROM PaymentRequest pr WHERE pr.paymentRequestParent.id = ?")
	Long countByParentId(Long id);

}
