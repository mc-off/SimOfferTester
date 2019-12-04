package test;


import org.junit.*;
import static org.junit.Assert.*;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffSimOfferPage;


public class TestList extends BaseRunner {
    @Test
    public void testFioHint() {
        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        tinkoffSimOfferPage.printFioField("");

        assertEquals(tinkoffSimOfferPage.getFioHint(), "Как вас зовут? Не стесняйтесь");
    }

    @Test
    public void testPhoneHint() {
        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.open();

        tinkoffSimOfferPage.printPhoneField("");

        assertEquals(tinkoffSimOfferPage.getPhoneHint(),"Ой, вы забыли номер");
    }


    @Test
    public void testGoogleSearch() {

        GoogleMainPage googleMainPage = pagesFactory.googleMainPage;
        googleMainPage.open();

        googleMainPage.openSearchResultsPageByRequest("тинькофф мобайл тарифы");

        GoogleResultPage googleResultPage = pagesFactory.googleResults;
        googleResultPage.clickSearchResultsByLinkContains("https://www.tinkoff.ru/mobile-operator/tariffs/");

        TinkoffSimOfferPage tinkoffSimOfferPage = pagesFactory.tinkoffSimOfferPage;

        tinkoffSimOfferPage.switchToWindow("Тарифы");

        assertTrue(tinkoffSimOfferPage.isLoadedByTitleContains("Тарифы"));

        tinkoffSimOfferPage.switchToMainTab();

        googleResultPage.closeCurrentTab();

        tinkoffSimOfferPage.switchToWindow("Тарифы");

        assertTrue(tinkoffSimOfferPage.isLoadedByTitleContains("Тарифы"));

        assertTrue(tinkoffSimOfferPage.isLoadedByUrlContains("https://www.tinkoff.ru/mobile-operator/tariffs/"));

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

        tinkoffSimOfferPage.refresh();

        assertTrue(tinkoffSimOfferPage.isRegionEqualToTestRegion("Москва"));

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

        assertNotEquals(moscowDefault, krasnodarDefault);

        assertEquals(moscowMax, krasnodarMax);
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

        tinkoffSimOfferPage.printPostcodeField("123060");
        //Символ '/' точка, всё нормально
        //система обычный символ '.' читает как 'ю' при эспорте в поле
        //раскладка дело не меняла, комбинация alt+46 тоже
        //tinkoffSimOfferPage.printAreaField("г/ Москва");
        tinkoffSimOfferPage.printStreetField("проезд Волоколамский 1-й");
        tinkoffSimOfferPage.printBuildingField("1");

        assertTrue(tinkoffSimOfferPage.isOrderButtonClickabe());
    }

  /* @Test
    public void fileDownload() {
        try {
            TinkoffSimDocumentationPage tinkoffSimDocumentationPage = pagesFactory.tinkoffSimDocumentationPage;
            Counter counter = new Counter();
            tinkoffSimDocumentationPage.open();
            long numberOfFilesBefore = counter.numberOfFilesInDirectory(pagesFactory.downloadPath);

            tinkoffSimDocumentationPage.downloadAndWaitTillFileDownloading();

            long numberOfFilesAfter = counter.numberOfFilesInDirectory(pagesFactory.downloadPath);

            assertTrue(tinkoffSimDocumentationPage.equalityOfFilesInFolder(numberOfFilesBefore,numberOfFilesAfter));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }*/
}
