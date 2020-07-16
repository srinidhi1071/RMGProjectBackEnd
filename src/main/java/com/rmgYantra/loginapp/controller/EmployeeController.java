package com.rmgYantra.loginapp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmgYantra.loginapp.exceptions.ResourceNotFoundException;
import com.rmgYantra.loginapp.exceptions.UserNameAlreadyPresentException;
import com.rmgYantra.loginapp.model.Employee;
import com.rmgYantra.loginapp.repo.EmployeeRepo;
import com.rmgYantra.loginapp.service.EmployeeDAOService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeDAOService employeeDAOService;

	@Autowired
	private EmployeeRepo employeeRepo;

	@PostMapping("/employees")
	public ResponseEntity createEmployee(@RequestBody Employee employee) {
		if (employeeRepo.findByUsername(employee.getUsername()).isPresent())
			throw new UserNameAlreadyPresentException("Username " + employee.getUsername() + " Already Exists");
		Employee createdEmployee = employeeDAOService.addEmployee(employee);
		HashMap<String, String> hm = new HashMap<>();
		hm.put("employeeId", createdEmployee.getEmpId());
		hm.put("employeeName", createdEmployee.getEmpName());
		hm.put("mobile", createdEmployee.getMobileNo());
		hm.put("email", createdEmployee.getEmail());
		hm.put("dob", createdEmployee.getDob());
		hm.put("username", createdEmployee.getUsername());
		hm.put("msg", "Employee Added Successfully");
		ResponseEntity re = new ResponseEntity(hm, HttpStatus.CREATED);
		return re;
	}

	@GetMapping("/employees")
	public Page<Employee> getAllEmployees(@RequestParam(required = false) String designation, Pageable pageable) {
		if (designation != null) {
			return employeeRepo.findByDesignation(designation, pageable);
		} else {
			return employeeDAOService.findAllEmployees(pageable);
		}
	}

	@GetMapping("/employees/{empId}")
	public Employee getEmpById(@PathVariable String empId) {
		return employeeRepo.findById(empId).get();
	}

	@GetMapping("/employees/{userName}")
	public Employee getEmpByUsername(@PathVariable String userName) {
		return employeeRepo.findByUsername(userName)
				.orElseThrow(() -> new ResourceNotFoundException("Username Notfound"));
	}

	@PutMapping("/employees/{empId}")
	public ResponseEntity<Employee> updateProject(@PathVariable String empId, @RequestBody Employee employee) {
		Employee emp = employeeRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Project Not Found For the Id::" + empId));
		emp.setEmpId(empId);
		emp.setEmpName(employee.getEmpName());
		emp.setEmail(employee.getEmail());
		emp.setMobileNo(employee.getMobileNo());
		emp.setDob(employee.getDob());
		emp.setExperience(employee.getExperience());
		emp.setDesignation(employee.getDesignation());
		emp.setProject(employee.getProject());
		emp.setRole(employee.getRole());
		emp.setUsername(employee.getUsername());
		return ResponseEntity.ok(employeeRepo.save(emp));

	}

	@DeleteMapping("/employees/{empId}")
	public ResponseEntity deleteProject(@PathVariable String empId) {
		if (employeeDAOService.findEmployeetById(empId)) {
			employeeRepo.deleteById(empId);
			HashMap<String, String> hm = new HashMap<>();
			hm.put("msg", "Employee deleted Successfully");
			return new ResponseEntity(hm, HttpStatus.NO_CONTENT);
		} else {
			throw new ResourceNotFoundException("Employee not found for the Id::" + empId);
		}
	}

	@PostMapping("/employees/resetPassword")
	public void resetPassword(@RequestBody Employee emp, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		employeeDAOService.updatePassword(emp, oldPassword,newPassword);
	}


}
