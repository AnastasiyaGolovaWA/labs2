Feature: the version can be retrieved
  Scenario: client makes call to GET /version
    When the client calls /version
    Then the client receives result is 20013
    #And the client receives server version 1.0