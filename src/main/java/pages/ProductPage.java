package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    private By someButton = new By.ByCssSelector("a.tabs__link");
    private By addToCartButton = new By.ByCssSelector("ul.product-buttons button.button_color_green");
    private By sellerTitle = new By.ByCssSelector("p.product-seller__title");
    private By discountProduct = new By.ByCssSelector("p.product-prices__big_color_red");
    private By addToWishlistButton = new By.ByCssSelector("div.product-trade button.wish-button");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void addToCart(){
        driver.findElement(someButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
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
    //    driver.findElement(addToWishlistButton).click();
    //}
}
