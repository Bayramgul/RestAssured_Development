Feature: Generate Token

  @token
  Scenario: Generating Token

    When I provide the header for Generating Token
    And I provide the body for Generating Token
    And I make Generating Token call
    Then I validate the Generating Token status code
    And I get the token from the response and save in a file