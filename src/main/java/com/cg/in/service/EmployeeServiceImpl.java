package com.cg.in.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.in.EmployeeMapper;
import com.cg.in.controller.EmployeeController;
import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	private EmployeeRepository employeeRepository;


	public EmployeeMapper employeeMapper;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper)
	{
		this.employeeRepository=employeeRepository;
		this.employeeMapper=employeeMapper;

	}

	@Override
	public EmployeeBo createEmployee(EmployeeBo employeeBo) {
		
		Employee employee=EmployeeMapper.INSTANCE.toEntity(employeeBo);

		Employee createdEmployee=employeeRepository.save(employee);

		return employeeMapper.toBO(createdEmployee);
	}

	@Override
	public EmployeeBo getEmployeeById(int id) {

		Employee emp= employeeRepository.findById(id).get();

		return EmployeeMapper.INSTANCE.toBO(emp);
	}

	@Override
	public List<EmployeeBo> getAllEmployees()
	{

		return employeeRepository.findAll().stream()
         .map(employeeMapper::toBO)
         .collect(Collectors.toList());
	}

}
