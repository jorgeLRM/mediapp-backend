package com.mitocode.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class ConsultDetailDTO {
	
	@EqualsAndHashCode.Include
	private Integer idDetail;
	
	@JsonBackReference
	private ConsultDTO consult;
	
	@NotNull
	private String diagnosis;
	
	@NotNull
	private String treatment;
}
