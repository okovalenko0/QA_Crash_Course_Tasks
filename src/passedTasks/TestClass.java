import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;


public class TestClass {
    WebDriver driver;
    WebDriverWait wait;

    private final String searchFieldXPath = "//input[@name='search']";
    private final String okButtonXPath = ".button.search-form__submit";
    private final String firstEl = "//div[@class='goods-tile__inner']//span[@class='goods-tile__title']";

    @BeforeClass
    public void settings(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexe\\IdeaProjects\\QACourse\\chromedriver.exe");
    }

    @BeforeMethod
    public void startBrowser(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.get("https://rozetka.com.ua");
    }

    @Test
    public void testCase(){
        WebElement srchElement = driver.findElement(By.xpath(searchFieldXPath));
        srchElement.clear();
        srchElement.sendKeys("Монитор");

        WebElement okButton = driver.findElement(By.cssSelector(okButtonXPath));
        okButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstEl)));

        WebElement firstProduct = driver.findElement(By.xpath(firstEl));
        String firstElTitle = firstProduct.getText().trim();
        firstElTitle = firstElTitle.replaceAll("\"", "");

        Assert.assertEquals(firstElTitle.contains("Mонитор"), true);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}