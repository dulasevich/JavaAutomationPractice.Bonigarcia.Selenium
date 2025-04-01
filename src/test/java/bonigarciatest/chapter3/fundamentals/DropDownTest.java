package bonigarciatest.chapter3.fundamentals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.stream.Collectors;

public class DropDownTest {

    private static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
    private static final List<String> DROPDOWN_OPTIONS = List.of("Action", "Another action", "Something else here", "Separated link");
    private static final String DROPDOWN_LOCATOR = "//button[@id='my-dropdown-%d']";
    private WebDriver driver;
    private Actions actions;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void leftClickTest() throws InterruptedException {
        WebElement leftClickButton = driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, 1)));
        Thread.sleep(2000);
        leftClickButton.click();

        checkDropDownOnClick("#0d6efd", leftClickButton);
    }

    @Test
    void rightClickTest() {
        WebElement rightClickButton = driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, 2)));
        actions.contextClick(rightClickButton).perform();

        checkDropDownOnClick("#198754", rightClickButton);
    }

    @Test
    void doubleClickTest() {
        WebElement doubleClickButton = driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, 3)));
        actions.doubleClick(doubleClickButton).perform();

        checkDropDownOnClick("#dc3545", doubleClickButton);
    }

    private List<String> getDropDownOptions(WebElement element) {
        return element.findElements(By.xpath("./following-sibling::ul//a"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private void checkDropDownOnClick(String color, WebElement element) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(color, Color.fromString(element.getCssValue("background-color")).asHex(),
                        "Background color on click is wrong"),
                () -> Assertions.assertEquals(DROPDOWN_OPTIONS, getDropDownOptions(element), "Dropdown options are wrong")
        );
    }
}

