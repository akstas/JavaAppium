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
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        String titleName = "Object-oriented programming language";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
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
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
    @Test
    public void testAddTwoArticleToOneFolderListEx5(){
        String searchFirstValue = "Java";
        String firstTitleText = "Java (programming language)";
        String searchSecondValue = "JavaScript";
        String secondTextElement = "Programming language";
        String folderName = "Learning programming";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchFirstValue)
                .clickByArticleWithSubstring(firstTitleText);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid())
        {
            articlePageObject.addArticleToMyList(folderName, true);
        } else {
            articlePageObject.addArticleToMySaved();
            if(articlePageObject.checkScreenSyncYourPreferences())
            {
                articlePageObject.clickCloseSyncYourPreferences();
            }
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
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
        String articleTitle = articlePageObject.getArticleTitleInList();
        assertEquals("First document is not in the list",firstTitleText, articleTitle);
    }
}
