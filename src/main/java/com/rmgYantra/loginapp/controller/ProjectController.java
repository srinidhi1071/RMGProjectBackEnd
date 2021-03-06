package com.rmgYantra.loginapp.controller;

import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rmgYantra.loginapp.exceptions.ProjectNameAlreadyPresentException;
import com.rmgYantra.loginapp.exceptions.ResourceNotFoundException;
import com.rmgYantra.loginapp.model.Project;
import com.rmgYantra.loginapp.repo.ProjectRepo;
import com.rmgYantra.loginapp.service.ProjectDAOService;

@RestController
@CrossOrigin(origins = "*")
public class ProjectController {

	@Autowired
	public ProjectDAOService projDAOService;

	@Autowired
	public ProjectRepo projRepo;

	@PostMapping("/addProject")
	public ResponseEntity addProject(@RequestBody Project project) {
		
		if(projRepo.findByProjectName(project.getProjectName()).isPresent())
		{
			throw new ProjectNameAlreadyPresentException("The Project Name :"+project.getProjectName()+" Already Exists");
		}
		Project proj = projDAOService.addProject(project);
		HashMap<String, String> hm = new HashMap<>();
		hm.put("projectName", proj.getProjectName());
		hm.put("projectId", proj.getProjectId());
		hm.put("createdOn", proj.getCreatedOn());
		hm.put("status",proj.getStatus()); 
		hm.put("createdBy", proj.getCreatedBy());
		hm.put("msg", "Successfully Added");
		ResponseEntity re = new ResponseEntity(hm, HttpStatus.CREATED);
		return re;
	}

	@GetMapping("/projects")
	public List<Project> getAllProjects() {
		return projDAOService.findAllProjects();
	}

	@GetMapping("/projects/{projectId}")
	public Project getSingleProject(@PathVariable String projectId) {
		return projRepo.findById(projectId).get();
	}

	@PutMapping("/projects/{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Project project) {
		Project proj = projRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project Not Found For the Id::" + projectId));
		proj.setProjectId(projectId);
		proj.setCreatedBy(project.getCreatedBy());
		proj.setProjectName(project.getProjectName());
		proj.setTeamSize(project.getTeamSize());
		proj.setStatus(project.getStatus());
		Project updatedProject = projRepo.save(proj);
		return ResponseEntity.ok(updatedProject);
	}

	@DeleteMapping("/projects/{projectId}")
	public ResponseEntity deleteProject(@PathVariable String projectId) {
		if (projDAOService.findProjectById(projectId)) {
			projDAOService.deleteProject(projectId);
			HashMap<String, String> hm = new HashMap<>();
			hm.put("msg", "resource deleted successfully");
			ResponseEntity re = new ResponseEntity(hm, HttpStatus.NO_CONTENT);
			return re;
		} else {
			throw new ResourceNotFoundException("Project Not Found For the Id::" + projectId);
		}

	}
}
