package com.EmployeeApp.service;

public interface Registration<ID, EMPLOYEE> {
	EMPLOYEE registerEmployee(EMPLOYEE employee) throws Exception;

	EMPLOYEE getEmployee(ID employeeId) throws Exception;

	EMPLOYEE updateEmployee(EMPLOYEE employee) throws Exception;

    void deleteEmployee(ID employeeId) throws Exception;
}
