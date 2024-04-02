@reportportal @severity=normal
Feature: Navigation Feature
  As an user
  I successfully navigate to all possible pages
  from the navigation pane

  @navigation
  Scenario Outline: Report Portal - Validate Flow from Navigation Container
    Given I launch Report Portal
    Then I should be in 'Login' Page
    When I enter 'Valid_Credentials' for login
    Then I should be in 'Dashboard' Page
    And I validate '<section>' elements from Navigation Bar
    Examples:
      | section     |
      | TOP_PANE    |
      | SIDE_PANE   |
      | BOTTOM_PANE |
