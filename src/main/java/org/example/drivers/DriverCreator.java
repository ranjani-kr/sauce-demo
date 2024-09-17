package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

                // Set Chrome to run in headless mode
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");  // Enable headless mode
                options.addArguments("--disable-gpu");  // Disable GPU rendering
                options.addArguments("--no-sandbox");  // Bypass OS security model
                options.addArguments("--disable-dev-shm-usage");  // Overcome limited resource problems
                options.addArguments("--remote-debugging-port=9222");  // Required for some environments
                options.addArguments("--window-size=1920,1080");  // Set screen size (optional but useful)git

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