package pagesRozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    private final By results = new By.ByCssSelector("section.content div.goods-tile");
    private final By titlePath = new By.ByCssSelector("span.goods-tile__title");
    private final By sellerRozetkaCheckbox = new By.ByXPath("//div[@data-filter-name='seller']//input[@id='Rozetka']/parent::a");
    private final By sellerRUOtherCheckbox = new By.ByXPath("//input[@id='Другие продавцы']");
    private final By sellerUAOtherCheckbox = new By.ByXPath("//input[@id='Інші продавці']");

    public SearchResultPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public List<String> getResultTitles(){
        List<WebElement> resultWebElements = driver.findElements(results);
        List<String> titles = new ArrayList<>();
        for(WebElement resultElement : resultWebElements){
            WebElement title = resultElement.findElement(titlePath);
            titles.add(title.getText());
        }
        return titles;
    }

    public void getProduct(){
        wait.until(ExpectedConditions.presenceOfElementLocated(results));
        try {
            driver.findElement(results).click();
        }
        catch(StaleElementReferenceException ex)
        {
            driver.findElement(results).click();
        }
    }

    public void markSeller(String sellerType){
        switch (sellerType) {
            case ("Rozetka"):
                wait.until(ExpectedConditions.elementToBeClickable(sellerRozetkaCheckbox));
                driver.findElement(sellerRozetkaCheckbox).click();
                break;
            case ("Other RU"):
                wait.until(ExpectedConditions.elementToBeClickable(sellerRUOtherCheckbox));
                driver.findElement(sellerRUOtherCheckbox).click();
                break;
            case ("Other UA"):
                wait.until(ExpectedConditions.elementToBeClickable(sellerUAOtherCheckbox));
                driver.findElement(sellerUAOtherCheckbox).click();
                break;
        }
    }
}
