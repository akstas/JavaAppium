package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;
    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSecounds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSecounds);
        return element.getAttribute(attribute);
    }
    public int getAmountOfElement(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void swipeElementToLeft(String locator, String errorMessage){
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(
                    locator,
                    errorMessage,
                    10
            );
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(PointOption.point(rightX, middleY));
            action.waitAction(WaitOptions.waitOptions((Duration.ofMillis(300))));
            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(leftX, middleY));
            } else {
                int offsetX = (-2 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offsetX, 0));
            }

            action.release();
            action.perform();
        } else {
            System.out.println("Method swipeUp() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    public void swipeUpToElement(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0){
            if (alreadySwiped > maxSwipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }
    public void swipeUpTitleElementAppear(String locator, String errorMessage, int maxSwipes)
    {
        int alreadySwiped = 0;
        while (!isElementLocatedOnTheScreen(locator))
        {
            if(alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }
    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int elementLocationByY= this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();

        if (Platform.getInstance().isMW()) {
        JavascriptExecutor JSExecuter = (JavascriptExecutor) driver;
        Object jsResult = JSExecuter.executeScript("return window.pageYOffset");
        elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }
    public void swipeUpQuick() {

        swipeUp(200);

    }
    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);
            action
                    .press(PointOption.point(x, startY))
                    .waitAction(WaitOptions.waitOptions((Duration.ofMillis(timeOfSwipe))))
                    .moveTo(PointOption.point(x, endY))
                    .release()
                    .perform();
            } else {
            System.out.println("Method swipeUp() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    public WebElement waitForElementPresentAndDoubleClick(String locator, String errorMessage, int timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.click();
        element.click();
        return element;
    }
    public WebElement waitForElementPresentAndClick(String locator, String errorMessage, int timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }
    public WebElement waitForElementPresentByIdAndSendKeys(String locator, String value ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }
    public WebElement waitForElementPresent(String locator, String errorMessage, long timeOutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeOutInMessage)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInMessage);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitForElementAndClear(String locator ,String errorMessage, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    private By getLocatorByString(String locatorWithType)
    {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")){return By.xpath(locator);}
        else if (byType.equals("id")){return By.id(locator);}
        else if (byType.equals("name")){return By.name(locator);}
        else if (byType.equals("css")){return By.cssSelector(locator);}
        else{ throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);}
    }
    public void clickElementToTheRightUpperCorer(String locator, String errorMessage)
    {
        if (driver instanceof AppiumDriver){
            WebElement element = waitForElementPresent(locator, errorMessage, 10);
            int rightX = element.getLocation().getX();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getWidth();
            int middleY = (upperY + lowerY) / 2;
            int width = element.getSize().getWidth();

            int pointToClickX = (rightX + width) -3;
            int pointToClickY = middleY;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.longPress(PointOption.point(pointToClickX, pointToClickY)).perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorer() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecuter = (JavascriptExecutor) driver;
            JSExecuter.executeScript("window.scrollBy(0, 250)");
            } else {
                System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    public void scrollWebPAgeTillElementNotVisible(String locator,String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        WebElement element = this.waitForElementPresent(locator, errorMessage, 5);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++alreadySwiped;
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElement(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int amountAttempts){
        int currentAttempts = 0;
        boolean needMoreAttempts = true;

        while (needMoreAttempts) {
            try {
                this.waitForElementPresentAndClick(locator, errorMessage, 1);
                needMoreAttempts = false;
            } catch (Exception e) {
                if (currentAttempts > amountAttempts) {
                    this.waitForElementPresentAndClick(locator, errorMessage, 1);
                }
            }
            ++ currentAttempts;
        }
    }
}
