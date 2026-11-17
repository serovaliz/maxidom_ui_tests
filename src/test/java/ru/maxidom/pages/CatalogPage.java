package ru.maxidom.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class CatalogPage {
    public CatalogPage shouldBeOpened(String title) {
        webdriver().shouldHave(urlContaining("/catalog/"));
        $("h1").shouldHave(exactText(title));
        return this;
    }

    @Step("Открыть категорию: {name}")
    public CatalogPage openCategory(String name, String href) {
        $$("main a[href='" + href + "']").findBy(exactText(name))
                .scrollIntoView("{block: 'center'}").shouldBe(visible).click();
        return this;
    }

    @Step("Проверить категорию {title} и подраздел {subsectionName}")
    public CatalogPage shouldShowCategory(String title, String urlPart,
                                          String subsectionName, String subsectionHref) {
        webdriver().shouldHave(urlContaining(urlPart));
        $("h1").shouldHave(exactText(title));
        $$("main a[href='" + subsectionHref + "']").findBy(exactText(subsectionName)).shouldBe(visible);
        return this;
    }
}
