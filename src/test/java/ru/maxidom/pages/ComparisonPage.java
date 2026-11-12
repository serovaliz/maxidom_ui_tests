package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ComparisonPage {
    public ComparisonPage shouldBeOpened() {
        webdriver().shouldHave(urlContaining("/catalog/compare/"));
        $("main .breadcrumbs").shouldHave(text("Сравнение товаров"));
        return this;
    }

    @Step("Проверить наличие товара в сравнении: {productName}")
    public ComparisonPage shouldContainProduct(String productName) {
        $("h1").shouldHave(exactText("Сравнение товаров"));
        $$(".container-compare .compare__card-title a").findBy(exactText(productName)).shouldBe(visible);
        $("main").shouldNotHave(text("Список сравниваемых элементов пуст"));
        return this;
    }
}
