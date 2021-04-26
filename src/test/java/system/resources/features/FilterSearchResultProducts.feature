Feature: Filter Search Result Products
    As a client of the Netshoes store
    I want to be able to filter the products searched 
    So that I can easily find the product that I want

    Background: SETUP
    Given I access the store's main page

    @automation
    Scenario: Verify filter displayed on Eletronic section
    Given I click in "Eletrônicos" products
    Then  I am in "Eletrônicos" section
    Then I should be able to see this filters below
        |Gênero         |
        |Tipo de Produto|
        |Tamanho        |
        |Marca          |
        |Preço          |
        |Cor            |
        |Avaliação      |
        |Promoções      |
        |Departamento   |
        |Desconto       |

    @automation
    Scenario: Verify if the filter is applied 
    Given I click in "Eletrônicos" products
    Then I am in "Eletrônicos" section
    When I select this filter below
        |genero          |feminino    |
    Then This filter below must be displayed as applied 
        |genero      |Feminino    |
        |departamento|Eletrônicos |

    @automation
    Scenario: Verify if the field Marca is in accordance with the field Tipo de Produto 
    Given I click in "Eletrônicos" products
    Then  I am in "Eletrônicos" section
    When I select this filter below
        |tipo-de-produto |notebook    |
    Then The field Marca must display this attributes below 
        |AKG    |
        |Acer   | 
        |Apple  |
        |Asus   |
        |Compaq |
        |Fuseco |
        |HP     |
        |LENOVO |
        |Samsung|