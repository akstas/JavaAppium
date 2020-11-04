package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_IN_LIST = "id:section_0";
        FOOTER_ELEMENT = "css:mw-footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch']";  //"xpath://*[@id='ca-watch']";//"css:#page-actions-watch mw-ui-icon-wikimedia-star-base20";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch watched";
    }

    public MWArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
