package pagesRozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    private final By someButton = new By.ByCssSelector("h1.product__title");
    private final By addToCartButton = new By.ByCssSelector("ul.product-buttons button.button_color_green");
    private final By sellerTitle = new By.ByCssSelector("p.product-seller__title");
    private final By discountProduct = new By.ByCssSelector("p.product-prices__big_color_red");
    //private final By addToWishlistButton = new By.ByCssSelector("div.product-trade button.wish-button");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void addToCart(){
        driver.findElement(someButton).click();
        try {
            driver.findElement(someButton).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
            driver.findElement(addToCartButton).click();
        }
        catch(StaleElementReferenceException ex)
        {
            driver.findElement(someButton).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
            driver.findElement(addToCartButton).click();
        }
    }

    public String checkProductSeller(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        return driver.findElement(sellerTitle).getText();
    }

    public void checkProductDiscount(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(discountProduct).getText();
    }

    //Not realized, because need to be authorized
    //public void addToWishlist(){
    //    driverFactory.findElement(addToWishlistButton).click();
    //}
}
