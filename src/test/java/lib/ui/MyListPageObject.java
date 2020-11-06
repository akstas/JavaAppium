package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

abstract public class MyListPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            SWIPE_ACTION_DELETE,
            REMOVE_FROM_SAVED_BUTTON,
            CONTENT_COUNT,
            PAGE_HEADING;

    public MyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public MyListPageObject openFolderByName(String folderName)
    {
        String FOLDER_NAME = getFolderXpathByName(folderName);
        this.waitForElementPresentAndClick(
                FOLDER_NAME,
                "Cannot find created folder",
                10);
        return this;
    }
    private static String getFolderXpathByName(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }
    private static String getSavedArticleXpathByName(String folderName)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", folderName);
    }
    private static String getXpathByNameInPage(String folderName)
    {
        return PAGE_HEADING.replace("{TITLE}", folderName);
    }

    public MyListPageObject swipeByArticleToDelete(String articleTitle)
    {
        waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByName(articleTitle);

        if (!Platform.getInstance().isMW()){
            this.swipeElementToLeft(
                    articleXpath,
                    "Cannot find saved article");
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementPresentAndClick(
                    removeLocator,
                    "Cannot click button to remove article from saved.",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorer(articleXpath, "Cannot find saved article");
            this.waitForElementPresentAndClick(SWIPE_ACTION_DELETE, "Cannot find delete button", 10);
        }
        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(articleTitle);
        return this;
    }

    private static String getRemoveButtonByTitle(String articleTitle)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    private static String getArticleXpathByName(String articleTitle)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_NAME}", articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle)
    {
        String articleXpath = getSavedArticleXpathByName(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still present with title " + articleTitle, 15);
    }
    public void waitForArticleToAppearByTitle(String articleTitle)
    {
        String articleXpath = getSavedArticleXpathByName(articleTitle);
        this.waitForElementPresent(articleXpath, "Saved article still not present with title " + articleTitle, 15);
    }

    public void CheckCountAndArticleInList(String articleTitle){

        List<WebElement> elements = driver.findElements(By.cssSelector(CONTENT_COUNT));  //getAmountOfElement
        String articleXpathInList = getSavedArticleXpathByName(articleTitle);
        int a = elements.size();
        if (elements.size() == 1){
            this.waitForElementPresentAndClick(
                    articleXpathInList,
                    "Article not present",
                    1);
        }
       // String articleXpathInPage = getXpathByNameInPage(articleTitle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String getTextTittle = articlePageObject.getArticleTitle();
        Assert.assertEquals("Title not expected",getTextTittle, articleTitle);

    }
}
