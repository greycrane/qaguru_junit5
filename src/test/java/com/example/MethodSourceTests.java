package com.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MethodSourceTests {

    @BeforeEach
    void configTests() {
        Configuration.headless = true;
    }

    static Stream<Arguments> checkTitlesInBannerForSearchType() {
        return Stream.of(
                Arguments.of("Авиабилеты", "Поиск дешёвых авиабилетов", "Лёгкий способ купить авиабилеты дёшево"),
                Arguments.of("Отели", "Поиск отелей на Ostrovok.ru", "Как бронировать c кэшбэком 7%")
        );
    }

    @MethodSource
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    @ParameterizedTest(name = "Проверка заголовков баннера в разделе {0}")
    void checkTitlesInBannerForSearchType(String searchType, String bannerText1, String bannerText2) {
        open("https://aviasales.ru");
        $(By.linkText(searchType)).click();
        $(".page-header__titles").shouldHave(text(bannerText1));
        $(".page-header__titles").shouldHave(text(bannerText2));
    }
}