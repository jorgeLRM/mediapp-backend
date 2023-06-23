package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;

public interface ConsultService extends GenericService<Consult, Integer> {
	
	Consult saveTransactional(Consult consult, List<Exam> exams);
	
}
