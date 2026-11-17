package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class CartPage {
    public CartPage shouldBeOpened(String title) {
        webdriver().shouldHave(urlContaining("/personal/cart2/"));
        $("h1").shouldHave(exactText(title));
        return this;
    }

    @Step("Проверить пустую корзину: {emptyMessage}")
    public CartPage shouldBeEmpty(String emptyMessage) {
        $("main").shouldHave(text(emptyMessage));
        return this;
    }
}
