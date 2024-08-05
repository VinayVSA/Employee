package com.cg.in.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


import com.cg.in.EmployeeMapper;
import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

//@MockitoSettings(strictness = Strictness.LENIENT)

@Slf4j
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeBo employeeBo;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        employee = new Employee(1, "John Doe", "Developer", 50000);
        employeeBo = new EmployeeBo(1, "John Doe", "Developer", 50000);
    }

    @Test
    void testCreateEmployee() {
    
      log.info("bo to entity");
      when(employeeMapper.toEntity(any(EmployeeBo.class))).thenReturn(employee);
      log.info("save to the repo");
      when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
      log.info("entity to bo");
      when(employeeMapper.toBO(any(Employee.class))).thenReturn(employeeBo);

      
        
        EmployeeBo result = employeeService.createEmployee(employeeBo);
        
        assertEquals(employeeBo, result);

       assertNotNull(result);
       assertEquals(1, result.getEmpId());
       assertEquals("John Doe", result.getEmpName());
    }
    

    @Test
    void testGetEmployeeById() {
    	log.info("retrieving by id");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        log.info("entity to bo");
        when(employeeMapper.toBO(any(Employee.class))).thenReturn(employeeBo);

        EmployeeBo result = employeeService.getEmployeeById(1);
        
        //assertEquals(employeeBo, result);

        assertNotNull(result);
       assertEquals(1, result.getEmpId());
       assertEquals("John Doe", result.getEmpName());
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee);
        List<EmployeeBo> employeeBos = Arrays.asList(employeeBo);

        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeMapper.toBO(any(Employee.class))).thenReturn(employeeBo);

        List<EmployeeBo> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getEmpName());
    }

}
