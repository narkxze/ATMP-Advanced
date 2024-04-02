@reportportal
Feature: Report Portal Feature
  As an User
  I want to open and test Report Portal
  in various browsers

  @health
  Scenario Outline: Report Portal Login - Negative Scenarios
    Given I launch Report Portal
    Then I validate the current page as 'LOGIN' Page
    When I enter credentials for '<FLOW>'
    But The Login Authorization error 'An error occurred while connecting to server: You do not have enough permissions. Bad credentials' is displayed
    Examples:
      | FLOW                        |
      | INCORRECT_USERNAME          |
      | INCORRECT_PASSWORD          |
      | INCORRECT_USERNAME_PASSWORD |

  @health
  Scenario: Report Portal Login - Positive Scenarios
    Given I launch Report Portal
    Then I validate the current page as 'LOGIN' Page
    When I enter credentials for 'VALID_CREDENTIALS'
    Then I validate the current page as 'DASHBOARD' Page

  @navigation @check
  Scenario Outline: Report Portal - Validate Navigation Container
    Given I launch Report Portal
    Then I validate the current page as 'LOGIN' Page
    When I enter credentials for 'VALID_CREDENTIALS'
    Then I validate the current page as 'DASHBOARD' Page
    And I validate '<NAVI_PANES>' elements from Navigation Bar
    Examples:
      | NAVI_PANES  |
      | TOP_PANE    |
      | SIDE_PANE   |
      | BOTTOM_PANE |