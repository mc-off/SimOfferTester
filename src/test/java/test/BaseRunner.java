package test;

import modules.CheckBox;
import modules.Select;
import modules.TextInput;
import org.junit.After;
import org.junit.Before;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BaseRunner {

    WebDriver driver;
    CheckBox checkBox;
    Select select;
    TextInput textInput;
    private String browserName = System.getProperty("browser");
    String downloadPath = System.getProperty("path");

    @Before
    public void setUp() {
        initialize();
        getDownloadPath();
        driver = getDriver(downloadPath);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
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
    private void initialize()
    {
        checkBox = new CheckBox();
        select = new Select();
        textInput = new TextInput();
    }
    private WebDriver getDriver(String downloadPath) {
        try {
            BrowserFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            browserName = "chrome";
            System.setProperty("browser", browserName);
        }
        return BrowserFactory.valueOf(browserName).create(downloadPath);
    }
    WebElement xpath(String response){
        return driver.findElement(By.xpath(response));
    }
}