@reportportal @cred
Feature: Filters CRED Feature
  As an end user
  I should create/delete/edit the filters
  with parameters from external source
  and validate in Report Portal

  Background:
    Given I launch Report Portal
    And I enter 'Valid_Credentials' for login

  @addition
  Scenario: Report Portal - Add New Filters (Bulk Addition)
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    When I click Add Filters
    And I create and validate the creation of filters named in 'US1-Create' sheet of 'filters.xlsx' file

  @deletion
  Scenario: Report Portal - Delete Filters (Bulk Deletion)
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    And I delete and validate the absence of filters named in 'US2-Delete' sheet of 'filters.xlsx' file

  @edit
  Scenario: Report Portal - Edit Filters (Bulk Edit)
    Then I should be in 'Dashboard' Page
    When I navigate to 'Filters' page from the Navigation Bar
    And I edit and validate the change of filters named in 'US3-Edit' sheet of 'filters.xlsx' file



