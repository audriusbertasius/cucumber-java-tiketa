Feature: Tiketa

  Scenario Outline: Buy a ticket
    Given I am on the Tiketa "search" page
    When I search for <partCaption> in Caption field
    When I select <eventPlace> as event place
    When I choose start <dateFrom> and end <dateTo> dates
    When I click Search button
    When I press Buy button on <fullCaption> event
    Then I can verify the <options> options that are available

    Examples:
      | partCaption | eventPlace                   | dateFrom | dateTo       | fullCaption                 | options |
      | "Corteo"    | "Avia Solutions Group arena" | ""       | "2022-12-31" | "CIRQUE DU SOLEIL - CORTEO" | 5       |