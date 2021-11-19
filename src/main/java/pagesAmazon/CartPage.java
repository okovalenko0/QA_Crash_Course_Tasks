package pagesAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    //Confirm add to cart button locator
    private final By confirmAddToCartButton = new By.ByXPath("//span[@class='a-button-inner']/a[contains(text(), 'Cart')]");
    //Proceed to checkout button locator
    private final By proceedToCheckoutButtonCart = new By.ByXPath("//input[@name='proceedToRetailCheckout']");
    //Delete product button locator
    private final By deleteFirstProductButton = new By.ByXPath("//input[@value='Delete']");
    //Cart label locator
    private final By emptyCartLabel = new By.ByCssSelector("h1.a-spacing-mini");

    //Main Constructor
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //Delete product from cart funcs
    private String getCartLabel(){
        return driver.findElement(emptyCartLabel).getText();
    }

    private void clickProductDeleteButton(){
        driver.findElement(deleteFirstProductButton).click();
    }

    public String deleteFirstElementInCart(){
        this.clickProductDeleteButton();
        return this.getCartLabel();
    }

    //Add to cart
    public void confirmAddToCart(){
        driver.findElement(confirmAddToCartButton).click();
    }

    //Proceed checkout
    public BuyPage confirmProductBuy(){
        driver.findElement(proceedToCheckoutButtonCart).click();
        return new BuyPage(driver, wait);
    }
}
