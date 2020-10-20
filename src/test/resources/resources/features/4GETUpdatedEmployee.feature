Feature: Verifying updated employee using /getOneEmployee.php HRMS API
Background:
Given user generates token

@SyntaxHRMSAPIEndToEnd22
Scenario: Retrieving updated Employee using /getOneEmployee.php API
Given user calls getOneEmployee API to verify updated employee
When User retrieves response for getOneEmployeeAPI to verify updated employee 
Then status code is 200 for getOneEmployeeAPI to verify updated employee
Then user validates updated employee exists