package passedTasks.testsTask13.steps;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pagesRozetka.*;
import driverFactory.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public class UserTestSteps {

    //Create DriverFactory for multi-browser testing
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    // You can use EDGE or CHROME in DriverFactory
    WebDriver driver = webDriverFactory.createWebDriver(DriverType.CHROME);
    //Explicity waiter added manual for better performance
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @Given("I am on the Rozetka main page")
    public void iAmOnTheRozetkaMainPage() {
        driver.get("https://rozetka.com.ua");
    }

    //FIRST TEST CASE
    @When("I search for product {string}")
    public void iSearchForProduct(String product) {
        HomePage homePage = new HomePage(driver, wait);
        SearchResultPage searchResultPage = homePage.search(product);

        searchResultPage.getProduct();
    }

    @Then("The product price should have a discount")
    public void theProductPriceShouldHaveADiscount() {
        ProductPage productPage = new ProductPage(driver, wait);

        productPage.checkProductDiscount();
    }

    //SECOND TEST CASE
    @When("I search for product {string}, and mark sorting by seller {string}")
    public void iSearchForProductAndMarkSortingBySeller(String product, String seller) {
        HomePage homePage = new HomePage(driver, wait);
        SearchResultPage searchResultPage = homePage.search(product);

        searchResultPage.markSeller(seller);
        searchResultPage.getProduct();
    }

    @Then("The product seller {string} must match the one specified earlier")
    public void theProductSellerMustMatchTheOneSpecifiedEarlier(String seller) {
        ProductPage productPage = new ProductPage(driver, wait);
        Assert.assertTrue(productPage.checkProductSeller().contains(seller));
    }

    //THIRD TEST CASE
    @When("I search for product {string}, and add product to cart")
    public void iSearchForProductAndAddProductToCart(String product) {
        HomePage homePage = new HomePage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);

        SearchResultPage searchResultPage = homePage.search(product);
        searchResultPage.getProduct();
        productPage.addToCart();
    }

    @Then("The product must be removed from the cart")
    public void theProductMustBeRemovedFromTheCart() {
        CartPage cartPage = new CartPage(driver, wait);

        String currentMessage = cartPage.deleteFirstElementInCart();
        Assert.assertTrue(currentMessage.contains("Корзина пуста"));
    }

    @After
    public void doSomethingAfter(Scenario scenario){
        if (scenario.isFailed()) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(scenario.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}