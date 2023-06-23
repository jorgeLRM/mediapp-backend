package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity
public class Role {
	
	@Id
	@EqualsAndHashCode.Include
	private Integer idRole;
	
	@Column(nullable=false, length=50)
	private String name;
	
	@Column(nullable=false, length=100)
	private String description;
	
}
