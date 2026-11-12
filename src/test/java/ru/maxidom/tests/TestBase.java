package ru.maxidom.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class TestBase {
    @BeforeAll
    static void configureBrowser() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://www.maxidom.ru");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        String remote = System.getProperty("remote", "");
        if (!remote.isBlank()) {
            Configuration.remote = remote;
        }
        if (Configuration.browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=ru-RU");
            Configuration.browserCapabilities = options;
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeEach
    void startWithFreshSession() {
        closeWebDriver();
    }

    @AfterEach
    void closeBrowser() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            closeWebDriver();
        }
    }
}
