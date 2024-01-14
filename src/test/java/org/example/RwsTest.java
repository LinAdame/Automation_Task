package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RwsTest {
    private WebDriver driver = WebDriverManager.firefoxdriver().create();
    private Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(5000));

    @Test
    public void alzaTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        TvPage tvPage = new TvPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.open();
        homePage.acceptCookies();

        //Open TV page
        tvPage.open();
        tvPage.sortByPriceAsc();
        String tvName = tvPage.selectFirstTv();

        //Open cart page
        cartPage.open();
        String price = cartPage.getMainItemPrice();
        String name = cartPage.getMainItemName();
        Assertions.assertEquals("Televize " + tvName, name);
        Assertions.assertEquals("2 819 Kč", price);

        //Click on + button
        cartPage.increaseCountByOne();

        price = cartPage.getMainItemPrice();
        Assertions.assertEquals("5 638 Kč", price);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}