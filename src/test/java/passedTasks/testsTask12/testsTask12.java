package passedTasks.testsTask12;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import driverFactory.*;
import pagesRozetka.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public class testsTask12 {
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void startBrowser(){
        driver = webDriverFactory.createWebDriver(DriverType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rozetka.com.ua");
    }

    @Test
    public void isTheDiscountOnProduct(){
        String query = "selenium";
        HomePage homePage = new HomePage(driver, wait);
        ProductResultPage productResultPage = homePage.search(query);
        ProductPage productPage = new ProductPage(driver, wait);

        productResultPage.getProduct();
        productPage.checkProductDiscount();
    }

    @Test()
    public void isTheElementOfMarkedSeller(){
        String query = "selenium";
        String sellerType = "Rozetka";
        HomePage homePage = new HomePage(driver, wait);
        ProductResultPage productResultPage = homePage.search(query);
        ProductPage productPage = new ProductPage(driver, wait);

        productResultPage.markSeller(sellerType);
        productResultPage.getProduct();
        Assert.assertTrue(productPage.checkProductSeller().contains(sellerType));
    }

    @Test
    public void isTheDeleteFromCartWorking(){
        String query = "selenium";
        String cartMessage = "Корзина пуста";
        HomePage homePage = new HomePage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);
        CartPage cartPage = new CartPage(driver, wait);

        ProductResultPage productResultPage = homePage.search(query);
        productResultPage.getProduct();
        productPage.addToCart();

        String currentMessage = cartPage.deleteFirstElementInCart();
        Assert.assertTrue(currentMessage.contains(cartMessage));
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
