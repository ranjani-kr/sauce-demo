package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LauncherPage {
    // Non-static WebDriver, as it should be tied to an instance
    private final WebDriver webDriver;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");

    // Constructor to initialize WebDriver
    public LauncherPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Non-static method to navigate to a URL
    public void navigateTo(String url) {
        webDriver.get(url);
    }

    // Non-static method to enter login details
    public void enterLoginDetails(String un, String pwd) {
        webDriver.findElement(username).sendKeys(un);
        webDriver.findElement(password).sendKeys(pwd);
    }

    // Non-static method to click the login button and return HomePage
    public HomePage clickOnLoginButton() {
        webDriver.findElement(loginButton).click();
        return new HomePage(webDriver);
    }
}