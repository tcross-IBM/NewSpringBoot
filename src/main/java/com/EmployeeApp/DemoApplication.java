package com.EmployeeApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.EmployeeApp.controller.EmployeeController;
import com.EmployeeApp.repository.EmployeeRepository;
import com.EmployeeApp.service.Registration;
import com.EmployeeApp.service.RegistrationImpl;


//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class}) <-- broke application
@SpringBootApplication(scanBasePackages={
		"com.EmployeeApp.controller", "com.EmployeeApp.repository", "com.EmployeeApp.service"})
//@ComponentScan(basePackageClasses = EmployeeController.class)
@ComponentScan(basePackageClasses = {EmployeeController.class, RegistrationImpl.class, EmployeeRepository.class})
public class DemoApplication {

	public static void main(String[] args) {		
		SpringApplication.run(DemoApplication.class, args);
	}
	
}


