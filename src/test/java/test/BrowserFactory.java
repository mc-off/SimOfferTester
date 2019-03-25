package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;


public enum  BrowserFactory {
    chrome {
        public WebDriver create() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            return  new ChromeDriver(options);
        }
    },
    chrome_invisible {
        public WebDriver create() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--headless");
            return  new ChromeDriver(options);
        }
    },
    firefox {
        public WebDriver create() {
            //Disable login to console and redirect log to an external file
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./src/test/java/log");

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            return new FirefoxDriver(options);
        }
    },
    opera {
        public WebDriver create()
        {
            return new OperaDriver();
        }
    };

    public WebDriver create() {
        return null;
    }
}
