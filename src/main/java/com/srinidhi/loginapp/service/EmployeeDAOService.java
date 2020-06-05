package com.srinidhi.loginapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srinidhi.loginapp.exceptions.InvalidEmail;
import com.srinidhi.loginapp.exceptions.InvalidMobileNumberException;
import com.srinidhi.loginapp.model.Employee;
import com.srinidhi.loginapp.repo.EmployeeRepo;

@Service
public class EmployeeDAOService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	public Employee addEmployee(Employee employee)  {
		if (!employee.getMobileNo().matches("[0-9]{10}")) {
			throw new InvalidMobileNumberException("Enter a valid mobile number");
		} else if (!employee.getEmail().matches(
				"^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$")) {
			throw new InvalidEmail("Enter a valid email ID");
		}
		Employee emp = new Employee();
		emp.setDesignation(employee.getDesignation());
		emp.setEmail(employee.getEmail());
		emp.setEmpName(employee.getEmpName());
		emp.setExperience(employee.getExperience());
		emp.setMobileNo(employee.getMobileNo());
		emp.setRole(employee.getRole());
		emp.setUsername(employee.getUsername());
		String pw=employee.getUsername().substring(0,4)+"@"+employee.getMobileNo().substring(6);
		emp.setPassword(passwordEncoder.encode(pw));
		Employee savedEmp = employeeRepo.save(emp);
		return savedEmp;
	}

	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}

	public boolean findEmployeetById(String empId) {
		Employee project = employeeRepo.findById(empId).get();
		if (project != null) {
			return true;
		} else {
			return false;
		}
	}
}
