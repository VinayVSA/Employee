package com.cg.in.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.in.EmployeeMapper;
import com.cg.in.converter.JaxbJsonConverter;
import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {


	private EmployeeRepository employeeRepository;


	public EmployeeMapper employeeMapper;
	
	public JaxbJsonConverter jjc;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper,JaxbJsonConverter jjc)
	{
		this.employeeRepository=employeeRepository;
		this.employeeMapper=employeeMapper;
		this.jjc=jjc;

	}

	@Override
	public EmployeeBo createEmployee(EmployeeBo employeeBo) {
		
		log.info("Convertion the EmployeeBO to EmployeEO");
		Employee employee=employeeMapper.toEntity(employeeBo);

		log.info("Creating the employee object");
		Employee createdEmployee=employeeRepository.save(employee);

		// Convert POJO to JSON and store the output
	    String json = convertPojoToJson(employeeBo);

	    // Use the returned JSON string for converting back to POJO
	    convertJsonToPojo(json);
		log.info("Returning the the EmployeeBO object");
		
		return employeeMapper.toBO(createdEmployee);
		
		
	}

	@Override
	public EmployeeBo getEmployeeById(int id) {

		log.info("Retrieving the employee object using employee id");
		Employee emp= employeeRepository.findById(id).get();

		log.info("Returning the EmployeeBO object");
		return employeeMapper.toBO(emp);
	}

	@Override
	public List<EmployeeBo> getAllEmployees()
	{
       log.info("Retrieving the list of employees");
		return employeeRepository.findAll().stream()
         .map(employeeMapper::toBO)
         .collect(Collectors.toList());
	}
	
	public String convertPojoToJson(EmployeeBo emp) {
        try {
            String jsonString = jjc.convertPojoToJson(emp);
            log.info("Converted POJO to JSON: {}", jsonString);
            return jsonString;
        } catch (Exception e) {
            log.error("Error converting POJO to JSON", e);
            return null;
        }
    }
	
	 public void convertJsonToPojo(String json) {
	        try {
	            EmployeeBo emp = jjc.convertJsonToPojo(json, EmployeeBo.class);
	            log.info("Converted JSON to POJO: {}", emp);
	        } catch (Exception e) {
	            log.error("Error converting JSON to POJO", e);
	        }
	    }

}
