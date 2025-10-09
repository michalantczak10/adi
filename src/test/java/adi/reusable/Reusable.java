package adi.reusable;

import adi.enums.Urls;
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

    public void openUrl(String url) {
        driver.get(url);
    }

    public void waitForPage(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Parameters.TIMEOUT.getParameter()));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public void waitForVisibilityOfElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Parameters.TIMEOUT.getParameter()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void clickElement(By element) {
        WebElement webElement = driver.findElement(element);
        webElement.click();
    }

    public void waitForVisibilityOfElementAndClick(By element) {
//        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        clickElement(element);
    }

    public void waitForVisibilityAndSendKeysToElement(By element, String value) {
//        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        sendKeysToElement(element, value);
    }

    public void sendKeysToElement(By element, String symbolName) {
        driver.findElement(element).sendKeys(symbolName);
    }

    public String waitForVisibilityAndGetElementText(By element) {
//        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        return driver.findElement(element).getText();
    }
}
