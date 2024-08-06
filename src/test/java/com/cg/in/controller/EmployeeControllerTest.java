package com.cg.in.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.in.entities.EmployeeBo;
import com.cg.in.service.EmployeeServiceImpl;

//import com.cg.in.service.EmployeeServiceImplTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	@Mock
	private EmployeeServiceImpl employeeService;
	
	@InjectMocks
	private EmployeeController empController;
	
	private EmployeeBo empBo;
	
	
	

	

}
