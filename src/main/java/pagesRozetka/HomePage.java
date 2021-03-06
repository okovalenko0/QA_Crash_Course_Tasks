package pagesRozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final By searchInput = new By.ByXPath("//input[@name='search']");
    private final By searchButton = new By.ByCssSelector("button.search-form__submit");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public SearchResultPage search(String searchQuery){
        WebElement searchInputElement = driver.findElement(searchInput);
        searchInputElement.clear();
        searchInputElement.sendKeys(searchQuery);
        driver.findElement(searchButton).click();
        return new SearchResultPage(driver, wait);
    }
}
