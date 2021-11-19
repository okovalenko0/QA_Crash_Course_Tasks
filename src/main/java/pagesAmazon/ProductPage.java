package pagesAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    //Brand title locator
    private final By brandTextTitle = new By.ByXPath("//span[contains(text(), 'Brand')]/parent::td/parent::tr/td[2]/span");
    //ItStock locator
    private final By inStock = new By.ByXPath("//div[@id='availability']//span");
    //Product price locator
    private final By priceElement = new By.ByXPath("//span[@id='price_inside_buybox']");
    //Add to cart button locator
    private final By addToCartButton = new By.ByXPath("//input[@id='add-to-cart-button']");
    //Proceed to checkout button locator
    private final By proceedToCheckoutButtonProduct = new By.ByXPath("//div[@class='a-row a-spacing-top-small']/span/span/input");

    //Main Constructor
    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //Add to Cart
    private void clickAddToCartButton(){
        driver.findElement(addToCartButton).click();
    }

    public CartPage addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        this.clickAddToCartButton();
        return new CartPage(driver, wait);
    }

    //Other useful funcs
    public String getBrandTitle(){
        return driver.findElement(brandTextTitle).getText();
    }

    //Not used but realized
    public String getInStock(){
        return driver.findElement(inStock).getText();
    }

    public Double getPrice(){
        String price = driver.findElement(priceElement).getText();
        return Double.parseDouble(price);
    }
}
