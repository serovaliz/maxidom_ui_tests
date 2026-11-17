package ru.maxidom.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginModal {
    private final SelenideElement dialog = $(".wrapper-auth");

    public LoginModal shouldBeOpened(String title) {
        dialog.shouldBe(visible);
        dialog.$(".title__enter").shouldHave(exactText(title));
        return this;
    }

    @Step("Переключить вход на: {tabLabel}")
    public LoginModal chooseLoginMethod(String tabLabel) {
        dialog.$$(".switcher__element").findBy(exactText(tabLabel)).click();
        return this;
    }

    @Step("Проверить видимое поле «{visiblePlaceholder}» и скрытое «{hiddenPlaceholder}»")
    public LoginModal shouldShowLoginField(String visiblePlaceholder, String hiddenPlaceholder) {
        dialog.$("input[placeholder='" + visiblePlaceholder + "']").shouldBe(visible);
        dialog.$("input[placeholder='" + hiddenPlaceholder + "']").shouldNotBe(visible);
        return this;
    }

    @Step("Ввести пароль без отправки формы")
    public LoginModal enterPassword(String passwordValue) {
        passwordInput().shouldBe(visible).setValue(passwordValue);
        return this;
    }

    @Step("Переключить видимость пароля")
    public LoginModal togglePasswordVisibility() {
        dialog.$(".see-icon-wrap").click();
        return this;
    }

    @Step("Проверить скрытый пароль и сохранение введённого значения")
    public LoginModal shouldHidePassword(String expectedPassword) {
        passwordInput().shouldHave(attribute("type", "password"), value(expectedPassword));
        return this;
    }

    @Step("Проверить видимый пароль и сохранение введённого значения")
    public LoginModal shouldRevealPassword(String expectedPassword) {
        passwordInput().shouldHave(attribute("type", "text"), value(expectedPassword));
        return this;
    }

    private SelenideElement passwordInput() {
        return dialog.$(".see-icon-wrap").parent().$("input");
    }
}
