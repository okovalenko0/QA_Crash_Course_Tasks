package pagesRozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    private By moreActionsButton = new By.ByXPath("//button[@id='cartProductActions0']");
    private By deleteActionButton = new By.ByXPath("//rz-trash-icon/button");
    private By addWishlistActioncButton = new By.ByXPath("//rz-add-to-wishlist-btn/button");
    private By doOrderButton = new By.ByCssSelector("a.cart-receipt__submit");
    private By continueBuys = new By.ByCssSelector("a.cart-footer__continue");
    private By emptyCart = new By.ByCssSelector("h4.cart-dummy__heading");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void performOrder(){
        driver.findElement(doOrderButton).click();
    }

    public void continueBuys(){
        driver.findElement(continueBuys).click();
    }

    public String deleteFirstElementInCart(){
        wait.until(ExpectedConditions.elementToBeClickable(doOrderButton));
        WebElement moreActionsButtonElement = driver.findElement(moreActionsButton);
        moreActionsButtonElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(doOrderButton));
        WebElement deleteActionButtonElement = driver.findElement(deleteActionButton);
        deleteActionButtonElement.click();
        return driver.findElement(emptyCart).getText();
    }



    //Not realized, because need to be authorized
    //public void addToWishListFirstElementInCart(){
    //    wait.until(ExpectedConditions.elementToBeClickable(moreActionsButton));
    //    driverFactory.findElement(moreActionsButton).click();
    //    wait.until(ExpectedConditions.elementToBeClickable(addWishlistActioncButton));
    //    driverFactory.findElement(addWishlistActioncButton).click();
    //}
}
