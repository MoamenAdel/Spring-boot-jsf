package com.research.repositories.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.PaymentRequestParent;
import com.research.repositories.BaseRepository;

public interface PaymentRequestParentRepo extends BaseRepository<PaymentRequestParent>{

	@Query("SELECT prp FROM PaymentRequestParent prp WHERE project.id = ?")
	List<PaymentRequestParent> findByProjectId(Long id);

}
