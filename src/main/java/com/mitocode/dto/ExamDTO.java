package com.mitocode.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExamDTO {
	
	@EqualsAndHashCode.Include
	private Integer idExam;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
}
