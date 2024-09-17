package org.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
    private final WebDriver webDriver;
    private final By logo = By.className("app_logo");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
        wait.until(ExpectedConditions.elementToBeClickable(logo));
        return webDriver.findElement(logo).getText();
    }

}
