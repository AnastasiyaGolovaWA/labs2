Feature: calculation test

  @sum  @Before
  Scenario: test addition
    * i execute endpoint is "addition" with num1 is "1000" and num2 is "2000"
    * the client receives status code of 200
    * the client receives result is "3000"

  @sum  @Before
  Scenario: test addition with binary numbers
    * i execute endpoint is "addition" with num1 is "0b1000" and num2 is "0b1001"
    * the client receives status code of 200
    * the client receives result is "17"

  @sum  @Before
  Scenario: test addition with hex and binary numbers
    * i execute endpoint is "addition" with num1 is "0x152" and num2 is "0b11"
    * the client receives status code of 200
    * the client receives result is "341"

  @division  @Before
  Scenario: test division with hex and binary numbers
    * i execute endpoint is "division" with num1 is "0x152" and num2 is "0b11"
    * the client receives status code of 200
    * the client receives result is "112"

  @multiplication  @Before
  Scenario: test multiplication with hex and binary numbers
    * i execute endpoint is "multiplication" with num1 is "0x152" and num2 is "0b11"
    * the client receives status code of 200
    * the client receives result is "1014"

  @subtraction  @Before
  Scenario: test subtraction with hex and binary numbers
    * i execute endpoint is "subtraction" with num1 is "0x152" and num2 is "0b11"
    * the client receives status code of 200
    * the client receives result is "335"

  @sum  @Before
  Scenario Outline: test addition with parameters
    * i execute endpoint is "addition" with num1 is "<num1>" and num2 is "<num2>"
    * the client receives status code of 200
    Examples:
      | num1    | num2   |
      | 1001010 | 131313 |
      | 202202  | 330303 |
      | 3320202 | 20202  |

  Scenario: test get parameters with class
    * test get parameters for class

      | num1    | num2   |
      | 1001010 | 131313 |
      | 202202  | 330303 |
      | 3320202 | 20202  |

