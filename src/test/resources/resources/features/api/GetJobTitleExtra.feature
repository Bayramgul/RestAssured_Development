Feature: Gets the list of Job Titles

  @GetJobTitles
  Scenario: Get Job Titles
    When I provide the parameters for Job Titles
    And I make a call to Job Titles
    Then I validate the Job Titles status code is 200
    And I validate Job Titles response