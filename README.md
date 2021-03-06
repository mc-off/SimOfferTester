# SimOfferTester

Набор тест кейсов дял формы заказа симкарты [Тинькофф Мобайл](https://www.tinkoff.ru/mobile-operator/tariffs/)

<h2>Тест кейсы:</h2>

**testFioHint** - проверка появления подскажки о неккоректном заполнении поля ФИО

**testPhoneHint** - проверка появления подскажки о неккоректном заполнении поля телефона

**testEmailHint** - проверка появления подскажки о неккоректном заполнении поля e-mail

**testGoogleSearch** - поиск сайта в интернете, проверка того, что открылся именно тот сайт

**regionChange** - проверка того, что регион не меняется при перезагрузке страницы и того, что у разных регионов разные мин. суммы, но одинаковые макс. суммы

**zeroTariffDelivery** - проверка того, что при выборе тарифа с оплатой 0 рублей кнопка доставки кликабельна.

**fileDownload** - проверка возможности загрузки файлов с сайта.

Все тест кейсы находятся в классе TestList.java

**Junit** *Before* и *After* вынесены в отдельный класс

<h2>Подготовка к запуску</h2>

1. Скачайте проект

В коммандной строке:

2. Установите стандартную директорию выполнения команд в папку с проектом (in Windows cd * *\SimOfferTester*)

3. Скачайте и установите PATH для [ChromeWebDriver](http://chromedriver.chromium.org/downloads)

4. Скачайте и установите PATH для [GeckoDriver (драйвер Firefox)](https://github.com/mozilla/geckodriver/)

5. Скачайте и установите PATH для [OperaDriver (драйвер Opera)](https://github.com/operasoftware/operachromiumdriver/releases)

<h2>Запуск</h2>

Для запуска всех тестов со стандартным браузером Chrome в коммандной строке выполните команду 
1. mvn test

Для выбора браузера для запуска используйте ключ 

2. -Dbrowser=(*имя_браузера*) verify 

Возможен выбор из chrome, chrome_invisible, firefox, opera

Установка дирректории для загрузки файлов. По умолчанию - "C:/downoloads//" Значение по умолчанию редактируется в классе Application в методе getDownloadPath()

3. -Dpath=(*дирректория*) verify

<h3>Многопоток и многопроцессорность</h3>

Редактировать параметры можно в файле **pom.xml** в плагине *maven-surefire-plugin*

<h3>Модули</h3>

Реализованы доп. модули для работы с элементами веб страницы.

Оптимизированы для работы на [главной страничке с тарифами](https://www.tinkoff.ru/mobile-operator/tariffs/)

**CheckBox** - устанавливает значения true или false в чек-боксах

**Select** - модуль взаимодействия со структурами *select*

**TextInput** - моудль взаимодействия с окнами ввода

<h3>PS</h3>

На Mac тест с гугл поиском может не проходить, иногда даже не помогает ручное обновление вкладки

Обязательно нужно менять стандартное место скачивания файлов на тот, что удобен вам, иначе тест не пройдет

Поддержка Firefox и Opera уже не осуществяется, просьба тестировать стандартным выбором из фабрики браузеров

Протестировано на MacOS и Windows 10 в последней версии Google Chrome

