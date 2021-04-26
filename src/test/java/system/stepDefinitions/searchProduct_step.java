package system.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import system.pages.MainPage;
import system.pages.SearchProdutsPage;

import java.util.*;

import static system.helpers.Constants.ERROR_MESSAGE_SUB_TITLE;
import static system.helpers.Constants.ERROR_MESSAGE_TITLE;

public class searchProduct_step {

    private MainPage page = new MainPage();
    private SearchProdutsPage searchPage = new SearchProdutsPage();

    @After
    public void tearDown(){
        page.closePage();
    }

    @Given("I access the store's main page")
    public void accessMainPage() {
        page.accessPage();
    }

    @Given("I click on the search bar")
    public void clickSearchBar() {
        page.clickSearchBar();
    }

    @When("I type the product name {string}")
    public void searchByProduct(String product) {
        page.searchInput(product);
    }

    @When("I click on the search button")
    public void clickSearchButton() {
        page.clickButtonSearch();
    }

    @Then("I verify that the list of products related to the search of {string} is displayed")
    public void resultSearchlist(String product) {
        List<String> searchResult = searchPage.getSearchResultList();

        for(int i = 0; i < searchResult.size(); i++){
            String searchResultProduct = searchResult.get(i).toLowerCase();
            Assertions.assertTrue(searchResultProduct.contains(product.toLowerCase()));
        }
    }

    @Then("I verify that an error message informing there is no results for the searched product is displayed")
    public void verifyErrorMessage() {
        List<String> errorMessageList = searchPage.getErrorSearchMessage();
        Assertions.assertEquals(ERROR_MESSAGE_TITLE, errorMessageList.get(0).toLowerCase());
        Assertions.assertEquals(ERROR_MESSAGE_SUB_TITLE, errorMessageList.get(1).toLowerCase());
    }

    @Then("I hover through the search suggestions list and verify that the products suggestions are displayed")
    public void hoverSuggestionsListAndVerifyResults() throws InterruptedException {
        List<List> productsListsuggested = searchPage.getHoverSuggestionListResult();
        Integer listSize = productsListsuggested.size();
        Assertions.assertEquals(5,listSize);
        for(int i = 0; i < listSize-1; i++){
            Assertions.assertEquals(5,productsListsuggested.get(i).size());
        }
    }
//
//    @Then("I close the browser page")
//    public void closeBrowser(){
//        page.closePage();
//    }
}
