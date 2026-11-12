package ru.maxidom.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.maxidom.pages.HomePage;
import ru.maxidom.pages.SearchResultsPage;


@DisplayName("UI-автотесты для сайта Максидома")
public class MaxidomUiTests extends TestBase {
    private final HomePage homePage = new HomePage();
    private static final String SEARCH_QUERY = "Шуруповерт";

    @Test
    @DisplayName("Поиск существующего товара возвращает релевантные результаты")
    void searchReturnsRelevantProducts() {
        homePage.openPage().header().searchFor(SEARCH_QUERY)
                .shouldHaveProducts(SEARCH_QUERY);
    }

    @Test
    @DisplayName("Несуществующий запрос показывает сообщение и пустую выдачу")
    void searchWithUnknownQueryShowsEmptyResults() {
        homePage.openPage().header().searchFor("zzzxqv987654321")
                .shouldHaveNoResults();
    }

    @Test
    @DisplayName("Из поиска открывается карточка именно выбранного товара")
    void searchResultOpensMatchingProduct() {
        SearchResultsPage results = homePage.openPage().header().searchFor(SEARCH_QUERY)
                .shouldHaveProducts(SEARCH_QUERY);
        String productName = results.firstProductName();
        results.openFirstProduct().shouldShowProduct(productName);
    }

    @Test
    @DisplayName("Выбранный товар появляется в списке сравнения")
    void productCanBeAddedToComparison() {
        SearchResultsPage results = homePage.openPage().header().searchFor(SEARCH_QUERY)
                .shouldHaveProducts(SEARCH_QUERY);
        String productName = results.firstProductName();
        results.addFirstProductToComparison().header().openComparison()
                .shouldContainProduct(productName);
    }

    @Test
    @DisplayName("Корзина нового посетителя пуста")
    void newVisitorHasEmptyCart() {
        homePage.openPage().header().openCart().shouldBeEmpty();
    }

    @Test
    @DisplayName("Из каталога можно перейти в категорию «Электроинструмент»")
    void catalogOpensPowerToolsCategory() {
        homePage.openPage().header().openCatalog()
                .openPowerTools().shouldShowPowerTools();
    }

    @Test
    @DisplayName("В форме входа можно переключиться с телефона на E-mail и обратно")
    void loginMethodCanBeSwitched() {
        homePage.openPage().header().openLogin()
                .shouldUsePhone()
                .chooseEmail().shouldUseEmail()
                .choosePhone().shouldUsePhone();
    }

    @Test
    @DisplayName("Показ и скрытие пароля сохраняют введённое значение")
    void passwordVisibilityCanBeToggled() {
        homePage.openPage().header().openLogin()
                .enterDemoPassword().shouldHidePassword()
                .togglePasswordVisibility().shouldRevealPassword()
                .togglePasswordVisibility().shouldHidePassword();
    }
}
