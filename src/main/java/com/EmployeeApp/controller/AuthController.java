package com.EmployeeApp.controller;

import lombok.AllArgsConstructor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.EmployeeApp.entity.ERole;
//import com.EmployeeApp.entity.Role;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;
//import com.EmployeeApp.repository.RoleRepository;
//import com.EmployeeApp.security.JwtUtils;

//@CrossOrigin(origins = "*", maxAge = 3600)

//@RequestMapping("/api/auth")
//@Import({SecurityConfig.class})
//@AllArgsConstructor
@Controller
public class AuthController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	

	@Autowired(required=false)/* <- delete requried later */
    private PasswordEncoder passwordEncoder;
	
	
	public AuthController() {
		
	}
	
	public AuthController(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}
	

//    @PostMapping("/register")
//    public ResponseEntity registerUser(Employee employee){
//        try {
//            if (employeeRepository.findByEmail(employee.getEmail()).isPresent())
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
//            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//            Employee save = EmployeeRepository.save(employee);
//            return ResponseEntity.ok(HttpStatus.CREATED);
//        } catch (Exception e){
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//    }

    @GetMapping("/register")
    public String addNewEmployee(Model model) {
    	Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "register";
    }
    

}