package com.srinidhi.loginapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srinidhi.loginapp.exceptions.ResourceNotFoundException;
import com.srinidhi.loginapp.model.Employee;
import com.srinidhi.loginapp.repo.EmployeeRepo;
import com.srinidhi.loginapp.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepo employeeRepo;

	@PostMapping("/employees")
	public ResponseEntity createEmployee(@RequestBody Employee employee) {
		Employee createdEmployee = employeeService.addEmployee(employee);
		HashMap<String, String> hm = new HashMap<>();
		hm.put("employeeId", createdEmployee.getEmpId());
		hm.put("employeeName", createdEmployee.getEmpName());
		hm.put("mobile", createdEmployee.getMobileNo());
		hm.put("email", createdEmployee.getEmail());
		hm.put("dob", createdEmployee.getDob());
		hm.put("msg", "Employee Added Successfully");
		ResponseEntity re = new ResponseEntity(hm, HttpStatus.CREATED);
		return re;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.findAllEmployees();
	}

	@GetMapping("/employees/{empId}")
	public Employee getSingleProject(@PathVariable String empId) {
		return employeeRepo.findById(empId).get();
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
		return ResponseEntity.ok(employeeRepo.save(emp));

	}

	@DeleteMapping("/employees/{empId}")
	public ResponseEntity deleteProject(@PathVariable String empId) {
		if (employeeService.findEmployeetById(empId)) {
			employeeRepo.deleteById(empId);
			HashMap<String, String> hm = new HashMap<>();
			hm.put("msg", "Employee deleted Successfully");
			return new ResponseEntity(hm, HttpStatus.NO_CONTENT);
		} else {
			throw new ResourceNotFoundException("Employee not found for the Id::" + empId);
		}
	}

}
