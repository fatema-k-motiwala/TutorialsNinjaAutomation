package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class FooterOptionsPage extends RootPage {

	WebDriver driver;

	public FooterOptionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//footer//a[text()='About Us']")
	private WebElement aboutUs;

	@FindBy(xpath = "//footer//a[text()='Delivery Information']")
	private WebElement deliveryInformation;

	@FindBy(xpath = "//footer//a[text()='Privacy Policy']")
	private WebElement privacyPolicy;

	@FindBy(xpath = "//footer//a[text()='Terms & Conditions']")
	private WebElement termsConditions;

	@FindBy(xpath = "//footer//a[text()='Contact Us']")
	private WebElement contactUs;

	@FindBy(xpath = "//footer//a[text()='Returns']")
	private WebElement returns;

	@FindBy(xpath = "//footer//a[text()='Site Map']")
	private WebElement siteMap;

	@FindBy(xpath = "//footer//a[text()='Brands']")
	private WebElement brands;

	@FindBy(xpath = "//footer//a[text()='Gift Certificates']")
	private WebElement giftCertificate;

	@FindBy(xpath = "//footer//a[text()='Affiliate']")
	private WebElement affiliate;

	@FindBy(xpath = "//footer//a[text()='Specials']")
	private WebElement specials;

	@FindBy(xpath = "//footer//a[text()='My Account']")
	private WebElement myAccount;

	@FindBy(xpath = "//footer//a[text()='Order History']")
	private WebElement orderHistory;

	@FindBy(xpath = "//footer//a[text()='Wish List']")
	private WebElement wishList;

	@FindBy(xpath = "//footer//a[text()='Newsletter']")
	private WebElement newsletter;

	public AboutUsPage clickOnAboutUs() {
		elementUtilities.clickOnElement(aboutUs);
		return new AboutUsPage(driver);
	}

	public DeliveryInformationPage clickOnDeliveryInformation() {
		elementUtilities.clickOnElement(deliveryInformation);
		return new DeliveryInformationPage(driver);
	}

	public PrivacyPolicyPage clickOnPrivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicy);
		return new PrivacyPolicyPage(driver);
	}

	public TermsConditionsPage clickOnTermsConditions() {
		elementUtilities.clickOnElement(termsConditions);
		return new TermsConditionsPage(driver);
	}

	public ContactUsPage clickOnContactUs() {
		elementUtilities.clickOnElement(contactUs);
		return new ContactUsPage(driver);
	}

	public ReturnsPage clickOnReturns() {
		elementUtilities.clickOnElement(returns);
		return new ReturnsPage(driver);
	}

	public SiteMapPage clickOnSiteMap() {
		elementUtilities.clickOnElement(siteMap);
		return new SiteMapPage(driver);
	}

	public BrandsPage clickOnBrands() {
		elementUtilities.clickOnElement(brands);
		return new BrandsPage(driver);
	}

	public GiftCertificatePage clickOnGiftCertificate() {
		elementUtilities.clickOnElement(giftCertificate);
		return new GiftCertificatePage(driver);
	}

	public AffiliatePage clickOnAffiliate() {
		elementUtilities.clickOnElement(affiliate);
		return new AffiliatePage(driver);
	}

	public SpecialsPage clickOnSpecials() {
		elementUtilities.clickOnElement(specials);
		return new SpecialsPage(driver);
	}

	public LoginPage clickOnMyAccount() {
		elementUtilities.clickOnElement(myAccount);
		return new LoginPage(driver);
	}

	public LoginPage clickOnOrderHistory() {
		elementUtilities.clickOnElement(orderHistory);
		return new LoginPage(driver);
	}

	public LoginPage clickOnWishList() {
		elementUtilities.clickOnElement(wishList);
		return new LoginPage(driver);
	}

	public LoginPage clickOnNewsletter() {
		elementUtilities.clickOnElement(newsletter);
		return new LoginPage(driver);
	}

}
