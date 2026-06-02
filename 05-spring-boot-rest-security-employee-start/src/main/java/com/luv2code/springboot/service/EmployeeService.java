package com.luv2code.springboot.service;

import java.util.List;

import com.luv2code.springboot.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee save(Employee emp);
	
	Employee findById(int empId);
	
	void deleteById(int empId);
}
