package com.EmployeeApp.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeApp.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{
	Employee findByEmail(String email);
	default void saveIfNotExist(Employee employee) {
		if(findByEmail(employee.getEmail()) == null) {
			System.out.println("username not found");
			save(employee);
		}
	}
	Employee findByEmailIgnoreCase(String username);
}
