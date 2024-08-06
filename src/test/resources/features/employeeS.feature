Feature: Employee management

  Scenario: Create a new employee
    Given I have employee details
    When I create the employee
    Then the employee is created successfully

  Scenario: Retrieve an employee by ID
    Given an employee with ID 1 exists
    When I retrieve the employee by ID 1
    Then the employee details are returned