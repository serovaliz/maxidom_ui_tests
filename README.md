# UI-автотесты для сайта Максидома в рамках обучения автоматизации

8 UI-тестов для https://www.maxidom.ru/ с применением паттерна Page Object 

## Стек

- Java
- Gradle
- Selenide
- Allure
- JUnit 5

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
