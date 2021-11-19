package pagesAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyPage extends BasePage{

    //Continue button locator
    private final By continueButton = new By.ByXPath("//input[@id='continue']");

    //Main Constructor
    public BuyPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //Verify that user clicked to previous button
    public boolean isUserWantToBuyProduct(){
        return driver.findElement(continueButton).isDisplayed();
    }
}
