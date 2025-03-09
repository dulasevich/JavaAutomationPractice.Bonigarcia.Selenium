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

    public static List<String> getChapterLinks(WebDriver driver, String chapterName) {
        return driver.findElements(By.xpath(XpathUtil.combineChapterXpath(chapterName)))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
