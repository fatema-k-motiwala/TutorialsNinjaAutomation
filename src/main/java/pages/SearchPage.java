package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.CommonUtilities;

public class SearchPage extends RootPage {

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchBreadcrumb;

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProduct;

	@FindBy(linkText = "iMac")
	private WebElement existingProductTwo;

	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMessage;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> productThumbnail;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaBox;

	@FindBy(id = "button-search")
	private WebElement searchButton;

	@FindBy(id = "description")
	private WebElement searchlnProductDescriptionField;

	@FindBy(name = "category_id")
	private WebElement categoryId;

	@FindBy(name = "sub_category")
	private WebElement subCategoryIdCheckBox;

	@FindBy(id = "list-view")
	private WebElement listOption;

	@FindBy(xpath = "//div[@class='product-thumb']//span[text()='Add to Cart']")
	private WebElement addToCartOption;

	@FindBy(xpath = "//button[@*='Add to Wish List']")
	private WebElement addToWishListOption;

	@FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[1]")
	private WebElement productOneName;

	@FindBy(xpath = "//button[@*='Compare this Product']")
	private WebElement compareThisProductOption;

	@FindBy(xpath = "(//div[@class='product-thumb']//img)[1]")
	private WebElement productOneImage;

	@FindBy(id = "grid-view")
	private WebElement gridOption;

	@FindBy(id = "compare-total")
	private WebElement productCompareOption;

	@FindBy(id = "input-sort")
	private WebElement sortDropdownField;
	
	@FindBy(xpath = "//div[@class='product-thumb']//h4/a")
	private List<WebElement> sortedProducts;
	
	@FindBy(id = "input-limit")
	private WebElement showCountDropdown;
	
	public void selectOptionInShowCountDropdown(String optionText) {
		elementUtilities.selectOptionFromDropdownFieldUsingText(showCountDropdown, optionText);}


	public void selectSortOptionInDropdownField(String optionText) {
	elementUtilities.selectOptionFromDropdownFieldUsingText(sortDropdownField, optionText);}

	public boolean areProductsDisplayedInAscendingOrder() {
		List<String> originalList = elementUtilities.getTextOfElements(sortedProducts) ;
		return CommonUtilities.areItemsinListInAscendingOrder(originalList);
	}
	
	public ProductComparisionPage selectProductCompareOption() {
		elementUtilities.clickOnElement(productCompareOption);
		return new ProductComparisionPage(driver);
	}

	public void selectGridOption() {
		elementUtilities.clickOnElement(gridOption);
	}

	public ProductDisplayPage clickOnProductOneImage() {
		elementUtilities.clickOnElement(productOneImage);
		return new ProductDisplayPage(driver);
	}

	public void clickOnCompareThisProductOption() {
		elementUtilities.clickOnElement(compareThisProductOption);
	}

	public ProductDisplayPage clickOnProductOneName() {
		elementUtilities.clickOnElement(productOneName);
		return new ProductDisplayPage(driver);
	}

	public void clickOnAddToWishListOption() {
		elementUtilities.clickOnElement(addToWishListOption);
	}

	public void clickOnAddToCartOption() {
		elementUtilities.clickOnElement(addToCartOption);
	}

	public void selectListOption() {
		elementUtilities.clickOnElement(listOption);
	}

	public void selectOptionFromCategoryIdUsingIndex(int optionIndex) {
		elementUtilities.selectOptionFromDropdownFieldUsingIndex(categoryId, optionIndex);
	}

	public void selectOptionFromCategoryIdUsingText(String optionText) {
		elementUtilities.selectOptionFromDropdownFieldUsingText(categoryId, optionText);
	}

	public void clickOnSubCategoryIdCheckBox() {
		elementUtilities.clickOnElement(subCategoryIdCheckBox);
	}

	public void clickOnSearchlnProductDescriptionField() {
		elementUtilities.clickOnElement(searchlnProductDescriptionField);
	}

	public void clickOnSearchButton() {
		elementUtilities.clickOnElement(searchButton);
	}

	public void enterTextIntoSearchCriteriaBox(String product) {
		elementUtilities.enterTextIntoElement(searchCriteriaBox, product);
	}

	public String getSearchCriteriaBoxPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(searchCriteriaBox, "placeholder");
	}

	public int getProductCount() {
		return elementUtilities.getElementsCount(productThumbnail);
	}

	public String getNoProductMessage() {
		return elementUtilities.getElementText(noProductMessage);
	}

	public boolean didWeNavigateToSearchPage() {
		return elementUtilities.isElementDisplayed(searchBreadcrumb);

	}
	public SearchPage clickOnBreadcrumb() {
		elementUtilities.clickOnElement(searchBreadcrumb);
		return new SearchPage(driver);
	}

	

	public boolean isProductDisplayedInSearchResults() {
		return elementUtilities.isElementDisplayed(existingProduct);

	}

	public boolean isDescriptionProductDisplayedInSearchResults() {
		return elementUtilities.isElementDisplayed(existingProductTwo);

	}
}
