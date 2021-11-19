package passedTasks.testsTask6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class testsTasks6 {

    @Test
    public void mainTest(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        String search = "//input[@title='Пошук']";

        WebElement searchElement = driver.findElement(By.xpath(search));
        searchElement.sendKeys("softserve learning and certification");
        searchElement.submit();

        driver.findElement(By.className("g")).click();

        String curl = driver.getCurrentUrl();
        try {
            Assert.assertEquals("https://career.softserveinc.com/uk-ua/learning-and-certification", curl);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        driver.quit();
    }
}
