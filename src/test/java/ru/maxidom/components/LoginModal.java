package ru.maxidom.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginModal {
    private final SelenideElement dialog = $(".wrapper-auth");
    private final SelenideElement password = dialog.$("input[placeholder='Пароль']");

    public LoginModal shouldBeOpened() {
        dialog.shouldBe(visible);
        dialog.$(".title__enter").shouldHave(exactText("Вход в личный кабинет"));
        return this;
    }

    @Step("Переключить вход на E-mail")
    public LoginModal chooseEmail() {
        dialog.$$(".switcher__element").findBy(exactText("E-mail")).click();
        return this;
    }

    @Step("Проверить поле E-mail и скрытое поле телефона")
    public LoginModal shouldUseEmail() {
        dialog.$("input[placeholder='Введите почту']").shouldBe(visible);
        dialog.$("input[placeholder='Введите телефон']").shouldNotBe(visible);
        return this;
    }

    @Step("Переключить вход на телефон")
    public LoginModal choosePhone() {
        dialog.$$(".switcher__element").findBy(exactText("Телефон")).click();
        return this;
    }

    @Step("Проверить поле телефона и скрытое поле E-mail")
    public LoginModal shouldUsePhone() {
        dialog.$("input[placeholder='Введите телефон']").shouldBe(visible);
        dialog.$("input[placeholder='Введите почту']").shouldNotBe(visible);
        return this;
    }

    @Step("Ввести демонстрационный пароль без отправки формы")
    public LoginModal enterDemoPassword() {
        password.shouldBe(visible).setValue("TestPassword-123");
        return this;
    }

    @Step("Переключить видимость пароля")
    public LoginModal togglePasswordVisibility() {
        dialog.$(".see-icon-wrap").click();
        return this;
    }

    @Step("Проверить скрытый пароль и сохранение введённого значения")
    public LoginModal shouldHidePassword() {
        password.shouldHave(attribute("type", "password"), value("TestPassword-123"));
        return this;
    }

    @Step("Проверить видимый пароль и сохранение введённого значения")
    public LoginModal shouldRevealPassword() {
        password.shouldHave(attribute("type", "text"), value("TestPassword-123"));
        return this;
    }
}
