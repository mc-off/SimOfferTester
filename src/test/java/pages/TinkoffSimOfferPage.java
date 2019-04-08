package pages;

import modules.CheckBox;
import modules.Select;
import modules.TextInput;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                        WebElement inputField = xpathWebElement("//input[contains(@name,'fio')]");
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString +  Keys.chord(Keys.ENTER));
                    if (!textInput.getElementString(inputField).equals(testString)){
                        logger.error("Значение в поле ФИО не соответствует изначально положенному " + testString);
                    }
        return true;
                });
    }

    public void hintFioEquality(String hintString) {
        if (xpathWebElement("//div[contains(@class, 'app-form-step__fio-field')]" +
                "//div[contains(@class, 'ui-form-field-error-message')]").getText()
                .equals(hintString)) {
            logger.info("Fio hint equal to " + hintString);
        }
        else {
            logger.error("Fio hint does not equal to " + hintString);
        }
    }

    public void printEmailField(String testString) {
        logger.info("Ввожу в поле ФИО текст " +  testString);
        WebElement inputField = xpathWebElement("//input[@name='email']");
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString +  Keys.chord(Keys.ENTER));
        if (!textInput.getElementString(inputField).equals(testString)){
            logger.error("Значение в поле Email не соответствует изначально положенному " + testString);
        }

    }

    public void hintEmailEquality(String hintString) {
        wait.until(d -> {
            boolean match = false;
            System.out.println(xpathWebElement("//div[contains(@class,'ui-form__row_dropdownSuggest')]" +
                    "//div[contains(@class, 'ui-form-field-error-message')]").getText());
            if (xpathWebElement("//div[contains(@class,'ui-form__row_dropdownSuggest')]" +
                    "//div[contains(@class, 'ui-form-field-error-message')]").getText()
                    .equals(hintString)) {
                logger.info("Email hint equal to " + hintString);
                match = true;
            }
            return match;
        });
    }

    public void printPhoneField(String testString) {
        logger.info("Ввожу в поле Phone текст " +  testString);
        WebElement inputField = xpathWebElement("//input[@name='phone_mobile']");
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString +  Keys.chord(Keys.ENTER));
        if (!textInput.getElementString(inputField).equals("+7(" +testString)){
            logger.error("Значение в поле Phone не соответствует изначально положенному " + testString);
        }

    }

    public void hintPhoneEquality(String hintString) {
        wait.until(d -> {
            boolean match = false;
            if (xpathWebElement("//div[contains(@class, 'ui-form__row ui-form__row_tel')]" +
                    "//div[contains(@class, 'ui-form-field-error-message')]").getText()
                    .equals(hintString)) {
                logger.info("Phone hint equal to " + hintString);
                match = true;
            } else {
                logger.error("Phone hint does not equal to " + hintString);
            }
            return match;
        });
    }

    public void printPostcodeField(String testString) {
        logger.info("Ввожу в поле Postcode текст " +  testString);
        WebElement inputField = xpathWebElement("//input[contains(@name,'postal_code')]");
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString);

    }

    public void printAreaField(String testString) {
        logger.info("Ввожу в поле Area текст " +  testString);
        driver.findElement(By.name("area")).click();
        WebElement inputField = driver.findElement(By.name("area"));
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString);

    }

    public void printStreetField(String testString) {
        logger.info("Ввожу в поле Street текст " +  testString);
        WebElement inputField = driver.findElement(By.name("street"));
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString);
    }

    public void printBuildingField(String testString) {
        logger.info("Ввожу в поле Building текст " +  testString);
        WebElement inputField = xpathWebElement("//input[contains(@name,'building')]");
        TextInput textInput = new TextInput();
        textInput.sendKeys(inputField,testString +  Keys.chord(Keys.ENTER));
    }

    public void testForLoading(){
        logger.info("Test for loading");
        wait.until(ExpectedConditions.elementToBeClickable(xpathWebElement("//input[contains(@name,'fio')]")))
                .isDisplayed();
    }

    private String getFirstRegionTitle(){
        wait
                .until(ExpectedConditions.visibilityOf(xpathWebElement("//div[contains(@class,'MvnoRegionConfirmation__title')]")));
        return xpathWebElement("//div[contains(@class,'MvnoRegionConfirmation__title')]" +
                "//span[contains(@class,'MvnoRegionConfirmation__regionName')]").getText();
    }

    public boolean isFirstRegionMoscow() {
        wait
                .until(ExpectedConditions.visibilityOf(xpathWebElement("//div[contains(@class,'MvnoRegionConfirmation__title')]")));
        return getFirstRegionTitle().contains("Москва");
    }

    public void clickToFirstRegionHint(String button) {
        logger.info("Нажимаю на кнопку"+ button+ "в подсказке об выборе изначального региона");
        xpathWebElement("//span[contains(text(),'"+button+"')]").click();
    }

    public void selectRegionFromList(String regionName) {
        logger.info("Выбираю регион " + regionName + " из списка");
        xpathWebElement("//div[contains(@class,'MobileOperatorRegionsPopup__listPart')]" +
                "//div[contains(text(),'" +regionName+"')]")
                .click();
    }

    public boolean isRegionEqualToTestRegion(String testRegion){
        return xpathWebElement("//div[contains(@class,'MvnoRegionConfirmation__title')]")
                .getText().contains(testRegion);
    }
    public String getTariffPrice(){
        String price = xpathWebElement("//div[@data-qa-file='MobileOperatorProductCalculator']" +
                "//h3")
                .getText();
        logger.info("Цена тарифа " + price);
        return  price;
    }

    public void setInternetProperty(String internetProperty){
        logger.info("Устанавливаю в поле значения интернет трафика значение "+internetProperty);
        Select select = new Select();
        select.setWebElement(xpathWebElement("//span[.='Интернет']"));
        select.chooseVariantFromList(internetProperty).click();
        if (!select.takeListValue(xpathWebElement("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+internetProperty+"')]"))
                .equals(internetProperty))
            logger.error("Не удается установить макс значение в списке выбора ГБ интернета");
    }

    public void setPhoneProperty(String phoneProperty){
        logger.info("Устанавливаю в поле значения минус звонков значение "+phoneProperty);
        Select select = new Select();
        select.setWebElement(xpathWebElement("//span[.='Звонки']"));
        select.chooseVariantFromList(phoneProperty).click();
        if (!select.takeListValue(xpathWebElement("//span[contains(@class,'ui-select__title-flex-text')" +
                "and contains(text(),'"+phoneProperty+"')]"))
                .equals(phoneProperty))
            logger.error("Не удается установить макс значение в списке выбора минут звонков");
    }

    public void setCheckBoxEnabled(String checkBoxLabel){
        logger.info("Активирую чек-бокс "+checkBoxLabel);
        CheckBox checkBox = new CheckBox();
        checkBox.setActive(xpathWebElement("//div[contains(@class,'Checkbox')]"),checkBoxLabel);
    }

    public void setCheckBoxDisabled(String checkBoxLabel){
        logger.info("Деактивирую чек-бокс "+checkBoxLabel);
        CheckBox checkBox = new CheckBox();
        checkBox.setInactive(xpathWebElement("//div[contains(@class,'Checkbox')]"),checkBoxLabel);
    }

    public void openRegionSelectList(){
        logger.info("Открываю список выбора региона (не первоначальный");
        xpathWebElement("//div[contains(@class,'MvnoRegionConfirmation__title')]").click();
    }

    public void clickOfferButton(){
        wait
                .until(ExpectedConditions
                        .elementToBeClickable(xpathWebElement("//button[contains(@class,'Button__button')]")))
                .click();
        logger.info("Нажимаю на кнопку Заказать сим-карту");
    }

    public boolean isOrderButtonClickabe(){
       wait .until(ExpectedConditions
                .elementToBeClickable(xpathWebElement("//div[contains(@class,'UIAppointmentForm__button')]")));
       logger.info("проверяю, кликабельна ли кнопка Заказать доставку");
       return true;
    }
}






