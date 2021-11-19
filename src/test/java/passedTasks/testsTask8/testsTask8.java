package passedTasks.testsTask8;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public class testsTask8 {

    WebDriver driver;
    WebDriverWait wait;

    private final String searchField = "//input[@name='search']";
    private final String okButton = ".button.search-form__submit";
    private final String firstElement = "//div[@class='goods-tile__inner']//span[@class='goods-tile__title']";
    private final String buyButton = ".button.button_color_green>span";
    private final String checkoutButton = "//div[@class='cart-receipt ng-star-inserted']//a";
    private final String waitPage = "div.checkout-total input";

    @BeforeClass
    public void settings(){
        System.setProperty("webdriver.chrome.driverFactory", "C:\\Users\\alexe\\IdeaProjects\\QACourse\\chromedriver.exe");
    }

    @BeforeMethod
    public void startBrowser(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rozetka.com.ua");
    }

    @Test
    public void testCase(){
        //Wait for site loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchField)));

        WebElement searchElement = driver.findElement(By.xpath(searchField));
        searchElement.clear();
        searchElement.sendKeys("Монитор");

        WebElement okButtonElement = driver.findElement(By.cssSelector(okButton));
        okButtonElement.click();

        //Wait for first element loading
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstElement)));

        WebElement firstProductElement = driver.findElement(By.xpath(firstElement));
        String firstElTitle = firstProductElement.getText();

        if(firstElTitle.contains("Mонитор")) {
            firstProductElement.click();

            //Wait for BUY button loading
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(buyButton)));
            WebElement buyButtonElement = driver.findElement(By.cssSelector((buyButton)));

            buyButtonElement.click();

            //Wait for Checkout button loading
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkoutButton)));
            WebElement checkoutButtonElement = driver.findElement(By.xpath(checkoutButton));

            checkoutButtonElement.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(waitPage)));
        }
        else Assert.assertFalse(true);
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
    }

    @AfterClass
    public void closeBrowser(){ driver.quit(); }
}
