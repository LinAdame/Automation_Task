package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));

    }

    public void open() {
        driver.get("https://www.alza.cz/Order1.htm");
    }

    public String getMainItemPrice() {
        wait.until(d -> driver.findElement(By.className("c5")).getText().trim().length() > 1);
        return driver.findElement(By.className("c5")).getText();
    }

    public String getMainItemName() {
        wait.until(d -> driver.findElement(By.className("mainItem")).getText().trim().length() > 1);
        return driver.findElement(By.className("mainItem")).getText();
    }

    public void increaseCountByOne() {
        driver.findElement(By.className("countPlus")).click();
    }
}
