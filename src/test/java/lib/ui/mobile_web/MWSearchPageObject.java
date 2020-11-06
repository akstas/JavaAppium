package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "xpath:/html/body/div[3]/div/div[1]/div/div[2]/button";  //"css:button.cancel";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.mw-search-nonefound]";
        SEARCH_LOCATOR_RREPLACE_TPL = "xpath://*[contains(text(),'Java')]/../../..//*[contains(text(),'{SUBSTRINGTITLE}')]/../../..//div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRINGDESCRIPTION}')]";
        SEARCH_CLEAR_MINI = "css:button.mw-ui-icon-small.clear";

    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
