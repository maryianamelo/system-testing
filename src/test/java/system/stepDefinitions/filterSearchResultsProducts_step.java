package system.stepDefinitions;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import system.pages.MainPage;
import system.pages.SearchProdutsPage;


public class filterSearchResultsProducts_step{

    SearchProdutsPage searchProductsPage = new SearchProdutsPage();
    MainPage mainPage = new MainPage();

    @Given("I click in {string} products")
    public void clickInSection(String section){
        mainPage.clickInSectionTab(section);
    }

    @When("I am in {string} section")
    public void seeSectionDisplayed(String section){
        Assertions.assertEquals(section.toLowerCase(), searchProductsPage.getSectionName().toLowerCase());
    }

    @Then("I should be able to see this filters below")
    public void i_should_be_able_to_see_this_filters_below(DataTable dataTable) {
        Assertions.assertEquals(dataTable.asList(), searchProductsPage.getFiltersLabel());
    }

    @When("I select this filter below")
    public void i_select_this_filter_below(DataTable dataTable) throws InterruptedException {
        for (int i = 0; i < dataTable.height(); i++){
            searchProductsPage.clickOnFilter(dataTable.column(0).get(i).toString(), dataTable.column(1).get(i).toString());
        }
    }

    @Then("This filter below must be displayed as applied")
    public void this_filter_below_must_be_displayed_as_applied(DataTable dataTable) {
        String field;
        for (int i = 0; i < dataTable.height(); i++){
            field = dataTable.column(0).get(i).toString();
            Assertions.assertEquals(dataTable.column(1).get(i).toString(), searchProductsPage.getFilterApllied(field));
        }
    }
    @Then("The field Marca must display this attributes below")
    public void the_field_marca_must_display_this_attributes_below(DataTable dataTable) throws InterruptedException {
        Assertions.assertEquals(dataTable.asList(), searchProductsPage.getMarcaItems());
    }




}