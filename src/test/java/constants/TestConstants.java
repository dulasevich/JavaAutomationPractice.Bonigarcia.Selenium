package constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConstants {

    // Chapters
    public static final String WEB_DRIVER_FUNDAMENTALS = "Chapter 3. WebDriver Fundamentals";
    public static final String BROWSER_AGNOSTIC_FEATURES = "Chapter 4. Browser-Agnostic Features";
    public static final String BROWSER_SPECIFIC_FEATURES = "Chapter 5. Browser-Specific Manipulation";
    public static final String POM = "Chapter 7. The Page Object Model (POM)";
    public static final String TESTING_FRAMEWORK_SPECIFICS = "Chapter 8. Testing Framework Specifics";
    public static final String THIRD_PARTY_INTEGRATIONS = "Chapter 9. Third-Party Integrations";

    // XPaths
    public static final String TITLE_XPATH = "//h1[contains(@class, 'display-6')]";
    public static final String FRAME_HEADER_XPATH = "//frame[@name='frame-header']";
    public static final String LINK_XPATH = "//a[@href=\"%s\"]";

    // Links for different chapters
    public static final List<String> WEB_DRIVER_FUNDAMENTALS_LINKS = List.of(
            "Web form",
            "Navigation",
            "Dropdown menu",
            "Mouse over",
            "Drag and drop",
            "Draw in canvas",
            "Loading images",
            "Slow calculator"
    );
    public static final List<String> BROWSER_AGNOSTIC_LINKS = List.of(
            "Long page",
            "Infinite scroll",
            "Shadow DOM",
            "Cookies",
            "Frames",
            "IFrames",
            "Dialog boxes",
            "Web storage"
    );
    public static final List<String> BROWSER_SPECIFIC_LINKS = List.of(
            "Geolocation",
            "Notifications",
            "Get user media",
            "Multilanguage",
            "Console logs"
    );
    public static final List<String> POM_LINKS = List.of("Login form", "Slow login");
    public static final List<String> TESTING_FRAMEWORK_LINKS = List.of("Random calculator");
    public static final List<String> THIRD_PARTY_LINKS = List.of("Download files", "A/B Testing", "Data types");
}