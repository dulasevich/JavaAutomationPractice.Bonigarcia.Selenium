package bonigarciatest.chapter3.fundamentals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class NavigationTest {

    private static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
    private WebDriver driver;
    private static final By TEXT_CONTENT = By.xpath("//p");
    private static final By PREVIOUS_PAGE_BUTTON = By.xpath("//a[text()='Previous']");
    private static final By NEXT_PAGE_BUTTON = By.xpath("//a[text()='Next']");
    private static final String SPECIFIC_PAGE_BUTTON_LOCATOR = "//a[text()='%d']";
    private static final String CLASS_ATTRIBUTE = "class";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void navigationDefaultPageTest() {
        WebElement navigationTitle = driver.findElement(By.xpath("//h1[@class ='display-6']"));
        List<WebElement> paginationOptions = driver.findElements(By.xpath("//ul[@class='pagination']/li/a"));

        Assertions.assertAll(
                () -> Assertions.assertEquals("Navigation example", navigationTitle.getText(), "Title is wrong"),
                () -> Assertions.assertEquals(List.of("Previous", "1", "2", "3", "Next"),
                        paginationOptions.stream().map(WebElement::getText).collect(Collectors.toList()), "Pagination elements are incorrect"),
                () -> Assertions.assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        driver.findElement(TEXT_CONTENT).getText(), "Text content is wrong"),
                () -> Assertions.assertTrue(isPageNavigationButtonDisabled(driver.findElement(PREVIOUS_PAGE_BUTTON)),
                        "Previous button is enabled on the 1st page"),
                () -> Assertions.assertFalse(isPageNavigationButtonDisabled(driver.findElement(NEXT_PAGE_BUTTON)),
                        "Next button is disabled on the 1st page"),
                () -> Assertions.assertTrue(isPageActive(driver.findElement(By.xpath(String.format(SPECIFIC_PAGE_BUTTON_LOCATOR, 1)))),
                        "First page NOT selected by default")
        );
    }

    @Test
    void navigationTest() {
        getSpecificPageButton(2).click();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor " +
                                "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                        driver.findElement(TEXT_CONTENT).getText(), "Text content is wrong"),
                () -> Assertions.assertFalse(isPageNavigationButtonDisabled(driver.findElement((PREVIOUS_PAGE_BUTTON))),
                        "Previous button is disabled on the 2nd page"),
                () -> Assertions.assertTrue(isPageActive(getSpecificPageButton(2)), "Second page NOT selected")
        );

        driver.findElement(NEXT_PAGE_BUTTON).click();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        driver.findElement(TEXT_CONTENT).getText(), "Text content is wrong"),
                () -> Assertions.assertTrue(isPageNavigationButtonDisabled(driver.findElement((NEXT_PAGE_BUTTON))),
                        "Next button is enabled on the 3rd page"),
                () -> Assertions.assertTrue(isPageActive(getSpecificPageButton(3)), "Third page NOT selected")
        );
    }

    private WebElement getSpecificPageButton(int pageNumber) {
        return driver.findElement(By.xpath(String.format(SPECIFIC_PAGE_BUTTON_LOCATOR, pageNumber)));
    }

    private Boolean isPageNavigationButtonDisabled(WebElement element) {
        return element.findElement(By.xpath("./..")).getDomAttribute(CLASS_ATTRIBUTE).contains("disabled");
    }

    private Boolean isPageActive(WebElement element) {
        return element.findElement(By.xpath("./..")).getDomAttribute(CLASS_ATTRIBUTE).contains("active");
    }
}
