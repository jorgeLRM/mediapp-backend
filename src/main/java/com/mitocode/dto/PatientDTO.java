package com.mitocode.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class PatientDTO {
	
	@EqualsAndHashCode.Include
	private Integer idPatient;
	
	@NotNull
	@Size(min=3, message="{firstname.size}")
	private String firstName;
	
	@NotNull
	@Size(min=3, message="{lastname.size}")
	private String lastName;
	private String dni;
	private String address;
	private String phone;
	
	@Email
	private String email;
	
}
