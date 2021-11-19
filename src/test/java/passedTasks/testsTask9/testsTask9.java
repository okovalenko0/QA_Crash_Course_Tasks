package passedTasks.testsTask9;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import driverFactory.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class testsTask9 {
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    WebDriver driver;

    private final String searchField = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input";
    private final String firstElement = "//a/h3";
    private final String search = "SoftServe";

    @BeforeMethod
    public void startBrowser(){
        driver = webDriverFactory.createWebDriver(DriverType.EDGE);
        driver.get("https://google.com");
    }

    @Test
    public void testCase(){
        WebElement searchElement = driver.findElement(By.xpath(searchField));
        searchElement.clear();
        searchElement.sendKeys(search);
        searchElement.sendKeys(Keys.ENTER);


        List<WebElement> searchResults = driver.findElements(By.xpath(firstElement));

        boolean isUrlFinded = false;
        for(WebElement searchResult : searchResults)
        {
            if(searchResult.getText().contains(search)){
                isUrlFinded = true;
                break;
            }
        }
        Assert.assertTrue(isUrlFinded);
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
