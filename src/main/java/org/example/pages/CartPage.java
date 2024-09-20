package org.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private final WebDriver webDriver;
    private final By cartItemsLocator = By.cssSelector(".cart_list .cart_item");
    private final By productNameLocator = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> cartItems = webDriver.findElements(cartItemsLocator);
        for (WebElement cartItem : cartItems) {
            String actualProductName = cartItem.findElement(productNameLocator).getText();
            if (actualProductName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public CheckoutYourInformationPage clickOnCheckoutButton() {
        webDriver.findElement(checkoutButton).click();
        return new CheckoutYourInformationPage(webDriver);
    }
}
