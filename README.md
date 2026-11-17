# UI-автотесты для сайта Максидома в рамках обучения автоматизации

8 UI-тестов для https://www.maxidom.ru/ с применением паттерна Page Object 

## Стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="media/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="media/logo/Java.svg">
<img width="6%" title="Selenide" src="media/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="media/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="media/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="media/logo/JUnit5.svg">
<img width="6%" title="Jenkins" src="media/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="media/logo/Telegram.svg">
</p>

## Сценарии

| Тест | Проверка |
| --- | --- |
| `searchReturnsRelevantProducts` | Поиск «шуруповерт»: непустая выдача и релевантное название товара |
| `searchWithUnknownQueryShowsEmptyResults` | Сообщение об отсутствии результатов и отсутствие карточек выдачи |
| `searchResultOpensMatchingProduct` | Переход в карточку, соответствие названия выбранному товару и видимая фотография |
| `productCanBeAddedToComparison` | Счётчик сравнения и наличие выбранного товара в таблице сравнения |
| `newVisitorHasEmptyCart` | Переход в корзину и сообщение о пустой корзине |
| `catalogOpensPowerToolsCategory` | Переход из каталога в «Электроинструмент», заголовок и подраздел «Дрели» |
| `loginMethodCanBeSwitched` | Переключение полей телефона и почты в форме входа в обе стороны |
| `passwordVisibilityCanBeToggled` | Показ/скрытие пароля с сохранением введённого значения |


## <img width="4%" style="vertical-align:middle" title="Jenkins" src="media/logo/Jenkins.svg"> Сборка в Jenkins

Для запуска сборки необходимо перейти в раздел <code>Собрать с параметрами</code> и нажать кнопку <code>Собрать</code>.
<p align="center">

[Ссылка на сборку Jenkins](https://jenkins.qa.guru/job/jenkins_ui_tests_serova_es/)
<img title="Jenkins Build" src="media/screens/jenkins.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure-Report" src="media/logo/Allure_Report.svg"> Пример Allure-Report

<p align="center">

[Ссылка на Allure-отчёт](https://jenkins.qa.guru/job/jenkins_ui_tests_serova_es/24/allure/)
<img title="Allure-отчёт" src="media/screens/Allure.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logo/Telegram.svg"> Пример уведомления в Telegram

<p align="center">

[Ссылка на Telegram-канал](https://t.me/forhomeworksqa)
<p align="center">
</p>
<img title="TG notifications" src="media/screens/TGnotif.png">

## <img width="4%" style="vertical-align:middle" title="Selenoid" src="media/logo/Selenoid.svg"> Пример запуска в Selenoid

<p align="center">
</p>
<img title="Selenoid video" src="media/screens/873b9334287f7c3065fb91cb53eb20a1.gif">

