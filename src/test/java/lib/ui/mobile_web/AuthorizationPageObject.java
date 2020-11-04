package lib.ui.mobile_web;

import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://body/div//a[text()='Log in']",//"xpath://html/body/div[4]/div[2]/a",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "id:wpLoginAttempt";

    public AuthorizationPageObject (RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementPresentAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementPresentByIdAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementPresentByIdAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);
    }

    public void submitForm() {
        this.waitForElementPresentAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button.", 5);
    }


}
