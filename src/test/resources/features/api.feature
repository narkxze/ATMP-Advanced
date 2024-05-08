@reportportal @api
Feature: Report Portal API Feature
  As an admin
  I should be able to perform operations on Filter Module
  by api calls

  @api-get
  Scenario: Report Portal API -> Get all Available Filters through API
    Given I send GET request for '/v1/{projectName}/filter' endpoint for 'superadmin_personal' project
    Then The Response code is 200
    And Response filtered by JSON Path contains values
      | field           | value       |
      | content[0].name | DEMO_FILTER |

  @api-post
  Scenario: Report Portal API -> Create New Filter through API
    Given I prepare and send POST request for '/v1/{projectName}/filter/' endpoint to Create Filter under 'superadmin_personal' project
      | name              | description                | type   | condition | filteringField     | value          | isAsc | sortingColumn |
      | Win Mobile Filter | Windows Mobile Description | Launch | has       | compositeAttribute | windows mobile | true  | startTime     |
    Then The Response code is 201
    And Response filtered by JSON Path is not null
      | field |
      | id    |

  @api-put
  Scenario Outline: Report Portal API -> Update Created Filter through API
    Given I prepare and send POST request for '/v1/{projectName}/filter/' endpoint to Create Filter under 'superadmin_personal' project
      | name                | description     | type   | condition | filteringField     | value | isAsc | sortingColumn |
      | <tobeUpdatedFilter> | ios Description | Launch | has       | compositeAttribute | ios   | true  | startTime     |
    Then The Response code is 201
    And Response filtered by JSON Path is not null
      | field |
      | id    |
    Given I prepare and send PUT request for '/v1/{projectName}/filter/{filterId}' endpoint to Update Filter under 'superadmin_personal' project
      | oldFilter           | name        | description               | type   | condition | filteringField     | value  | isAsc | sortingColumn |
      | <tobeUpdatedFilter> | <newFilter> | debian filter description | Launch | has       | compositeAttribute | debian | true  | startTime     |
    But The Response code is 200
    And Response filtered by JSON Path contains values
      | field   | value                |
      | message | successfully updated |
    Examples:
      | tobeUpdatedFilter | newFilter     |
      | iosFilter         | debian filter |

  @api-delete
  Scenario Outline: Report Portal API -> Delete Created Filter through API
    Given I prepare and send POST request for '/v1/{projectName}/filter/' endpoint to Create Filter under 'superadmin_personal' project
      | name                | description     | type   | condition | filteringField     | value | isAsc | sortingColumn |
      | <tobeDeletedFilter> | ios Description | Launch | has       | compositeAttribute | ios   | true  | startTime     |
    Then The Response code is 201
    And Response filtered by JSON Path is not null
      | field |
      | id    |
    Given I prepare and send DELETE request for '/v1/{projectName}/filter/{filterId}' endpoint to Delete Filter under 'superadmin_personal' project
      | oldFilter           |
      | <tobeDeletedFilter> |
    But The Response code is 200
    And Response filtered by JSON Path contains values
      | field   | value                |
      | message | successfully deleted |
    Examples:
      | tobeDeletedFilter |
      | iosFilter         |