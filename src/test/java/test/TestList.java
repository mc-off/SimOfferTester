package test;


import functions.Counter;
import org.junit.*;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffSimDocumentationPage;
import pages.TinkoffSimOfferPage;

import java.io.IOException;


public class TestList extends BaseRunner {
    @Test
    public void testFioHint() {
        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        tinkoffSimOfferPage.printFioField("");

        tinkoffSimOfferPage.hintFioEquality("Укажите ваше ФИО");
    }

    @Test
    public void testPhoneHint() {
        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        tinkoffSimOfferPage.printPhoneField("");

        tinkoffSimOfferPage.hintPhoneEquality("Необходимо указать номер телефона");
    }

    @Test
    public void testEmailHint() {

        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        tinkoffSimOfferPage.printEmailField("godEmail");

        tinkoffSimOfferPage.hintEmailEquality("Введите корректный адрес эл. почты");
    }

    @Test
    public void testGoogleSearch() {

        GoogleMainPage googleMainPage = pagesFactory.googleMainPage;
        googleMainPage.open();

        googleMainPage.openSearchResultsPageByRequest("тинькофф мобайл тарифы");

        GoogleResultPage googleResultPage = pagesFactory.googleResults;
        googleResultPage.clickSearchResultsByLinkContains("https://www.tinkoff.ru/mobile-operator/tariffs/");

        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.switchToWindow("Тарифы Тинькофф Мобайла");

        if (tinkoffSimOfferPage.isLoadedByTitleContains("Тарифы Тинькофф Мобайла"))
            tinkoffSimOfferPage.switchToMainTab();
        googleResultPage.closeCurrentTab();

        tinkoffSimOfferPage.switchToWindow("Тарифы Тинькофф Мобайла");

        if (tinkoffSimOfferPage.isLoadedByTitleContains("Тарифы Тинькофф Мобайла")) {

            tinkoffSimOfferPage.isLoadedByUrlContains("https://www.tinkoff.ru/mobile-operator/tariffs/");
        }
    }

    @Test

    public void regionChange() {
        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        if (tinkoffSimOfferPage.isItFirstConnect()) {
            if (tinkoffSimOfferPage.isFirstRegionMoscow()) {
                tinkoffSimOfferPage.clickToFirstRegionHint("Да");
            } else {
                tinkoffSimOfferPage.clickToFirstRegionHint("Нет");
                tinkoffSimOfferPage.selectRegionFromList("Москва");
            }
        }
        else{
            if (tinkoffSimOfferPage.isRegionEqualToTestRegion("Москва")){
                tinkoffSimOfferPage.openRegionSelectList();
                tinkoffSimOfferPage.selectRegionFromList("Москва");
            }
        }

        tinkoffSimOfferPage.open();

        if (tinkoffSimOfferPage.isRegionEqualToTestRegion("Москва")) {
            String moscowDefault = tinkoffSimOfferPage.getTariffPrice();

            tinkoffSimOfferPage.setInternetProperty("Безлимитный интернет");
            tinkoffSimOfferPage.setPhoneProperty("Безлимитные минуты");
            tinkoffSimOfferPage.setCheckBoxEnabled("Режим модема");
            tinkoffSimOfferPage.setCheckBoxEnabled("Безлимитные СМС");

            String moscowMax = tinkoffSimOfferPage.getTariffPrice();

            tinkoffSimOfferPage.openRegionSelectList();
            tinkoffSimOfferPage.selectRegionFromList("Краснодарский");

            String krasnodarDefault = tinkoffSimOfferPage.getTariffPrice();

            tinkoffSimOfferPage.setInternetProperty("Безлимитный интернет");
            tinkoffSimOfferPage.setPhoneProperty("Безлимитные минуты");
            tinkoffSimOfferPage.setCheckBoxEnabled("Режим модема");
            tinkoffSimOfferPage.setCheckBoxEnabled("Безлимитные СМС");

            String krasnodarMax = tinkoffSimOfferPage.getTariffPrice();

            tinkoffSimOfferPage.equalityOfTariffPrices((!moscowDefault.equals(krasnodarDefault)),(moscowMax.equals(krasnodarMax)));
        }
    }

    @Test
    public void zeroTariffDelivery() {
        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;
        tinkoffSimOfferPage.open();

        if (tinkoffSimOfferPage.isFirstRegionMoscow()) {
            tinkoffSimOfferPage.clickToFirstRegionHint("Да");
        } else {
            tinkoffSimOfferPage.clickToFirstRegionHint("Нет");
            tinkoffSimOfferPage.selectRegionFromList("Москва");
        }

        tinkoffSimOfferPage.setInternetProperty("0 ГБ");
        tinkoffSimOfferPage.setPhoneProperty("0 минут");

        tinkoffSimOfferPage.setCheckBoxDisabled("Мессенджеры");
        tinkoffSimOfferPage.setCheckBoxDisabled("Социальные сети");

        tinkoffSimOfferPage.printFioField("Иван Иван Иван");
        tinkoffSimOfferPage.printPhoneField("(900)800-70-00");
        tinkoffSimOfferPage.printEmailField("ivan@domain.com");

        tinkoffSimOfferPage.clickOfferButton();

        tinkoffSimOfferPage.printPostcodeField("108825");
        //Символ '/' точка, всё нормально
        //система обычный символ '.' читает как 'ю' при эспорте в поле
        //раскладка дело не меняла, комбинация alt+46 тоже
        tinkoffSimOfferPage.printAreaField("г/ Москва");
        tinkoffSimOfferPage.printStreetField("ул Дунайская");
        tinkoffSimOfferPage.printBuildingField("1");

        tinkoffSimOfferPage.isOrderButtonClickabe();
    }

   @Test
    public void fileDownload() {
        try {
            TinkoffSimDocumentationPage tinkoffSimDocumentationPage = pagesFactory.tinkoffSimDocumentationPage;
            Counter counter = new Counter();
            tinkoffSimDocumentationPage.open();
            long numberOfFilesBefore = counter.numberOfFilesInDirectory(pagesFactory.downloadPath);

            tinkoffSimDocumentationPage.downloadAndWaitTillFileDownloading();

            long numberOfFilesAfter = counter.numberOfFilesInDirectory(pagesFactory.downloadPath);
            tinkoffSimDocumentationPage.equalityOfFilesInFolder(numberOfFilesBefore,numberOfFilesAfter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
