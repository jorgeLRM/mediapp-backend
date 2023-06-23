package com.mitocode.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class ConsultExamPK implements Serializable {
	
	private static final long serialVersionUID = -660716061113055365L;

	@ManyToOne
	@JoinColumn(name = "id_consult")
	private Consult consult;
	
	@ManyToOne
	@JoinColumn(name = "id_exam")
	private Exam exam;
}
