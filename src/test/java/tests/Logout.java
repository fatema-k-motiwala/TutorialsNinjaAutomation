package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import utils.CommonUtilities;

public class Logout extends Base {
	WebDriver driver;



	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApp1icationPageURL();
		headerOptions = new HeaderOptions(driver);
	}

	@Test(priority = 1)
	public void verifyLoggingOutUsingMyAccountLogoutOption() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionDisplayed());
		accountLogoutPage = headerOptions.getLogoutPage();
		accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@Test(priority = 2)
	public void verifyLoggingOutUsingRightColumnOptions() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionDisplayed());
		accountLogoutPage = headerOptions.getLogoutPage();
		accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@Test(priority = 3)
	public void verifyLoggingOutandBrowsingBack() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		navigateBackInBrowser(driver);
		refreshPage(driver);
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionDisplayed());

	}

	@Test(priority = 4)
	public void verifyNoLogoutOptionBeforeLogininAccountDropMenu() {
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertFalse(headerOptions.isLogoutOptionDisplayed());
	}

	@Test(priority = 5)
	public void verifyNoLogoutOptionBeforeLoginInRightColumnOptions() {
		headerOptions.clickOnMyAccountDropMenu();
		registerPage = headerOptions.selectRegisterOption();
		rightColumnOptions = registerPage.getRightColumnOptions();
		Assert.assertFalse(rightColumnOptions.isLogoutOptionDisplayed());
	}

	@Test(priority = 6)
	public void verifyLoginImmediat1eyAfterLogout() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		headerOptions = accountLogoutPage.getHeaderOptions();
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 7)
	public void verifyLogoutPageBreadcrumbURLTitleHeading() {

		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Account Logout");
		Assert.assertEquals(getPageURL(accountLogoutPage.getDriver()), prop.getProperty("logoutPageURL"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToLogoutPage());
		Assert.assertEquals(accountLogoutPage.getPageHeading(), "Account Logout");

	}

	@Test(priority = 8)
	public void verifyLogoutUI() throws IOException {

		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();

		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutOptions.png"));
		}

		accountLogoutPage = headerOptions.selectLogoutOption();

		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualApplicationLogoutPage.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualApplicationLogoutPage.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedApplicationLogoutPage.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxApplicationLogoutPage.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxApplicationLogoutPage.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxApplicationLogoutPage.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeApplicationLogoutPage.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeApplicationLogoutPage.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeApplicationLogoutPage.png"));
		}
	}

	@Test(priority = 9)
	public void verifyLogOutInAllEnvironments() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.logInToApplication(prop.getProperty("exisitingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionDisplayed());
		accountLogoutPage = headerOptions.getLogoutPage();
		accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

}
