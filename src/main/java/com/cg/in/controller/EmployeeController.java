package com.cg.in.controller;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
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

import com.cg.in.converter.JaxbJsonConverter;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.service.EmployeeServiceImpl;
import com.cg.in.utils.constants.Utility;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//import jakarta.websocket.server.PathParam;
@Slf4j
@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {
	

	
	private EmployeeServiceImpl employeeService;
	

    private JaxbJsonConverter jaxbJsonConverter;
	
	
	private final JobLauncher jobLauncher;
	
	
   
    
    @Autowired
	public EmployeeController(EmployeeServiceImpl employeeService,JaxbJsonConverter jaxbJsonConverter,JobLauncher jobLauncher)
	{
    	this.jobLauncher = jobLauncher;
        this.employeeService = employeeService;
        this.jaxbJsonConverter = jaxbJsonConverter;
	}
	
	
	
	

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
	 
//	 @PostMapping("/convertToJson")
//	    public ResponseEntity<String> convertToJson(@RequestBody EmployeeBo employee) {
//	        try {
//	            String json = jaxbJsonConverter.convertPojoToJson(employee);
//	            return ResponseEntity.ok(json);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(500).body("Error converting POJO to JSON: " + e.getMessage());
//	        }
//	    }
//
//	    @PostMapping("/convertToPojo")
//	    public ResponseEntity<EmployeeBo> convertToPojo(@RequestBody String json) {
//	        try {
//	            EmployeeBo employee = jaxbJsonConverter.convertJsonToPojo(json);
//	            return ResponseEntity.ok(employee);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(500).body(null);
//	        }
//	    }
	 
	 
	    }

	 


