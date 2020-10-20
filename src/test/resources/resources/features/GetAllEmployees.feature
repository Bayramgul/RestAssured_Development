Feature: This Feature is to test all employees endpoint

Background:
Given user generates token

@GetAllEmployees
Scenario: Retrieving updated Employee using /getAllEmployees.php
Given user calls getAllEmployees to retrieve all employee
When User retrieves response for getAllEmployees to retrieve List of employees 
Then status code is 200 for getAllEmployees
Then user validates List of Employees is successfully Returned