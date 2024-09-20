package org.example.fuctionalTest;

import org.example.drivers.DriverCreator;
import org.example.pages.HomePage;
import org.example.pages.LauncherPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTests {
    private WebDriver webDriver;
    public final String LAUNCH_PAGE_URL = "https://www.saucedemo.com/";
    String username = "standard_user";
    String password = "secret_sauce";
    String LOW_TO_HIGH = "Price (low to high)";
    String A_Z = "Name (A to Z)";
    String Z_A = "Name (Z to A)";
    String HIGH_LOW = "Price (high to low)";
    private LauncherPage launcherPage;
    private HomePage homePage;

    @BeforeTest
    public void setUp() {
        webDriver = new DriverCreator().create("chrome");
        launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo(LAUNCH_PAGE_URL);
        launcherPage.enterLoginDetails(username, password);
        homePage = launcherPage.clickOnLoginButton(); // Initialize homePage after login
    }
    @Test
    public void verifySortHighToLow() {
        homePage.chooseFilterValue(HIGH_LOW);
        List<Double> prices = homePage.getProductPrices();

        // Check if prices are sorted in descending order
        List<Double> sortedPrices = prices.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(prices, sortedPrices, "Products are not sorted from High to Low");
    }

    @Test
    public void verifySortLowToHigh() {
        homePage.chooseFilterValue(LOW_TO_HIGH);
        List<Double> prices = homePage.getProductPrices();

        // Check if prices are sorted in ascending order
        List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(prices, sortedPrices, "Products are not sorted from Low to High");
    }
    @Test
    public void verifySortAZ() {
        homePage.chooseFilterValue(A_Z);
        List<String> productNames = homePage.getProductNames();

        // Check if product names are sorted alphabetically (A to Z)
        List<String> sortedNames = productNames.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted from A to Z");
    }
    @Test
    public void verifySortZA() {
        homePage.chooseFilterValue(Z_A);
        List<String> productNames = homePage.getProductNames();

        // Check if product names are sorted in reverse alphabetical order (Z to A)
        List<String> sortedNames = productNames.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted from Z to A");
    }
    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

