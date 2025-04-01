package bonigarciatest.chapter3.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragDropTest {

    private static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";

    @Test
    void dragDropTest() {
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.get(URL);

        WebElement elementToDrag = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement placeToDrop = driver.findElement(By.xpath("//div[@id='target']"));
        Assertions.assertNotEquals(placeToDrop.getLocation(), elementToDrag.getLocation(), "Element is already in the target by default");

        actions.dragAndDrop(elementToDrag, placeToDrop).perform();
        Assertions.assertEquals(placeToDrop.getLocation(), elementToDrag.getLocation(), "Element is moved to the wrong location");

        driver.quit();
    }
}

