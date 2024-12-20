package com.EmployeeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.EmployeeApp.controller.AuthController;
import com.EmployeeApp.controller.EmployeeController;
import com.EmployeeApp.model.ERole;
import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;
import com.EmployeeApp.security.SecurityConfiguration;
import com.EmployeeApp.services.CustomUserDetailsService;
import com.EmployeeApp.services.EmployeeService;


////uncomment to disable security
////@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class}) //<-- broke application have to comment and uncomment at start in order to run app
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class,  GroovyTemplateAutoConfiguration.class}, scanBasePackages={
//		"com.EmployeeApp.controller", "com.EmployeeApp.repository", "com.EmployeeApp.services"})
////@ComponentScan(basePackageClasses = EmployeeController.class)
//@ComponentScan(basePackageClasses = {EmployeeController.class, EmployeeRepository.class, UserRepository.class})
//public class DemoApplication {
//
//	public static void main(String[] args) {		
//		SpringApplication.run(DemoApplication.class, args);
//	}
//	
//}


//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class}) //<-- broke application have to comment and uncomment at start in order to run app
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages={
		"com.EmployeeApp.controller", "com.EmployeeApp.repository", "com.EmployeeApp.services", "com.EmployeeApp.security"})
//@ComponentScan(basePackageClasses = EmployeeController.class)
@ComponentScan(basePackageClasses = {EmployeeController.class, EmployeeRepository.class, AuthController.class, SecurityConfiguration.class, CustomUserDetailsService.class, EmployeeService.class})
public class DemoApplication {
	
    @Autowired
    private EmployeeRepository employeeRepository;

	public static void main(String[] args) {		
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
    CommandLineRunner initDatabase() {
        return args -> {
            Employee employee = new Employee();
            employee.setEmail("admin");
            employee.setPassword(new BCryptPasswordEncoder().encode("password"));
            employee.setRole(ERole.ADMIN);
            employeeRepository.saveIfNotExist(employee);
        };
	
	}
}




