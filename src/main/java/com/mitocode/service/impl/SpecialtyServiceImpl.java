package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Specialty;
import com.mitocode.service.impl.repository.SpecialtyRepository;
import com.mitocode.service.impl.repository.GenericRepository;
import com.mitocode.service.SpecialtyService;

@Service
public class SpecialtyServiceImpl extends GenericServiceImpl<Specialty, Integer> implements SpecialtyService{

	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Override
	protected GenericRepository<Specialty, Integer> getRepository() {
		return specialtyRepository;
	}
}
