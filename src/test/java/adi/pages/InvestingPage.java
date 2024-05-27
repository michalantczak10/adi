package adi.pages;

import adi.enums.ExpectedPageTitles;
import adi.reusableFeatures.ReusableFeatures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvestingPage {
    ReusableFeatures reusableFeatures;
    WebDriver driver;
    public By indicatorsState = By.cssSelector(".rounded-full.text-center.mb-6.mt-1.font-semibold.leading-5.text-white");
    public By acceptCookiesButton = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
    public By interval1DButton = By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div[2]/div[1]/div[3]/div/div[1]/div/button[7]");

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
        if (getIndicatorsState().equals("Mocne kup") || getIndicatorsState().equals("Kup")) {
            return 1;
        } else if (getIndicatorsState().equals("Mocne sprzedaj") || getIndicatorsState().equals("Sprzedaj")) {
            return -1;
        } else {
            return 0;
        }
    }
}