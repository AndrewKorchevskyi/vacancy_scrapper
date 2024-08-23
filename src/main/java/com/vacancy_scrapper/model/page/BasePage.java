package com.vacancy_scrapper.model.page;

import com.vacancy_scrapper.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Register a shutdown hook to close the driver upon JVM exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (driver != null) {
                driver.quit();
            }
        }));
    }

    // Optional method to explicitly close the driver if needed
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
