package adi.pages;

import adi.enums.ExpectedPageTitles;
import adi.reusableFeatures.ReusableFeatures;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class XtbHomePage {
    WebDriver driver;
    ReusableFeatures reusableFeatures;
    By search = By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[1]/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[2]/input");
    By sellButton = By.xpath("//*[@id='clickAndTradeButtonBid']");
    By buyButton = By.xpath("//*[@id='clickAndTradeButtonAsk']");
    By closeButton = By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[13]/div[2]/span");
    By applyButton = By.xpath("//*[@id='applyBtn']");
//    By popupConfirmTradeDraggable = By.xpath("//*[@id='popupConfirmTradeDraggable']");
    By openPosition = By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[1]/div");
    By positionType = By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[2]");

    public XtbHomePage(WebDriver driver) {
        this.driver = driver;
        this.reusableFeatures = new ReusableFeatures(driver);
    }

    public boolean isPositionIsOpen() {
        reusableFeatures.waitForPageTitle(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle());
        boolean positionOpened;
        try {
            reusableFeatures.waitForVisibilityOfElement(openPosition);
            positionOpened = true;
        } catch (Exception e) {
            positionOpened = false;
        }
        return positionOpened;
    }

    public Integer getOpenPositionType() {
        reusableFeatures.waitForPageTitle(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle());
        if (isPositionIsOpen()) {
            if (reusableFeatures.waitForVisibilityOfElementAndGetElementText(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), positionType).equals("Sell")) {
                return -1;
            } else if (reusableFeatures.waitForVisibilityOfElementAndGetElementText(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), positionType).equals("Buy")) {
                return 1;
            }
        }
        return 0;
    }

    public void openSellPosition() {
        reusableFeatures.waitForPageTitle(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle());
        reusableFeatures.waitForVisibilityOfElementAndSendKeysToElement(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), search, "LITECOIN");
        reusableFeatures.waitForVisibilityOfElementAndSendKeysToElement(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), search, String.valueOf(Keys.ENTER));
//        driver.findElement(search).sendKeys(Keys.ENTER);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), sellButton);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), applyButton);
    }

    public void openBuyPosition() {
        reusableFeatures.waitForPageTitle(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle());
        reusableFeatures.waitForVisibilityOfElementAndSendKeysToElement(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), search, "LITECOIN");
        reusableFeatures.waitForVisibilityOfElementAndSendKeysToElement(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), search, String.valueOf(Keys.ENTER));
//        driver.findElement(search).sendKeys(Keys.ENTER);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), buyButton);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), applyButton);

    }

    public void closePosition() {
        reusableFeatures.waitForPageTitle(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle());
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), closeButton);
//        reusableFeatures.waitForVisibilityOfElement(popupConfirmTradeDraggable);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle(), applyButton);

    }
}