#Author: Mohammad Shokriyan
@API_0001
Feature: This Generates a Token

  Scenario: Generate Toke for Selected User
    Given Generate Token Body
      | email                | password     |
      | syntaxAPI1@gmail.com | sampleapi123 |
    When post token request "/generateToken.php"
