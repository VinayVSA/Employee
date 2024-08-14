package com.cg.in;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;
@Component
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper INSTANCE =Mappers.getMapper(EmployeeMapper.class);
	
    EmployeeBo toBO(Employee employee);
    Employee toEntity(EmployeeBo employeeBO);
    
 // Method to map a collection of Employee to EmployeeBo
    List<EmployeeBo> toBOList(List<Employee> employees);
    
    // Method to map a collection of EmployeeBo to Employee
    List<Employee> toEntityList(List<EmployeeBo> employeeBOs);
}
