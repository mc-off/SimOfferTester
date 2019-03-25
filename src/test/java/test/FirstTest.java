package test;


import org.junit.*;
import org.openqa.selenium.*;


public class FirstTest extends BaseRunner{

    @Test
    public void testFirst() {
        String baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        driver.get(baseUrl);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Да'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[5]")).click();
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("Алексеев Алекс Алексеевич");
        driver.findElement(By.name("fio")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("fio")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("fio")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).clear();
        driver.findElement(By.name("phone_mobile")).sendKeys("9008007766");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("ivan@domin.ru");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Не имею гражданства РФ'])[2]/following::span[4]")).click();
        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Заказать сим-карту'])[1]/following::div[2]")).click();
    }

}
