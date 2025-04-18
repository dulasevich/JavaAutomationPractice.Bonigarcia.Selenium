package bonigarciatest.chapter3.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoadingImagesTest {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java";

    @Test
    void loadingImagesTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL + "/index.html");
        driver.navigate().to(BASE_URL + "/loading-images.html");

        By result = By.xpath("//p[@class='lead']");
        Assertions.assertAll(
                () -> Assertions.assertEquals("Please wait until the images are loaded...", driver.findElement(result).getText(),
                        "Incorrect loading result text displays once page opens"),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[@id='spinner']")).isDisplayed(),
                        "Loading spinner is not displayed")
        );

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.textToBe(result, "Done!"));
        List.of(By.xpath("//img[@id='compass']"), By.xpath("//img[@id='calendar']"),
                        By.xpath("//img[@id='award']"), By.xpath("//img[@id='landscape']"))
                .forEach(locator -> Assertions.assertTrue(driver.findElement(locator).isDisplayed()));

        driver.quit();
    }
}

