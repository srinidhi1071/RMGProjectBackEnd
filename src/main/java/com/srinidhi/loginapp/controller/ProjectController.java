package com.srinidhi.loginapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srinidhi.loginapp.model.Project;
import com.srinidhi.loginapp.service.ProjectDAOService;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")
public class ProjectController {
	
	@Autowired
	public ProjectDAOService projDAOService;
	
	@PostMapping("/addProject")
	public ResponseEntity addProject(@RequestBody Project project) {
		Project proj = projDAOService.addProject(project);
		HashMap<String, String> hm = new HashMap<>();
		hm.put("projectName", proj.getProjectName());
		hm.put("projectId", proj.getProjectId());
		hm.put("createdOn", proj.getCreatedOn());
		hm.put("msg", "Successfully Added");
		ResponseEntity re = new ResponseEntity(hm, HttpStatus.CREATED);
		return re;
	}
	
	@GetMapping("/projects")
	public List<Project> getAllProjects(){
		return projDAOService.findAllProjects();
	}
}
