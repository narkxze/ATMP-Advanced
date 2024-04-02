@reportportal @severity=critical
Feature: Login Feature
  As an user
  I try to login Report Portal
  and check the positive and negative flows

  @health
  Scenario Outline: Report Portal Login - Negative Scenarios
    Given I launch Report Portal
    When I enter '<userflow>' for login
    But I should be in 'Login' Page
    And I see the error 'An error occurred while connecting to server: You do not have enough permissions. Bad credentials'
    Examples:
      | userflow                    |
      | INCORRECT_USERNAME          |
      | INCORRECT_PASSWORD          |
      | INCORRECT_USERNAME_PASSWORD |

  @health
  Scenario: Report Portal Login - Positive Scenario
    Given I launch Report Portal
    When I enter 'Valid_Credentials' for login
    Then I should be in 'Dashboard' Page