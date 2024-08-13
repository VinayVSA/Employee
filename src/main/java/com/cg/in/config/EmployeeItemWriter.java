package com.cg.in.config;

import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
@Component
public class EmployeeItemWriter implements ItemWriter<Employee> {

	private static final String FILE_HEADER = "ID,EmpName,Designation,Salary";
    private static final String FILE_NAME = "src/main/resources/employeesH2ToCsv.csv";

    @Override
    public void write(Chunk<? extends Employee> employees) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            if (new java.io.File(FILE_NAME).length() == 0) {
                writer.write(FILE_HEADER + "\n");
            }
            for (Employee employee : employees) {
                writer.write(String.format("%d,%s,%s,%.2f\n", employee.getEmpId(), employee.getEmpName(), employee.getEmpDesignation(), employee.getSalary()));
                
            }
        }
    }
	
}
