Feature: User API tests


  Scenario Outline: Make sure there is a discount on the first product shown
    Given I am on the Rozetka main page
    When I search for product "<product>"
    Then The product price should have a discount

    Examples:
    | product  |
    | monitor  |

  Scenario Outline: Make sure that sorting by seller is working properly
    Given I am on the Rozetka main page
    When I search for product "<product>", and mark sorting by seller "<seller>"
    Then The product seller "<seller>" must match the one specified earlier

    Examples:
      | product    | seller  |
      | microwave  | Rozetka |

  Scenario Outline: Make sure removing an item from your cart is working correctly
    Given I am on the Rozetka main page
    When I search for product "<product>", and add product to cart
    Then The product must be removed from the cart

    Examples:
      | product    |
      | notebook   |