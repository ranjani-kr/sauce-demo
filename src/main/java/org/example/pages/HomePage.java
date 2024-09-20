package org.example.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.example.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
    private final WebDriver webDriver;
    private final By logo = By.className("app_logo");
    private final By filterIcon = By.className("product_sort_container");
    private final By currentFilterValue = By.className("active_option");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By productList = By.xpath("//div[@class='inventory_list']/div");
    private final By addToCartButtonLocator = By.cssSelector("button[id^='add-to-cart']");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By productName = By.className("inventory_item_name");
    private final By productDescription = By.className("inventory_item_desc");
    private final By productAmount = By.className("inventory_item_price");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageTitle() {
        return getElementText(logo);
    }
    public void chooseFilterValue(String filterValue) {
        WebElement filterDropDown = webDriver.findElement(filterIcon);
        Select filterDD = new Select(filterDropDown);
        filterDD.selectByVisibleText(filterValue);
    }
    public List<String> getProductNames() {
        return webDriver.findElements(productNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public List<Double> getProductPrices() {
        return webDriver.findElements(productPrices).stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public String getSelectedFilterValue() {
        return getElementText(currentFilterValue);
    }
    private String getElementText(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return webDriver.findElement(locator).getText();
    }
    public List<WebElement> getProductLists() {
        return webDriver.findElements(productList);
    }
    public Product addItemToCartByIndex(int index) throws Exception {
        List<WebElement> productList = getProductLists();
        if (index >= 0 && index < productList.size()) {
            WebElement item = productList.get(index);
            // Get the title of the product
            String productTitle = item.findElement(productName).getText();
            String productDes = item.findElement(productDescription).getText();
            // Concatenate the $ and the price value
            String productPrice = item.findElement(productAmount).getText().replace("\n", "").trim();

            // Click the "Add to Cart" button
            WebElement addToCartButton = item.findElement(addToCartButtonLocator);
            addToCartButton.click();
            return Product.builder()
                    .name(productTitle)
                    .description(productDes)
                    .price(productPrice)
                    .build();
        }
        throw new Exception("Invalid index: " + index);
    }

    public String getBadgeValue() {
        return getElementText(shoppingCartBadge);
    }

    public CartPage clickOnCartIcon() {
        webDriver.findElement(shoppingCartBadge).click();
        return new CartPage(webDriver);
    }
}
