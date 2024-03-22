Feature: Report Portal Feature
  As an end User
  I want to open Report Portal
  in various browsers

  @health
  Scenario Outline: Report Portal Login - Negative Scenarios
    Given I launch Report Portal in '<BROWSER>'
    Then I validate the current page as 'LOGIN' Page
    When I enter credentials for '<FLOW>'
    But The Login Authorization error 'An error occurred while connecting to server: You do not have enough permissions. Bad credentials' is displayed

    Examples:
      | BROWSER | FLOW                        |
      | CHROME  | INCORRECT_USERNAME          |
      | CHROME  | INCORRECT_PASSWORD          |
      | CHROME  | INCORRECT_USERNAME_PASSWORD |
      | FIREFOX | INCORRECT_USERNAME          |
      | FIREFOX | INCORRECT_PASSWORD          |
      | FIREFOX | INCORRECT_USERNAME_PASSWORD |

#   TODO - OS Security Pop Up
#  @health
#  Scenario Outline: Report Portal Login - Positive Scenarios
#    Given I launch Report Portal in '<BROWSER>'
#    Then I validate the current page as 'LOGIN' Page
#    When I enter credentials for '<FLOW>'
#
#    Examples:
#      | BROWSER | FLOW              |
#      | CHROME  | VALID_CREDENTIALS |