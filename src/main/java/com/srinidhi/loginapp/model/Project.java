package com.srinidhi.loginapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {
	
	@Id
	private String projectId;
	private String projectName;
	private int teamSize;
	private String createdBy;
	private String createdOn;
	
	public Project() {
		
	}
	
	public Project(String projectId, String projectName, int teamSize, String createdBy, String createdOn) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.teamSize = teamSize;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}



	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	
	

}
