package com.mitocode.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultListExamDTO {
	
	@NotNull
	private ConsultDTO consult;
	
	@NotNull
	private List<ExamDTO> exams;
	
}
