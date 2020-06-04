package com.srinidhi.loginapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srinidhi.loginapp.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String>{

}
