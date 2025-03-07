package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class LoginPage extends RootPage{

WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath ="//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadcrumb;
	
	@FindBy(linkText = "Forgotten Password")
	private WebElement forgottenPasswordLink;
	
	@FindBy(xpath="(//div[@id='content']//h2)[1]")
	private WebElement firstPageHeading;

	@FindBy(xpath="(//div[@id='content']//h2)[2]")
	private WebElement secondPageHeading;

	public String getFirstPageHeading()
	{
		return elementUtilities.getElementText(firstPageHeading);
	}
	
	public String getSecondPageHeading()
	{
		return elementUtilities.getElementText(secondPageHeading);
	}
	
	public LoginPage selectLoginBreadcrumb() {
		elementUtilities.clickOnElement(loginBreadcrumb);
		return new LoginPage(driver);
	}
	
	public String getEmailFieldPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(emailField, "placeholder");
	}

	public String getPasswordFieldPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(passwordField, "placeholder");
	}
	
	public boolean isForgottenPasswordLinkDisplayed()
	{
		return elementUtilities.isElementDisplayedOnPage(forgottenPasswordLink);
	}
	
	public ForgotYourPasswordPage clickOnForgottenPassword()
	{
		elementUtilities.clickOnElement(forgottenPasswordLink);
		return new ForgotYourPasswordPage(driver);
	}
	
	public RegisterPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new RegisterPage(driver);
	}

	public MyAccountPage logInToApplication(String emailText, String passwordText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);

	}

	public void enterEmailAddress(String emailText)
	{
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}

	public void enterPassword(String passwordText)
	{
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}
	
	public MyAccountPage clickOnLoginButton() {
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}

	public boolean didWeNavigateToLoginPage() {
		return elementUtilities.isElementDisplayedOnPage(loginBreadcrumb);
		 
	}
	
	public String getPasswordDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(passwordField, attributeName);
	}

	public void copyPasswordFromPasswordField() {
		elementUtilities.copyTextUsingKeyboardKeys(driver);
	}
	
	public void pastePasswordIntoEmailField() {
		elementUtilities.pasteTextUsingKeyboardKeys(driver, emailField);
	}

	public String getPastedTextFromEmailField() {
		return elementUtilities.getElementDomProperty(emailField, "value");
	}

}

