package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TvPage {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private final String ORDER_BY_PRICE_ASC = "ui-id-4";
    private final String ADD_TO_CART = "btnk1";

    public TvPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }

    public void open() {
        driver.get("https://www.alza.cz/televize/18849604.htm");
    }

    public void sortByPriceAsc() {
        //Order by price ASC
        wait.until(d -> driver.findElement(By.id(ORDER_BY_PRICE_ASC)).isDisplayed());
        driver.findElement(By.id(ORDER_BY_PRICE_ASC)).click();
    }

    public String selectFirstTv() throws InterruptedException {
        //Pick the cheapest one
        wait.until(d -> driver.findElement(By.className(ADD_TO_CART)).isDisplayed());
        Thread.sleep(3000);
        String tvName = driver.findElement(By.cssSelector("#blockFilterNoEmpty .fb .browsinglink")).getText();
        driver.findElement(By.className(ADD_TO_CART)).click();

        //Click grey button for Koupit nove
        try {
            driver.findElement(By.cssSelector(".alzaDialogButtons .grey")).click();
        } catch (NoSuchElementException exception) {
            System.out.println("Element was not found");
        }

        wait.until(d -> driver.findElement(By.xpath("//a[@data-testid=\"headerBasketIcon\"]")).getText().equals("1"));

        return tvName;
    }
}
