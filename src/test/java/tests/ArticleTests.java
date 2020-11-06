package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.mobile_web.AuthorizationPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    private static final String
            login = "",    // yourLogin
            password = ""; // yourPassword


    @Test
    public void testCompareArticleTitle()
    {
        String titleName = "Object-oriented programming language";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine("Java")
                .clickByArticleWithSubstring(titleName);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("We see unxpected title","Java (programming language)", articleTitle);
    }
    @Test
    public void testSwipeArticle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
    @Test
    public void testAddTwoArticleToOneFolderListEx5()  throws InterruptedException {
        String searchFirstValue = "Java";
        String firstTitleText = "Object-oriented programming language";
        String searchSecondValue = "JavaScript";
        String secondTextElement = "High-level programming language";
        String folderName = "Learning programming";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine(searchFirstValue)
                .clickByArticleWithSubstring(firstTitleText);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid())
        {
            articlePageObject.addArticleToMyList(folderName, true);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        if (!Platform.getInstance().isMW()){
            if(articlePageObject.checkScreenSyncYourPreferences())
            {
                articlePageObject.clickCloseSyncYourPreferences();
            }
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login.",
                    articleTitle,
                    articlePageObject.getArticleTitle());

            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput(false);
        if (Platform.getInstance().isIOS()) {
            searchPageObject.EraseSearchInput();
        }
        searchPageObject
                .typeSearchLine(searchSecondValue)
                .clickByArticleWithSubstring(secondTextElement);
        if (Platform.getInstance().isAndroid())
        {
            articlePageObject.addArticleToMyList(folderName, false);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListPageObject
                    .openFolderByName(folderName)
                    .swipeByArticleToDelete(searchSecondValue);
        } else{
            myListPageObject
                    .swipeByArticleToDelete(searchSecondValue);
        }
        assertEquals("First document is not in the list","Java (programming language)", articleTitle);
        if (Platform.getInstance().isMW()){
            myListPageObject.CheckCountAndArticleInList("Java (programming language)");
        }
    }
}
