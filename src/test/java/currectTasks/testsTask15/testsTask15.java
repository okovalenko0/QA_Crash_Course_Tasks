package currectTasks.testsTask15;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pagesRozetka.*;
import driverFactory.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public class testsTask15 {
    WebDriver driver;
    WebDriverWait wait;
    WebDriverFactory webDriverFactory;

    @DataProvider(name = "searchDataQuery")
    public Object[][] createData() {
        return new Object[][]{
                {"huggies"},
                {"selenium"},
                {"soap"}
        };
    }

    @BeforeMethod
    public void startBrowser(){
        webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(DriverType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://rozetka.com.ua");
    }

    @Test(dataProvider = "searchDataQuery")
    public void mainTest(String query){
        HomePage homePage = new HomePage(driver, wait);
        SearchResultPage searchResultPage = homePage.search(query);

        List<String> titles = searchResultPage.getResultTitles();
        Assert.assertTrue(titles.get(0).toLowerCase().contains(query));
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
        driver.quit();
    }
}
