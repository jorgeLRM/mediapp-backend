package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class ConsultDTO {
	
	@EqualsAndHashCode.Include
	private Integer idConsult;
	
	@NotNull
	private PatientDTO patient;
	
	@NotNull
	private MedicDTO medic;
	
	@NotNull
	private SpecialtyDTO specialty;
	
	@NotNull
	private String numConsult;
	
	@NotNull
	private LocalDateTime consultDate;
	
	@JsonManagedReference
	@NotNull
	private List<ConsultDetailDTO> details;
}
