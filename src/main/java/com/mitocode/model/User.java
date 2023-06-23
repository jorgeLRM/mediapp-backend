package com.mitocode.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "users")
public class User {
	
	@Id
	@EqualsAndHashCode.Include
	private Integer idUser;
	
	@Column(nullable=false, length=60, unique=true)
	private String username;
	
	@Column(nullable=false, length=60)
	private String password;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
		joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
		inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole")
	)
	private List<Role> roles;
	
}
