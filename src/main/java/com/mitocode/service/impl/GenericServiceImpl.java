package com.mitocode.service.impl;

import java.util.List;

import com.mitocode.repository.GenericRepository;
import com.mitocode.service.GenericService;

public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID>{

	protected abstract GenericRepository<T, ID> getRepository();
	
	@Override
	public T save(T t) {
		return getRepository().save(t);
	}

	@Override
	public T update(T t) {
		return getRepository().save(t);
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public T findById(ID id) {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	public void delete(ID id) {
		getRepository().deleteById(id);
	}

}
