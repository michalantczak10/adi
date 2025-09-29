package adi.pages;

import adi.reusable.Reusable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static adi.enums.ExpectedPageTitles.XTB_HOME_PAGE;

public class XtbHomePage {
    WebDriver driver;
    Reusable reusable;
    By selectAccount = By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/xs-combobox/div/div/button");
    By demoAccount = By.xpath("//span[text()='DEMO']");
    By realAccount = By.xpath("//span[text()='REAL']");
    By search = By.cssSelector("input[ng-model='searchString']");
    By sellButton = By.cssSelector("click-and-trade-button[id='clickAndTradeButtonBid']");
    By buyButton = By.cssSelector("click-and-trade-button[id='clickAndTradeButtonAsk']");
    By closeButton = By.cssSelector("span[data-xsot='ordersCloseTradeBtn']");
    By applyButton = By.cssSelector("button[class='applyBtn']");
    By intervalButton = By.xpath("//*[@id=\"chartPanelsContainer\"]/xchart-panel/div/div[2]/xs-combobox-chart-interval/div/div/button");
    By openPosition = By.cssSelector(
            "/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[1]/div");
    By positionType = By.xpath(
            "/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[2]");
    public By bollingerBands = By.xpath("//div[contains(@class, 'indicator-label-container')]//span[contains(text(), 'Bollinger [20, 2.5]')]/following-sibling::span[@class='indicator-value-label ng-binding']");
    public By currentClosePrice = By.xpath("//div[contains(@class, 'indicator-label-container')]//span[contains(text(), 'SMA [1, 0]')]/following-sibling::span[@class='indicator-value-label ng-binding']");
    public By interval1D = By.xpath("//a[contains(text(), '1D')]");


    public XtbHomePage(WebDriver driver) {
        this.driver = driver;
        this.reusable = new Reusable(driver);
    }

    public Integer checkBollingerBandsTrend() {
        if (getCurrentClosePriceValue() < getLowerBandValue()) {
            return -1;
        } else if (getCurrentClosePriceValue() > getUpperBandValue()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getIndicatorValue(By indicator) {
        return reusable.waitForVisibilityAndGetElementText(XTB_HOME_PAGE.getExpectedPageTitle(), indicator);
    }

    public void selectSymbol(String symbol) {
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, symbol);
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, String.valueOf(Keys.ENTER));
    }

    public void selectInterval(String interval) {
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), intervalButton);
        try {
            WebElement element = driver.findElement(By.cssSelector(".jspPane"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.transform = 'translate3d(0px, -135px, 0px)';", element);
            reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), By.xpath("//a[contains(text(), '" + interval + "')]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getCurrentClosePriceValue() {
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, "SOLANA");
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, String.valueOf(Keys.ENTER));
        return Double.parseDouble(getIndicatorValue(currentClosePrice).replace(",", ".").replaceAll("[^\\d.]", ""));
    }

    public double getUpperBandValue() {
        String[] bollingerBandsComponents =
                getIndicatorValue(bollingerBands).split(", ");
        return Double.parseDouble(bollingerBandsComponents[0]);
    }

    public double getMiddleBandValue() {
        String[] bollingerBandsComponents =
                getIndicatorValue(bollingerBands).split(", ");
        return Double.parseDouble(bollingerBandsComponents[1]);
    }

    public double getLowerBandValue() {
        String[] bollingerBandsComponents =
                getIndicatorValue(bollingerBands).split(", ");
        return Double.parseDouble(bollingerBandsComponents[2]);
    }

    public boolean isPositionIsOpen() {
        reusable.waitForPageTitle(XTB_HOME_PAGE.getExpectedPageTitle());
        boolean positionOpened;
        try {
            reusable.waitForVisibilityOfElement(openPosition);
            positionOpened = true;
        } catch (Exception e) {
            positionOpened = false;
        }
        return positionOpened;
    }

    public Integer getOpenPositionType() {
        reusable.waitForPageTitle(XTB_HOME_PAGE.getExpectedPageTitle());
        if (isPositionIsOpen()) {
            if (reusable.waitForVisibilityAndGetElementText(XTB_HOME_PAGE.getExpectedPageTitle(), positionType)
                    .equals("Sell")) {
                return -1;
            } else if (reusable.waitForVisibilityAndGetElementText(XTB_HOME_PAGE.getExpectedPageTitle(), positionType)
                    .equals("Buy")) {
                return 1;
            }
        }
        return 0;
    }

    public void openSellPosition() {
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, "SOLANA");
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, String.valueOf(Keys.ENTER));
        driver.findElement(search).sendKeys(Keys.ENTER);
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), sellButton);
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), applyButton);
    }

    public void openBuyPosition() {
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, "SOLANA");
        reusable.waitForVisibilityAndSendKeysToElement(XTB_HOME_PAGE.getExpectedPageTitle(), search, String.valueOf(Keys.ENTER));
        driver.findElement(search).sendKeys(Keys.ENTER);
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), buyButton);
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), applyButton);
    }

    public void closePosition() {
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), closeButton);
//        reusable.waitForVisibilityOfElement(popupConfirmTradeDraggable);
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), applyButton);
    }

    public void selectAccount(String account) {
        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), selectAccount);
        if (account.equals("DEMO")) {
            reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), demoAccount);
        } else if (account.equals("REAL")) {
            reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), realAccount);
        }
    }
}