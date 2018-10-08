Feature: Artist Search

#  @Smoke
#  Scenario: Show Artist Search Results
#    Given I start the application
#    When  I click search icon
#    Then I expect to see the following artist search results

  @smoke
  Scenario Outline: Show Artist Search Results
#    Given the following remote artist results exist
#      | mbId                                      | name |
#      | bfcc6d75-a6a5-4bc6-8282-47aec8531818      | Cher |
#      | 2d499150-1c42-4ffb-a90c-1cc635519d33      | Cheryl Cole |
#      | 48fbfb0b-92ee-45eb-99c2-0bde4c05962e      | Cher Lloyd |
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