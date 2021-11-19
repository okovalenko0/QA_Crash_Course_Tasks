package passedTasks.testsTask14;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import driverFactory.*;
import pagesAmazon.*;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public class testsTask14 {
    WebDriverFactory webDriverFactory;
    WebDriver driver;
    WebDriverWait wait;

    HomePage homePage;
    SearchResultPage searchResultPage;
    ProductPage productPage;
    CartPage cartPage;
    BuyPage buyPage;

    @BeforeMethod
    public void startBrowser(){
        webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(DriverType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);

        driver.get("https://www.amazon.com/");
    }

    @Test
    public void isThePriceInTheRightRange(){
        String query = "notebook computer";
        Integer low = 10, high = 1000;


        searchResultPage = homePage.searchProduct(query);
        searchResultPage.setCustomPriceRange(low, high);
        Double price = searchResultPage.getPrice();
        Assert.assertTrue(price > low && price < high);
    }

    @Test
    public void isTheProductOfMarkedBrand(){
        String query = "phone", brandFromSearch;
        Integer brandId = 1, productId = 0;

        searchResultPage = homePage.searchProduct(query);
        brandFromSearch = searchResultPage.selectBrand(brandId);
        productPage = searchResultPage.getProduct(productId);

        Assert.assertEquals(brandFromSearch, productPage.getBrandTitle());
    }

    @Test
    public void deleteElementInCart(){
        String query = "pants", cartMsg = "empty";
        Integer productId = 0;

        searchResultPage = homePage.searchProduct(query);
        productPage = searchResultPage.getProduct(productId);
        cartPage = productPage.addToCart();
        cartPage.confirmAddToCart();

        Assert.assertTrue(cartPage.deleteFirstElementInCart().contains(cartMsg));
    }

    @Test
    public void buyProductInCart(){
        String query = "car seat";
        Integer productId = 0;

        searchResultPage = homePage.searchProduct(query);
        productPage = searchResultPage.getProduct(productId);
        cartPage = productPage.addToCart();
        cartPage.confirmAddToCart();
        buyPage = cartPage.confirmProductBuy();

        Assert.assertTrue(buyPage.isUserWantToBuyProduct());
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
