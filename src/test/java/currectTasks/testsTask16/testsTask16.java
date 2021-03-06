package currectTasks.testsTask16;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testsTask16 extends BaseClass {

    @Test
    public void isThePriceInTheRightRange(){
        String query = "notebook computer";
        Integer low = 10, high = 1000;

        searchResultPage = homePage.searchProduct(query);
        searchResultPage.setCustomPriceRange(low, high);
        Double price = searchResultPage.getPrice();

        Assert.assertTrue(price > low && price < high);
    }

    @Test(dependsOnMethods = "isThePriceInTheRightRange")
    public void isTheProductOfMarkedBrand(){
        String query = "phone", brandFromSearch;
        Integer brandId = 1, productId = 0;

        searchResultPage = homePage.searchProduct(query);
        brandFromSearch = searchResultPage.selectBrand(brandId);
        productPage = searchResultPage.getProduct(productId);

        Assert.assertEquals(brandFromSearch, productPage.getBrandTitle());
    }

    @Test(dependsOnMethods = "isTheProductOfMarkedBrand")
    public void isTheDeleteMethodWorking(){
        String query = "pants", cartMsg = "empty";
        Integer productId = 0;

        searchResultPage = homePage.searchProduct(query);
        productPage = searchResultPage.getProduct(productId);
        cartPage = productPage.addToCart();
        cartPage.confirmAddToCart();

        Assert.assertTrue(cartPage.deleteFirstElementInCart().contains(cartMsg));
    }

    @Test(dependsOnMethods = "isTheDeleteMethodWorking")
    public void isTheBuyMethodWorking(){
        String query = "car seat";
        Integer productId = 0;

        searchResultPage = homePage.searchProduct(query);
        productPage = searchResultPage.getProduct(productId);
        cartPage = productPage.addToCart();
        cartPage.confirmAddToCart();
        buyPage = cartPage.confirmProductBuy();

        Assert.assertTrue(buyPage.isUserWantToBuyProduct());
    }
}
