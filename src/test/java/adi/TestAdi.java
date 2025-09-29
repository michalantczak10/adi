package adi;

import adi.pages.XtbHomePage;
import adi.pages.XtbLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestAdi {

    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void testJusti() {
        XtbLoginPage xtbLoginPage = new XtbLoginPage(driver);
        XtbHomePage xtbHomePage = new XtbHomePage(driver);
        tradeStrategy(xtbLoginPage, xtbHomePage);
        quitDriver();
    }

    public void tradeStrategy(XtbLoginPage xtbLoginPage, XtbHomePage xtbHomePage) {
        xtbLoginPage.login();
        xtbHomePage.selectAccount("DEMO");
        xtbHomePage.selectSymbol("SOLANA");
        xtbHomePage.selectInterval("MN");
        if (xtbHomePage.checkBollingerBandsTrend() == -1 && xtbHomePage.getOpenPositionType().equals(-1)) {
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY TREND SPADKOWY ...");
            System.out.println("POZYCJA KUPNA NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == 0 && xtbHomePage.getOpenPositionType().equals(-1)) {
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY BRAK WYRAŹNEGO TRENDU ...");
            System.out.println("POZYCJA KUPNA NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == 1 && xtbHomePage.getOpenPositionType().equals(-1)) {
            xtbHomePage.closePosition();
            xtbHomePage.openBuyPosition();
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY TREND WZROSTOWY ...");
            System.out.println("POZYCJA KUPNA NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == -1 && xtbHomePage.getOpenPositionType().equals(0)) {
            xtbHomePage.openSellPosition();
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY TREND SPADKOWY ...");
            System.out.println("POZYCJA KUPNA NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == 0 && xtbHomePage.getOpenPositionType().equals(0)) {
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY BRAK WYRAŹNEGO TRENDU ...");
            System.out.println("POZYCJA KUPNA NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == 1 && xtbHomePage.getOpenPositionType().equals(0)) {
            xtbHomePage.openBuyPosition();
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY TREND WZROSTOWY ...");
            System.out.println("POZYCJA KUPNA NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == -1 && xtbHomePage.getOpenPositionType().equals(1)) {
            xtbHomePage.closePosition();
            xtbHomePage.openSellPosition();
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY TREND SPADKOWY ...");
            System.out.println("POZYCJA KUPNA BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == 0 && xtbHomePage.getOpenPositionType().equals(1)) {
            xtbHomePage.closePosition();
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY BRAK WYRAŹNEGO TRENDU ...");
            System.out.println("POZYCJA KUPNA BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        } else if (xtbHomePage.checkBollingerBandsTrend() == 1 && xtbHomePage.getOpenPositionType().equals(1)) {
            System.out.println("BOLLINGER(20, 2.5):");
            System.out.println("AKTUALNA CENA ZAMKNIĘCIA: " + xtbHomePage.getCurrentClosePriceValue());
            System.out.println("WARTOŚĆ GÓRNEJ WSTĘGI: " + xtbHomePage.getUpperBandValue());
            System.out.println("WARTOŚĆ ŚRODKOWEJ WSTĘGI: " + xtbHomePage.getMiddleBandValue());
            System.out.println("WARTOŚĆ DOLNEJ WSTĘGI: " + xtbHomePage.getLowerBandValue());
            System.out.println();
            System.out.println("WSKAŹNIKI WSKAZAŁY TREND WZROSTOWY ...");
            System.out.println("POZYCJA KUPNA BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE BYŁA DO TEJ PORY OTWARTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ ZAMKNIĘTA ...");
            System.out.println("POZYCJA KUPNA NIE ZOSTAŁA TERAZ OTWARTA ...");
            System.out.println("POZYCJA SPRZEDAŻY NIE ZOSTAŁA TERAZ OTWARTA ...\n");
        }
    }

    @After
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}