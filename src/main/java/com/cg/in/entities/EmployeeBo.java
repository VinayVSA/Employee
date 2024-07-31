package com.cg.in.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeBo {

	private int empId;
	
	@NotNull(message = "Employee Name cannot be null")
	@Size(min = 2, max = 20, message = "Employee Name must be between 2 and 20 characters")
    private String empName;
	
	@NotNull(message = "Employee Designation cannot be null")
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String empDesignation;
	
	 @Min(value = 0, message = "Salary must be positive")
    private double salary;



}
