package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountPage extends RootPage {

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformation;

	@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeToNewsletterOption;

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPasswordOption;

	@FindBy(linkText="Modify your address book entries")
	private WebElement modifyYourAddressBookEntries;
	
	@FindBy(linkText = "Modify your wish list")
	private WebElement modifyYourWishListOption;
	
	@FindBy(linkText = "View your order history")
	private WebElement viewYourOrderHistoryOption;
	
	@FindBy(linkText = "Your Reward Points")
	private WebElement yourRewardPointsOption;
	
	@FindBy(linkText = "Downloads")
	private WebElement downloadsOption;
	
	@FindBy(linkText = "View your return requests")
	private WebElement viewYourReturnRequestsOption;
	
	@FindBy(linkText = "Your Transactions")
	private WebElement yourTransactionsOption;
	
	@FindBy(linkText = "Recurring payments")
	private WebElement recurringPaymentsOption;
	
	@FindBy(linkText = "Edit your affiliate information")
	private WebElement editYourAffiliateInformationOption;
	
	@FindBy(linkText = "Register for an affiliate account")
	private WebElement registerForAnAffiliateAccount;
	
	@FindBy(linkText = "Custom Affiliate Tracking Code")
	private WebElement customAffilateTrackingCodeOption;
	
	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		elementUtilities.clickOnElement(changeYourPasswordOption);
		return new ChangePasswordPage(driver);
	}
	
	public NewsLetterPage clickOnSubscribeUnsubscribeToNewsletterOption() {
		elementUtilities.clickOnElement(subscribeUnsubscribeToNewsletterOption);
		return new NewsLetterPage(driver);
	}

	public MyAccountInformationPage clickOnEditYourAccountInformation() {
		elementUtilities.clickOnElement(editYourAccountInformation);
		return new MyAccountInformationPage(driver);
	}

	public AffiliateTrackingPage clickOnCustomAffiliateTrackingCodeOption() {
		elementUtilities.clickOnElement(customAffilateTrackingCodeOption);
		return new AffiliateTrackingPage(driver);
	}
	
	public AffiliatePage clickOnEditYourAffiliateInformationOption() {
		elementUtilities.clickEitherOfTheseElements(registerForAnAffiliateAccount,editYourAffiliateInformationOption);
		return new AffiliatePage(driver);
	}
	
	public RecurringPaymentsPage clickOnRecurringPaymentsOption() {
		elementUtilities.clickOnElement(recurringPaymentsOption);
		return new RecurringPaymentsPage(driver);
	}
	
	public YourTransactionsPage clickOnYourTransactionOption() {
		elementUtilities.clickOnElement(yourTransactionsOption);
		return new YourTransactionsPage(driver);
	}
	
	public ReturnsPage clickOnViewYourReturnRequestsOption() {
		elementUtilities.clickOnElement(viewYourReturnRequestsOption);
		return new ReturnsPage(driver);
	}
	
	public RewardPointsPage clickOnRewardPointsOption() {
		elementUtilities.clickOnElement(yourRewardPointsOption);
		return new RewardPointsPage(driver);
	}
	
	
	public DownloadsPage clickOnDownloadsOption() {
		elementUtilities.clickOnElement(downloadsOption);
		return new DownloadsPage(driver);
	}
	
	public OrderHistoryPage clickOnViewYourOrderHistoryOption() {
		elementUtilities.clickOnElement(viewYourOrderHistoryOption);
		return new OrderHistoryPage(driver);
	}
	
	public MyWishListPage clickOnModifyYourWishListOption() {
		elementUtilities.clickOnElement(modifyYourWishListOption);
		return new MyWishListPage(driver);
	}
	
	public AddressBookPage clickOnModifyYourAddressBoxEntriesOption() {
		elementUtilities.clickOnElement(modifyYourAddressBookEntries);
		return new AddressBookPage(driver);
	}

	public boolean didWeNavigateToMyAccountPage() {
		return elementUtilities.isElementDisplayed(editYourAccountInformation);
	}

}
