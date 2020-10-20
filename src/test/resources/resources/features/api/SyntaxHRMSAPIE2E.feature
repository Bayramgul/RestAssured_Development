@APIE2E
Feature: This feature tests Syntax HRMS API workflow
  Description: This feature tests Syntax HRMS web services workflow

  Scenario: Generating a JSON Web token
    Given authenticated credentials are available to generate a token
      | email           | password  |
      | email@email.com | syntax123 |
    When a POST call is made to "/generateToken.php"
    Then a token is generated
    And /generateToken.php response status code is 201

  Scenario: Creating an employee
    Given the Content-Type and JWT are set as headers
    And a payload to create an employee consists of valid key and value pairs
      | emp_firstname   | emp_lastname   | emp_middle_name  | emp_gender | emp_birthday | emp_status | emp_job_title   |
      | syntaxFirstName | syntaxLastName | syntaxMiddleName | F          | 2000-07-11   | Employee   | Cloud Architect |
    When a POST call is made to "/createEmployee.php"
    Then /createEmployee response matches expected data
      | emp_firstname   | emp_lastname   | emp_middle_name  | emp_gender | emp_birthday | emp_status | emp_job_title   |
      | syntaxFirstName | syntaxLastName | syntaxMiddleName | Female     | 2000-07-11   | Employee   | Cloud Architect |
    And /createEmployee.php response status code is 201
    And /createEmployee.php response body contains key "Message" and value "Entry Created"
    And /createEmployee.php response body contains "employee_id"
    And the employee ID is stored as a global variable

  Scenario: Retrieving created employee
    Given the Content-Type and JWT are set as headers for retrieving created employee
    When a GET call is made to "/getOneEmployee.php" to retrieve created employee
    Then retrieved response for created employee matches expected data
      | emp_firstname   | emp_middle_name  | emp_lastname   | emp_birthday | emp_gender | emp_job_title   | emp_status |
      | syntaxFirstName | syntaxMiddleName | syntaxLastName | 2000-07-11   | Female     | Cloud Architect | Employee   |
    And retrieved response for created employee contains same employee ID generated from created employee
    And retrieved response for created employee status code is 200

  Scenario: Retrieving all employees and verifying created employee details display in response
    Given the Content-Type and JWT are set as headers
    When a GET call is made to "/getAllEmployees.php" to retrieve all employees
    Then retrieved response for all employees contains created employee expected data
      | emp_firstname   | emp_middle_name  | emp_lastname   | emp_birthday | emp_gender | emp_job_title   | emp_status |
      | syntaxFirstName | syntaxMiddleName | syntaxLastName | 2000-07-11   | Female     | Cloud Architect | Employee   |
     And retrieved response for all employees contains same employee ID generated from created employee
     And retrieved response for all employees status code is 200
     
     Scenario: Updating created employee
    Given the Content-Type and JWT are set as headers for updating created employee
    And a payload to update created employee consists of valid key and value pairs
      | employee_id  | emp_firstname          | emp_lastname          | emp_middle_name         | emp_gender | emp_birthday | emp_status | emp_job_title    |
      | "employeeID" | syntaxUpdatedFirstName | syntaxUpdatedLastName | syntaxUpdatedMiddleName | F          | 2000-07-11   | Employee   | Cloud Consultant |
    When a PUT call is made to "/updateEmployee.php" to update created employee
    Then retrieved response for updating employee contains updated expected data
      | emp_firstname          | emp_middle_name         | emp_lastname          | emp_birthday | emp_gender | emp_job_title    | emp_status |
      | syntaxUpdatedFirstName | syntaxUpdatedMiddleName | syntaxUpdatedLastName | 2000-07-11   | Female     | Cloud Consultant | Employee   |
    And retrieved response for updating created employee contains same employee ID generated from created employee
    And retrieved response for updating created employee status code is 201

  Scenario: Retrieving updated employee and verifying updated details display in response
    Given the Content-Type and JWT are set as headers for retrieving updated employee
    When a GET call is made to "/getOneEmployee.php" to retrieve updated employee
    Then retrieved response for updated employee contains updated employee expected data
      | emp_firstname          | emp_middle_name         | emp_lastname          | emp_birthday | emp_gender | emp_job_title    | emp_status |
      | syntaxUpdatedFirstName | syntaxUpdatedMiddleName | syntaxUpdatedLastName | 2000-07-11   | Female     | Cloud Consultant | Employee   |
    And retrieved response for updated employee contains same employee ID generated from updated employee
    And retrieved response for updated employee status code is 200

  Scenario: Partially updating employee
    Given the Content-Type and JWT are set as headers for partially updating created employee
    And a payload to partially update created employee consists of valid key and value pairs
      | employee_id   | emp_firstname                   |
      | "employee ID" | syntaxPartiallyUpdatedFirstName |
    And retrieved response for partially updating created employee contains partially updated expected data
      | emp_firstname                   | emp_middle_name         | emp_lastname          | emp_birthday | emp_gender | emp_job_title    | emp_status |
      | syntaxPartiallyUpdatedFirstName | syntaxUpdatedMiddleName | syntaxUpdatedLastName | 2000-07-11   | Female     | Cloud Consultant | Employee   |

  Scenario: Retrieving partially updated employee and verifying partially updated details dispay in response
    Given the Content-Type and JWT are set as headers for retrieving partially updated employee
    When a GET call is made to "/getOneEmployee.php" to retrieve partially updated employee
    Then retrieved response for partially updated employee contains partially updated expected data
      | emp_firstname                   | emp_middle_name         | emp_lastname          | emp_birthday | emp_gender | emp_job_title    | emp_status |
      | syntaxPartiallyUpdatedFirstName | syntaxUpdatedMiddleName | syntaxUpdatedLastName | 2000-07-11   | Female     | Cloud Consultant | Employee   |
    And retrieved response for partially updated employee contatins same employee ID generated from original employee
    And retrieved response for partially updated employee status code is 200

  Scenario: Deleting employee
    Given the Content-Type and JWT are set as headers for deleting test employee
    When a DELETE call is made to "/deleteEmployee.php" to delete test employee
    Then deleted employee response body contains key "message" and value "Entry deleted"
    And deleted employee response body status code is 201
