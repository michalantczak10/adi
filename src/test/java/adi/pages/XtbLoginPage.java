package adi.pages;

import adi.reusable.Reusable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static adi.enums.Urls.XTB_LOGIN_PAGE;

public class XtbLoginPage {

    WebDriver driver;
    Reusable reusable;
    public By login = By.cssSelector("input[name='xslogin']");
    public By password = By.cssSelector("input[name='xspass']");
    public By loginButton = By.cssSelector("input[type='button']");

    public XtbLoginPage(WebDriver driver) {
        this.driver = driver;
        this.reusable = new Reusable(driver);
    }

    public void login() {
        reusable.openUrl(XTB_LOGIN_PAGE.getUrl());
        reusable.waitForPage("https://xstation5.xtb.com/#/_/login");
        reusable.waitForVisibilityAndSendKeysToElement(login, "michalantczak@outlook.com");
        reusable.waitForVisibilityAndSendKeysToElement(password, "TobiaszekKajtus1701!");
        reusable.waitForVisibilityOfElementAndClick(loginButton);
        reusable.waitForPage("https://xstation5.xtb.com/#/real/loggedIn");
    }
}