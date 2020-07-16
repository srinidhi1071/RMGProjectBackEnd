package com.rmgYantra.loginapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rmgYantra.loginapp.model.Employee;
import com.rmgYantra.loginapp.model.User;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, String>{


	public Employee findByEmpName(String empName);
	Optional<Employee> findByUsername(String username);
	public Page<Employee> findByDesignation(String designation,Pageable pageable);
	public Employee findByEmail(String email);
}