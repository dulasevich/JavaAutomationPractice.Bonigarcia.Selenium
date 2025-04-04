package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinksUtil {
    private static final String CHAPTER_LINKS_XPATH = "//h5[text()='%s']/following-sibling::a";

    public static List<String> getChapterLinks(WebDriver driver, String chapterName) {
        return driver.findElements(By.xpath(String.format(CHAPTER_LINKS_XPATH, chapterName)))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
}