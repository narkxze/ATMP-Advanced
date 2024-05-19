@reportportal @filters_ui
Feature: Report Portal - UI Filters
  As an end user
  I should create/delete/edit the filters
  with parameters from external source
  and validate in Report Portal

  Background:
    Given I launch Report Portal
    When I enter 'Valid_Credentials' for login

  @addition
  Scenario: Report Portal - Add New Filters (Bulk Addition)
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    When I click Add Filters
    And I create and validate the creation of filters named in 'US1-Create' sheet of 'filters.xlsx' file

  @edit
  Scenario: Report Portal - Edit Filters (Bulk Edit)
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    And I edit and validate the change of filters named in 'US3-Edit' sheet of 'filters.xlsx' file

  @deletion
  Scenario: Report Portal - Delete Filters (Bulk Deletion)
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    And I delete and validate the absence of filters named in 'US2-Delete' sheet of 'filters.xlsx' file

  @scroll
  Scenario: Report Portal - Pagination
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    When I click Add Filters
    Then I should be in 'Launches' Page
    And I scroll into the view of 'Pagination' element
    Then The element 'Pagination' contains text '50'

  @display_toggle
  Scenario: Report Portal - Display on Launches
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    And I toggle the display of 'DEMO_FILTER' to 'OFF'
    When I navigate to 'Launches' page from the Navigation Bar
    Then I should be in 'Launches' Page
    And The element 'Filter List' does not contains text 'DEMO_FILTER'
    When I navigate to 'Filters' page from the Navigation Bar
    And I toggle the display of 'DEMO_FILTER' to 'ON'
    And I navigate to 'Launches' page from the Navigation Bar
    Then I should be in 'Launches' Page
    And The element 'Filter List' contains text 'DEMO_FILTER'