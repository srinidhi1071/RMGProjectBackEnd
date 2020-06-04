package com.srinidhi.loginapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
	
	@Id
	private String projectId;
	private String projectName;
	private int teamSize;
	private String createdBy;
	private String createdOn;
	
	public Project(String projectId, String projectName, int teamSize, String createdBy, String createdOn) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.teamSize = teamSize;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
}
