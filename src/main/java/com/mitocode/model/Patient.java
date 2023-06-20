package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idPatient;
	
	@Column(length=70, nullable=false)
	private String firstName;
	
	@Column(length=70, nullable=false)
	private String lastName;
	
	@Column(length=8, nullable=false)
	private String dni;
	
	@Column(length=150, nullable=false)
	private String address;
	
	@Column(length=9, nullable=false)
	private String phone;
	
	@Column(length=55, nullable=false)
	private String email;
}
