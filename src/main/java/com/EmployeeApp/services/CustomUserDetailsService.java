package com.EmployeeApp.services;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Employee employee = employeeRepository.findByEmailIgnoreCase(email);

        if (employee == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        
        Collection<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_" + employee.getRole().toString())
            );
        

        return new org.springframework.security.core.userdetails.User(
	                employee.getEmail(),
	                employee.getPassword(),
	                authorities
	                
	               
	                // ... authorities (if needed) 
        );
    }
    

    
    
    
    
    
}