package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class CatalogPage {
    public CatalogPage shouldBeOpened() {
        webdriver().shouldHave(urlContaining("/catalog/"));
        $("h1").shouldHave(exactText("Каталог"));
        return this;
    }

    @Step("Открыть категорию «Электроинструмент»")
    public CatalogPage openPowerTools() {
        $$("main a[href='/catalog/elektroinstrument/']").findBy(exactText("Электроинструмент"))
                .scrollIntoView("{block: 'center'}").shouldBe(visible).click();
        return this;
    }

    @Step("Проверить категорию и её подраздел «Дрели»")
    public CatalogPage shouldShowPowerTools() {
        webdriver().shouldHave(urlContaining("/catalog/elektroinstrument/"));
        $("h1").shouldHave(exactText("Электроинструмент"));
        $$("main a[href='/catalog/dreli/']").findBy(exactText("Дрели")).shouldBe(visible);
        return this;
    }
}
