package parameters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;
import utils.XpathUtil;

import java.util.stream.Stream;

import static constants.TestConstants.CommonLabelsTitlesConstants.*;
import static constants.TestConstants.EndpointsConstants.*;
import static constants.TestConstants.EndpointsConstants.DATA_TYPES_ENDPOINT;
import static constants.TestConstants.SpecificTitlesConstants.*;
import static constants.TestConstants.SpecificTitlesConstants.SLOW_LOGIN_TITLE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestData {

    public static Stream<Arguments> linksProvider() {
        return Stream.of(
                Arguments.of(XpathUtil.combineLinkXpath(WEB_FORM_ENDPOINT), WEB_FORM_ENDPOINT, WEB_FORM),
                Arguments.of(XpathUtil.combineLinkXpath(NAVIGATION_ENDPOINT), NAVIGATION_ENDPOINT, NAVIGATION_TITLE),
                Arguments.of(XpathUtil.combineLinkXpath(DROPDOWN_MENU_ENDPOINT), DROPDOWN_MENU_ENDPOINT, DROPDOWN_MENU),
                Arguments.of(XpathUtil.combineLinkXpath(MOUSE_OVER_ENDPOINT), MOUSE_OVER_ENDPOINT, MOUSE_OVER),
                Arguments.of(XpathUtil.combineLinkXpath(DRAG_DROP_ENDPOINT), DRAG_DROP_ENDPOINT, DRAG_DROP),
                Arguments.of(XpathUtil.combineLinkXpath(DRAW_CANVAS_ENDPOINT), DRAW_CANVAS_ENDPOINT, DRAW_CANVAS_TITLE),
                Arguments.of(XpathUtil.combineLinkXpath(LOADING_IMAGES_ENDPOINT), LOADING_IMAGES_ENDPOINT, LOADING_IMAGES),
                Arguments.of(XpathUtil.combineLinkXpath(SLOW_CALCULATOR_ENDPOINT), SLOW_CALCULATOR_ENDPOINT, SLOW_CALCULATOR),
                Arguments.of(XpathUtil.combineLinkXpath(LONG_PAGE_ENDPOINT), LONG_PAGE_ENDPOINT, LONG_PAGE_TITLE),
                Arguments.of(XpathUtil.combineLinkXpath(INFINITE_SCROLL_ENDPOINT), INFINITE_SCROLL_ENDPOINT, INFINITE_SCROLL),
                Arguments.of(XpathUtil.combineLinkXpath(SHADOW_DOM_ENDPOINT), SHADOW_DOM_ENDPOINT, SHADOW_DOM),
                Arguments.of(XpathUtil.combineLinkXpath(COOKIES_ENDPOINT), COOKIES_ENDPOINT, COOKIES),
                Arguments.of(XpathUtil.combineLinkXpath(FRAMES_ENDPOINT), FRAMES_ENDPOINT, FRAMES),
                Arguments.of(XpathUtil.combineLinkXpath(IFRAMES_ENDPOINT), IFRAMES_ENDPOINT, IFRAMES_TITLE),
                Arguments.of(XpathUtil.combineLinkXpath(DIALOG_BOXES_ENDPOINT), DIALOG_BOXES_ENDPOINT, DIALOG_BOXES),
                Arguments.of(XpathUtil.combineLinkXpath(WEB_STORAGE_ENDPOINT), WEB_STORAGE_ENDPOINT, WEB_STORAGE),
                Arguments.of(XpathUtil.combineLinkXpath(GEOLOCATION_ENDPOINT), GEOLOCATION_ENDPOINT, GEOLOCATION),
                Arguments.of(XpathUtil.combineLinkXpath(NOTIFICATIONS_ENDPOINT), NOTIFICATIONS_ENDPOINT, NOTIFICATIONS),
                Arguments.of(XpathUtil.combineLinkXpath(GET_USER_MEDIA_ENDPOINT), GET_USER_MEDIA_ENDPOINT, GET_USER_MEDIA),
                Arguments.of(XpathUtil.combineLinkXpath(MULTI_LANGUAGE_ENDPOINT), MULTI_LANGUAGE_ENDPOINT, MULTI_LANGUAGE_TITLE),
                Arguments.of(XpathUtil.combineLinkXpath(CONSOLE_LOGS_ENDPOINT), CONSOLE_LOGS_ENDPOINT, CONSOLE_LOGS),
                Arguments.of(XpathUtil.combineLinkXpath(LOGIN_FORM_ENDPOINT), LOGIN_FORM_ENDPOINT, LOGIN_FORM),
                Arguments.of(XpathUtil.combineLinkXpath(SLOW_LOGIN_ENDPOINT), SLOW_LOGIN_ENDPOINT, SLOW_LOGIN_TITLE),
                Arguments.of(XpathUtil.combineLinkXpath(RANDOM_CALCULATOR_ENDPOINT), RANDOM_CALCULATOR_ENDPOINT, RANDOM_CALCULATOR),
                Arguments.of(XpathUtil.combineLinkXpath(DOWNLOAD_FILES_ENDPOINT), DOWNLOAD_FILES_ENDPOINT, DOWNLOAD_FILES),
                Arguments.of(XpathUtil.combineLinkXpath(AB_TESTING_ENDPOINT), AB_TESTING_ENDPOINT, AB_TESTING),
                Arguments.of(XpathUtil.combineLinkXpath(DATA_TYPES_ENDPOINT), DATA_TYPES_ENDPOINT, DATA_TYPES)
        );
    }
}
