package app;

import modules.CheckBox;
import modules.Select;
import modules.TextInput;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffSimOfferPage;
import test.BrowsersFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;



public class Application {


    Logger logger = LoggerFactory.getLogger(Application.class);
    private WebDriverWait wait;
    private WebDriver driver;
    public GoogleMainPage googleMainPage;
    public GoogleResultPage googleResults;
    public TinkoffSimOfferPage tinkoffSimOfferPage;
    CheckBox checkBox;
    Select select;
    TextInput textInput;
    public final String browserName = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
    String downloadPath = System.getProperty("path");


    public Application() throws MalformedURLException {
        initialize();
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
    }

    public void quit() {
        driver.quit();
        driver = null;
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
    private void initialize() {
        checkBox = new CheckBox();
        select = new Select();
        textInput = new TextInput();
    }

    private WebDriver getDriver() throws MalformedURLException {
        return BrowsersFactory.buildDriver(browserName, downloadPath);
    }

}
