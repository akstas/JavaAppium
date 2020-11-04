package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResult()
    {
        if (Platform.getInstance().isMW())
        {
            return;
        }
        String searchValue = "Java";
        String attributeTitle = "Java (programming language)";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchValue)
                .clickByArticleWithSubstring(attributeTitle);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        rotateScreenLandscape();
        String titleAfterLandscapeRotation = ArticlePageObject.getArticleTitle();
        assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterLandscapeRotation);
        rotateScreenPortrait();
        String titleAfterPortraitRotation = ArticlePageObject.getArticleTitle();
        assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterPortraitRotation);
    }
    @Test
    public void testCheckSearchArticleInBackground()
    {
        if (Platform.getInstance().isMW())
        {
            return;
        }
        String searchValue = "Java";
        String attributeTitle = "Object-oriented programming language";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput()
                .typeSearchLine(searchValue)
                .waitForSearchResult(attributeTitle);
        backgroundApp(2);
        searchPageObject.waitForSearchResult(attributeTitle);
    }
}
