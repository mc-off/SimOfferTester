package app;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffSimDocumentationPage;
import pages.TinkoffSimOfferPage;
import test.BrowsersFactory;
import java.util.concurrent.TimeUnit;



public class PagesFactory {

    public WebDriverWait wait;
    private WebDriver driver;
    public GoogleMainPage googleMainPage;
    public GoogleResultPage googleResults;
    public TinkoffSimOfferPage tinkoffSimOfferPage;
    public TinkoffSimDocumentationPage tinkoffSimDocumentationPage;
    private final String browserName = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
    public String downloadPath = System.getProperty("path");


    public PagesFactory(){
        getDownloadPath();
        //driver
        driver = new EventFiringWebDriver(getDriver());
        ((EventFiringWebDriver) driver).register(new BrowsersFactory.MyListener());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        //page
        googleMainPage = new GoogleMainPage(driver);
        googleResults = new GoogleResultPage(driver);
        tinkoffSimOfferPage = new TinkoffSimOfferPage(driver);
        tinkoffSimDocumentationPage = new TinkoffSimDocumentationPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    private void getDownloadPath()
    {
        try {
            if (System.getProperty("path").equals(""))
                throw new NullPointerException();
        }
        catch (NullPointerException | IllegalArgumentException e)
        {
            downloadPath = "C:\\downloads\\\\";
            System.setProperty("path", downloadPath);
        }
    }

    private WebDriver getDriver(){
        return BrowsersFactory.buildDriver(browserName, downloadPath);
    }

}
