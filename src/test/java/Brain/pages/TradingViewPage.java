package Brain.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TradingViewPage {
    WebDriver driver;
    String indicatorsStateFor1mInterval;
    String indicatorsStateFor5mInterval;
    String indicatorsStateFor15mInterval;
    String indicatorsStateFor30mInterval;
    String indicatorsStateFor1hInterval;
    String indicatorsStateFor2hInterval;
    String indicatorsStateFor4hInterval;
    String indicatorsStateFor1dInterval;
    String indicatorsStateFor1wInterval;
    String indicatorsStateFor1MInterval;
    int indicatorsStatesSummaryForAllIntervals;
    //    By indicatorsStateStrongSell = By.className("text-large-vLbFM67a strong-sell-vLbFM67a speedometerText-Tat_6ZmA");
//    By indicatorsStateSell = By.className("text-large-vLbFM67a sell-vLbFM67a speedometerText-Tat_6ZmA");
//    By indicatorsStateNeutral = By.className("text-large-vLbFM67a neutral-vLbFM67a speedometerText-Tat_6ZmA");
//    By indicatorsStateBuy = By.xpath("//*[@id=\"js-category-content\"]/div[2]/div/div/div[4]/div[2]/div[2]/div[3]/span[2]");
//    By indicatorsStateNeutral = By.xpath("//*[@id=\"js-category-content\"]/div[2]/div/div/div[4]/div[2]/div[2]/div[3]/span[2]");


    By indicatorsState = By.className("text-large-vLbFM67a speedometerText-Tat_6ZmA");

    By interval1m = By.xpath("//*[@id=\"1m\"]");
    By interval5m = By.xpath("//*[@id=\"5m\"]");
    By interval15m = By.xpath("//*[@id=\"15m\"]");
    By interval30m = By.xpath("//*[@id=\"30m\"]");
    By interval1h = By.xpath("//*[@id=\"1h\"]");
    By interval2h = By.xpath("//*[@id=\"2h\"]");
    By interval4h = By.xpath("//*[@id=\"4h\"]");
    By interval1d = By.xpath("//*[@id=\"1D\"]");
    By interval1w = By.xpath("//*[@id=\"1W\"]");
    By interval1M = By.xpath("//*[@id=\"1M\"]");

    //
    public TradingViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchTo1mInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1m));
        driver.findElement(interval1m).click();
    }

    public void switchTo5mInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval5m));
        driver.findElement(interval5m).click();
    }

    public void switchTo15mInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval15m));
        driver.findElement(interval15m).click();
    }

    public void switchTo30mInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval30m));
        driver.findElement(interval30m).click();
    }

    public void switchTo1hInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1h));
        driver.findElement(interval1h).click();
    }

    public void switchTo2hInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval2h));
        driver.findElement(interval2h).click();
    }

    public void switchTo4hInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval4h));
        driver.findElement(interval4h).click();
    }

    public void switchTo1dInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1d));
        driver.findElement(interval1d).click();
    }

    public void switchTo1wInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1w));
        driver.findElement(interval1w).click();
    }

    public void switchTo1MInterval() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(interval1M));
        driver.findElement(interval1M).click();
    }

    public String getIndicatorsState() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(indicatorsState));
        return driver.findElement(indicatorsState).getText();
    }

    public Integer getIndicatorsStatesSummary() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Analiza techniczna Solana (COINBASE: SOLUSD) — TradingView";
        Assert.assertEquals(expectedTitle, actualTitle);
        switchTo1MInterval();
        try{
            Thread.sleep(30000);
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        indicatorsStateFor1MInterval = getIndicatorsState();
                System.out.println(driver.findElement(indicatorsState).getText());
        if (indicatorsStateFor1MInterval.equals("Silne kup") || indicatorsStateFor1MInterval.equals("Kup")) {
            indicatorsStatesSummaryForAllIntervals = 1;
        } else if (indicatorsStateFor1MInterval.equals("Silne sprzedaj") || indicatorsStateFor1MInterval.equals("Sprzedaj")) {
            indicatorsStatesSummaryForAllIntervals = -1;
        } else {
            indicatorsStatesSummaryForAllIntervals = 0;
        }
        return indicatorsStatesSummaryForAllIntervals;
    }
}