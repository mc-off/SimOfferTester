package pages;

import modules.TextInput;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TinkoffSimOfferPage extends Page {
    public TinkoffSimOfferPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void open() {
        logger.info("Перехожу по адрессу https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        if (!isLoadedByTitleContains("Тарифы")){
            logger.error("Заголовок страницы не соответствует заявленному");
        }
    }

    public void printFioField(String testString) {
        logger.info("Ввожу в поле ФИО текст " +  testString);
        WebElement inputField = xpath("//input[contains(@name,'fio')]");
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString +  Keys.chord(Keys.ENTER));
        if (!textInput.getElementString(inputField).equals(testString)){
            logger.error("Значение в поле ФИО не соответствует изначально положенному " + testString);
        }

    }

    public void hintFioEquality(String hintString) {
        if (xpath("//div[contains(@class, 'app-form-step__fio-field')]" +
                "//div[contains(@class, 'ui-form-field-error-message')]").getText()
                .equals(hintString)) {
            logger.info("Fio hint equal to " + hintString);
        }
        else {
            logger.error("Fio hint does not equal to " + hintString);
        }
    }


}
