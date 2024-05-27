package adi.reusableFeatures;

import adi.enums.ExpectedPageTitles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ReusableFeatures {
    WebDriver driver;

    public ReusableFeatures(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebsite(String url, String expectedPageTitle, boolean openInNewTab) {
        if (openInNewTab) {
            driver.switchTo().newWindow(WindowType.TAB);
        }
        driver.get(url);

        if (openInNewTab) {
            switchToTab(1);
        }

        waitForPageTitle(expectedPageTitle);
    }

    public void switchToTab(int tabNumber) {
        String currentHandle = driver.getWindowHandle();
        List<String> handles = new ArrayList<>(driver.getWindowHandles());

        if (tabNumber >= 0 && tabNumber < handles.size() && !handles.get(tabNumber).equals(currentHandle)) {
            driver.switchTo().window(handles.get(tabNumber));
        }
    }

    public void waitForPageTitle(String expectedPageTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleIs(expectedPageTitle));
    }

    public void waitForVisibilityOfElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

    public void waitForVisibilityOfElementAndSendKeysToElement(String expectedPageTitle, By element, String symbolName) {
        waitForPageTitle(expectedPageTitle);
        waitForVisibilityOfElement(element);
        sendKeysToElement(element, symbolName);
    }

    public void sendKeysToElement(By element, String symbolName){
        driver.findElement(element).sendKeys(symbolName);
    }

    public String waitForVisibilityOfElementAndGetElementText(String expectedPageTitle, By element) {
        waitForPageTitle(ExpectedPageTitles.INVESTING_PAGE.getExpectedPageTitle());
        waitForVisibilityOfElement(element);
        return driver.findElement(element).getText();
    }
}