package bonigarciatest.chapter4.browser_agnostic;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowDomTest {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java";
    private WebDriver driver;
    private static final By SHADOW_ROOT_LOCATOR = By.cssSelector("p");

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL + "/index.html");
        driver.navigate().to(BASE_URL + "/shadow-dom.html");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void shadowDomNegativeTest() {
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(SHADOW_ROOT_LOCATOR));
    }

    @Test
    void shadowDomTest() {
        WebElement content = driver.findElement(By.xpath("//div[@id='content']"));
        SearchContext searchContext = content.getShadowRoot();
        WebElement shadowRootText = searchContext.findElement(SHADOW_ROOT_LOCATOR);

        Assertions.assertEquals("Hello Shadow DOM", shadowRootText.getText(), "Shadow dom text is incorrect");
    }
}