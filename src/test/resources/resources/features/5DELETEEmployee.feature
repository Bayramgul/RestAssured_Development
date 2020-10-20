Feature: Verifying deletion employee using /deleteEmployee.php HRMS API
Background:
Given user generates token

@SyntaxHRMSAPIEndToEnd22
Scenario: Retrieving updated Employee using /deleteEmployee.php API
Given user calls deleteEmployee API to retrieve updated employee
When User retrieves response for deleteEmployee API to retrieve updated employee 
Then status code is 200 for deleteEmployee API 
Then user validates employee is successfully deleted