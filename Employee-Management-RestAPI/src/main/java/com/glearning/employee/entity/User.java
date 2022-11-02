package com.glearning.employee.entity;

import static javax.persistence.CascadeType.ALL;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	private String emailAddress;

	@ManyToMany(mappedBy = "users", cascade = ALL, fetch = FetchType.EAGER)
	private Set<Roles> roles;

	public void addRole(Roles roles) {
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}
		this.roles.add(roles);
		roles.setUsers(this);
	}
}
