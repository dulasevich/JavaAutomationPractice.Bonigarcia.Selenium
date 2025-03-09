package bonigarciatest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.LinksUtil;

import java.util.List;

import static constants.TestConstants.ChapterNamesConstants.*;
import static constants.TestConstants.CommonLabelsTitlesConstants.*;
import static constants.TestConstants.EndpointsConstants.*;
import static utils.XpathUtil.FRAME_HEADER_XPATH;
import static utils.XpathUtil.TITLE_XPATH;

public class HomePageTest {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void contentTest() {
        List<String> fundamentalsLinks = LinksUtil.getChapterLinks(driver, WEB_DRIVER_FUNDAMENTALS);
        List<String> browserAgnosticLinks = LinksUtil.getChapterLinks(driver, BROWSER_AGNOSTIC_FEATURES);
        List<String> browserSpecificLinks = LinksUtil.getChapterLinks(driver, BROWSER_SPECIFIC_FEATURES);
        List<String> pomLinks = LinksUtil.getChapterLinks(driver, POM);
        List<String> testingFrameworksLinks = LinksUtil.getChapterLinks(driver, TESTING_FRAMEWORK_SPECIFICS);
        List<String> thirdPartyLinks = LinksUtil.getChapterLinks(driver, THIRD_PARTY_INTEGRATIONS);

        Assertions.assertAll(
                () -> Assertions.assertEquals(List.of(WEB_FORM, NAVIGATION, DROPDOWN_MENU, MOUSE_OVER,
                        DRAG_DROP, DRAW_CANVAS, LOADING_IMAGES, SLOW_CALCULATOR), fundamentalsLinks,
                        "Chapter 3. WebDriver Fundamentals has incorrect links"),
                () -> Assertions.assertEquals(List.of(LONG_PAGE, INFINITE_SCROLL, SHADOW_DOM,
                        COOKIES, FRAMES, IFRAMES, DIALOG_BOXES, WEB_STORAGE), browserAgnosticLinks,
                        "Chapter 4. Browser-Agnostic Features has incorrect links"),
                () -> Assertions.assertEquals(List.of(GEOLOCATION, NOTIFICATIONS, GET_USER_MEDIA,
                        MULTI_LANGUAGE, CONSOLE_LOGS), browserSpecificLinks,
                        "Chapter 5. Browser-Specific Manipulation has incorrect links"),
                () -> Assertions.assertEquals(List.of(LOGIN_FORM, SLOW_LOGIN), pomLinks,
                        "Chapter 7. The Page Object Model (POM) has incorrect links"),
                () -> Assertions.assertEquals(List.of(RANDOM_CALCULATOR), testingFrameworksLinks,
                        "Chapter 8. Testing Framework Specifics has incorrect links"),
                () -> Assertions.assertEquals(List.of(DOWNLOAD_FILES, AB_TESTING, DATA_TYPES), thirdPartyLinks,
                        "Chapter 9. Third-Party Integrations has incorrect links")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters.TestData#linksProvider")
    void linksTest(String xPath, String endpoint, String title) {
        driver.findElement(By.xpath(xPath)).click();
        if (endpoint.equals(FRAMES_ENDPOINT)) {
            driver.switchTo().frame(driver.findElement(By.xpath(FRAME_HEADER_XPATH)));
        }
        Assertions.assertEquals(driver.findElement(By.xpath(TITLE_XPATH)).getText(), title, "Incorrect title");
        Assertions.assertEquals(BASE_URL + endpoint, driver.getCurrentUrl(), "Incorrect page opens");
    }
}
