package pagesRozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

//WORKING ONLY WITH AUTHORIZED USER
public class WishlistPage extends BasePage {

    By wishlistElementsList = new By.ByCssSelector("div.goods-tile__inner");
    By wishlistElementsTitle = new By.ByCssSelector("div.goods-tile__inner span.goods-tile__title");

    public WishlistPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public List<String> getListOfProductTitlesInWishlist() {
        List<WebElement> wishlistElements = driver.findElements(wishlistElementsList);
        List<String> titles = new ArrayList<>();

        for (WebElement wishlistElement : wishlistElements) {
            WebElement title = wishlistElement.findElement(wishlistElementsTitle);
            titles.add(title.getText());
        }
        return titles;
    }
}
