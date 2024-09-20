package org.example.e2eTests;

import org.example.drivers.DriverCreator;
import org.example.models.Customer;
import org.example.models.Product;
import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BuyAProductTest {
    private WebDriver webDriver;
    public final String LAUNCH_PAGE_URL = "https://www.saucedemo.com/";
    String username = "standard_user";
    String password = "secret_sauce";
    String sortOption = "Price (low to high)";

    @BeforeClass
    public void setUp() {
        webDriver = new DriverCreator().create("chrome");
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void sortByLowToHighAndBuyFirstTwoProductFromTheList() throws Exception {
        Customer customerInfo = Customer.init();

        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo(LAUNCH_PAGE_URL);
        launcherPage.enterLoginDetails(username, password);

        HomePage homePage = launcherPage.clickOnLoginButton();
        String titleText = homePage.getPageTitle();
        Assert.assertEquals(titleText, "Swag Labs");

        homePage.chooseFilterValue(sortOption);
        String selectedFilterValue = homePage.getSelectedFilterValue();
        Assert.assertEquals(selectedFilterValue, sortOption);

        Product infantOnesie = homePage.addItemToCartByIndex(0);
        Product bikeLight = homePage.addItemToCartByIndex(1);
        String badgeNumber = homePage.getBadgeValue();
        Assert.assertEquals(badgeNumber, "2");

        CartPage cartPage = homePage.clickOnCartIcon();
        Assert.assertTrue(cartPage.isProductInCart(infantOnesie.getName()));
        Assert.assertTrue(cartPage.isProductInCart(bikeLight.getName()));

        CheckoutYourInformationPage checkoutYourInformationPage = cartPage.clickOnCheckoutButton();
        checkoutYourInformationPage.enterCheckoutInformation(customerInfo);
        CheckoutOverViewPage checkOutOverViewPage = checkoutYourInformationPage.clickOnContinueButton();

        // Verify the page title
        Assert.assertEquals(checkOutOverViewPage.getPageTitle(), "Checkout: Overview");

        // Get the product list and verify that the correct products are listed in the overview
        List<Product> productsInOverview = checkOutOverViewPage.getProductList();
        Assert.assertTrue(productsInOverview.contains(infantOnesie));
        Assert.assertTrue(productsInOverview.contains(bikeLight));

        // Verify payment and shipping information
        Assert.assertEquals(checkOutOverViewPage.getPaymentInformation(), "SauceCard #31337");
        Assert.assertEquals(checkOutOverViewPage.getShippingInformation(), "Free Pony Express Delivery!");

        // Verify item total, tax, and total price
        Assert.assertEquals(checkOutOverViewPage.getItemTotal(), "Item total: $17.98");
        Assert.assertEquals(checkOutOverViewPage.getTax(), "Tax: $1.44");
        Assert.assertEquals(checkOutOverViewPage.getTotalPrice(), "Total: $19.42");

        //Verify the order confirmation page title and order confirmation message
        CheckoutCompletePage checkoutCompletePage = checkOutOverViewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.getTitle(), "Checkout: Complete!");
        Assert.assertEquals(checkoutCompletePage.getOrderConfirmationMessage(), "Thank you for your order!");
    }
}