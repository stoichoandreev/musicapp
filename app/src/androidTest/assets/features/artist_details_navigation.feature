Feature: Artist search and navigation to artist details

  @details
  @e2e
  Scenario Outline: Display details for search results item
    When I start the application
    And I click search icon
    And I enter search query <query>
    And I close the keyboard
    And I select artist at <position>
    Then I expect to see the following artist details
      | artistTitle | artistListeners | artistPlayed |
      | <artist>    | <listeners>     | <played>     |
    Examples:
      | position | query | artist | listeners                  | played                |
      | 0        | Cher  | Cher   | Artist Listeners : 1076938 | Was Played : 14952381 |