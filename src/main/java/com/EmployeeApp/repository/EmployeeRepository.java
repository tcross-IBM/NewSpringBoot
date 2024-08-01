package com.EmployeeApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeApp.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{
}
