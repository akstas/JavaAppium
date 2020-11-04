package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListPageObject {
    public iOSMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    static
    {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SWIPE_ACTION_DELETE = "xpath://XCUIElementTypeButton[contains(@name,'swipe action delete')]";//"id:swipe action delete";
    }
}
