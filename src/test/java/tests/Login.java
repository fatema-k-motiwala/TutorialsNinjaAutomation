package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import pages.MyAccountPage;
import utils.CommonUtilities;

public class Login extends Base {

	public WebDriver driver;
	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApplicationPageURL();

		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();

	}

	@Test(priority = 1)
	public void loginUsingValidCredentials() {

		assertTrue(loginPage.didWeNavigateToLoginPage());
		loginPage.enterEmailAddress(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 2)
	public void loginUsingInvalidCredentials() {

		loginPage.enterEmailAddress(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("mismatchingPassword"));
		loginPage.clickOnLoginButton();
		assertEquals(loginPage.getPageLevelWarning(), "Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 3)
	public void loginUsingInvalidEmailAndValidPassword() {

		loginPage.enterEmailAddress(CommonUtilities.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		assertEquals(loginPage.getPageLevelWarning(), "Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 4)
	public void loginUsingValidEmailAndInvalidPassword() {

		loginPage.enterEmailAddress(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("mismatchingPassword"));
		loginPage.clickOnLoginButton();
		assertEquals(loginPage.getPageLevelWarning(), "Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 5)
	public void loginWithoutCreddentials() {

		loginPage.clickOnLoginButton();
		assertEquals(loginPage.getPageLevelWarning(), "Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 6)
	public void verifyForgottenPasswordLink() {

		assertTrue(loginPage.isForgottenPasswordLinkDisplayed());
		forgotYourPasswordPage = loginPage.clickOnForgottenPassword();
		assertTrue(forgotYourPasswordPage.didWeNavigateToForgottenPasswordPage());

	}

	@Test(priority = 7)
	public void verifyLoggingInUsingKeyboardKeys() {

		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, prop.getProperty("existingEmail"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);

		myAccountPage = new MyAccountPage(driver);
		assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@Test(priority = 8)
	public void verifyPlaceholdersInLoginPage() {

		Assert.assertEquals(loginPage.getEmailFieldPlaceHolderText(), "E-Mail Address");
		Assert.assertEquals(loginPage.getPasswordFieldPlaceHolderText(), "Password");

	}

	@Test(priority = 9)
	public void verifyBrowsingBackAfterLogin() {

		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmail"), prop.getProperty("validPassword"));
		navigateBackInBrowser(myAccountPage.getDriver());
		refreshPage(myAccountPage.getDriver());
		assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 10)
	public void verifyBrowsingBackAfterLogout() {

		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		accountLogoutPage = headerOptions.selectLogoutOption();
		navigateBackInBrowser(accountLogoutPage.getDriver());
		refreshPage(accountLogoutPage.getDriver());
		loginPage = accountLogoutPage.getLoginPage();
		assertTrue(loginPage.didWeNavigateToLoginPage());

	}

	@Test(priority = 11)
	public void loginUsingInactiveEmail() {

		myAccountPage = loginPage.logInToApplication(prop.getProperty("inactiveEmail"),
				prop.getProperty("validPassword"));
		assertEquals(loginPage.getPageLevelWarning(), "Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 12)
	public void verifyNumberOfUnsuccessfulAttempts() {
		String invalidEmail = CommonUtilities.generateBrandNewEmail();
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		myAccountPage = loginPage.logInToApplication(invalidEmail, prop.getProperty("mismatchingPassword"));
		Assert.assertEquals(loginPage.getPageLevelWarning(),
				"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
	}

	@Test(priority = 13)
	public void verifyLoginPasswordFieldForVisibility() {

		Assert.assertEquals(loginPage.getPasswordDomAttribute("type"), "password");

	}

	@Test(priority = 14)
	public void verifyCopyingOfTextEnteredIntoPasswordField() {

		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.copyPasswordFromPasswordField();
		loginPage.pastePasswordIntoEmailField();
		Assert.assertNotEquals(loginPage.getPastedTextFromEmailField(), prop.getProperty("validPassword"));
	}

	@Test(priority = 15)
	public void verifyPasswordIsNotVisibleInPageSource() {

		loginPage.enterPassword(prop.getProperty("randomPassword"));
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));
		loginPage.clickOnLoginButton();
		assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));

	}

	@Test(priority = 16)
	public void verifyLoggingIntoApplicaLtionUsingChangedPassword() {

		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordTwo"));
		changePasswordPage = myAccountPage.clickOnChangeYourPasswordOption();
		changePasswordPage.enterPasswordInPasswordField(prop.getProperty("validPasswordThree"));
		changePasswordPage.enterPasswordInPasswordConfirmField(prop.getProperty("validPasswordThree"));
		myAccountPage = changePasswordPage.clickOnContinueButton();
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
		String expectedMessage = "Success: Your password has been successfully updated.";
		Assert.assertEquals(myAccountPage.getPageLevelSuccess(), expectedMessage);
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		homePage = accountLogoutPage.clickOnContinueButton();
		headerOptions = homePage.getHeaderOptions();
		loginPage = headerOptions.navigateToLoginPage();
		loginPage.logInToApplication(prop.getProperty("existingEmailTwo"), prop.getProperty("validPasswordTwo"));
		expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedMessage);
		myAccountPage = loginPage.logInToApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordThree"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
		swapPasswords(prop);

	}

	@Test(priority = 17)
	public void verifyLoginAccountPageNavigations() {

		headerOptions = loginPage.getHeaderOptions();
		contactUsPage = headerOptions.selectPhoneIcon();
		Assert.assertTrue(getPageTitle(contactUsPage.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectHeartIcon();
		Assert.assertEquals(getPageTitle(headerOptions.getDriver()), "Account Login");

		loginPage = headerOptions.selectWishList();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		shoppingCartPage = headerOptions.selectshoppingCartHeaderIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectshoppingCartHeaderOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectcheckoutIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectcheckoutOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		homePage = headerOptions.selectLogo();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		searchPage = headerOptions.selectSearchButton();
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
		navigateBackInBrowser(searchPage.getDriver());

//		homePage = headerOptions.selectHomeBreadcrumb();
//		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
//		navigateBackInBrowser(homePage.getDriver());

		loginPage = headerOptions.selectAccountBreadcrumb();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = loginPage.selectLoginBreadcrumb();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = loginPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgotYourPasswordPage = loginPage.clickOnForgottenPassword();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgotYourPasswordPage.getDriver());

		loginPage.clickOnLoginButton();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		rightColumnOptions = loginPage.getRightColumnOptions();

		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgotYourPasswordPage = rightColumnOptions.clickOnForgotYourPassword();
		Assert.assertEquals(getPageTitle(forgotYourPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgotYourPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRewardsPointsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnReturnsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		footerOptionsPage = loginPage.getFooterOptionsPage();

		aboutUsPage = footerOptionsPage.clickOnAboutUs();
		Assert.assertEquals(getPageTitle(aboutUsPage.getDriver()), "About Us");
		navigateBackInBrowser(aboutUsPage.getDriver());

		deliveryInformationPage = footerOptionsPage.clickOnDeliveryInformation();
		Assert.assertEquals(getPageTitle(deliveryInformationPage.getDriver()), "Delivery Information");
		navigateBackInBrowser(deliveryInformationPage.getDriver());

		privacyPolicyPage = footerOptionsPage.clickOnPrivacyPolicy();
		Assert.assertEquals(getPageTitle(privacyPolicyPage.getDriver()), "Privacy Policy");
		navigateBackInBrowser(privacyPolicyPage.getDriver());

		termsConditionsPage = footerOptionsPage.clickOnTermsConditions();
		Assert.assertEquals(getPageTitle(termsConditionsPage.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsConditionsPage.getDriver());

		contactUsPage = footerOptionsPage.clickOnContactUs();
		Assert.assertEquals(getPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		returnsPage = footerOptionsPage.clickOnReturns();
		Assert.assertEquals(getPageTitle(returnsPage.getDriver()), "Product Returns");
		navigateBackInBrowser(returnsPage.getDriver());

		siteMapPage = footerOptionsPage.clickOnSiteMap();
		Assert.assertEquals(getPageTitle(siteMapPage.getDriver()), "Site Map");
		navigateBackInBrowser(siteMapPage.getDriver());

		brandsPage = footerOptionsPage.clickOnBrands();
		Assert.assertEquals(getPageTitle(brandsPage.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(brandsPage.getDriver());

		giftCertificatePage = footerOptionsPage.clickOnGiftCertificate();
		Assert.assertEquals(getPageTitle(giftCertificatePage.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftCertificatePage.getDriver());

		affiliatePage = footerOptionsPage.clickOnAffiliate();
		Assert.assertEquals(getPageTitle(affiliatePage.getDriver()), "Affiliate Program");
		navigateBackInBrowser(affiliatePage.getDriver());

		specialsPage = footerOptionsPage.clickOnSpecials();
		Assert.assertEquals(getPageTitle(specialsPage.getDriver()), "Special Offers");
		navigateBackInBrowser(specialsPage.getDriver());

		loginPage = footerOptionsPage.clickOnMyAccount();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptionsPage.clickOnOrderHistory();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptionsPage.clickOnWishList();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptionsPage.clickOnNewsletter();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

	}

	@Test(priority = 18)
	public void verifyDifferrentWaysOfNavigatingToLoginPage() {
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		rightColumnOptions = loginPage.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		registerPage = loginPage.clickOnContinueButton();
		loginPage = registerPage.selectLoginPageOption();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

	}

	@Test(priority = 19)
	public void verifyRegisterAccountPageBreadcrumbURLTitleHeading() {

		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		Assert.assertEquals(getPageURL(loginPage.getDriver()), getBaseURL()+prop.getProperty("loginPageURL"));
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		Assert.assertEquals(loginPage.getFirstPageHeading(), "New Customer");
		Assert.assertEquals(loginPage.getSecondPageHeading(), "Returning Customer");

	}

	@Test(priority = 20)
	public void verifyLoginPageUI() throws IOException {

		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageChromeUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageChromeUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageChromeUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageFirefoxUI.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageFirefoxUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageEdgeUI.png"));
		}

	}

	@Test(priority = 21)
	public void verifyRegisterAccountInAllEnvironments() {

		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		loginPage.enterEmailAddress(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		myAccountPage = loginPage.clickOnLoginButton();
		assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}
	
}
