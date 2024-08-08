package com.cg.in.entities;

import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@XmlRootElement
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeBo {

	private int empId;
	
	@XmlElement
	@NotNull(message = "Employee Name cannot be null")
	@Size(min = 2, max = 20, message = "Employee Name must be between 2 and 20 characters")
    private String empName;
	
	@XmlElement
	@NotNull(message = "Employee Designation cannot be null")
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String empDesignation;
	
	@XmlElement
	 @Min(value = 0, message = "Salary must be positive")
    private double salary;

	 
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        EmployeeBo that = (EmployeeBo) o;
	        return empId == that.empId &&
	                Double.compare(that.salary, salary) == 0 &&
	                Objects.equals(empName, that.empName) &&
	                Objects.equals(empDesignation, that.empDesignation);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(empId, empName, empDesignation, salary);
	    }


}
