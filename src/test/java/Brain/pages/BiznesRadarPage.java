package Brain.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BiznesRadarPage {
    WebDriver driver;
    String indicatorsStateFor1hInterval;
    String indicatorsStateFor1dInterval;
    String indicatorsStateFor1wInterval;
    int indicatorsStatesSummaryForAllIntervals;
    By indicatorsState = By.xpath("/html/body/div[2]/div[2]/div[1]/div/main/div/div/div[6]/div[3]/div[1]/div[1]/div[1]");
    By interval1h = By.xpath("/html/body/div[2]/div[2]/div[1]/div/main/div/div[2]/div[6]/div[1]/a[2]");
    By interval1d = By.xpath("/html/body/div[2]/div[2]/div[1]/div/main/div/div[2]/div[6]/div[1]/a[3]");
    By interval1w = By.xpath("/html/body/div[2]/div[2]/div[1]/div/main/div/div[2]/div[6]/div[1]/a[4]");

    public BiznesRadarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchTo1hInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Ripple XRP/PLN 1:1 (XRPPLN.BTB) - BiznesRadar.pl";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1h));
        driver.findElement(interval1h).click();
    }

    public void switchTo1dInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Ripple XRP/PLN 1:1 (XRPPLN.BTB) - BiznesRadar.pl";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1d));
        driver.findElement(interval1d).click();
    }

    public void switchTo1wInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Ripple XRP/PLN 1:1 (XRPPLN.BTB) - BiznesRadar.pl";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1w));
        driver.findElement(interval1w).click();
    }


    public String getIndicatorsState() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Ripple XRP/PLN 1:1 (XRPPLN.BTB) - BiznesRadar.pl";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(indicatorsState));
        return driver.findElement(indicatorsState).getText();
    }

    public Integer getIndicatorsStatesSummary() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Ripple XRP/PLN 1:1 (XRPPLN.BTB) - BiznesRadar.pl";
        Assert.assertEquals(expectedTitle, actualTitle);
        switchTo1wInterval();
        indicatorsStateFor1wInterval = getIndicatorsState();
        switchTo1dInterval();
        indicatorsStateFor1dInterval = getIndicatorsState();
//        switchTo1hInterval();
//        indicatorsStateFor1hInterval = getIndicatorsState();
        if (indicatorsStateFor1wInterval.equals("mocne kupuj") || indicatorsStateFor1wInterval.equals("kupuj")) {
            if (indicatorsStateFor1dInterval.equals("mocne kupuj") || indicatorsStateFor1dInterval.equals("kupuj")) {
//                if (indicatorsStateFor1hInterval.equals("mocne kupuj") || indicatorsStateFor1hInterval.equals("kupuj")) {
                    indicatorsStatesSummaryForAllIntervals = 1;
//                }
            }
        } else if (indicatorsStateFor1wInterval.equals("mocne sprzedaj") || indicatorsStateFor1wInterval.equals("sprzedaj")) {
            if (indicatorsStateFor1dInterval.equals("mocne sprzedaj") || indicatorsStateFor1dInterval.equals("sprzedaj")) {
//                if (indicatorsStateFor1hInterval.equals("mocne sprzedaj") || indicatorsStateFor1hInterval.equals("sprzedaj")) {
                    indicatorsStatesSummaryForAllIntervals = -1;
//                }
            }
        } else {
            indicatorsStatesSummaryForAllIntervals = 0;
        }
        return indicatorsStatesSummaryForAllIntervals;
    }
}