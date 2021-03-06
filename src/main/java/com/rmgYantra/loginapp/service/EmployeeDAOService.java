package com.rmgYantra.loginapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rmgYantra.loginapp.exceptions.InvalidEmail;
import com.rmgYantra.loginapp.exceptions.InvalidMobileNumberException;
import com.rmgYantra.loginapp.exceptions.InvalidOldPasswordException;
import com.rmgYantra.loginapp.model.Employee;
import com.rmgYantra.loginapp.repo.EmployeeRepo;

@Service
public class EmployeeDAOService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	public PasswordEncoder passwordEncoder;

	public Employee addEmployee(Employee employee) {
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
		String pw = employee.getUsername().substring(0, 4) + "@" + employee.getMobileNo().substring(6);
		emp.setPassword(passwordEncoder.encode(pw));
		emp.setProject(employee.getProject());
		Employee savedEmp = employeeRepo.save(emp);
		return savedEmp;
	}

	public Page<Employee> findAllEmployees(Pageable pageable) {
		return employeeRepo.findAll(pageable);
	}

	public boolean findEmployeetById(String empId) {
		Employee project = employeeRepo.findById(empId).get();
		if (project != null) {
			return true;
		} else {
			return false;
		}
	}

	public void updatePassword(Employee emp, String oldPassword, String newPassword) {
		Employee employee = employeeRepo.findById(emp.getEmpId()).get();
		
		if (passwordEncoder.matches(oldPassword, employee.getPassword())) {
			employee.setPassword(passwordEncoder.encode(newPassword));
			employeeRepo.save(employee);
		}else {
			throw new InvalidOldPasswordException("Invalid old password");
		}
	}
}
