package parameters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static constants.TestConstants.LINK_XPATH;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestData {

    public static Stream<Arguments> linksProvider() {
        return Stream.of(
                Arguments.of(String.format(LINK_XPATH, "web-form.html"), "web-form.html", "Web form"),
                Arguments.of(String.format(LINK_XPATH, "navigation1.html"), "navigation1.html", "Navigation example"),
                Arguments.of(String.format(LINK_XPATH, "dropdown-menu.html"), "dropdown-menu.html", "Dropdown menu"),
                Arguments.of(String.format(LINK_XPATH, "mouse-over.html"), "mouse-over.html", "Mouse over"),
                Arguments.of(String.format(LINK_XPATH, "drag-and-drop.html"), "drag-and-drop.html", "Drag and drop"),
                Arguments.of(String.format(LINK_XPATH, "draw-in-canvas.html"), "draw-in-canvas.html", "Drawing in canvas"),
                Arguments.of(String.format(LINK_XPATH, "loading-images.html"), "loading-images.html", "Loading images"),
                Arguments.of(String.format(LINK_XPATH, "slow-calculator.html"), "slow-calculator.html", "Slow calculator"),
                Arguments.of(String.format(LINK_XPATH, "long-page.html"), "long-page.html", "This is a long page"),
                Arguments.of(String.format(LINK_XPATH, "infinite-scroll.html"), "infinite-scroll.html", "Infinite scroll"),
                Arguments.of(String.format(LINK_XPATH, "shadow-dom.html"), "shadow-dom.html", "Shadow DOM"),
                Arguments.of(String.format(LINK_XPATH, "cookies.html"), "cookies.html", "Cookies"),
                Arguments.of(String.format(LINK_XPATH, "frames.html"), "frames.html", "Frames"),
                Arguments.of(String.format(LINK_XPATH, "iframes.html"), "iframes.html", "IFrame"),
                Arguments.of(String.format(LINK_XPATH, "dialog-boxes.html"), "dialog-boxes.html", "Dialog boxes"),
                Arguments.of(String.format(LINK_XPATH, "web-storage.html"), "web-storage.html", "Web storage"),
                Arguments.of(String.format(LINK_XPATH, "geolocation.html"), "geolocation.html", "Geolocation"),
                Arguments.of(String.format(LINK_XPATH, "notifications.html"), "notifications.html", "Notifications"),
                Arguments.of(String.format(LINK_XPATH, "get-user-media.html"), "get-user-media.html", "Get user media"),
                Arguments.of(String.format(LINK_XPATH, "multilanguage.html"), "multilanguage.html", "Multilanguage page"),
                Arguments.of(String.format(LINK_XPATH, "console-logs.html"), "console-logs.html", "Console logs"),
                Arguments.of(String.format(LINK_XPATH, "login-form.html"), "login-form.html", "Login form"),
                Arguments.of(String.format(LINK_XPATH, "login-slow.html"), "login-slow.html", "Slow login form"),
                Arguments.of(String.format(LINK_XPATH, "random-calculator.html"), "random-calculator.html", "Random calculator"),
                Arguments.of(String.format(LINK_XPATH, "download.html"), "download.html", "Download files"),
                Arguments.of(String.format(LINK_XPATH, "ab-testing.html"), "ab-testing.html", "A/B Testing"),
                Arguments.of(String.format(LINK_XPATH, "data-types.html"), "data-types.html", "Data types")
        );
    }
}
