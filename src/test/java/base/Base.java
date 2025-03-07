package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;

import pages.AboutUsPage;
import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.AffiliatePage;
import pages.BrandsPage;
import pages.ChangePasswordPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptionsPage;
import pages.ForgotYourPasswordPage;
import pages.GiftCertificatePage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.NewsLetterPage;
import pages.PrivacyPolicyPage;
import pages.ProductComparisionPage;
import pages.ProductDisplayPage;
import pages.RegisterPage;
import pages.ReturnsPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialsPage;
import pages.TermsConditionsPage;
import utils.CommonUtilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public String browserName;
	public RegisterPage registerPage;
	public HeaderOptions headerOptions;
	public AccountSuccessPage accountSuccessPage;
	public MyAccountPage myAccountPage;
	public NewsLetterPage newsLetterPage;
	public LoginPage loginPage;
	public RightColumnOptions rightColumnOptions;
	public MyAccountInformationPage myAccountInformationPage;
	public ContactUsPage contactUsPage;
	public ShoppingCartPage shoppingCartPage;
	public HomePage homePage;
	public SearchPage searchPage;
	public ForgotYourPasswordPage forgotYourPasswordPage;
	public FooterOptionsPage footerOptionsPage;
	public AboutUsPage aboutUsPage;
	public DeliveryInformationPage deliveryInformationPage;
	public PrivacyPolicyPage privacyPolicyPage;
	public TermsConditionsPage termsConditionsPage;
	public ReturnsPage returnsPage;
	public SiteMapPage siteMapPage;
	public BrandsPage brandsPage;
	public GiftCertificatePage giftCertificatePage;
	public AffiliatePage affiliatePage;
	public SpecialsPage specialsPage;
	public Actions actions;
	public ChangePasswordPage changePasswordPage;
	public AccountLogoutPage accountLogoutPage;
	public ProductDisplayPage productDisplayPage;
	public ProductComparisionPage productComparisionPage;
	
	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver openBrowserAndApp1icationPageURL() {
		prop = CommonUtilities.loadPropertiesFile();
		browserName = prop.getProperty("browserName");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("internetexplorer")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("appURL"));

		return driver;

	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
	}


	public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}

	public Actions clickKeyboradKeyMu1tip1eTimes(Actions actions, Keys keyName, int noOfTimes) {
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}

	public Actions typeTextUsingActions(Actions actions, String text) {
		actions.sendKeys(text).perform();
		return actions;
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public Properties swapPasswords(Properties prop) {
		String oldPassword = prop.getProperty("validPasswordTwo");
		String newPassword = prop.getProperty("validPasswordThree");
		prop.setProperty("validPasswordTwo", newPassword);
		prop.setProperty("validPasswordThree", oldPassword);
	    prop = CommonUtilities.storePropertiesFile(prop);
	    return prop;
	}
	
}
