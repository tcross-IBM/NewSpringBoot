package com.EmployeeApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.EmployeeApp.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer>{
	
}
