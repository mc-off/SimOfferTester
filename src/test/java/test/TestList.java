package test;


import org.junit.*;
import org.openqa.selenium.*;


public class TestList extends BaseRunner{

    @Test
    public void firstTest(){
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("Михайлов Михаил Михайлович");
        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).clear();
        driver.findElement(By.name("phone_mobile")).sendKeys("(900)800-70-00");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("ivan@domain.com");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Не имею гражданства РФ'])[2]/following::span[4]")).click();
        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
    }

    @Test
    public void testRegionSelect() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Да'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Выберите регион'])[1]/following::input[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[5]")).click();
    }

    @Test
    public void testFioHint() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::form[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'app-form-step__fio-field')]//div[contains(@class, 'ui-form-field-error-message')]")).getText();
    }

    @Test
    public void testPhoneHint() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_tel')]//div[contains(@class, 'ui-form-field-error-message')]")).getText();
    }

    @Test
    public void testEmailHint() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("gg");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ui-form__row_dropdownSuggest')]//div[contains(@class, 'ui-form-field-error-message')]")).getText();
        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("gg@");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ui-form__row_dropdownSuggest')]//div[contains(@class, 'ui-form-field-error-message')]")).getText();
        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("gg@ya");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ui-form__row_dropdownSuggest')]//div[contains(@class, 'ui-form-field-error-message')]")).getText();
        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("gg@ya.");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ui-form__row_dropdownSuggest')]//div[contains(@class, 'ui-form-field-error-message')]")).getText();
        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
    }
}
