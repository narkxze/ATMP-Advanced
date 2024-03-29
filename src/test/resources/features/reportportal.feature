Feature: Report Portal Feature
  As an User
  I want to open and test Report Portal
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


  @health
  Scenario Outline: Report Portal Login - Positive Scenarios
    Given I launch Report Portal in '<BROWSER>'
    Then I validate the current page as 'LOGIN' Page
    When I enter credentials for 'VALID_CREDENTIALS'
    Then I validate the current page as 'DASHBOARD' Page
    Examples:
      | BROWSER |
      | CHROME  |
      | FIREFOX |

  @navigation
  Scenario Outline: Report Portal - Validate Navigation Container
    Given I launch Report Portal in '<BROWSER>'
    Then I validate the current page as 'LOGIN' Page
    When I enter credentials for 'VALID_CREDENTIALS'
    Then I validate the current page as 'DASHBOARD' Page
    And I validate '<NAVI_PANES>' elements from Navigation Bar
    Examples:
      | BROWSER | NAVI_PANES  |
      | CHROME  | TOP_PANE    |
      | CHROME  | SIDE_PANE   |
      | CHROME  | BOTTOM_PANE |
      | FIREFOX | TOP_PANE    |
      | FIREFOX | SIDE_PANE   |
      | FIREFOX | BOTTOM_PANE |