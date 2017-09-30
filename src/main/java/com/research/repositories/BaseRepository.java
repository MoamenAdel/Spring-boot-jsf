package com.research.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;

import com.research.entity.BaseEntity;
import com.research.exception.BusinessException;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long> {

//	@Query("from #{#entityName} t where t.retired = false")
//	public List<T> findAll();
//
//	@Query("from #{#entityName} t where t.retired = false and t.id = ?")
//	public T findOne(Long id);
//
//	@Query("update #{#entityName} entity set entity.retired = true , entity.retireDate = CURRENT_TIMESTAMP WHERE entity.id = ?")
//	public void retireById(Long id);

	public default void softDelete(Long id) {

		T e = findOne(id);
		if(e==null)
			throw new BusinessException("entity is null",HttpStatus.BAD_REQUEST, -1);
		e.setRetired(true);
		save(e);
	}

	public default void softDelete(T e) {
		e.setRetired(true);
		save(e);
	}

	public default void softDelete(Iterable<T> list) {

		list.forEach(e -> e.setRetired(true));
		save(list);
	}
}
