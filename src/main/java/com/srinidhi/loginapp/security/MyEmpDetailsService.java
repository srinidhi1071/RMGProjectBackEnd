package com.srinidhi.loginapp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.srinidhi.loginapp.model.Employee;
import com.srinidhi.loginapp.model.User;
import com.srinidhi.loginapp.repo.EmployeeRepo;


public class MyEmpDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeRepo empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> emp = empRepo.findByUsername(username);
		emp.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));
		return	emp.map(MyEmpDetails::new).get();
	}

}