package com.srinidhi.loginapp.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinidhi.loginapp.exceptions.InvalidEmail;
import com.srinidhi.loginapp.exceptions.InvalidMobileNumberException;
import com.srinidhi.loginapp.model.Employee;
import com.srinidhi.loginapp.model.Project;
import com.srinidhi.loginapp.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee addEmployee(Employee employee) {
		employee.setEmpId("TY_" + (employeeRepo.count() + 1));
		if (!employee.getMobileNo().matches("[0-9]{10}")) {
			throw new InvalidMobileNumberException("Enter a valid mobile number");
		}else if(!employee.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$")) {
			throw new InvalidEmail("Enter a valid email ID");
		}
		return employeeRepo.save(employee);
	}

	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public boolean findEmployeetById(String empId) {
		Employee project = employeeRepo.findById(empId).get();
		if(project!=null) {
			return true;
		}else {
			return false;
		}
	}
}