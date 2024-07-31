package com.cg.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.in.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
