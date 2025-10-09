package adi.pages;

import adi.reusable.Reusable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class XtbHomePage {
    WebDriver driver;
    Reusable reusable;
    By logoutButton = By.xpath("//span[text()='Wyloguj']");
    By selectAccount = By.xpath("//xs-combobox[@title='Zmień konto']");
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
    public By buttonOkMargin = By.xpath("//button[contains(text(), 'Ok')]");
    public By buttonCloseAlert = By.cssSelector("div[class='xs-alert-close-btn']");


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
        return reusable.waitForVisibilityAndGetElementText(indicator);
    }

    public void selectSymbol(String symbol) {
        reusable.waitForVisibilityAndSendKeysToElement(search, symbol);
        reusable.waitForVisibilityAndSendKeysToElement(search, String.valueOf(Keys.ENTER));
    }

//    public void selectInterval(String interval) {
//        reusable.waitForVisibilityOfElementAndClick(XTB_HOME_PAGE.getExpectedPageTitle(), intervalButton);
//        Map<String, Integer> intervalSteps = new HashMap<>();
//        intervalSteps.put("M1", 0);
//        intervalSteps.put("M5", 1);
//        intervalSteps.put("M15", 2);
//        intervalSteps.put("M30", 3);
//        intervalSteps.put("H1", 4);
//        intervalSteps.put("H4", 5);
//        intervalSteps.put("D1", 6);
//        intervalSteps.put("W1", 7);
//        intervalSteps.put("MN", 8);
//        int steps = intervalSteps.getOrDefault(interval, 0);
//        Actions actions = new Actions(driver);
//
//        for (int i = 0; i < steps; i++) {
//
//            try {
//                WebElement menu = driver.switchTo().activeElement();
//                actions.moveToElement(menu).click().perform();
//                actions.sendKeys(Keys.ENTER).perform();
//                Thread.sleep(100);
//
//
//            } catch (Exception e) {
//                actions.sendKeys(Keys.ARROW_DOWN).perform();
//                throw new RuntimeException("Nie udało się wybrać interwału: " + interval, e);
//            }
//        }
//    }

    public void selectInterval(String interval) {
        try {
            reusable.waitForVisibilityOfElementAndClick(intervalButton);

            Actions actions = new Actions(driver);
            WebElement menu = driver.switchTo().activeElement();
            actions.moveToElement(menu).click().perform();

            int maxTries = 20;
            boolean found = false;

            for (int i = 0; i < maxTries; i++) {
                WebElement activeElement = driver.switchTo().activeElement();
                String currentText = activeElement.getText().trim();

                if (currentText.equalsIgnoreCase(interval)) {
//                    actions.sendKeys(Keys.ENTER).perform();
                    found = true;
                    break;
                }
                else {

                    actions.sendKeys(Keys.ARROW_DOWN).perform();
                    Thread.sleep(100);
                }
            }

            if (!found) {
                throw new RuntimeException("Nie znaleziono interwału: " + interval);
            }

        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas wybierania interwału: " + interval, e);
        }
    }


    public void closePopupAboutMargin(){
        try {
            reusable.waitForVisibilityOfElementAndClick(buttonOkMargin);
        } catch (Exception _) {
        }
    }

    public void closeAlert(){
        try {
            reusable.waitForVisibilityOfElementAndClick(buttonCloseAlert);
        } catch (Exception _) {
        }
    }

    public double getCurrentClosePriceValue() {
        reusable.waitForVisibilityAndSendKeysToElement(search, "SOLANA");
        reusable.waitForVisibilityAndSendKeysToElement(search, String.valueOf(Keys.ENTER));
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
//        reusable.waitForPage(XTB_HOME_PAGE.getExpectedPage());
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
//        reusable.waitForPage(XTB_HOME_PAGE.getExpectedPage());
        if (isPositionIsOpen()) {
            if (reusable.waitForVisibilityAndGetElementText(positionType)
                    .equals("Sell")) {
                return -1;
            } else if (reusable.waitForVisibilityAndGetElementText(positionType)
                    .equals("Buy")) {
                return 1;
            }
        }
        return 0;
    }

    public void openSellPosition() {
        reusable.waitForVisibilityAndSendKeysToElement(search, "SOLANA");
        reusable.waitForVisibilityAndSendKeysToElement(search, String.valueOf(Keys.ENTER));
        driver.findElement(search).sendKeys(Keys.ENTER);
        reusable.waitForVisibilityOfElementAndClick(sellButton);
        reusable.waitForVisibilityOfElementAndClick(applyButton);
    }

    public void openBuyPosition() {
        reusable.waitForVisibilityAndSendKeysToElement(search, "SOLANA");
        reusable.waitForVisibilityAndSendKeysToElement(search, String.valueOf(Keys.ENTER));
        driver.findElement(search).sendKeys(Keys.ENTER);
        reusable.waitForVisibilityOfElementAndClick(buyButton);
        reusable.waitForVisibilityOfElementAndClick(applyButton);
    }

    public void closePosition() {
        reusable.waitForVisibilityOfElementAndClick(closeButton);
//        reusable.waitForVisibilityOfElement(popupConfirmTradeDraggable);
        reusable.waitForVisibilityOfElementAndClick(applyButton);
    }

    public void selectAccount(String account) {
        reusable.waitForVisibilityOfElementAndClick(selectAccount);
        if (account.equals("DEMO")) {
            reusable.waitForVisibilityOfElementAndClick(demoAccount);
        } else if (account.equals("REAL")) {
            reusable.waitForVisibilityOfElementAndClick(realAccount);
        }
    }
}