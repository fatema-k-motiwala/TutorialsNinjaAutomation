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
	private WebElement subscribeUnsubscribeToNews1etterOption;

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPasswordOption;

	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		elementUtilities.clickOnElement(changeYourPasswordOption);
		return new ChangePasswordPage(driver);
	}
	
	public NewsLetterPage clickOnSubscribeUnsubscribeToNews1etterOption() {
		elementUtilities.clickOnElement(subscribeUnsubscribeToNews1etterOption);
		return new NewsLetterPage(driver);
	}

	public MyAccountInformationPage clickOnEditYourAccountInformation() {
		elementUtilities.clickOnElement(editYourAccountInformation);
		return new MyAccountInformationPage(driver);
	}

	public boolean didWeNavigateToMyAccountPage() {
		return elementUtilities.isElementDisplayed(editYourAccountInformation);
	}

}
