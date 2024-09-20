package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private final WebDriver webDriver;
    private final By title = By.className("title");
    private final By orderConfirmationMessage = By.className("complete-header");

    public CheckoutCompletePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getTitle() {
        return webDriver.findElement(title).getText();
    }

    public String getOrderConfirmationMessage() {
        return webDriver.findElement(orderConfirmationMessage).getText();
    }
}
