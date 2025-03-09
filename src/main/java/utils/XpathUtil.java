package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XpathUtil {

    public static final String TITLE_XPATH = "//h1[contains(@class, 'display-6')]";
    public static final String FRAME_HEADER_XPATH = "//frame[@name='frame-header']";

    public static String combineLinkXpath(String endpoint) {
        return String.format("//a[@href=\"%s\"]", endpoint);
    }

    public static String combineChapterXpath(String chapterName) {
        return String.format("//h5[text()='%s']/following-sibling::a", chapterName);
    }
}
