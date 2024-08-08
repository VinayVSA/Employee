package com.cg.in.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.cg.in.EmployeeApplication;
import com.cg.in.config.WireMockConfig;
import com.cg.in.entities.EmployeeBo;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {EmployeeApplication.class, WireMockConfig.class})
@ActiveProfiles("test")
public class EmployeeControllerTest {
    
	
    @Autowired
    private WireMockServer wireMockServer;

    @SpyBean
    private EmployeeController employeeController;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
    	System.out.println("WireMock server running on port: " + wireMockServer.port());
        if (!wireMockServer.isRunning()) {
            wireMockServer.start();
        }
        wireMockServer.resetAll();
    }
    
    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testCreateEmployee() {
        int wireMockPort = wireMockServer.port(); // Get the dynamically assigned port
        
        // Configure WireMock stub for dynamic port
        stubFor(post(urlEqualTo("/employee/create"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"empId\": 1, \"empName\": \"John Doe\", \"empDesignation\": \"Developer\", \"salary\": 5000 }")));

        when(restTemplate.postForObject(anyString(), any(EmployeeBo.class), eq(EmployeeBo.class)))
                .thenReturn(new EmployeeBo(1, "John Doe", "Developer", 5000));

        ResponseEntity<EmployeeBo> response = employeeController.createEmployee(new EmployeeBo(1, "John Doe", "Developer", 5000));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(new EmployeeBo(1, "John Doe", "Developer", 5000), response.getBody());
    }
    
    @Test
    public void testGetEmployeeById() {
        // Configure WireMock stub for getEmployeeById endpoint
        stubFor(get(urlPathEqualTo("/employee/retrieve"))
                .withQueryParam("id", equalTo("1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"empId\": 1, \"empName\": \"John Doe\", \"empDesignation\": \"Developer\", \"salary\": 5000 }")));

        when(restTemplate.getForObject(anyString(), eq(EmployeeBo.class), anyInt()))
                .thenReturn(new EmployeeBo(1, "John Doe", "Developer", 5000));

        EmployeeBo response = employeeController.getEmployeeById(1);
        assertEquals(new EmployeeBo(1, "John Doe", "Developer", 5000), response);
    }

    @Test
    public void testGetEmployee() {
        stubFor(get(urlEqualTo("/employee"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{ \"empId\": 1, \"empName\": \"John Doe\", \"empDesignation\": \"Developer\", \"salary\": 5000 }]")));

        List<EmployeeBo> employees = Arrays.asList(new EmployeeBo(1, "John Doe", "Developer", 5000));
        when(restTemplate.getForObject(anyString(), eq(EmployeeBo[].class)))
                .thenReturn(employees.toArray(new EmployeeBo[0]));

        List<EmployeeBo> response = employeeController.getEmployee();
        assertEquals(employees, response);
    }

    @Test
    public void testHealthCheck() {
        // Configure WireMock stub for healthCheck endpoint
        stubFor(get(urlEqualTo("/employee/health"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Service is up and running")));

        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("Service is up and running");

        ResponseEntity<String> response = employeeController.healthCheck();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service is up and running", response.getBody());
    }
    
    
}