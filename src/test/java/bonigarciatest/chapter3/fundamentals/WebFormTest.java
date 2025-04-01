package bonigarciatest.chapter3.fundamentals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class WebFormTest {

    private static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
    private static final String TEXT_TO_ENTER = "text";
    private static final String VALUE_ATTRIBUTE = "value";
    private WebDriver driver;

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

    @ParameterizedTest
    @ValueSource(strings = {"//input[@id='my-text-id']", "//input[@name='my-password']", "//textarea"})
    void inputFieldsTest(String xpath) {
        WebElement textInputField = driver.findElement(By.xpath(xpath));
        textInputField.sendKeys(TEXT_TO_ENTER);
        Assertions.assertEquals(TEXT_TO_ENTER, textInputField.getDomProperty(VALUE_ATTRIBUTE), "Text entered is wrong");

        textInputField.clear();
        Assertions.assertTrue(textInputField.getDomProperty(VALUE_ATTRIBUTE).isEmpty(), "Field is NOT blank");
    }

    @Test
    void disabledInputTest() {
        WebElement disabledInputField = driver.findElement(By.xpath("//input[@name='my-disabled']"));

        Assertions.assertAll(
                () -> Assertions.assertFalse(disabledInputField.isEnabled(), "Disabled input is enabled"),
                () -> Assertions.assertNotNull(disabledInputField.getDomAttribute("disabled"),
                        "disabled input is missing attribute"),
                () -> Assertions.assertThrows(ElementNotInteractableException.class, () -> disabledInputField.sendKeys(TEXT_TO_ENTER),
                        "It's possible to enter text to the disabled input")
        );
    }

    @Test
    void readOnlyInputTest() {
        WebElement readOnlyInputField = driver.findElement(By.xpath("//input[@name='my-readonly']"));
        readOnlyInputField.sendKeys(TEXT_TO_ENTER);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(readOnlyInputField.getDomAttribute("readonly"),
                        "readonly input is missing attribute"),
                () -> Assertions.assertEquals("Readonly input", readOnlyInputField.getDomProperty(VALUE_ATTRIBUTE),
                        "Read only text is wrong")
        );
    }

    @Test
    void dropDownSelectOptionsTest() {
        WebElement dropDownSelectElement = driver.findElement(By.xpath("//select[@name='my-select']"));
        Select dropDownSelect = new Select(dropDownSelectElement);

        Assertions.assertAll(
                () -> Assertions.assertEquals("Open this select menu", dropDownSelect.getFirstSelectedOption().getText(),
                        "Incorrect default option selected"),
                () -> Assertions.assertEquals(List.of("Open this select menu", "One", "Two", "Three"),
                        dropDownSelect.getOptions().stream().map(WebElement::getText).collect(Collectors.toList()),
                        "List of options is incorrect")
        );
    }

    @Test
    void dropDownSelectTest() {
        WebElement dropDownSelectElement = driver.findElement(By.xpath("//select[@name='my-select']"));
        Select dropDownSelect = new Select(dropDownSelectElement);

        dropDownSelect.selectByIndex(1);
        Assertions.assertEquals("One", dropDownSelect.getFirstSelectedOption().getText(), "Incorrect option selected");

        dropDownSelect.selectByVisibleText("Two");
        Assertions.assertEquals("Two", dropDownSelect.getFirstSelectedOption().getText(), "Incorrect option selected");

        dropDownSelect.selectByValue("3");
        Assertions.assertEquals("Three", dropDownSelect.getFirstSelectedOption().getText(), "Incorrect option selected");
    }

    @Test
    void dropDownDataListOptionsTest() {
        WebElement dataListInput = driver.findElement(By.xpath("//input[@name='my-datalist']"));
        List<WebElement> dataListOptions = driver.findElements(By.xpath("//datalist/option"));

        Assertions.assertAll(
                () -> Assertions.assertEquals("Type to search...", dataListInput.getDomAttribute("placeholder"),
                        "Incorrect background text added"),
                () -> Assertions.assertEquals(List.of("San Francisco", "New York", "Seattle", "Los Angeles", "Chicago"),
                        dataListOptions.stream().map(element -> element.getDomAttribute(VALUE_ATTRIBUTE)).collect(Collectors.toList()),
                        "List of options is incorrect")
        );
    }

    @Test
    void dropDownDataListOptions() {
        WebElement dataListInput = driver.findElement(By.xpath("//input[@name='my-datalist']"));
        dataListInput.sendKeys("Seattle");

        Assertions.assertEquals("Seattle", dataListInput.getDomProperty(VALUE_ATTRIBUTE), "Expected options is NOT selected");
    }

    @Test
    void fileInputTest() {
        String fileName = "test.txt";
        URL fileUrl = WebFormTest.class.getClassLoader().getResource(fileName);
        Assertions.assertNotNull(fileUrl, "File not found in the resources");

        String absolutePath = new File(URLDecoder.decode(fileUrl.getPath(), StandardCharsets.UTF_8)).getAbsolutePath();
        WebElement fileInput = driver.findElement(By.xpath("//input[@name='my-file']"));
        fileInput.sendKeys(absolutePath);
        Assertions.assertTrue(fileInput.getDomProperty(VALUE_ATTRIBUTE).contains(fileName), "File is NOT attached ot incorrect added");
    }

    @Test
    void checkBoxTest() {
        WebElement checkedCheckBox = driver.findElement(By.xpath("//input[@id='my-check-1']"));
        WebElement defaultCheckBox = driver.findElement(By.xpath("//input[@id='my-check-2']"));

        Assertions.assertAll(
                () -> Assertions.assertNotNull(checkedCheckBox.getDomAttribute("checked"), "Checked option is not selected"),
                () -> Assertions.assertNull(defaultCheckBox.getDomAttribute("checked"), "Default option is selected")
        );

        checkedCheckBox.click();
        defaultCheckBox.click();
        Assertions.assertAll(
                () -> Assertions.assertFalse(checkedCheckBox.isSelected(), "Checked option is still selected"),
                () -> Assertions.assertTrue(defaultCheckBox.isSelected(), "Default option is NOT selected")
        );
    }

    @Test
    void radioButtonTest() {
        WebElement checkedRadioButton = driver.findElement(By.xpath("//input[@id='my-radio-1']"));
        WebElement defaultRadioButton = driver.findElement(By.xpath("//input[@id='my-radio-2']"));

        Assertions.assertAll(
                () -> Assertions.assertNotNull(checkedRadioButton.getDomAttribute("checked"), "Checked button is not selected"),
                () -> Assertions.assertNull(defaultRadioButton.getDomAttribute("checked"), "Default button is not selected")
        );

        defaultRadioButton.click();
        Assertions.assertAll(
                () -> Assertions.assertFalse(checkedRadioButton.isSelected(), "Checked option is still selected"),
                () -> Assertions.assertTrue(defaultRadioButton.isSelected(), "Default option is NOT selected")
        );
    }

    @Test
    void colorPickerTest() {
        WebElement colorPicker = driver.findElement(By.xpath("//input[@name='my-colors']"));
        Assertions.assertEquals("#563d7c", colorPicker.getDomAttribute(VALUE_ATTRIBUTE), "Default color is wrong");

        Color green = new Color(61, 123, 82, 1);
        String script = String.format("arguments[0].setAttribute('value', '%s');", green.asHex());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, colorPicker);

        Assertions.assertEquals("#3d7b52", colorPicker.getDomProperty(VALUE_ATTRIBUTE), "Incorrect color selected");
    }

    @Test
    void datePickerTest() {
        String dateFormat = "MM/dd/yyyy";
        WebElement datePicker = driver.findElement(By.xpath("//input[@name='my-date']"));
        Assertions.assertTrue(datePicker.getDomProperty(VALUE_ATTRIBUTE).isEmpty(), "Some date is selected");

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));
        datePicker.sendKeys(date);
        datePicker.sendKeys(Keys.TAB);
        Assertions.assertEquals(date, datePicker.getDomProperty(VALUE_ATTRIBUTE), "Date is not equal to the entered one");

        datePicker.click();
        LocalDate newDate = LocalDate.now().plusDays(1);
        //handling the case when next day is in the next month, locator is different
        String xpathClassName = LocalDate.now().getMonth().maxLength() == LocalDate.now().getDayOfMonth() ? "new day" : "day";
        WebElement dateToSelect = driver.findElement(By.xpath(String.format("//td[@class='%s' and text()=%d]", xpathClassName, newDate.getDayOfMonth())));

        dateToSelect.click();
        Assertions.assertEquals(newDate.format(DateTimeFormatter.ofPattern(dateFormat)), datePicker.getDomProperty(VALUE_ATTRIBUTE),
                "Incorrect date selected");
    }

    @Test
    void rangeTest() {
        WebElement rangePicker = driver.findElement(By.xpath("//input[@name='my-range']"));
        Assertions.assertEquals("5", rangePicker.getDomAttribute(VALUE_ATTRIBUTE), "Default position of picker is wrong");

        rangePicker.sendKeys(Keys.ARROW_LEFT);
        Assertions.assertEquals("4", rangePicker.getDomProperty(VALUE_ATTRIBUTE), "Picker moved to incorrect position");
    }

    @Test
    void submitButtonTest() throws InterruptedException {
        WebElement submitButton = driver.findElement(By.xpath("//button"));
        submitButton.click();
        Thread.sleep(1000);
        Assertions.assertEquals("Form submitted", driver.findElement(By.xpath("//h1[@class='display-6']")).getText(),
                "Submission confirmation is either wrong or not displayed");
    }
}

