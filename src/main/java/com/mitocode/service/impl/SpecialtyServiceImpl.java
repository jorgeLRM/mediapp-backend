package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Specialty;
import com.mitocode.repository.SpecialtyRepository;
import com.mitocode.repository.GenericRepository;
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
