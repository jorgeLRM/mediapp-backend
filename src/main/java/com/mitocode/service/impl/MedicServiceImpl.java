package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Medic;
import com.mitocode.repository.GenericRepository;
import com.mitocode.repository.MedicRepository;
import com.mitocode.service.MedicService;

@Service
public class MedicServiceImpl extends GenericServiceImpl<Medic, Integer> implements MedicService{

	@Autowired
	private MedicRepository medicRepository;

	@Override
	protected GenericRepository<Medic, Integer> getRepository() {
		return medicRepository;
	}
}
