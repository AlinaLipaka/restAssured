Feature: Verify different GET operations

  Scenario: Verify author of the post
    Given I perform GET operation for the "/post"
    And I perform GET the post number "2"
    Then I should see the author name as "Vasja"


  Scenario: Verify collection of authors in the post
    Given I perform GET operation for the "/post"
    Then I should see the author names

  Scenario: Verify Parameter of Get
    Given I perform GET operation for the "/post"
    Then I should verify GET parameters

  Scenario: Verify Query Parameter of Get
    Given I perform GET operation for the "/post"
    Then I should verify query parameter
