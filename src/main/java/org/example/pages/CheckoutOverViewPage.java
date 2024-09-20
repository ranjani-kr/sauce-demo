package org.example.pages;

import org.example.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverViewPage {

    private final WebDriver webDriver;

    private final By checkoutPageTitle = By.className("title");
    // Locators for product verification
    private final By cartItemsLocator = By.cssSelector(".cart_list .cart_item");
    private final By productName = By.className("inventory_item_name");
    private final By productDesc = By.className("inventory_item_desc");
    private final By productPrice = By.className("inventory_item_price");

    // Locators for summary labels and values
    private final By paymentInfoLabel = By.cssSelector("div[data-test='payment-info-value']");
    private final By shippingInfoLabel = By.cssSelector("div[data-test='shipping-info-value']");
    private final By itemTotalLabel = By.cssSelector("div[data-test='subtotal-label']");
    private final By taxLabel = By.cssSelector("div[data-test='tax-label']");
    private final By totalLabel = By.cssSelector("div[data-test='total-label']");
    private final By finishButton = By.id("finish");

    public CheckoutOverViewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageTitle() {
        return webDriver.findElement(checkoutPageTitle).getText();
    }

    public List<Product> getProductList() {
        List<WebElement> cartItems = webDriver.findElements(cartItemsLocator);
        List<Product> products = new ArrayList<>();

        for (WebElement item : cartItems) {
            String name = item.findElement(productName).getText();
            String description = item.findElement(productDesc).getText();
            String price = item.findElement(productPrice).getText();

            Product product = Product.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .build();

            products.add(product);
        }
        return products;
    }
    public String getPaymentInformation() {
        return webDriver.findElement(paymentInfoLabel).getText();
    }

    // Method to verify the shipping information
    public String getShippingInformation() {
        return webDriver.findElement(shippingInfoLabel).getText();
    }

    public String getItemTotal() {
        return webDriver.findElement(itemTotalLabel).getText();
    }

    public String getTax() {
        return webDriver.findElement(taxLabel).getText();
    }

    public String getTotalPrice() {
        return webDriver.findElement(totalLabel).getText();
    }

    public CheckoutCompletePage clickOnFinishButton(){
        webDriver.findElement(finishButton).click();
        return new CheckoutCompletePage(webDriver);
    }
}
