package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Patient;
import com.mitocode.service.impl.repository.GenericRepository;
import com.mitocode.service.impl.repository.PatientRepository;
import com.mitocode.service.PatientService;

@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient, Integer> implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	@Override
	protected GenericRepository<Patient, Integer> getRepository() {
		return patientRepository;
	}

}
