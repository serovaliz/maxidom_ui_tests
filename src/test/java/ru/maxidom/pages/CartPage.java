package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class CartPage {
    public CartPage shouldBeOpened() {
        webdriver().shouldHave(urlContaining("/personal/cart2/"));
        $("h1").shouldHave(exactText("Корзина"));
        return this;
    }

    @Step("Проверить пустую корзину нового посетителя")
    public CartPage shouldBeEmpty() {
        $("main").shouldHave(text("Нет товаров в корзине"));
        return this;
    }
}
