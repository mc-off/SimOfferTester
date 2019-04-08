package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultPage extends Page {
    public GoogleResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickSearchResultsByLinkContains(String link){
        wait.until(d -> xpathSearcherByText(link).size() > 0);
        xpathSearcherByText(link).get(0).click();
    }
}
