@API_0002
Feature: This API Generating Token

  Scenario: Generate Token for Selected User
    Given Generate Token Body
      | email          | password |
      | test@email.com | string   |
    When post token request "/generateToken.php"
    And generate request header with token
    And create request payload
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | testingAPI    | testingAPI   | testingAPI      | M          | 1990-06-10   | Worker     | Accountant    |
    And make POST request call to "/createEmployee.php"
    Then verify created employee response with expected data
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | testingAPI    | testingAPI   | testingAPI      | Male       | 1990-06-10   | Worker     | Accountant    |
