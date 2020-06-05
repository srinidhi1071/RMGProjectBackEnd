package com.srinidhi.loginapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "proj_seq")
	@GenericGenerator(name = "proj_seq",strategy = "com.srinidhi.loginapp.model.StringPrefixedSequesceGenerator",
	parameters = {@Parameter(name = StringPrefixedSequesceGenerator.VALUE_PREFIX_PARAMETER, value = "TY_PROJ_"),
			@Parameter(name = StringPrefixedSequesceGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d"),
			@Parameter(name = StringPrefixedSequesceGenerator.INCREMENT_PARAM, value = "200")})
	private String projectId;
	private String projectName;
	private int teamSize;
	private String createdBy;
	private String createdOn;
	private String status;
	
	
	
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Project(String projectId, String projectName, int teamSize, String createdBy, String createdOn,
			String status) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.teamSize = teamSize;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.status = status;
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



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
