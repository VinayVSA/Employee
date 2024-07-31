package com.cg.in;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper INSTANCE =Mappers.getMapper(EmployeeMapper.class);

    EmployeeBo toBO(Employee employee);
    Employee toEntity(EmployeeBo employeeBO);
}
