package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ProductPage {
    @Step("Проверить адрес выбранного товара: {expectedUrl}")
    public ProductPage shouldBeOpenedAt(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

    @Step("Проверить название и фотографию открытого товара: {expectedName}")
    public ProductPage shouldShowProduct(String expectedName) {
        $("h1 .span-name").shouldBe(visible).shouldHave(exactText(expectedName));
        $$("main img").filterBy(visible).findBy(attribute("alt", expectedName)).shouldBe(visible);
        return this;
    }
}
