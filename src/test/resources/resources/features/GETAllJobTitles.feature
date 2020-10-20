Feature: Validating Syntax /jobTitle.php HRMS API
Background:
Given user generates token

@GETAllJobTitles
Scenario: Retrieving all Job Titles using /jobTitle.php API
Given user calls jobTitle API to retrieve all job titles
When User retrieves response for jobTitle API to retrieve all Job Titles 
Then status code is 200 for jobTitle API 
Then user validates Job Titles 
      | Cloud Architect                   |
      | Cloud Consultant                  |
      | Cloud Product and Project Manager |
      | IT Analyst                        |
      | Network Administrator             |
      | IT Support Manager                |
      | Data Quality Manager              |
      | Database Administrator            |
      | Application Developer             |
      | Developer                         |
      | Accountant                        |
      | Chief Financial Officer           |
      | Controller                        |
      | Production Manager                |
      | Jr Production Manager             |
      | Sales&Marketing Manager           |
      | Jr Sales Manager                  |
      | Graphic Designer                  |
      | CEO                               |
      | Automation Engineer               |
      | API Tester                        |
      | Instructor                        |
      | CTO                               |
      | QA Tester                         |
      | Sr Production Manager             |
      | Sales&Marketing Manager           |