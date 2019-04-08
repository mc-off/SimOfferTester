package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TinkoffSimDocumentationPage extends Page {
    public TinkoffSimDocumentationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void open(){
        driver.get("https://www.tinkoff.ru/mobile-operator/documents/");
        logger.info("Перехожу по ссылке https://www.tinkoff.ru/mobile-operator/documents/");
    }
    public void downloadAndWaitTillFileDownloading() throws InterruptedException{
        logger.info("Try to download first file");
        xpathWebElement("//a[contains(@href,'first-month-free')]").click();
        Thread.sleep(5000);
        logger.info("Sleep is over");
    }
    public void equalityOfFilesInFolder(Long before, Long after){
        wait
                .until(d -> {
                    return (before<after);
                });
    }
}
