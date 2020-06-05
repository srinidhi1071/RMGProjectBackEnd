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
			@Parameter(name = StringPrefixedSequesceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d"),
			@Parameter(name = StringPrefixedSequesceGenerator.INCREMENT_PARAM, value = "50")})
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
