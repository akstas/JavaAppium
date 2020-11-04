package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    static { MY_LISTS = "id:Saved"; }

}
