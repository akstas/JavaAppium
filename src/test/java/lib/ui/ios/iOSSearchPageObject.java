package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "name:search";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_EMPTY_RESULT_ELEMENT_IMAGE = "xpath://*[@resource_id='org.wikipedia:id/search_empty_image']";
        SEARCH_CLEAR_MINI = "id:clear mini";
        SEARCH_LOCATOR_RREPLACE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRINGTITLE}')]/..//*[contains(@name,'{SUBSTRINGDESCRIPTION}')]";
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
