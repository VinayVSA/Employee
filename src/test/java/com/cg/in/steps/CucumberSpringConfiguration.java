package com.cg.in.steps;

import org.springframework.boot.test.context.SpringBootTest;

import com.cg.in.EmployeeApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = EmployeeApplication.class)
public class CucumberSpringConfiguration {

}
