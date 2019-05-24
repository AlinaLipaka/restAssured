Feature:
  Verify different GET operations using REST-assured

  @execute
  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/post"
    Then I should see the author names "Tyra"

  @notworking
  Scenario: Verify data from GET with collection
    Given I perform GET for users and verify result

  @execute
  Scenario: Verify parameter of Get
    Given I perform GET operation for "/post"
    Then I should verify GET parameter

  @execute
  Scenario: Verify query parameter of GET
    Given I perform GET operation for "/post"
    Then I should verify GET query parameter

  Scenario: Try method Post
    Given I perform POST using "test@testing.lv" "testname2" "testsurname" "male"
    Then I perform GET using "testname2" and verify if surname is "testsurname"

  Scenario: Try method Put
    Given I perform PUT on user with email "vasja@test.lv" using:
     # | email      | test5@testing4.lv |
      | first_name | testname5         |
      | last_name  | testsurname5      |
      | gender     | male              |
    Then I perform GET using "testname5" and verify if surname is "testsurname5"

  Scenario: Try method Post with array
    Given I perform POST using:
      | email      | test@testing4.lv |
      | first_name | testname4        |
      | last_name  | testsurname4     |
      | gender     | female           |
    Then I perform GET using "testname4" and verify if surname is "testsurname4"

  @unfinished
  Scenario: Try method Delete
    Given I perform DELETE on user with id "123"
    Then I verify that user with id "123" does not exist

