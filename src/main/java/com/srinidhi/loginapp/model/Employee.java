 package com.srinidhi.loginapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_seq")
	@GenericGenerator(name = "emp_seq",strategy = "com.srinidhi.loginapp.model.StringPrefixedSequesceGenerator",
	parameters = {@Parameter(name = StringPrefixedSequesceGenerator.VALUE_PREFIX_PARAMETER, value = "TYP_"),
			@Parameter(name = StringPrefixedSequesceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d"),
			@Parameter(name = StringPrefixedSequesceGenerator.INCREMENT_PARAM, value = "200")})
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
	
	private String username;
	
	private String designation;
	
	private String password;
	
	private String role; 
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getDesignation() {
		return designation;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}




	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}
	
	
	

}
