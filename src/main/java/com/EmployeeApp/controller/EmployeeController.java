package com.EmployeeApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(this.employeeRepository.findAll());
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
}
