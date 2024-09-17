package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
public class DriverCreator {
    public WebDriver create(String browser) {
        browser = setDefaultBrowser(browser);
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // Automatically downloads and sets up FirefoxDriver
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup(); // Automatically downloads and sets up EdgeDriver
                return new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup(); // Automatically downloads and sets up ChromeDriver
                return new ChromeDriver();
        }
    }
    private String setDefaultBrowser(String browser) {
        if (Objects.isNull(browser) || browser.isEmpty()) {
            browser = "chrome";
        }
        return browser;
    }
}