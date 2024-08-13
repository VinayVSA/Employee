package com.cg.in.config;


import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;
import com.cg.in.repository.EmployeeRepository;



@Component
public class EmployeeItemWriterCsvToH2 implements ItemWriter<Employee>{

	
	 @Autowired
	    private EmployeeRepository employeeRepository;

	    @Override
	    public void write(Chunk<? extends Employee> chunk) throws Exception {
	        employeeRepository.saveAll(chunk.getItems());
	    }
}
