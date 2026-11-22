Feature: Login

  Scenario: Valid user logs in
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    Then I should see the products page

  Scenario: Locked user sees an error
    Given I am on the login page
    When I login with "locked_out_user" and "secret_sauce"
    Then I should see the error "locked out"

  Scenario: Add backpack to cart
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    And I add the product "Backpack" to the cart
    Then the cart badge should show "1"
    
  Scenario: Wrong password shows an error
    Given I am on the login page
    When I login with "standard_user" and "abc123"
    Then I should see the error "do not match"
    
  Scenario: valid login opens the Inventory Page
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    Then the page url should contain "inventory"
    
    Scenario: Inventory shows six products
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    Then I should see 6 products on the page
    
   Scenario: Open the cart after adding a product
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    And I add the product "Backpack" to the cart
    And I open the cart
    Then the page url should contain "cart"
    
      Scenario: Remove a product from the cart
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    And I add the product "Backpack" to the cart
    And I remove the product "Backpack" from the cart
    Then the cart badge should be empty
    
      Scenario Outline: Bad logins show an error
    Given I am on the login page
    When I login with "<username>" and "<password>"
    Then I should see the error "<message>"

    Examples:
      | username        | password     | message              |
      | locked_out_user | secret_sauce | locked out           |
      | standard_user   | abc123       | do not match         |
      |                 | secret_sauce | Username is required |
      |  standard_user  |              | Password is required |