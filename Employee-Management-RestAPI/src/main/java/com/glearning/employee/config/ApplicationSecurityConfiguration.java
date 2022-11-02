package com.glearning.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employee.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final DomainUserDetailsService UserDetailsSerivce;
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//		//configure users
//		authenticationManagerBuilder
//		                           .inMemoryAuthentication()
//		                           .withUser("Ramesh")
//		                           .password(passwordEncoder().encode("root"))
//		                           .roles("USER","ADMIN")
//		                           .and()
//		                           .withUser("Mahesh")
//		                           .password(passwordEncoder().encode("root"))
//		                           .roles("USER");

		        authenticationManagerBuilder
		                .userDetailsService(this.UserDetailsSerivce)
		                .passwordEncoder(passwordEncoder());
		    

	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		        httpSecurity.headers().frameOptions().disable();
		        httpSecurity
		        .authorizeRequests()
		        .antMatchers(HttpMethod.GET,"/api/employee/**")
		        .hasAnyRole("USER","ADMIN")
		        .and()
		        .authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/employee/**")
				.hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employee/**")
				.hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employee/**")
				.hasAuthority("ADMIN")
				// .antMatchers("/api/e","/student/delete").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().httpBasic()
//	            .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
//	            .and()
//	            .logout().logoutSuccessUrl("/login").permitAll()
//	            .and()
//	            .exceptionHandling().accessDeniedPage("/student/403")
				.and().cors().and().csrf().disable();
		      
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
}
