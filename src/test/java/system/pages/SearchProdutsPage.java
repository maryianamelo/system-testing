package system.pages;

import org.openqa.selenium.interactions.Actions;
import system.helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.ArrayList;
import java.util.List;

public class SearchProdutsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    Actions action;

    By filterLabels = By.className("filters__filter__title__textLabel");
    By marcaItems = By.cssSelector("input[data-keynormalizedname='marca'] + label");
    By searchResultsLocator = By.cssSelector("a[class=\"item-card__description__product-name\"]>span");
    By errorMessageOneLocator = By.cssSelector("div[class=\"divisor-bottom no-results\"]>.wrapper>h2");
    By errorMessageTwoLocator = By.cssSelector("div[class=\"divisor-bottom no-results\"]>.wrapper>h3");
    By searchSuggestionsListLocator = By.cssSelector("ul[data-suggestion-list]>li:nth-child(3)");
    By suggestionProductsListLocator = By.cssSelector("div[class=\"top-results small\"]>a>figure>figcaption>span[class=\"name\"]");



    public SearchProdutsPage(){
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getDriverWait();
        action = new Actions(driver);
    }

    public String getSectionName(){
        By section = By.cssSelector(".search-query");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(section));
        WebElement sectionTitle = this.driver.findElement(section);
        return sectionTitle.getText(); 
    }

    public List<String> getFiltersLabel() {
        List<WebElement> filterLabelNameElement = this.driver.findElements(filterLabels);
        List<String> filterName = new ArrayList<String>();
        for (int i = 0; i < filterLabelNameElement.size(); i++) {
            if (filterLabelNameElement.get(i).getText().isEmpty() == false){
                filterName.add(filterLabelNameElement.get(i).getText());
            }
        }
        return filterName;
    }

    public void clickOnFilter(String field, String value) throws InterruptedException {
        By filterLabel = By.cssSelector("li[class='filters__filter__list__item']>a>label[for='"+field+"-"+value+"']");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(filterLabel));
        WebElement filterLabelElement = this.driver.findElement(filterLabel);
        action.moveToElement(filterLabelElement).perform();
        By filterToSelect = By.xpath("//input[@data-keynormalizedname='"+field+"'][@value='"+value+"']//..");
        WebElement filterToSelectElement = this.driver.findElement(filterToSelect);
        this.wait.until(ExpectedConditions.elementToBeClickable(filterToSelect));
        try {
            filterToSelectElement.click();
            Thread.sleep(5);
        }catch (StaleElementReferenceException ex){

        }
    }

    public String getFilterApllied(String field){
        By filterApplied = By.cssSelector(".filters__title__filters-selecteds__selected > li[data-keynormalizedname='"+field+"']");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(filterApplied));
        WebElement filterAppliedElement = this.driver.findElement(filterApplied);
        action.moveToElement(filterAppliedElement).perform();
        return filterAppliedElement.getText();

    }

    public List<String> getMarcaItems() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> getMarcaItemnsElement = this.driver.findElements(marcaItems);
        List<String> getMarcaName = new ArrayList<String>();
        for(int i =0; i < getMarcaItemnsElement.size(); i++){
            try{
                getMarcaName.add(getMarcaItemnsElement.get(i).getText());
            }
            catch(StaleElementReferenceException ex){

            }
        }
        return getMarcaName;
    }
    
    public List<String> getSearchResultList(){
        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        List<String> productsNameList = new ArrayList<String>();
        for(int i = 0; i < searchResults.size(); i++){
            productsNameList.add(searchResults.get(i).getText());
        }
        return productsNameList;
    }

    public List<String> getErrorSearchMessage(){
        WebElement errorMessageTitle = driver.findElement(errorMessageOneLocator);
        WebElement errorMessageSubTitle = driver.findElement(errorMessageTwoLocator);
        List<String> errorMessageList = new ArrayList<String>();
        errorMessageList.add(errorMessageTitle.getText());
        errorMessageList.add(errorMessageSubTitle.getText());
        return errorMessageList;
    }


    public List<List> getHoverSuggestionListResult() throws InterruptedException {
        List<List> productsListsuggested = new ArrayList<List>();

        for(int i = 0; i < 5; i++){
            By searchSuggestionsListLocator = By.cssSelector("ul[data-suggestion-list]>li:nth-child("+(i+1)+")");
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionsListLocator));
            WebElement suggestions = driver.findElement(searchSuggestionsListLocator);
            action.moveToElement(suggestions).perform();
            Thread.sleep(2000);
            List<WebElement> products = driver.findElements(suggestionProductsListLocator);
            List<String> productsSuggested = new ArrayList<String>();
            for(int t = 0; t < products.size(); t++) {
                productsSuggested.add(products.get(t).getText());
            }
            productsListsuggested.add(productsSuggested);
        }

        return productsListsuggested;

    }

}
