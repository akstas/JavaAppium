package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject
{

    private static final String
        STEP_LEARN_MORE_ABOUT_WIKIPEDIA = "name:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE = "name:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "name:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "name:Learn more about data collected",
        NEXT_LINK = "name:Next",
        GET_STARTED_BUTTON = "name:Get started",
        SKIP = "name:Skip";

    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_WIKIPEDIA, "Cannot find link 'Learn more about Wikipedia link'", 10);
    }
    public void waitForNewTextNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find text 'New ways to explore'", 10);
    }
    public void waitForNewLinkAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES, "Cannot find link 'Add or edit preferred languages'", 10);
    }
    public void waitForNewLinkLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Cannot find link 'Learn more about data collected'", 10);
    }
    public void clickForNextButton()
    {
        this.waitForElementPresentAndClick(NEXT_LINK, "Cannot find button 'Next'", 10);
    }
    public void clickGetStartedButton()
    {
        this.waitForElementPresentAndClick(GET_STARTED_BUTTON, "Cannot find button 'Get started'", 10);
    }
    public void clickSkip()
    {
        this.waitForElementPresentAndClick(SKIP, "Cannot find link 'SKIP'", 15);
    }
}
