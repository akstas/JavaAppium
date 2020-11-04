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
import org.junit.Assert;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{
    private static final String folderName = "Learning programming";
    private static final String
                        login = "Akstaslearnqa",
                        password = "Akstaslearnqa123";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        //String folderName = "Learning programming";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid())
        {
            articlePageObject.addArticleToMyList(folderName, true);
        }else {
            articlePageObject.addArticleToMySaved();
        }
        if (!Platform.getInstance().isMW()){
        if(articlePageObject.checkScreenSyncYourPreferences())
        {
            articlePageObject.clickCloseSyncYourPreferences();
        }}

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
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(folderName);
        }

            myListPageObject.swipeByArticleToDelete(articleTitle);
    }
}
