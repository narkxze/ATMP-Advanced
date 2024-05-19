@reportportal @api @filters_api
Feature: Report Portal - API Filters
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
  Scenario Outline: Report Portal API -> Create New Filter through API
    Given I prepare and send POST request for '/v1/{projectName}/filter/' endpoint to Create Filter under 'superadmin_personal' project
      | name   | description   | type   | condition | filteringField     | value          | isAsc | sortingColumn |
      | <name> | <description> | Launch | has       | compositeAttribute | windows mobile | true  | startTime     |
    Then The Response code is 201
    And Response filtered by JSON Path is not null
      | field |
      | id    |
    Given I prepare and send DELETE request for '/v1/{projectName}/filter/{filterId}' endpoint to Delete Filter under 'superadmin_personal' project
      | filter |
      | <name> |
    But The Response code is 200
    And Response filtered by JSON Path contains values
      | field   | value                |
      | message | successfully deleted |

    Examples:
      | name              | description                |
      | Win Mobile Filter | Windows Mobile Description |

  @api-put
  Scenario Outline: Report Portal API -> Update Created Filter through API
    Given I prepare and send POST request for '/v1/{projectName}/filter/' endpoint to Create Filter under 'superadmin_personal' project
      | name     | description       | type   | condition | filteringField     | value | isAsc | sortingColumn |
      | <filter> | <old-description> | Launch | has       | compositeAttribute | ios   | true  | startTime     |
    Then The Response code is 201
    And Response filtered by JSON Path is not null
      | field |
      | id    |
    Given I prepare and send PUT request for '/v1/{projectName}/filter/{filterId}' endpoint to Update Filter under 'superadmin_personal' project
      | name     | old-description   | description   | type   | condition | filteringField     | value  | isAsc | sortingColumn |
      | <filter> | <old-description> | <description> | Launch | has       | compositeAttribute | debian | true  | startTime     |
    But The Response code is 200
    And Response filtered by JSON Path contains values
      | field   | value                |
      | message | successfully updated |
    Given I prepare and send DELETE request for '/v1/{projectName}/filter/{filterId}' endpoint to Delete Filter under 'superadmin_personal' project
      | filter   |
      | <filter> |
    But The Response code is 200
    And Response filtered by JSON Path contains values
      | field   | value                |
      | message | successfully deleted |
    Examples:
      | filter    | old-description        | description                |
      | iosFilter | ios filter description | new ios filter description |

  @api-delete
  Scenario Outline: Report Portal API -> Delete Created Filter through API
    Given I prepare and send POST request for '/v1/{projectName}/filter/' endpoint to Create Filter under 'superadmin_personal' project
      | name                | description   | type   | condition | filteringField     | value | isAsc | sortingColumn |
      | <tobeDeletedFilter> | <description> | Launch | has       | compositeAttribute | ios   | true  | startTime     |
    Then The Response code is 201
    And Response filtered by JSON Path is not null
      | field |
      | id    |
    Given I prepare and send DELETE request for '/v1/{projectName}/filter/{filterId}' endpoint to Delete Filter under 'superadmin_personal' project
      | filter              |
      | <tobeDeletedFilter> |
    But The Response code is 200
    And Response filtered by JSON Path contains values
      | field   | value                |
      | message | successfully deleted |
    Examples:
      | tobeDeletedFilter | description     |
      | iosFilter         | ios Description |