package com.luv2code.springboot.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.luv2code.springboot.entity.Employee;
import com.luv2code.springboot.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	
	private EmployeeService employeeService;
	private ObjectMapper objectMapper;
	
	//quick and dirty: inject employee dao
	public EmployeeRestController(EmployeeService employeeService,ObjectMapper objectMapper) {
		this.employeeService = employeeService;
		this.objectMapper = objectMapper;
	}
	
	
@GetMapping("/employees")
public List<Employee> getAll(){
	return employeeService.findAll();	
}

@GetMapping("/employees/{empId}")
public Employee findById(@PathVariable int empId) {
	 Employee theEmployee = employeeService.findById(empId);
	 
	 if(theEmployee == null) {
		 throw new RuntimeException("Employee not Found -" + empId);
	 }
	 return theEmployee;
}

@PostMapping("/employees")
public Employee addEmployee(@RequestBody Employee theEmployee) {
	theEmployee.setId(0);
	Employee dbEmployee = employeeService.save(theEmployee);
	return  dbEmployee;	
}

@PutMapping("/employees")
public Employee updateEmp(@RequestBody Employee emp) {
	Employee dbEmployee = employeeService.save(emp);
	return dbEmployee;
}

@DeleteMapping("/employees/{empId}")
public String delete(@PathVariable int empId) {
	Employee employe = employeeService.findById(empId);
	if(employe == null) {
		throw new RuntimeException("Employee Not Found at Id -"+empId);
	}
	employeeService.deleteById(empId);
	
	return "Deleted data for employee-"+empId;
}

@PatchMapping("/employees/{empId}")
public Employee partialUpdate(@PathVariable int empId,@RequestBody Map<String, Object> patchPayLoad)
{
	Employee tempEmployee = employeeService.findById(empId);
	
	if(tempEmployee == null) {
		throw  new RuntimeException("Employee not Found -"+empId);
	}
	if(patchPayLoad.containsKey("id")) {
		throw new RuntimeException("Employee id not allowed in requestBody-"+empId);
	}
	Employee patchEmployee = apply(patchPayLoad, tempEmployee);
	
	Employee employee = employeeService.save(patchEmployee);
	return employee;	
}

private Employee apply(Map<String, Object> patchPayLoad, Employee tempEmployee) {
	
	ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);
	
	ObjectNode patchNode = objectMapper.convertValue(patchPayLoad, ObjectNode.class);
	
	employeeNode.setAll(patchNode);
	
	return objectMapper.convertValue(employeeNode , Employee.class);
}


}
