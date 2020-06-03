package com.srinidhi.loginapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srinidhi.loginapp.model.Project;

public interface ProjectRepo extends JpaRepository<Project,String> {

}
