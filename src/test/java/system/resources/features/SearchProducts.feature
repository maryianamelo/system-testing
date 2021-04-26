#language: en

@SearchProducts
Feature: Search Products
    As a client of the Netshoes store
    I want to search by the products
    So that I can find what I want

  Background: SETUP
    Given I access the store's main page


  @automated
  Scenario: Search for an existing product
    Given I click on the search bar
    When  I type the product name "bolsa nike brasilia xs duff 9.0 - 25 litros"
    And   I click on the search button
    Then  I verify that the list of products related to the search of "bolsa nike brasilia xs duff 9.0 - 25 litros" is displayed


  @automated
  Scenario: Search for a product that do not exist
    Given I click on the search bar
    When  I type the product name "anythingatall"
    And   I click on the search button
    Then  I verify that an error message informing there is no results for the searched product is displayed


  @wip
  Scenario: Products suggestions list is updated as the user hover through search suggestions list
    Given I click on the search bar
    When  I type the product name "Tênis"
    Then  I hover through the search suggestions list and verify that the products suggestions are displayed