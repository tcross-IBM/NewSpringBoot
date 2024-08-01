package com.EmployeeApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeController() {
		
	}
	
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


//if I never use code below again delete com.EmployeeApp.service package and associated code

//import java.util.HashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.EmployeeApp.model.Employee;
//import com.EmployeeApp.repository.EmployeeRepository;
//import com.EmployeeApp.service.RegistrationImpl;
//
//@RestController
//public class EmployeeController {
//	
//    @Autowired
//    private RegistrationImpl registrationImpl;
//    
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    
////	@GetMapping("/employee")
////	public ResponseEntity getAllEmployee() throws Exception {
////		HashMap<String, Object> resp = new HashMap<>();
////        registrationImpl.getAllEmployee();
////        return new ResponseEntity<>(resp, HttpStatus.OK);
////	}
//    
//    @PostMapping(path = "/employee")
//    public ResponseEntity registerUser(@RequestBody Employee employee) throws Exception {
//        HashMap<String, Object> resp = new HashMap<>();
//        registrationImpl.registerEmployee(employee);
//        resp.put("employee", employee);
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }
//    
//	@GetMapping("/employee")
//	public ResponseEntity<List<Employee>> getAllEmployees() {
//		return ResponseEntity.ok(this.employeeRepository.findAll());
//	}
//
//}
//
