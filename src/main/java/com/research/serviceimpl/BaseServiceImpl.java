package com.research.serviceimpl;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.research.entity.BaseEntity;
import com.research.exception.BusinessException;
import com.research.repositories.BaseRepository;
import com.research.service.BaseService;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	@Override
	public List<T> getAll() {
		BaseRepository baseRepository = getBaseRepo();
		return (List<T>) checkBaseRepo().findAll();
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		if (entity == null)
			throw new BusinessException("entity is null", HttpStatus.BAD_REQUEST, -1);
//		if (entity.getId() != null)
//			throw new BusinessException("entity contain Id ", HttpStatus.BAD_REQUEST, -1);
		Object returnObject = checkBaseRepo().save(entity);
		if (returnObject == null)
			throw new BusinessException("entity not found ", HttpStatus.BAD_REQUEST, -1);
		return (T) returnObject;
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub

		if (entity == null)
			throw new BusinessException("entity is null", HttpStatus.BAD_REQUEST, -1);
		if (entity.getId() == null)
			throw new BusinessException("entity doesnot  contain Id ", HttpStatus.BAD_REQUEST, -1);
		Object returnObject = checkBaseRepo().save(entity);
		if (returnObject == null)
			throw new BusinessException("entity not found ", HttpStatus.BAD_REQUEST, -1);
		return (T) returnObject;
	}

	@Override
	public T getOne(Long id) {
		// TODO Auto-generated method stub
		
		T returnObject= (T) checkBaseRepo().findOne(id);
		if(returnObject==null)
			throw new BusinessException("object not found", HttpStatus.BAD_REQUEST,-1);
		return returnObject;
			
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		checkBaseRepo().softDelete(id);
	}

	private BaseRepository checkBaseRepo() {
		BaseRepository baseRepository = getBaseRepo();
		if (baseRepository == null)
			throw new BusinessException("BaseRepo is null", HttpStatus.INTERNAL_SERVER_ERROR, -1);
		return baseRepository;
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		checkBaseRepo().softDelete(entity);
	}
	@Override
	public void delete(Iterable<T> list) {
		// TODO Auto-generated method stub
		checkBaseRepo().softDelete(list);
	}
}
