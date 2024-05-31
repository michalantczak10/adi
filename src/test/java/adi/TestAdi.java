package adi;

import adi.enums.ExpectedPageTitles;
import adi.pages.InvestingPage;
import adi.pages.XtbHomePage;
import adi.pages.XtbLoginPage;
import adi.reusableFeatures.ReusableFeatures;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static adi.enums.ExpectedPageTitles.XTB_PAGE;

public class TestAdi {

    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void testAdi() {
        ReusableFeatures reusableFeatures = new ReusableFeatures(driver);
        InvestingPage investingPage = new InvestingPage(driver);
        XtbLoginPage xtbLoginPage = new XtbLoginPage(driver);
        XtbHomePage xtbHomePage = new XtbHomePage(driver);
        tradeStrategy(reusableFeatures, investingPage, xtbLoginPage, xtbHomePage);
        quitDriver();
    }

    public void tradeStrategy(@NotNull ReusableFeatures reusableFeatures, @NotNull InvestingPage investingPage, @NotNull XtbLoginPage xtbLoginPage, @NotNull XtbHomePage xtbHomePage) {
        reusableFeatures.openWebsite("https://www.investing.com/crypto/litecoin/technical", ExpectedPageTitles.INVESTING_PAGE.getExpectedPageTitle(), false);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.INVESTING_PAGE.getExpectedPageTitle(), investingPage.acceptCookiesButton);
        reusableFeatures.waitForVisibilityOfElementAndClick(ExpectedPageTitles.INVESTING_PAGE.getExpectedPageTitle(), investingPage.interval1DButton);
        if (investingPage.getIndicatorsStatesSummary().equals(-1)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(-1)) {
                System.out.println("Wskaźniki wskazały trend spadkowy ...");
                System.out.println("Pozycja sprzedaży była do tej pory otwarta ...");
                System.out.println("Żadna pozycja nie została teraz zamknięta ...");
                System.out.println("Żadna pozycja nie została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(0)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            reusableFeatures.waitForPageTitle(XTB_PAGE.getExpectedPageTitle());
            if (xtbHomePage.getOpenPositionType().equals(-1)) {
//            xtbHomePage.closePosition();
                System.out.println("Wskaźniki wskazały brak trendu ...");
                System.out.println("Pozycja sprzedaży była do tej pory otwarta ...");
                System.out.println("Pozycja sprzedaży została teraz zamknięta ...");
                System.out.println("Żadna pozycja nie została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(1)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(-1)) {
//            xtbHomePage.closePosition();
//            xtbHomePage.openBuyPosition();
                System.out.println("Wskaźniki wskazały trend wzrostowy ...");
                System.out.println("Pozycja sprzedaży była do tej pory otwarta ...");
                System.out.println("Pozycja sprzedaży została teraz zamknięta ...");
                System.out.println("Pozycja kupna została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(-1)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(0)) {
//            xtbHomePage.openSellPosition();
                System.out.println("Wskaźniki wskazały trend spadkowy ...");
                System.out.println("Żadna pozycja nie była do tej pory otwarta ...");
                System.out.println("Żadna pozycja nie została teraz zamknięta ...");
                System.out.println("Pozycja sprzedaży została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(0)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(0)) {
                System.out.println("Wskaźniki wskazały brak trendu ...");
                System.out.println("Żadna pozycja nie była do tej pory otwarta ...");
                System.out.println("Żadna pozycja nie została teraz zamknięta ...");
                System.out.println("Żadna pozycja nie została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(1)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(0)) {
//            xtbHomePage.openBuyPosition();
                System.out.println("Wskaźniki wskazały trend wzrostowy ...");
                System.out.println("Żadna pozycja nie była do tej pory otwarta ...");
                System.out.println("Żadna pozycja nie została teraz zamknięta ...");
                System.out.println("Pozycja kupna została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(-1)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(1)) {
//            xtbHomePage.closePosition();
//            xtbHomePage.openSellPosition();
                System.out.println("Wskaźniki wskazały trend spadkowy ...");
                System.out.println("Pozycja kupna była do tej pory otwarta ...");
                System.out.println("Pozycja kupna została teraz zamknięta ...");
                System.out.println("Pozycja sprzedaży została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(0)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(1)) {
//            xtbHomePage.closePosition();
                System.out.println("Wskaźniki wskazały brak trendu ...");
                System.out.println("Pozycja kupna była do tej pory otwarta ...");
                System.out.println("Pozycja kupna została teraz zamknięta ...");
                System.out.println("Żadna pozycja nie została teraz otwarta ...\n");
            }
        } else if (investingPage.getIndicatorsStatesSummary().equals(1)) {
            reusableFeatures.openWebsite("https://xstation5.xtb.com/#/demo/loggedIn", XTB_PAGE.getExpectedPageTitle(), true);
            xtbLoginPage.login();
            if (xtbHomePage.getOpenPositionType().equals(1)) {
                System.out.println("Wskaźniki wskazały trend wzrostowy ...");
                System.out.println("Pozycja kupna była do tej pory otwarta ...");
                System.out.println("Żadna pozycja nie została teraz zamknięta ...");
                System.out.println("Żadna pozycja nie została teraz otwarta ...\n");
            }
        }
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}