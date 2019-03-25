package test;


import org.junit.*;
import org.openqa.selenium.*;


public class FirstTest extends BaseRunner{

    @Test
    public void testUntitledTestCase(){
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
}
