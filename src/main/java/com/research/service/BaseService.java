package com.research.service;

import java.io.Serializable;
import java.util.List;

import com.research.entity.BaseEntity;
import com.research.repositories.BaseRepository;

public interface BaseService<T extends BaseEntity> {

	public List<T> getAll();

	T save(T entity);

	T update(T entity);

	T getOne(Long id);

	void delete(Long id);
	void delete(T entity);
	void delete(Iterable<T> list);

	public BaseRepository getBaseRepo();

}
