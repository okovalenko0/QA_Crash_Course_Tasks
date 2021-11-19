package pagesAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage extends BasePage {

    //Product results locators
    private final By results = new By.ByXPath("//div[@data-index]//div[@class='a-section a-spacing-medium']");
    private final By titlePath = new By.ByXPath("//div[@data-index]//a//span[@class='a-size-base-plus a-color-base a-text-normal']");
    //Custom price range locators
    private final By priceLabel1 = new By.ByXPath("//span[@class='a-price']//span[@class='a-price-whole']");
    private final By priceLabel2 = new By.ByXPath("//span[@class='a-price']//span[@class='a-price-fraction']");
    private final By lowestInput = new By.ByXPath("//input[@id='low-price']");
    private final By highestInput = new By.ByXPath("//input[@id='high-price']");
    private final By customPriceButton = new By.ByXPath("//input[@class='a-button-input']");
    //Brand settings locators
    private final By seeMoreBrandButton = new By.ByXPath("//a[@aria-label='See more, Brand']");
    private final By allBrandElementsList = new By.ByXPath("//ul[@aria-labelledby='p_89-title']//li");
    private final By brandSelectButton = new By.ByCssSelector("span.a-list-item a");

    //Main Constructor
    public SearchResultPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //Custom Price Settings
    private void clearLowestInput(){
        driver.findElement(lowestInput).clear();
    }

    private void setLowestInput(Integer lowprice){
        String low = lowprice.toString();
        driver.findElement(lowestInput).sendKeys(low);
    }

    private void clearHighestInput(){
        driver.findElement(highestInput).clear();
    }

    private void setHighestInput(Integer highprice){
        String high = highprice.toString();
        driver.findElement(highestInput).sendKeys(high);
    }

    private void clickCustomPriceRange(){
        driver.findElement(customPriceButton).click();
    }

    public void setCustomPriceRange(Integer low, Integer high){
        this.clearLowestInput();
        this.clearHighestInput();
        this.setLowestInput(low);
        this.setHighestInput(high);
        this.clickCustomPriceRange();
    }

    public Double getPrice(){
        String price = "";
        price += driver.findElement(priceLabel1).getText();
        price += '.';
        price += driver.findElement(priceLabel2).getText();
        return Double.parseDouble(price);
    }

    //Brands Settings
    private void clickSeeMoreBrands(){
        if(driver.findElement(seeMoreBrandButton).isDisplayed()){
            driver.findElement(seeMoreBrandButton).click();
        }
    }

    private List<WebElement> getAllBrands(){
        this.clickSeeMoreBrands();
        return driver.findElements(allBrandElementsList);
    }

    private void clickBrandButton(WebElement brandLiElement){
        try {
            brandLiElement.findElement(brandSelectButton).click();
        }
        catch(StaleElementReferenceException ex) {
            brandLiElement.findElement(brandSelectButton).click();
        }
    }

    public String selectBrand(Integer brand){
        List<WebElement> brandsList = this.getAllBrands();
        WebElement brandElement = brandsList.get(brand);
        String brandTitle = brandElement.getText();
        this.clickBrandButton(brandElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(titlePath));
        return brandTitle;
    }

    //Product Settings
    private List<WebElement> getProducts(){
        return driver.findElements(results);
    }

    public ProductPage getProduct(Integer product){
        wait.until(ExpectedConditions.presenceOfElementLocated(titlePath));
        List<WebElement> products = this.getProducts();
        try {
            products.get(product).click();
        }
        catch(StaleElementReferenceException ex) {
            products.get(product).click();
        }
        return new ProductPage(driver, wait);
    }
}