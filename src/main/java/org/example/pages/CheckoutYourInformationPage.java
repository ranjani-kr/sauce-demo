package org.example.pages;

import org.example.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationPage {
    private final WebDriver webDriver;
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By continueButton = By.id("continue");
    private final By postalCode = By.id("postal-code");

    public CheckoutYourInformationPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void enterCheckoutInformation(Customer customer){
        webDriver.findElement(firstName).click();
        webDriver.findElement(firstName).sendKeys(customer.getFirstName());
        webDriver.findElement(lastName).click();
        webDriver.findElement(lastName).sendKeys(customer.getLastName());
        webDriver.findElement(postalCode).click();
        webDriver.findElement(postalCode).sendKeys(customer.getPostCode());
    }

    public CheckoutOverViewPage clickOnContinueButton(){
        webDriver.findElement(continueButton).click();
        return new CheckoutOverViewPage(webDriver);
    }
}
