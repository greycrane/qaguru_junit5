package com.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Exist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CsvFileSourceTests {
    @BeforeEach
    void configTests() {
        Configuration.headless = true;
    }

    @CsvFileSource(resources = "/testData.csv")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    @ParameterizedTest(name = "Проверка заголовков баннера в разделе {0}")
    void checkTitlesInBannerForSearchType(String searchType, String bannerText1, String bannerText2) {
        open("https://aviasales.ru");
        $(By.linkText(searchType)).click();
        $(".page-header__titles").shouldHave(text(bannerText1));
        $(".page-header__titles").shouldHave(text(bannerText2));
    }
}
