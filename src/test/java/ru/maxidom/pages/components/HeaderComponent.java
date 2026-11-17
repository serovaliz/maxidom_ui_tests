package ru.maxidom.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.maxidom.pages.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderComponent {
    private final SelenideElement searchInput = $("#standart-search #q");

    public void shouldBeReady() {
        searchInput.shouldBe(visible, enabled);
    }

    @Step("Найти товар по запросу: {query}")
    public SearchResultsPage searchFor(String query, String resultsTitle) {
        searchInput.shouldBe(visible, enabled).setValue(query).pressEnter();
        return new SearchResultsPage().shouldBeOpenedFor(query, resultsTitle);
    }

    @Step("Открыть каталог через шапку сайта")
    public CatalogPage openCatalog(String title) {
        $$("header a[href='/catalog/']").findBy(visible).click();
        return new CatalogPage().shouldBeOpened(title);
    }

    @Step("Открыть корзину через шапку сайта")
    public CartPage openCart(String linkText, String title) {
        $$("header a[href='/personal/cart2/']").filterBy(visible).findBy(exactText(linkText))
                .shouldBe(visible).click();
        return new CartPage().shouldBeOpened(title);
    }

    @Step("Открыть сравнение через шапку сайта")
    public ComparisonPage openComparison(String linkText, String breadcrumbText) {
        $$("header a[href='/catalog/compare/']").filterBy(visible).findBy(exactText(linkText))
                .shouldBe(visible).click();
        return new ComparisonPage().shouldBeOpened(breadcrumbText);
    }

    @Step("Открыть форму входа")
    public LoginModal openLogin(String title) {
        $("#gtm_clickLogin").shouldBe(visible).click();
        return new LoginModal().shouldBeOpened(title);
    }
}
