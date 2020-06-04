package com.srinidhi.loginapp.service;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinidhi.loginapp.model.Project;
import com.srinidhi.loginapp.repo.ProjectRepo;

@Service
public class ProjectDAOService {
	
	@Autowired
	public ProjectRepo projRepo;
	
	public Project addProject(Project proj) {
		proj.setProjectId("PROJ_"+(projRepo.count()+1));
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateCustom = format.format(date);
		proj.setCreatedOn(dateCustom);
		projRepo.save(proj);
		return  proj;
	}
	
	public List<Project> findAllProjects() {
		return projRepo.findAll();
	}
	
}
