package adi.pages;

import adi.enums.ExpectedPageTitles;
import adi.reusableFeatures.ReusableFeatures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvestingPage {
    ReusableFeatures reusableFeatures;
    WebDriver driver;
    public By indicatorsState = By.xpath("//span[contains(@class, 'conclusion')]");
    public By acceptCookiesButton = By.xpath("//*[@id='onetrust-accept-btn-handler']");
    public By interval1DButton = By.xpath("//button[@data-test='1d']");

    public InvestingPage(WebDriver driver) {
        this.driver = driver;
        this.reusableFeatures = new ReusableFeatures(driver);
    }

    public String getIndicatorsState() {
        reusableFeatures.waitForPageTitle(ExpectedPageTitles.INVESTING_PAGE.getExpectedPageTitle());
        reusableFeatures.waitForVisibilityOfElement(indicatorsState);
        return driver.findElement(indicatorsState).getText();
    }

    public Integer getIndicatorsStatesSummary() {
        if (getIndicatorsState().equals("Strong Buy") || getIndicatorsState().equals("Buy")) {
            return 1;
        } else if (getIndicatorsState().equals("Strong Sell") || getIndicatorsState().equals("Sell")) {
            return -1;
        } else {
            return 0;
        }
    }
}