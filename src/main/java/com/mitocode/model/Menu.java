package com.mitocode.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Menu {
	
	@Id
	@EqualsAndHashCode.Include
	private Integer idMenu;
	
	@Column(nullable=false, length=20)
	private String icon;
	
	@Column(nullable=false, length=20)
	private String name;
	
	@Column(nullable=false, length=50)
	private String url;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="menu_role",
		joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
		inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole")
	)
	private List<Role> roles;
	
}
