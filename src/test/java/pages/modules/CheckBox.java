package pages.modules;

import org.openqa.selenium.*;

public class CheckBox {

    public void setActive(WebElement webElement, String testString) {
        WebElement checkBox = webElement
                .findElement(By.xpath("//span[contains(text(),'" + testString +"')]"));
        if (checkBox.isEnabled()){
            checkBox.click();
        }
    }
    public void setInactive(WebElement webElement, String testString) {
        WebElement checkBox = webElement
                .findElement(By.xpath("//span[contains(text(),'" + testString +"')]"));
        if (checkBox.isEnabled()) {
            checkBox.click();
        }
    }
}
