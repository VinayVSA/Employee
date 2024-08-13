package com.cg.in.config;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;
import com.cg.in.repository.EmployeeRepository;
import java.util.Iterator;
@Component
public class EmployeeItemReader implements ItemReader<Employee> {
	private final EmployeeRepository employeeRepository;
    private Iterator<Employee> employeeIterator;

    @Autowired
    public EmployeeItemReader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee read() {
        if (employeeIterator == null) {
            employeeIterator = employeeRepository.findAll().iterator();
        }
        if (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            
            return employee;
        } else {
            return null;
        }
    }

}
