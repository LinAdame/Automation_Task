package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.alza.cz");
    }

    public void acceptCookies() {
        driver.findElement(By.cssSelector(".js-cookies-info-accept")).click();
    }
}
