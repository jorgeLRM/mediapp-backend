package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Exam;
import com.mitocode.service.impl.repository.ExamRepository;
import com.mitocode.service.impl.repository.GenericRepository;
import com.mitocode.service.ExamService;

@Service
public class ExamServiceImpl extends GenericServiceImpl<Exam, Integer> implements ExamService{

	@Autowired
	private ExamRepository examRepository;

	@Override
	protected GenericRepository<Exam, Integer> getRepository() {
		return examRepository;
	}
}
