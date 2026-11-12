package ru.maxidom.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.maxidom.components.HeaderComponent;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class SearchResultsPage {
    private final ElementsCollection cards = $$("main article.l-product");
    private final HeaderComponent header = new HeaderComponent();

    @Step("Проверить страницу результатов для запроса: {query}")
    public SearchResultsPage shouldBeOpenedFor(String query) {
        webdriver().shouldHave(urlContaining("/search/catalog/"));
        $("h1").shouldHave(exactText("Результаты поиска"));
        $("main .breadcrumbs").shouldHave(text(query));
        return this;
    }

    @Step("Проверить, что найдены товары с текстом: {query}")
    public SearchResultsPage shouldHaveProducts(String query) {
        cards.shouldHave(sizeGreaterThan(0));
        cards.first().$(".l-product__name-href").shouldBe(visible).shouldHave(text(query));
        return this;
    }

    @Step("Проверить сообщение об отсутствии результатов")
    public SearchResultsPage shouldHaveNoResults() {
        $("main").shouldHave(text("По вашему запросу ничего не найдено"));
        cards.shouldHave(com.codeborne.selenide.CollectionCondition.size(0));
        return this;
    }

    public String firstProductName() {
        return firstProductLink().getText();
    }

    private SelenideElement firstProductLink() {
        cards.shouldHave(sizeGreaterThan(0));
        return cards.first().$(".l-product__name-href").shouldBe(visible);
    }

    @Step("Открыть первый найденный товар")
    public ProductPage openFirstProduct() {
        SelenideElement link = firstProductLink();
        String expectedUrl = link.getAttribute("href");
        link.click();
        return new ProductPage().shouldBeOpenedAt(expectedUrl);
    }

    @Step("Добавить первый найденный товар в сравнение")
    public SearchResultsPage addFirstProductToComparison() {
        cards.shouldHave(sizeGreaterThan(0)).first().hover().$$(".l-product__action_compare")
                .findBy(visible).click();
        // Wait for the asynchronous operation before leaving the search page.
        $$("header a[href='/catalog/compare/']").findBy(matchText("^1$"))
                .shouldBe(visible);
        return this;
    }

    public HeaderComponent header() {
        return header;
    }
}
