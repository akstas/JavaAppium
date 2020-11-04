package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    protected static String MY_LISTS,
                            OPEN_NAVIGATION;

    public void openNavigation()
    {
        if (Platform.getInstance().isMW()){
            this.waitForElementPresentAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button.", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(
                    MY_LISTS,
                    "Cannot find navigation button to my list",
                    5
            );
        }
        this.waitForElementPresentAndClick(
                MY_LISTS,
                "Cannot find navigation button to my list",
                5
        );
    }
}
