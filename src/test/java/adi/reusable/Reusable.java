package adi.reusable;

import adi.enums.Parameters;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Reusable {
    WebDriver driver;

    public Reusable(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebsite(String url, String expectedPageTitle) {
        driver.get(url);
        waitForPageTitle(expectedPageTitle);
    }

    public void waitForPageTitle(String expectedPageTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Parameters.WAIT_TIME.getParameter()));
        wait.until(ExpectedConditions.titleIs(expectedPageTitle));
    }

    public void waitForVisibilityOfElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Parameters.WAIT_TIME.getParameter()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void clickElement(By element) {
        WebElement webElement = driver.findElement(element);
        webElement.click();
    }

    public void waitForVisibilityOfElementAndClick(String expectedPageTitle, By element) {
        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        clickElement(element);
    }

    public void waitForVisibilityAndSendKeysToElement(String expectedPageTitle, By element, String value) {
        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        sendKeysToElement(element, value);
    }

    public void sendKeysToElement(By element, String symbolName) {
        driver.findElement(element).sendKeys(symbolName);
    }

    public String waitForVisibilityAndGetElementText(String expectedPageTitle, By element) {
        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        return driver.findElement(element).getText();
    }
}
