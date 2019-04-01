package modules;

import org.openqa.selenium.*;

public class Select {

   private WebElement webElement;

    public Select() {}

    public WebElement chooseVariantFromList(String testString) {
        openList(webElement);
        return webElement.findElement(By.xpath("//span[text()='"+testString+"']"));
    }


    private void openList(WebElement webElement) {
        webElement.click();
    }

    public String takeListValue(WebElement webElement) {
        return webElement.getText();
    }

    public void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }
}
