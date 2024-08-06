package com.cg.in.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import com.cg.in.entities.EmployeeBo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerStepdefs {

	
	@Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<EmployeeBo> response;
    private ResponseEntity<String> healthCheckResponse;
    private ResponseEntity<EmployeeBo[]> allEmployeesResponse;
    private int employeeId;
    private EmployeeBo employee;

    @Given("I have Employee details")
    public void i_have_employee_details() {
        employee = new EmployeeBo();
        employee.setEmpName("John Doe");
        employee.setEmpDesignation("Developer");
        employee.setSalary(50000);
    }

    @When("I created the employee")
    public void i_created_the_employee() {
        response = restTemplate.postForEntity("http://localhost:8989/employee/create", employee, EmployeeBo.class);
    }

    @Then("I Return the created employee")
    public void i_return_the_created_employee() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmpName()).isEqualTo(employee.getEmpName());
    }

    @Given("I have the employee id {int}")
    public void i_have_the_employee_id(int id) {
        employeeId = id;
    }

    @When("I retrieve the employee details")
    public void i_retrieve_the_employee_details() {
        response = restTemplate.getForEntity("http://localhost:8989/employee/retrieve?id=" + employeeId, EmployeeBo.class);
    }

    @Then("I return employee details")
    public void i_return_employee_details() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @When("I retrieve all employee details")
    public void i_retrieve_all_employee_details() {
        allEmployeesResponse = restTemplate.getForEntity("http://localhost:8989/employee", EmployeeBo[].class);
    }

    @Then("I return all employee details")
    public void i_return_all_employee_details() {
        assertThat(allEmployeesResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allEmployeesResponse.getBody()).isNotEmpty();
    }

    @When("I check the health of the service")
    public void i_check_the_health_of_the_service() {
        healthCheckResponse = restTemplate.getForEntity("http://localhost:8989/employee/health", String.class);
    }

    @Then("the service is running")
    public void the_service_is_running() {
        assertThat(healthCheckResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(healthCheckResponse.getBody()).isEqualTo("Service is up and running");
    }
}
