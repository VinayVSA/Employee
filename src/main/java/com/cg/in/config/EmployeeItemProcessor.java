package com.cg.in.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee>{

	 @Override
	    public Employee process(Employee employee) {
	        // Example: Modify the employee data if needed
	        return employee;
	    }
	
}
