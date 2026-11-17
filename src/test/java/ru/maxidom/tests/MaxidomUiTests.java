package ru.maxidom.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.maxidom.pages.HomePage;
import ru.maxidom.pages.SearchResultsPage;
import ru.maxidom.testdata.testData;


@DisplayName("UI-автотесты для сайта Максидома")
public class MaxidomUiTests extends TestBase {
    private final HomePage homePage = new HomePage();

    @Test
    @DisplayName("Поиск существующего товара возвращает релевантные результаты")
    void searchReturnsRelevantProducts() {
        homePage.openPage()
                .header()
                .searchFor(testData.EXISTING_PRODUCT, testData.SEARCH_RESULTS_TITLE)
                .shouldHaveProducts(testData.EXISTING_PRODUCT);
    }

    @Test
    @DisplayName("Несуществующий запрос показывает сообщение и пустую выдачу")
    void searchWithUnknownQueryShowsEmptyResults() {
        homePage.openPage()
                .header()
                .searchFor(testData.UNKNOWN_QUERY, testData.SEARCH_RESULTS_TITLE)
                .shouldHaveNoResults(testData.NO_SEARCH_RESULTS);
    }

    @Test
    @DisplayName("Из поиска открывается карточка именно выбранного товара")
    void searchResultOpensMatchingProduct() {
        SearchResultsPage results = homePage.openPage()
                .header()
                .searchFor(testData.EXISTING_PRODUCT, testData.SEARCH_RESULTS_TITLE)
                .shouldHaveProducts(testData.EXISTING_PRODUCT);

        String productName = results.firstProductName();

        results.openFirstProduct()
                .shouldShowProduct(productName);
    }

    @Test
    @DisplayName("Выбранный товар появляется в списке сравнения")
    void productCanBeAddedToComparison() {
        SearchResultsPage results = homePage.openPage()
                .header()
                .searchFor(testData.EXISTING_PRODUCT, testData.SEARCH_RESULTS_TITLE)
                .shouldHaveProducts(testData.EXISTING_PRODUCT);

        String productName = results.firstProductName();

        results.addFirstProductToComparison()
                .header()
                .openComparison(testData.COMPARISON_LINK, testData.COMPARISON_TITLE)
                .shouldContainProduct(testData.COMPARISON_TITLE, productName, testData.EMPTY_COMPARISON);
    }

    @Test
    @DisplayName("Корзина нового посетителя пуста")
    void newVisitorHasEmptyCart() {
        homePage.openPage()
                .header()
                .openCart(testData.CART_LINK, testData.CART_TITLE)
                .shouldBeEmpty(testData.EMPTY_CART);
    }

    @Test
    @DisplayName("Из каталога можно перейти в категорию «Электроинструмент»")
    void catalogOpensPowerToolsCategory() {
        homePage.openPage()
                .header()
                .openCatalog(testData.CATALOG_TITLE)
                .openCategory(testData.POWER_TOOLS_NAME, testData.POWER_TOOLS_PATH)
                .shouldShowCategory(
                        testData.POWER_TOOLS_NAME,
                        testData.POWER_TOOLS_PATH,
                        testData.DRILLS_NAME,
                        testData.DRILLS_PATH
                );
    }

    @Test
    @DisplayName("В форме входа можно переключиться с телефона на E-mail и обратно")
    void loginMethodCanBeSwitched() {
        homePage.openPage()
                .header()
                .openLogin(testData.LOGIN_TITLE)
                .shouldShowLoginField(testData.PHONE_PLACEHOLDER, testData.EMAIL_PLACEHOLDER)
                .chooseLoginMethod(testData.EMAIL_TAB)
                .shouldShowLoginField(testData.EMAIL_PLACEHOLDER, testData.PHONE_PLACEHOLDER)
                .chooseLoginMethod(testData.PHONE_TAB)
                .shouldShowLoginField(testData.PHONE_PLACEHOLDER, testData.EMAIL_PLACEHOLDER);
    }

    @Test
    @DisplayName("Показ и скрытие пароля сохраняют введённое значение")
    void passwordVisibilityCanBeToggled() {
        homePage.openPage()
                .header()
                .openLogin(testData.LOGIN_TITLE)
                .enterPassword(testData.DEMO_PASSWORD)
                .shouldHidePassword(testData.DEMO_PASSWORD)
                .togglePasswordVisibility()
                .shouldRevealPassword(testData.DEMO_PASSWORD)
                .togglePasswordVisibility()
                .shouldHidePassword(testData.DEMO_PASSWORD);
    }
}
