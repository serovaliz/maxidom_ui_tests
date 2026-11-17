package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ComparisonPage {
    public ComparisonPage shouldBeOpened(String breadcrumbText) {
        webdriver().shouldHave(urlContaining("/catalog/compare/"));
        $("main .breadcrumbs").shouldHave(text(breadcrumbText));
        return this;
    }

    @Step("Проверить наличие товара в сравнении: {productName}")
    public ComparisonPage shouldContainProduct(String title, String productName, String emptyListMessage) {
        $("h1").shouldHave(exactText(title));
        $$(".container-compare .compare__card-title a").findBy(exactText(productName)).shouldBe(visible);
        $("main").shouldNotHave(text(emptyListMessage));
        return this;
    }
}
