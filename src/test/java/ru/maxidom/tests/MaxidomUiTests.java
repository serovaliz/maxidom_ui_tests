package ru.maxidom.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.maxidom.pages.HomePage;
import ru.maxidom.pages.SearchResultsPage;
import ru.maxidom.testdata.TestData;


@DisplayName("UI-автотесты для сайта Максидома")
public class MaxidomUiTests extends TestBase {
    private final HomePage homePage = new HomePage();

    @Test
    @DisplayName("Поиск существующего товара возвращает релевантные результаты")
    void searchReturnsRelevantProducts() {
        homePage.openPage()
                .header()
                .searchFor(TestData.EXISTING_PRODUCT, TestData.SEARCH_RESULTS_TITLE)
                .shouldHaveProducts(TestData.EXISTING_PRODUCT);
    }

    @Test
    @DisplayName("Несуществующий запрос показывает сообщение и пустую выдачу")
    void searchWithUnknownQueryShowsEmptyResults() {
        homePage.openPage()
                .header()
                .searchFor(TestData.UNKNOWN_QUERY, TestData.SEARCH_RESULTS_TITLE)
                .shouldHaveNoResults(TestData.NO_SEARCH_RESULTS);
    }

    @Test
    @DisplayName("Из поиска открывается карточка именно выбранного товара")
    void searchResultOpensMatchingProduct() {
        SearchResultsPage results = homePage.openPage()
                .header()
                .searchFor(TestData.EXISTING_PRODUCT, TestData.SEARCH_RESULTS_TITLE)
                .shouldHaveProducts(TestData.EXISTING_PRODUCT);

        String productName = results.firstProductName();

        results.openFirstProduct()
                .shouldShowProduct(productName);
    }

    @Test
    @DisplayName("Выбранный товар появляется в списке сравнения")
    void productCanBeAddedToComparison() {
        SearchResultsPage results = homePage.openPage()
                .header()
                .searchFor(TestData.EXISTING_PRODUCT, TestData.SEARCH_RESULTS_TITLE)
                .shouldHaveProducts(TestData.EXISTING_PRODUCT);

        String productName = results.firstProductName();

        results.addFirstProductToComparison()
                .header()
                .openComparison(TestData.COMPARISON_LINK, TestData.COMPARISON_TITLE)
                .shouldContainProduct(TestData.COMPARISON_TITLE, productName, TestData.EMPTY_COMPARISON);
    }

    @Test
    @DisplayName("Корзина нового посетителя пуста")
    void newVisitorHasEmptyCart() {
        homePage.openPage()
                .header()
                .openCart(TestData.CART_LINK, TestData.CART_TITLE)
                .shouldBeEmpty(TestData.EMPTY_CART);
    }

    @Test
    @DisplayName("Из каталога можно перейти в категорию «Электроинструмент»")
    void catalogOpensPowerToolsCategory() {
        homePage.openPage()
                .header()
                .openCatalog(TestData.CATALOG_TITLE)
                .openCategory(TestData.POWER_TOOLS_NAME, TestData.POWER_TOOLS_PATH)
                .shouldShowCategory(
                        TestData.POWER_TOOLS_NAME,
                        TestData.POWER_TOOLS_PATH,
                        TestData.DRILLS_NAME,
                        TestData.DRILLS_PATH
                );
    }

    @Test
    @DisplayName("В форме входа можно переключиться с телефона на E-mail и обратно")
    void loginMethodCanBeSwitched() {
        homePage.openPage()
                .header()
                .openLogin(TestData.LOGIN_TITLE)
                .shouldShowLoginField(TestData.PHONE_PLACEHOLDER, TestData.EMAIL_PLACEHOLDER)
                .chooseLoginMethod(TestData.EMAIL_TAB)
                .shouldShowLoginField(TestData.EMAIL_PLACEHOLDER, TestData.PHONE_PLACEHOLDER)
                .chooseLoginMethod(TestData.PHONE_TAB)
                .shouldShowLoginField(TestData.PHONE_PLACEHOLDER, TestData.EMAIL_PLACEHOLDER);
    }

    @Test
    @DisplayName("Показ и скрытие пароля сохраняют введённое значение")
    void passwordVisibilityCanBeToggled() {
        homePage.openPage()
                .header()
                .openLogin(TestData.LOGIN_TITLE)
                .enterPassword(TestData.DEMO_PASSWORD)
                .shouldHidePassword(TestData.DEMO_PASSWORD)
                .togglePasswordVisibility()
                .shouldRevealPassword(TestData.DEMO_PASSWORD)
                .togglePasswordVisibility()
                .shouldHidePassword(TestData.DEMO_PASSWORD);
    }
}
