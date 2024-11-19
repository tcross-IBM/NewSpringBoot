package com.EmployeeApp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

@Document(collection = "employees")
@JsonPropertyOrder({"id", "fname", "mname", "lname", "dob", "position"})
public class Employee {
	
	@Id
	private String id;
		 
	private String fname;
		 
	private String mname;
		 
	private String lname;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
		 
	private String position;
	
	private ERole role;
	
	@Column(unique = true)
	@Email(message = "Please provide a valid email address")
	private String email;
	
    private String password;
    
    @Column(unique = true)
    private String token;
		 

	public Employee() {
	}

	public Employee(String iD, String fname, String mname, String lname, Date dob, String position, ERole role, String email, String username, String password, String token) {
		super();
		id = iD;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.dob = dob;
		this.position = position;
		this.role = role;
		this.email = email;
		this.password = password;
		this.token = token;
	}
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	 
	 
	 
	
}
