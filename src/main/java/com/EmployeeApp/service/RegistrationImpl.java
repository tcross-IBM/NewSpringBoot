package com.EmployeeApp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;
import com.EmployeeApp.service.Registration;

@Service
public class RegistrationImpl implements Registration<String, Employee> {
	
	@Autowired
    private EmployeeRepository employeeRepository;
   

    @Override
    public Employee registerEmployee(Employee employee) throws Exception {
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployee(String employeeId) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).get();
        return employee;
    }

    public List<Employee> getAllEmployee() throws Exception {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) throws Exception {
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(String employeeId) throws Exception {
        if (employeeId == null) {
            throw new Exception("employee id is null");
        } else {
            employeeRepository.deleteById(employeeId);
        }
    }
}
