package com.rmgYantra.loginapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmgYantra.loginapp.model.Project;

public interface ProjectRepo extends JpaRepository<Project,String> {
	
	
	public Optional<Project> findByProjectName(String projName);

}
