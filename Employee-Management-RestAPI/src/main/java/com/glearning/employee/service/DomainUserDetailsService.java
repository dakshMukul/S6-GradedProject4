package com.glearning.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glearning.employee.entity.DomainUserDetails;
import com.glearning.employee.entity.User;
import com.glearning.employee.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("Invalid Username"));
		System.out.println("User from repository");
		System.out.println(user);
		
				return new DomainUserDetails(user);
	}

}
