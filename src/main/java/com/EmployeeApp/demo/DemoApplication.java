package com.EmployeeApp.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {		
		SpringApplication.run(DemoApplication.class, args);
	}

}


