# SimOfferTester

Набор тест кейсов дял формы заказа симкарты [Тинькофф Мобайл](https://www.tinkoff.ru/mobile-operator/tariffs/)

<h2>Тест кейсы:</h2>

**firstTest** - успешный заказ сим-карты, регион устанавливается по умолчанию

**testRegionSelect** - выбор региона из выпадающего списка

**testFioHint** - проверка появления подскажки о неккоректном заполнении поля ФИО

**testPhoneHint** - проверка появления подскажки о неккоректном заполнении поля телефона

**testEmailHint** - проверка появления подскажки о неккоректном заполнении поля e-mail

Все тест кейсы находятся в классе TestList.java

**Junit** *Before* и *After* вынесены в отдельный класс

<h2>Подготовка к запуску</h2>

1. Скачайте проект

В коммандной строке:

2. Установите станлартную директорию выполнения команд в папку с проектом (in Windows cd * *\SimOfferTester*)

3. Скачайте и установите PATH для [ChromeWebDriver](http://chromedriver.chromium.org/downloads)

4. Скачайте и установите PATH для [GeckoDriver (драйвер Firefox)](https://github.com/mozilla/geckodriver/)

5. Скачайте и установите PATH для [OperaDriver (драйвер Opera)](https://github.com/operasoftware/operachromiumdriver/releases)

<h2>Запуск</h2>

Для запуска всех тестов со стандартным браузером Chrome в коммандной строке выполните команду 
1. mvn test

Для выбора браузера для запуска используйте ключ 

2. -Dbrowser=(*имя_браузера*) verify 

Возможен выбор из chrome, chrome_invisible, firefox, opera

<h3>Многопоток и многопроцессорность</h3>

Редактировать параметры можно в файле **pom.xml** в плагине *maven-surefire-plugin*
