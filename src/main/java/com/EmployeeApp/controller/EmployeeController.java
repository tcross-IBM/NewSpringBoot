package com.EmployeeApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;

//@RestController
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeController() {
		
	}
	
	public EmployeeController(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}
	

//	@GetMapping("/employee")
//	public ResponseEntity<List<Employee>> getAllEmployees() {
//		return ResponseEntity.ok(this.employeeRepository.findAll());
//	}
//	
//	@GetMapping("/employee/{id}")
//	public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
//		return ResponseEntity.ok(this.employeeRepository.findById(id).get());
//	}
	

	
//	//comment out  later
//	@PostMapping("/employee")
//	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
//		return ResponseEntity.ok(this.employeeRepository.save(employee));
//	}
//	
//	@PutMapping("/employee/{id}")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails){
//		Employee employee = this.employeeRepository.findById(id).get();
//		
//		employee.setFname(employeeDetails.getFname());
//		employee.setMname(employeeDetails.getMname());
//		employee.setLname(employeeDetails.getMname());
//		employee.setDob(employeeDetails.getDob());
//		employee.setPosition(employeeDetails.getPosition());
//		
//		Employee updatedEmployee = employeeRepository.save(employee);
//		return ResponseEntity.ok(updatedEmployee);
//	}
//	
//	// delete employee rest api
//	@DeleteMapping("/employee/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String id){
//		Employee employee = this.employeeRepository.findById(id).get();
//		
//		employeeRepository.delete(employee);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}
//	//comment out end
	
//	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
    @GetMapping("/") 
    public String viewHomePage(Model model) {
        model.addAttribute("allemplist", this.employeeRepository.findAll());
        return "index";
    }
    
    @GetMapping("/addemployee")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addemployee";
    }
   
    @GetMapping("/addemployee/{id}")
    public String updateEmployee(@PathVariable(value = "id") String id, Model model) {
        Employee employee = this.employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);
        return "addemployee";
    }
 
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    	employee.setEmail(employee.getEmail().toLowerCase());
    	this.employeeRepository.save(employee);
//      return "redirect:/index";
    	return "redirect:/";
    }
 
    //change to put later?
    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") String id, Model model) {
        Employee employee = this.employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);
        return "update";
    }
 
    @GetMapping("/deleteEmployee/{id}")
    public String deleteThroughId(@PathVariable(value = "id") String id) {
    	this.employeeRepository.deleteById(id);
//        return "redirect:/index";
        return "redirect:/";
    }
    @GetMapping("/deleteAllEmployees")
    public String deleteAll() {
    	this.employeeRepository.deleteAll();
        //return "redirect:/index";
    	return "redirect:/";
    }
    
    //DONT FORGET TO ALSO ACCEPT LOWERCASE 
    @PostMapping("/updateEmployee")
    public String addToEmployee(String username, String password, String email, Model model, RedirectAttributes redirectAttributes) {
    	Employee employee = this.employeeRepository.findByEmail(email.toLowerCase());
    	if (employee != null) {
    		try {
	    		employee.setUsername(username);
	    		employee.setPassword(password);
	//    		model.addAttribute("employee", employee);
	    		employeeRepository.save(employee); 
	    		
	    		redirectAttributes.addFlashAttribute("successMessage", "User Info Created/Updated");
//	    		redirectAttributes.addAttribute("employee", employee.getId());
	    		
	    		return "redirect:/login";
    		} catch(Exception e) {
    			System.out.println(e.toString());
    		}
    	}
    	redirectAttributes.addFlashAttribute("failureMessage", "User not created due to error");
    	return "redirect:/register";
    }
    
//    @GetMapping("/updateEmployee")
//    public String addToEmployee(Model model) {
//    	System.out.println("model =" + model.toString());
//    	String email = (String) model.getAttribute("email");
//    	System.out.println("email = " + email);
//    	Employee employee = this.employeeRepository.findByEmail(email);
//    	if (employee != null) {
//    		model.addAttribute("employee", employee);
//    	}
//        return "redirect:/register";
//    }
//    

    
    
    
//    @GetMapping("/logout")
//    public String logout(){
//    	System.out.println("callEd");
//    	return "logout";
//    }
	
//    @GetMapping("/")
//    public String loginForm() {
//        return "redirect:/login";
//    }
    
  
    
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


