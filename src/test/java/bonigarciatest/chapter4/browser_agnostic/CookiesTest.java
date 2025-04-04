package bonigarciatest.chapter4.browser_agnostic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class CookiesTest {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java";
    private WebDriver driver;
    private static final By DISPLAY_BUTTON_LOCATOR = By.xpath("//button");
    private static final By COOKIES_LIST_LOCATOR = By.xpath("//p[@id='cookies-list']");
    private static final Cookie USERNAME_COOKIE = new Cookie("username", "John Doe", null);
    private static final Cookie DATE_COOKIE = new Cookie("date", "10/07/2018", null);

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL + "/index.html");
        driver.navigate().to(BASE_URL + "/cookies.html");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void cookiesDefaultViewTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.findElement(DISPLAY_BUTTON_LOCATOR).isDisplayed(),
                        "Display cookies button is missing"),
                () -> Assertions.assertEquals("Display cookies", driver.findElement(DISPLAY_BUTTON_LOCATOR).getText(),
                        "Button text is wrong"),
                () -> Assertions.assertTrue(driver.findElement(COOKIES_LIST_LOCATOR).getText().isEmpty(),
                        "Cookies are shown on default page")
        );
    }

    @Test
    void showCookiesTest() {
        driver.findElement(DISPLAY_BUTTON_LOCATOR).click();
        List<String> cookiesDisplayed = Arrays.asList(driver.findElement(COOKIES_LIST_LOCATOR).getText().split("\n"));
        Assertions.assertAll(
                () -> Assertions.assertEquals(Set.of(USERNAME_COOKIE, DATE_COOKIE), driver.manage().getCookies(),
                        "Browser cookies set incorrectly"),
                () -> Assertions.assertEquals(List.of(getStringFormattedCookie(USERNAME_COOKIE), getStringFormattedCookie(DATE_COOKIE)),
                        cookiesDisplayed, "Incorrect cookies display")
        );
    }

    @Test
    void manageCookiesTest() {
        driver.manage().deleteAllCookies();

        Cookie newCookie = new Cookie("someKey", "someValue");
        driver.manage().addCookie(newCookie);
        driver.findElement(DISPLAY_BUTTON_LOCATOR).click();
        String cookiesDisplayed = driver.findElement(COOKIES_LIST_LOCATOR).getText();
        Assertions.assertEquals(getStringFormattedCookie(newCookie), cookiesDisplayed, "Incorrect cookies display");
    }

    private String getStringFormattedCookie(Cookie cookie) {
        return String.format("%s=%s", cookie.getName(), cookie.getValue());
    }
}

