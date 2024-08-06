package com.cg.in.steps;

import com.cg.in.entities.EmployeeBo;
import com.cg.in.service.EmployeeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeStepdefs {

    @Autowired
    private EmployeeService employeeService;

    private EmployeeBo employeeBo;
    private EmployeeBo createdEmployee;

    @Given("I have employee details")
    public void i_have_employee_details() {
        employeeBo = new EmployeeBo(1, "John Doe", "developer", 123456);
    }

    @When("I create the employee")
    public void i_create_the_employee() {
        createdEmployee = employeeService.createEmployee(employeeBo);
    }

    @Then("the employee is created successfully")
    public void the_employee_is_created_successfully() {
        assertEquals(employeeBo.getEmpName(), createdEmployee.getEmpName());
    }

    @Given("an employee with ID {int} exists")
    public void an_employee_exists_with_id(Integer id) {
        employeeBo = new EmployeeBo(id, "Jane Doe", "developer", 987654);
        employeeService.createEmployee(employeeBo);
    }

    @When("I retrieve the employee by ID {int}")
    public void i_retrieve_the_employee_by_id(Integer id) {
        createdEmployee = employeeService.getEmployeeById(id);
    }

    @Then("the employee details are returned")
    public void the_employee_details_are_returned() {
        assertEquals(employeeBo.getEmpId(), createdEmployee.getEmpId());
    }
}
