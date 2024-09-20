package org.example.fuctionalTest;

import org.example.drivers.DriverCreator;
import org.example.pages.HomePage;
import org.example.pages.LauncherPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver webDriver;
    private LauncherPage launcherPage;

    @BeforeTest
    public void setUp() {
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
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][] {
                {"standard_user", "invalid_password", "Epic sadface: Username and password do not match any user in this service"},
                {"invalid_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"invalid_user", "invalid_password", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "secret_sauce", "Swag Labs"}
        };
    }
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedMessage) throws InterruptedException {
        launcherPage.enterLoginDetails(username, password);
        HomePage homePage = launcherPage.clickOnLoginButton();

        if (expectedMessage.equals("Swag Labs")) {
            // Verify successful login
            String pageTitle = homePage.getPageTitle();
            Assert.assertEquals(pageTitle, expectedMessage, "Login failed or incorrect page title");
        } else {
            // Verify error message
            String errorMessage = launcherPage.getLoginErrorMessage();
            Assert.assertEquals(errorMessage, expectedMessage, "Unexpected error message");
        }
    }
}

