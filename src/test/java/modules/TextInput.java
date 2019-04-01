package modules;

import org.openqa.selenium.*;

public class TextInput{

    public void sendKeys(WebElement webElement, String outputString)
    {
        clear(webElement);
        webElement.sendKeys(outputString);
    }

    private void clear(WebElement webElement)
    {
        webElement.clear();
    }

    public String getElementString(WebElement webElement)
    {
        return webElement.getAttribute("value");
    }

}
