//package com.EmployeeApp.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import com.EmployeeApp.entity.ERole;
//import com.EmployeeApp.entity.Role;
//
//public interface RoleRepository extends MongoRepository<Role, String> {
//  Optional<Role> findByName(ERole name);
//}