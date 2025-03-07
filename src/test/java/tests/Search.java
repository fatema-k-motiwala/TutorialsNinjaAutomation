package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;

public class Search extends Base {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApp1icationPageURL();
		headerOptions = new HeaderOptions(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithAnExistingProduct() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProduct"));
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	}

	@Test(priority = 2)
	public void verifySearchWithAnNonExistingProduct() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("nonExisitingProduct"));
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}
	
	@Test(priority = 4)
	public void verifySearchAfterLogin() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmailTwo"),prop.getProperty("validPasswordTwo"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProduct"));
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	}
	
	@Test(priority = 5)
	public void verifySearchWithProductHavingMultipleProducts() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProduct"));
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount() > 0);
	}
	
	@Test(priority = 6)
	public void verifyPlaceholdersInSearchPage() {
			Assert.assertEquals(headerOptions.getSearchBoxPlaceHolderText(), "Search");
			searchPage = headerOptions.selectSearchButton();
			Assert.assertEquals(searchPage.getSearchCriteriaBoxPlaceHolderText(), "Keywords");

	
	}
	
	@Test(priority = 7)
	public void verifySearchUsingSerchCriteriaField() {
			searchPage = headerOptions.selectSearchButton();
			searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("exisitingProduct"));
			searchPage.clickOnSearchButton();
			Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
			Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	
	}
	
	@Test(priority = 8)
	public void verifySearchUsingTextInProductDescription() {
			searchPage = headerOptions.selectSearchButton();
			searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("textInProductDescription"));
			searchPage.clickOnSearchlnProductDescriptionField();
			searchPage.clickOnSearchButton();
			Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
			Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
	
	}
	
	@Test(priority = 9)
	public void verifySearchBySelectingCategoryOfProduct() {
			searchPage = headerOptions.selectSearchButton();
			searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("exisitingProductTwo"));
			searchPage.selectOptionFromCategoryIdUsingIndex(prop.getProperty("CorrectcategoryId"));
			searchPage.clickOnSearchButton();
			Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
			searchPage.selectOptionFromCategoryIdUsingIndex(prop.getProperty("WrongcategoryId"));
			searchPage.clickOnSearchButton();
			Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");			
	
	}
	
	@Test(priority = 10)
	public void verifySearchBySelectingSubCategoryOfProduct() {
			searchPage = headerOptions.selectSearchButton();
			searchPage.enterTextIntoSearchCriteriaBox(prop.getProperty("exisitingProductTwo"));
			searchPage.selectOptionFromCategoryIdUsingText(prop.getProperty("ParentCategory"));
			searchPage.clickOnSearchButton();
			Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");			
			searchPage.clickOnSubCategoryIdCheckBox();
			searchPage.clickOnSearchButton();
			Assert.assertTrue(searchPage.isDescriptionProductDisplayedInSearchResults());
			
	}
	
	@Test(priority = 11)
	public void verifyListAndGridViewsInSearchResultsPageHavingOneProduct() throws InterruptedException {
		
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProductTwo"));
		searchPage = headerOptions.selectSearchButton();
		searchPage.selectListOption();
		Assert.assertTrue(searchPage.getProductCount()==1);
		searchPage.clickOnAddToCartOption();
		String expectedMessage = "Success: You have added "+prop.getProperty("exisitingProductTwo")+" to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save "+prop.getProperty("exisitingProductTwo")+" to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = "Success: You have added "+prop.getProperty("exisitingProductTwo")+" to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		refreshPage(searchPage.getDriver());
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductCount()==1);
		searchPage.clickOnAddToCartOption();
		expectedMessage = "Success: You have added "+prop.getProperty("exisitingProductTwo")+" to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save "+prop.getProperty("exisitingProductTwo")+" to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = "Success: You have added "+prop.getProperty("exisitingProductTwo")+" to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccess().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		
	}

	@Test(priority = 12)
	public void verifyListAndGridViewsWhenMu1tip1eProductsAreDisp1ayed() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProductThree"));
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount() > 0);
		searchPage.selectListOption();
		Assert.assertTrue(searchPage.getProductCount() > 0);
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductCount() > 0);
		
		
	}
	
	@Test(priority = 13)
	public void verifyNavigationToProductComparisonPageFromSearchResu1tsPage() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProductTwo"));
		searchPage = headerOptions.selectSearchButton();
		productComparisionPage = searchPage.selectProductCompareOption();
		Assert.assertTrue(productComparisionPage.didWeNavigateToProductComparisonPage());
		
	}
	
	@Test(priority = 14)
	public void verifyA11SortingOptionsInSearchResu1tsPage() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProductThree"));
		searchPage = headerOptions.selectSearchButton();
		searchPage.selectSortOptionInDropdownField("Default");
		Assert.assertTrue(searchPage.areProductsDisplayedInAscendingOrder());
	}
	
	@Test(priority = 15)
	public void verifyShowProductsByLimitingCount() {
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exisitingProductThree"));
		searchPage = headerOptions.selectSearchButton();
		Assert.assertTrue(searchPage.getProductCount() > 0);
		String productLimitOne = "20";
		searchPage.selectOptionInShowCountDropdown(productLimitOne);
		Assert.assertTrue(searchPage.getProductCount() == Integer.parseInt(productLimitOne));
		String productLimitTwo = "25";
		searchPage.selectOptionInShowCountDropdown(productLimitOne);
		Assert.assertTrue(searchPage.getProductCount() == Integer.parseInt(productLimitTwo));
		String productLimitThree = "50";
		searchPage.selectOptionInShowCountDropdown(productLimitOne);
		Assert.assertTrue(searchPage.getProductCount() == Integer.parseInt(productLimitThree));
		String productLimitFour = "75";
		searchPage.selectOptionInShowCountDropdown(productLimitOne);
		Assert.assertTrue(searchPage.getProductCount() == Integer.parseInt(productLimitFour));
		String productLimitFive = "100";
		searchPage.selectOptionInShowCountDropdown(productLimitOne);
		Assert.assertTrue(searchPage.getProductCount() == Integer.parseInt(productLimitFive));
	}
	
	
	
}
