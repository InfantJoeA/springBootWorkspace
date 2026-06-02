package com.luv2code.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.dao.EmployeeRepository;
import com.luv2code.springboot.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;

	
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll() ;
	}
	
	
	@Override
	public Employee save(Employee emp) {
		
		return employeeRepository.save(emp);
	}
	
	@Override
	public Employee findById(int empId) {
		Optional<Employee> result = employeeRepository.findById(empId);
		
		Employee theEmployee = null;
		
		if(result.isPresent()) {
			theEmployee = result.get();
		}else {
			throw new RuntimeException("Did not find any value for empId - "+empId);
		}
		return theEmployee;
	}
	
	@Override
	public void deleteById(int empId) {
		
		employeeRepository.deleteById(empId);
	}
}
