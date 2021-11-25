package currectTasks.testsTask17;

import driverFactory.DriverType;
import driverFactory.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pagesAmazon.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public abstract class BaseClass {
    WebDriverFactory webDriverFactory;
    WebDriver driver;
    WebDriverWait wait;

    HomePage homePage;
    SearchResultPage searchResultPage;
    ProductPage productPage;
    CartPage cartPage;
    BuyPage buyPage;

    @BeforeSuite
    public void setUp() {
        System.out.println("Starting all setup activities.");

        webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(DriverType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
    }

    @BeforeMethod
    public void startBrowser(){
        driver.get("https://www.amazon.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowserAndMakeScreenshot(ITestResult result) {
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

    @AfterSuite
    public void closeUp() {
        driver.quit();

        System.out.println("All close up activities completed.");
    }
}
