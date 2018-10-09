Feature: Artist Search

  @smoke
  Scenario Outline: Show Artist Search Results
    When I start the application
    And  I click search icon
    And  I enter search query <query>
    And  I close the keyboard
    Then I expect to see the following artist search results
      | position | name |
      | 0        | Cher |
      | 1        | Cheryl Cole |
      | 2        | Cher Lloyd |

    Examples:
      | query |
      |  Cher |
#      | position  | query | name |
#      | 0         |  Cher | Cher |
#      | 1         |  Cher | Cheryl Cole |
#      | 2         |  Cher | Cher Lloyd |