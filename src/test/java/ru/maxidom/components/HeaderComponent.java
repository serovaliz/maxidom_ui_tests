package ru.maxidom.components;

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
    public SearchResultsPage searchFor(String query) {
        searchInput.shouldBe(visible, enabled).setValue(query).pressEnter();
        return new SearchResultsPage().shouldBeOpenedFor(query);
    }

    @Step("Открыть каталог через шапку сайта")
    public CatalogPage openCatalog() {
        $$("header a[href='/catalog/']").findBy(visible).click();
        return new CatalogPage().shouldBeOpened();
    }

    @Step("Открыть корзину через шапку сайта")
    public CartPage openCart() {
        $$("header a[href='/personal/cart2/']").filterBy(visible).findBy(exactText("Корзина"))
                .shouldBe(visible).click();
        return new CartPage().shouldBeOpened();
    }

    @Step("Открыть сравнение через шапку сайта")
    public ComparisonPage openComparison() {
        $$("header a[href='/catalog/compare/']").filterBy(visible).findBy(exactText("Сравнение"))
                .shouldBe(visible).click();
        return new ComparisonPage().shouldBeOpened();
    }

    @Step("Открыть форму входа")
    public LoginModal openLogin() {
        $("#gtm_clickLogin").shouldBe(visible).click();
        return new LoginModal().shouldBeOpened();
    }
}
