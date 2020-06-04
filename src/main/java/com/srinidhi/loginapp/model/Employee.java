package com.srinidhi.loginapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
	
	@Id
	private String empId;
	
	@Size(min=5,max=20)
	private String empName;
	
	@Pattern(regexp="(^$|[0-9]{10})",message = "Enter a valid mobile number")
	private String mobileNo;
	
	@Size(min=5,max=20)
	private String email;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String dob;
	
	private double experience;
	

}
