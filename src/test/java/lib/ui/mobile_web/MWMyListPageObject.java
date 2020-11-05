package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {
    public MWMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
    static
    {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'mw-mf-watchlist-page-list')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'mw-mf-watchlist-page-list')]//h3[contains(text(),'Java (programming language)')]/../../..//*[contains(@class, 'watched')]";
    }
}
