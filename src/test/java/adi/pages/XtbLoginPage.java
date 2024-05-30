package adi.pages;

import adi.enums.ExpectedPageTitles;
import adi.enums.Parameters;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class XtbLoginPage {
    WebDriver driver;
    By login = By.xpath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[2]/div/div/form/div[1]/input");
    By password = By.xpath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[2]/div/div/form/div[2]/input");
    By loginButton = By.xpath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[2]/div/div/form/div[4]/input");

    public XtbLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        String actualTitle = driver.getTitle();
        String expectedTitle = ExpectedPageTitles.XTB_PAGE.getExpectedPageTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Parameters.WAIT_TIME.getParameter()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(login));
        driver.findElement(login).sendKeys("michalantczak@outlook.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        driver.findElement(password).sendKeys("Kudlatka@170188");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }
}