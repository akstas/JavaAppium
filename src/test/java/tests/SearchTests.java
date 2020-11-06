package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLineValue = "Linkin Park Diskography";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine(searchLineValue);
        int amountOfSearchResults = searchPageObject.getAmmountOfFoundArticles();
        assertTrue("We found too few result", amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchLineValue = "Android Kotlin";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine(searchLineValue)
                .waitForEmptyResultsLabel()
                .assertThereIsNotResultsOfSearch();
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .waitForCancelButtonToAppear()
                .clickCancelSearch()
                .waitForCancelButtonToDisapear();
    }

    @Test
    public void testSerchListEx3()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine("Android");
        int searchResultAmmountBefore = searchPageObject.getAmmountOfFoundArticles();
        assertTrue("Articles not still on the list", searchResultAmmountBefore > 0);
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNotResultsAfterCloseCancelOfSearch();
    }

    @Test
    public void testAssertTitleEx6() {
        String searchValue = "Java";
        String textTitle = "Java (programming language)";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine(searchValue)
                .clickByArticleWithSubstring(textTitle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String getTextTittle = articlePageObject.getArticleTitle();
        assertEquals("Expected title not equal", getTextTittle, textTitle);
    }

    @Test
    public void testSearchTitleAndDiscriptionEx9() throws InterruptedException {
        String searchFirstValue = "Java";
        String titleFirstValue = "Java (programming language)";
        String searchSecondValue = "Android";
        String titleSecondValue = "Android (operating system)";
        String searchThirdValue = "Kotlin";
        String titleThirdValue = "Kotlin (programming language)";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject
                .initSearchInput(false)
                .typeSearchLine(searchFirstValue);
        searchPageObject.waitForElementByTitleAndDescription(titleFirstValue, "Object-oriented programming language");
        searchPageObject.clickCancelSearch();

        searchPageObject
                .initSearchInput(true)
                .typeSearchLine(searchSecondValue);
        searchPageObject.waitForElementByTitleAndDescription(titleSecondValue, "Mobile operating system");
        searchPageObject.clickCancelSearch();

        searchPageObject
                .initSearchInput(true)
                .typeSearchLine(searchThirdValue);
        searchPageObject.waitForElementByTitleAndDescription(titleThirdValue, "General-purpose programming language");
    }
}