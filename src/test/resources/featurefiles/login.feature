Feature: FaceBook Login page

  @TestRailId_1
  Scenario: Verify facebook login page
    Given Launch Application
    When User enters Username
    And User enters password
    And Click on Login button
    Then Home page should display

  @TestRailId_2
  Scenario: Verify facebook login page
    Given Launch Application
    When User enters Username
    And User enters password
    And Click on Login button
    Then Home page should display
