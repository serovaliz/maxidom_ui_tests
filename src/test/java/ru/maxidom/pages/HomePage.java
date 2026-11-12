package ru.maxidom.pages;

import io.qameta.allure.Step;
import ru.maxidom.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.open;

public class HomePage {
    private final HeaderComponent header = new HeaderComponent();

    @Step("Открыть главную страницу Максидома")
    public HomePage openPage() {
        open("/");
        header.shouldBeReady();
        return this;
    }

    public HeaderComponent header() {
        return header;
    }
}
