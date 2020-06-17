package com.srinidhi.loginapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srinidhi.loginapp.model.Employee;
import com.srinidhi.loginapp.model.User;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String>{


	public Employee findByEmpName(String empName);
	Optional<Employee> findByUsername(String username);
	public List<Employee> findByDesignation(String designation);
}