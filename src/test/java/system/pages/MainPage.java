package system.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import system.helpers.DriverManager;
import static system.pages.URL.MAIN_PAGE;

public class MainPage {

    /*
     Representation
     */

    private WebDriver driver;
    private WebDriverWait wait;
    DriverManager manager = new DriverManager();

    private By siteLogo = By.cssSelector(".logo > h1");
    private By closeCookie = By.cssSelector(".cookie-notification-button");
    private By searchInput = By.id("search-input");
    private By buttonSearch = By.cssSelector("button[qa-automation=\"home-search-button\"]");

    /*
     Services
     */

    public MainPage() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getDriverWait();
    }

    public void accessPage(){
        driver.get(MAIN_PAGE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteLogo));
        driver.manage().window().maximize();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(closeCookie));
        WebElement closeCookieElement = this.driver.findElement(closeCookie);
        closeCookieElement.click();
    }

    public void clickInSectionTab(String sectionName){
        By section = By.xpath("//li[@class='swiper-slide swiper-slide-text swiper-slide-next']//a//div[contains(text(), '"+sectionName+"')]");
        WebElement sectionTab = this.driver.findElement(section);
        this.wait.until(ExpectedConditions.elementToBeClickable(sectionTab));
        sectionTab.click();
    }

    public void closePage(){
        manager.endSession();
    }

    public void clickSearchBar(){
        WebElement searchBar = this.driver.findElement(searchInput);
        searchBar.click();
    }

    public void searchInput(String product){
        WebElement searchBar = this.driver.findElement(searchInput);
        searchBar.sendKeys(product);
    }

    public void clickButtonSearch(){
        WebElement searchButton = this.driver.findElement(buttonSearch);
        searchButton.click();
    }
}
