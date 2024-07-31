package com.cg.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.in.constants.Utility;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.service.EmployeeServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//import jakarta.websocket.server.PathParam;
@Slf4j
@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping("/create")
	public ResponseEntity<EmployeeBo> createEmployee(@Valid @RequestBody EmployeeBo employee)
	{
		log.info(Utility.CREATED,employee);
		EmployeeBo createdEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<>(createdEmployee,HttpStatus.CREATED);
	}
	@GetMapping("/retrieve")
	public EmployeeBo getEmployeeById(@RequestParam("id") int id)
	{
		log.info(Utility.RETRIEVEEMPLOYEE,id);
		return employeeService.getEmployeeById(id);
	}
	@GetMapping
	public List<EmployeeBo> getEmployee()
	{
		log.info(Utility.RETRIEVE_ALL_EMPLOYEES);
		return employeeService.getAllEmployees();
	}

	 @GetMapping("/health")
	    public ResponseEntity<String> healthCheck() {
	        log.info("Health check endpoint hit");
	        return new ResponseEntity<>("Service is up and running", HttpStatus.OK);
	    }

}
