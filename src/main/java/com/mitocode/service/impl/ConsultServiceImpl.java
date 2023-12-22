package com.mitocode.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.impl.repository.ConsultExamRepository;
import com.mitocode.service.impl.repository.ConsultRepository;
import com.mitocode.service.impl.repository.GenericRepository;
import com.mitocode.service.ConsultService;

@Service
public class ConsultServiceImpl extends GenericServiceImpl<Consult, Integer> implements ConsultService{

	@Autowired
	private ConsultRepository consultRepository;
	
	@Autowired 
	ConsultExamRepository consultExamRepository;

	@Override
	protected GenericRepository<Consult, Integer> getRepository() {
		return consultRepository;
	}

	@Transactional
	@Override
	public Consult saveTransactional(Consult consult, List<Exam> exams) {
		consultRepository.save(consult);
		exams.forEach(exam -> consultExamRepository.saveExam(consult.getIdConsult(), exam.getIdExam()));
		return consult;
	}
}
