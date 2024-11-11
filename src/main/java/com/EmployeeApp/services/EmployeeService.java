package com.EmployeeApp.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String sendPasswordResetEmail(String email) {
        Employee employeeOptional = employeeRepository.findByEmail(email);

        if (employeeOptional != null) {
            Employee employee = employeeOptional;
            // Generate a unique token for password reset
            String token = UUID.randomUUID().toString();

            // Store the token in the database (e.g., in a separate collection or as a field in the employee document)

            // Send the reset email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Password Reset");
            mailMessage.setText("Click the following link to reset your password: localhost:8080/forgotpassword/" + token);
            mailSender.send(mailMessage);
            
            return token;
        } else {
        	//if email doesnt exist
        	return "failure";
        }
    }
}