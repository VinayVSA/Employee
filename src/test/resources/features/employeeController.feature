
Feature: Controller feature

  Scenario: Creation of Employee
    Given I have Employee details
    When I created the employee
    Then I Return the created employee
   

  Scenario: Retrieving the Employee details
    Given I have the employee id 1
    When I retrieve the employee details
    Then I return employee details

  
  Scenario: Retrieving all Employee details
    When I retrieve all employee details
    Then I return all employee details
  
  Scenario: Checking the health of the service
    When I check the health of the service
    Then the service is running
  
 