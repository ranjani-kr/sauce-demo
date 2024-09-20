package org.example.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
        wait.until(ExpectedConditions.elementToBeClickable(logo));
        return webDriver.findElement(logo).getText();
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

}
