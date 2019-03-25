package test;

import org.junit.After;
import org.junit.Before;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class BaseRunner {

    WebDriver driver;
    String browserName = System.getProperty("browser");

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver() {
        try {
            BrowserFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            browserName = "chrome";
            System.setProperty("browser", browserName);
        }
        return BrowserFactory.valueOf(browserName).create();
    }
}