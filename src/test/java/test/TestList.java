package test;


import org.junit.*;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffSimOfferPage;


public class TestList extends BaseRunner{
/*
    //CSS rewrite
    @Test
    public void firstTest(){
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        textInput.sendKeys(driver.findElement(By.cssSelector("input[name='fio']")),"Иван Иван Иван");
        if (!textInput.getElementString(driver.findElement
                (By.cssSelector("input[name='fio']"))).toLowerCase()
                .equals("Иван Иван Иван".toLowerCase()))
            fail("Fio field is invalid");

        textInput.sendKeys(driver.findElement(By.cssSelector("input[name='phone_mobile'")),"(900) 800-70-00");
        if (!textInput.getElementString(driver.findElement
                (By.cssSelector("input[name='phone_mobile'"))).toLowerCase()
                .equals("+7(900) 800-70-00".toLowerCase()))
            fail("Phone number field is invalid");

        textInput.sendKeys( driver.findElement(By.cssSelector("input[name='email'")),"ivan@domain.com");
        if (!textInput.getElementString(driver.findElement
                (By.cssSelector("input[name='email'"))).toLowerCase()
                .equals("ivan@domain.com".toLowerCase()))
            fail("Email field is invalid");

        driver.findElement(By.cssSelector("svg.ui-icon__svg-wrapper")).click();
    }

 */

    @Test
    public void testFioHint() {
        TinkoffSimOfferPage tinkoffSimOfferPage = app.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        tinkoffSimOfferPage.printFioField("");

        tinkoffSimOfferPage.hintFioEquality("Укажите ваше ФИО");

        tinkoffSimOfferPage.closeCurrentTab();
    }
/*
    @Test
    public void testPhoneHint() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        textInput.sendKeys(driver.findElement
                (By.cssSelector("input[name='phone_mobile'")),Keys.chord(Keys.ENTER));

        if (xpath("//div[contains(@class, 'ui-form__row ui-form__row_tel')]" +
                "//div[contains(@class, 'ui-form-field-error-message')]").getText()
                .equals("Необходимо указать номер телефона")){
            driver.close();
        }
        else {
            fail("Hints do not equal");
        }
    }

    @Test
    public void testEmailHint() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        textInput.sendKeys(driver.findElement
                (By.cssSelector("input[name='email'")),"gg" + Keys.chord(Keys.ENTER));

        if (xpath("//div[contains(@class,'ui-form__row_dropdownSuggest')]" +
                "//div[contains(@class, 'ui-form-field-error-message')]").getText()
                .equals("Введите корректный адрес эл. почты")){
            driver.close();
        }
        else {
            fail("Hints do not equal");
        }
    }
    @Test
    public void testGoogleSearch(){

        driver.get("https://www.google.ru/");

        WebElement searchElement = xpath("//input[contains(@title,'Поиск')]");

        textInput.sendKeys(searchElement, "тинькофф мобайл");

        searchElement.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOf(xpath("//ul[contains(@role,'listbox')]" +
                                "//span[.='тинькофф мобайл тарифы']")))
                .click();


        if (new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By
                        .xpath("//h3[contains(text(),'Тарифы Тинькофф Мобайла')]"))))
                .isDisplayed())
        {
            ((JavascriptExecutor)driver).executeScript("window.open()");

            ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());

            driver.switchTo().window(tabs.get(1));

            driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

            if ("Тарифы Тинькофф Мобайла".equals(driver.getTitle())) {

                driver.switchTo().window(tabs.get(0));

                driver.close();

                driver.switchTo().window(tabs.get(1));

                if (!"https://www.tinkoff.ru/mobile-operator/tariffs/".equals(driver.getCurrentUrl())) {
                    fail("Urls aren't equal");
                }
            }
            else
                fail("Title is incorrect");
        }
        else fail("Page doesn't show in Google");

        driver.close();
    }

    @Test

    public void regionChange(){
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        if (xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]")
                .isDisplayed())
        {
            if (xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]" +
                    "//span[contains(@class,'MvnoRegionConfirmation__regionName')]")
                    .getText()
                    .equals("Москва и Московская область?")) {
                xpath("//span[contains(text(),'Да')]").click();
            }
            else {
                xpath("//span[contains(text(),'Нет')]").click();
                xpath("//div[contains(@class,'MobileOperatorRegionsPopup__listPart')]" +
                        "//div[contains(text(),'Москва')]")
                        .click();
            }
        }
        else {
            fail();
        }

        String regionName = xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]")
                .getText();

        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        String newRegionName = xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]")
                .getText();

        if (!regionName.equals(newRegionName)) {
            fail("Reload region isn't equal to old region");
        }

        String moscowDefault = xpath("//div[@data-qa-file='MobileOperatorProductCalculator']" +
                "//h3")
                .getText();

        System.out.println(moscowDefault);

        String pathString = "Безлимитный интернет";

        select.setWebElement(xpath("//span[.='Интернет']"));
        select.chooseVariantFromList(pathString).click();
        if (!select.takeListValue(xpath("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+pathString+"')]"))
                .equals(pathString))
            fail("Internet value doesn't apply");


        pathString = "Безлимитные минуты";
        select.setWebElement(xpath("//span[.='Звонки']"));
        select.chooseVariantFromList(pathString).click();
        if (!select.takeListValue(xpath("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+pathString+"')]"))
                .equals(pathString))
            fail("Call minutes value doesn't apply");

        checkBox.setInactive(xpath("//div[contains(@class,'Checkbox')]"),"Режим модема");
        checkBox.setInactive(xpath("//div[contains(@class,'Checkbox')]"),"Безлимитные СМС");

        String moscowMax = xpath("//div[@data-qa-file='MobileOperatorProductCalculator']" +
                "//h3")
                .getText();

        System.out.println(moscowMax);

        xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]").click();

        xpath("//div[contains(@class,'MobileOperatorRegionsPopup__listPart')]" +
                "//div[contains(text(),'Краснодарский')]")
                .click();

        String krasnodarDefault = xpath("//div[@data-qa-file='MobileOperatorProductCalculator']" +
                "//h3")
                .getText();

        System.out.println(krasnodarDefault);

        pathString = "Безлимитный интернет";

        select.setWebElement(xpath("//span[.='Интернет']"));
        select.chooseVariantFromList(pathString).click();
        if (!select.takeListValue(xpath("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+pathString+"')]"))
                .equals(pathString))
            fail("Internet value doesn't apply");


        pathString = "Безлимитные минуты";

        select.setWebElement(xpath("//span[.='Звонки']"));
        select.chooseVariantFromList(pathString).click();
        if (!select.takeListValue(xpath("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+pathString+"')]"))
                .equals(pathString))
            fail("Call minutes value doesn't apply");

        checkBox.setInactive(xpath("//div[contains(@class,'Checkbox')]"),"Режим модема");
        checkBox.setInactive(xpath("//div[contains(@class,'Checkbox')]"),"Безлимитные СМС");

        String krasnodarMax = xpath("//div[@data-qa-file='MobileOperatorProductCalculator']//h3").getText();

        System.out.println(krasnodarMax);

        if (moscowDefault.equals(krasnodarDefault)){
            fail("Default prices are equal");
        }
        else {
            System.out.println("Success");
        }

        if (moscowMax.equals(krasnodarMax)){
            System.out.println("Success");
        }
        else {
            fail("Max prices not equal");
        }

        driver.close();
    }

    @Test
    public void zeroTariffDelivery() {

        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        if (xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]").isDisplayed())
        {
            if (xpath("//div[contains(@class,'MvnoRegionConfirmation__title')]" +
                    "//span[contains(@class,'MvnoRegionConfirmation__regionName')]")
                    .getText()
                    .equals("Москва и Московская область?")) {
                xpath("//span[contains(text(),'Да')]").click();
            }
            else {
                xpath("//span[contains(text(),'Нет')]").click();
                xpath("//div[contains(@class,'MobileOperatorRegionsPopup__listPart')]" +
                        "//div[contains(text(),'Москва')]")
                        .click();
            }
        }
        else {
            fail();
        }

        String pathString = "0 ГБ";
        select.setWebElement(xpath("//span[.='Интернет']"));
        select.chooseVariantFromList(pathString).click();
        if (!select.takeListValue(xpath("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+pathString+"')]"))
                .equals(pathString))
            fail("Internet value doesn't apply");


        pathString = "0 минут";
        select.setWebElement(xpath("//span[.='Звонки']"));
        select.chooseVariantFromList(pathString).click();
        if (!select.takeListValue(xpath("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+pathString+"')]"))
                .equals(pathString))
            fail("Call minutes value doesn't apply");

        checkBox.setInactive(xpath("//div[contains(@class,'Checkbox')]"),"Мессенджеры");

        checkBox.setInactive(xpath("//div[contains(@class,'Checkbox')]"),"Социальные сети");

        textInput.sendKeys(driver.findElement(By.name("fio")),"Иван Иван Иван");
        textInput.sendKeys(driver.findElement(By.name("phone_mobile")),"(900)800-70-00");
        textInput.sendKeys( driver.findElement(By.name("email")),"ivan@domain.com");

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(xpath("//button[contains(@class,'Button__button')]"))).click();

        textInput.sendKeys( driver.findElement(By.name("postal_code")),"108825");

        //Символ '/' точка, всё нормально
        //система обычный символ '.' читает как 'ю' при эспорте в поле
        //раскладка дело не меняла, комбинация alt+46 тоже
        textInput.sendKeys( driver.findElement(By.name("area")),"г/ Москва");

        textInput.sendKeys( driver.findElement(By.name("street")),"ул Дунайская");

        textInput.sendKeys( driver.findElement(By.name("building")),"1");

        if (new WebDriverWait(driver,10)
                .until(ExpectedConditions
                        .elementToBeClickable(xpath("//div[contains(@class,'UIAppointmentForm__button')]"))).isDisplayed())
            driver.close();
        else
            fail("Final button not clickable");
    }
    @Test
    public void fileDownload() throws  Exception
    {
        driver.get("https://www.tinkoff.ru/mobile-operator/documents/");
        long numberOfFilesBefore = numberOfFilesInDirectory(downloadPath);
        xpath("//a[contains(@href,'first-month-free')]").click();
        Thread.sleep(5000);
        long numberOfFilesAfter = numberOfFilesInDirectory(downloadPath);
        if (numberOfFilesAfter==numberOfFilesBefore)
            fail("Do not download");
        else
            driver.close();
    }

    private long numberOfFilesInDirectory(String path) {
        long count;
        try (Stream<Path> files = Files.list(Paths.get(path))) {
            count = files.count();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return 0;
        }
        return count;
    }
    */
}
