Feature: calculation test

  Scenario: test addition
    * i execute endpoint is "addition" with num1 is "1000" and num2 is "2000"
    * the client receives status code of 200
    * the client receives result is "3000"

  Scenario: test addition with binary numbers
    * i execute endpoint is "addition" with num1 is "0b1000" and num2 is "0b1001"
    * the client receives status code of 200
    * the client receives result is "17"

  Scenario: test addition with hex and binary numbers
    * i execute endpoint is "addition" with num1 is "0x152" and num2 is "0b11"
    * the client receives status code of 200
    * the client receives result is "341"
