package com.glearning.employee.util;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employee.entity.Employee;
import com.glearning.employee.entity.Roles;
import com.glearning.employee.entity.User;
import com.glearning.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class BootstrapAppData {

	private final EmployeeRepository employRepository;
	private final com.glearning.employee.repository.UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event) {
		Employee emp1 = new Employee();
		emp1.setName("Ramesh");
		emp1.setEmail("ramesh@gamil.com");
		this.employRepository.save(emp1);

		Employee emp2 = new Employee();
		emp2.setName("Mamesh");
		emp2.setEmail("mamesh@gamil.com");
		this.employRepository.save(emp2);
		/* ..................................................................... */

		User mahesh = new User();
		mahesh.setUsername("Mahesh");
		mahesh.setPassword(this.passwordEncoder.encode("root"));
		mahesh.setEmailAddress("mahesh@gmail.com");

		Roles maheshRole = new Roles();
		maheshRole.setRoleName("ADMIN");

		maheshRole.setUsers(mahesh);
		mahesh.addRole(maheshRole);

		// Ramesh
		User ramesh = new User();
		ramesh.setUsername("Ramesh");
		ramesh.setPassword(this.passwordEncoder.encode("root"));
		ramesh.setEmailAddress("Ramesh@gmail.com");

		Roles rameshRole = new Roles();
		rameshRole.setRoleName("USER");

		rameshRole.setUsers(ramesh);
		ramesh.addRole(rameshRole);

		this.userRepository.save(mahesh);
		this.userRepository.save(ramesh);
	}
}
