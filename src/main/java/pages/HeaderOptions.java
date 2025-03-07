package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class HeaderOptions extends RootPage {

	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkoutOption;

	@FindBy(linkText = "Qafox.com")
	private WebElement logo;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	@FindBy(name = "search")
	private WebElement searchBox;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(xpath = "//i[@class='fa fa-phone']")
	private WebElement phoneIcon;

	@FindBy(xpath = "//i[@class='fa fa-heart']")
	private WebElement heartIcon;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][contains(text(), 'Wish List')]")
	private WebElement wishList;

	@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
	private WebElement shoppingCartHeaderIcon;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartHeaderOption;

	@FindBy(xpath = "//i[@class='fa fa-share']")
	private WebElement checkoutIcon;

	public String getSearchBoxPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(searchBox, "placeholder");
	}
	
	
	public void enterProductIntoSearchBoxField(String product)
	{
		elementUtilities.enterTextIntoElement(searchBox, product);
	}
	public ShoppingCartPage selectcheckoutIcon() {
		elementUtilities.clickOnElement(checkoutIcon);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectcheckoutOption() {
		elementUtilities.clickOnElement(checkoutOption);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectshoppingCartHeaderOption() {
		elementUtilities.clickOnElement(shoppingCartHeaderOption);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectshoppingCartHeaderIcon() {
		elementUtilities.clickOnElement(shoppingCartHeaderIcon);
		return new ShoppingCartPage(driver);
	}

	public LoginPage selectWishList() {
		elementUtilities.clickOnElement(wishList);
		return new LoginPage(driver);
	}

	public ContactUsPage selectPhoneIcon() {
		elementUtilities.clickOnElement(phoneIcon);
		return new ContactUsPage(driver);
	}

	public LoginPage selectHeartIcon() {
		elementUtilities.clickOnElement(heartIcon);
		return new LoginPage(driver);
	}

	public LoginPage selectLoginOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public void clickOnMyAccountDropMenu() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}

	public RegisterPage selectRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

	public HomePage selectLogo() {
		elementUtilities.clickOnElement(logo);
		return new HomePage(driver);
	}

	public SearchPage selectSearchButton() {
		elementUtilities.clickOnElement(searchButton);
		return new SearchPage(driver);
	}
	
	public AccountLogoutPage selectLogoutOption() {
		elementUtilities.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}

	public boolean isLogoutOptionDisplayed() {
		return elementUtilities.isElementDisplayed(logoutOption);

	}
	
	public boolean isLoginOptionDisplayed() {
	return elementUtilities.isElementDisplayed(loginOption);

}

	public LoginPage navigateToLoginPage() {
		clickOnMyAccountDropMenu();
		return selectLoginOption();
	}

}
