package org.example.fuctionalTest;

import org.example.drivers.DriverCreator;
import org.example.pages.HomePage;
import org.example.pages.LauncherPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver webDriver;
    private LauncherPage launcherPage;

    @BeforeTest
    public void setUp() {
        // Initialize WebDriver and pass it to LauncherPage
        webDriver = new DriverCreator().create("chrome");
        launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://www.saucedemo.com/");
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    @Test
    public void validLoginTest() {
        // Use non-static methods
        launcherPage.enterLoginDetails("standard_user", "secret_sauce");
        HomePage homePage = launcherPage.clickOnLoginButton();

        // Assert using non-static methods from HomePage
        String pageTitle = homePage.getPageTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Login failed or incorrect page title");
        System.out.println("Github Actions verified successfully");
    }
}
