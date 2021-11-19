package pagesAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    //Search locators
    private final By searchInput = new By.ByCssSelector("div.nav-search-field input");
    private final By searchButton = new By.ByCssSelector("span input.nav-input");

    //Main Constructor
    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //Search product for some query
    private void clearSearchInput(){
        driver.findElement(searchInput).clear();
    }

    private void setSearchQuery(String searchQuery){
        driver.findElement(searchInput).sendKeys(searchQuery);
    }

    private void clickSearchButton(){
        driver.findElement(searchButton).click();
    }

    public SearchResultPage searchProduct(String query){
        this.clearSearchInput();
        this.setSearchQuery(query);
        this.clickSearchButton();
        return new SearchResultPage(driver, wait);
    }
}
