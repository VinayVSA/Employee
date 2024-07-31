package com.cg.in.service;

import java.util.List;

import com.cg.in.entities.EmployeeBo;

public interface EmployeeService {

	public EmployeeBo createEmployee(EmployeeBo employee);

	public EmployeeBo getEmployeeById(int id);

	public List<EmployeeBo> getAllEmployees();

}
