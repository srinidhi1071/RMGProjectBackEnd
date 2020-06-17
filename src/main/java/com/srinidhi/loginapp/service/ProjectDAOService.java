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
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateCustom = format.format(date);
		proj.setCreatedOn(dateCustom);
		Project savedProject = projRepo.save(proj);
		return  savedProject;
	}
	
	public List<Project> findAllProjects() {
		return projRepo.findAll();
	}
	
	public void deleteProject(String id) {
		projRepo.deleteById(id);
	}
	
	public boolean findProjectById(String projectId) {
		Project project = projRepo.findById(projectId).get();
		if(project!=null) {
			return true;
		}else {
			return false;
		}
	}
}
