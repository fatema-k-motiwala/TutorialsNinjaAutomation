package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RegisterPage extends RootPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
	private WebElement passwordConfirmationWarning;

	@FindBy(linkText = "login page")
	private WebElement loginPageOption;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerPageBreadcrumb;

	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNameLabel;

	@FindBy(css = "label[for='input-lastname']")
	private WebElement lastNameLabel;

	@FindBy(css = "label[for='input-email']")
	private WebElement emailLabel;

	@FindBy(css = "label[for='input-telephone']")
	private WebElement telephoneLabel;

	@FindBy(css = "label[for='input-password']")
	private WebElement passwordLabel;

	@FindBy(css = "label[for='input-confirm']")
	private WebElement passwordConfirmLabel;

	@FindBy(css = "div[class='pull-right']")
	private WebElement privacyPolicyLabel;

	public WebElement getFirstNameLabel() {
		return firstNameLabel;
	}

	public WebElement getLastNameLabel() {
		return lastNameLabel;
	}

	public WebElement getEmailLabel() {
		return emailLabel;
	}

	public WebElement getTelephoneLabel() {
		return telephoneLabel;
	}

	public WebElement getPasswordLabel() {
		return passwordLabel;
	}

	public WebElement getPasswordConfirmLabel() {
		return passwordConfirmLabel;
	}

	public WebElement getPrivacyPolicyLabel() {
		return privacyPolicyLabel;
	}

	public Boolean isFirstNameWarningDisplayed() {
		return elementUtilities.isElementDisplayed(firstNameWarning);
	}

	public Boolean isPasswordWarningDisplayed() {
		return elementUtilities.isElementDisplayed(passwordWarning);
	}

	public Boolean isTelephoneWarningDisplayed() {
		return elementUtilities.isElementDisplayed(telephoneWarning);
	}

	public Boolean isLastNameWarningDisplayed() {
		return elementUtilities.isElementDisplayed(lastNameWarning);
	}

	public Boolean isEmailWarningDisplayed() {
		return elementUtilities.isElementDisplayed(emailWarning);
	}

	public Boolean didWeNavigatetoRegisterpage() {
		return elementUtilities.isElementDisplayed(registerPageBreadcrumb);
	}

	public LoginPage selectLoginPageOption() {
		elementUtilities.clickOnElement(loginPageOption);
		return new LoginPage(driver);
	}

	public RegisterPage selectRegisterPageBreadcrumb() {
		elementUtilities.clickOnElement(registerPageBreadcrumb);
		return new RegisterPage(driver);
	}

	public String getPasswordConfirmationWarning() {
		return elementUtilities.getElementText(passwordConfirmationWarning);
	}

	public String getfirstNameWarning() {
		return elementUtilities.getElementText(firstNameWarning);
	}

	public String getlastNameWarning() {
		return elementUtilities.getElementText(lastNameWarning);
	}

	public String getemailWarning() {
		return elementUtilities.getElementText(emailWarning);
	}

	public void clearEmailField() {
		elementUtilities.clearTextfromElement(emailField);
	}

	public void clearTelephoneField() {
		elementUtilities.clearTextfromElement(telephoneField);
	}

	public void clearFirstNameField() {
		elementUtilities.clearTextfromElement(firstNameField);
	}

	public void clearlastNameField() {
		elementUtilities.clearTextfromElement(lastNameField);
	}

	public void clearPasswordField() {
		elementUtilities.clearTextfromElement(passwordField);
	}

	public String getFirstNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(firstNameField, propertyName);
	}

	public String getPasswordCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(passwordField, propertyName);

	}

	public String getPasswordConfirmCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(passwordConfirmField, propertyName);
	}

	public String getContinueButtonCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(continueButton, propertyName);
	}

	public String getTelephoneCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(telephoneField, propertyName);
	}

	public String getLastNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(lastNameField, propertyName);
	}

	public String getEmailCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(emailField, propertyName);
	}

	public String getEmailValidationMessage() {
		return elementUtilities.getElementDomProperty(emailField, "validationMessage");
	}

	public String gettelephoneWarning() {
		return elementUtilities.getElementText(telephoneWarning);
	}

	public String getpasswordWarning() {
		return elementUtilities.getElementText(passwordWarning);
	}

	public void selectYesNewsletterOption() {
		elementUtilities.clickOnElement(yesNewsletterOption);
	}

	public void selectNoNewsletterOption() {
		elementUtilities.clickOnElement(noNewsletterOption);
	}

	public AccountSuccessPage registeringAnAccount(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
		enterFirstName(firstNameText);
		enterLastName(lastNameText);
		enterEmail(emailText);
		enterTelephone(telephoneText);
		enterPassword(passwordText);
		enterConfirmationPassword(passwordText);
		selectPrivacyPolicy();
		return clickOnContinueButton();
	}

	public void enterFirstName(String firstNameText) {
		elementUtilities.enterTextIntoElement(firstNameField, firstNameText);
	}

	public void enterLastName(String lastNameText) {
		elementUtilities.enterTextIntoElement(lastNameField, lastNameText);
	}

	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}

	public void enterTelephone(String telephoneText) {
		elementUtilities.enterTextIntoElement(telephoneField, telephoneText);
	}

	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}

	public void enterConfirmationPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordConfirmField, passwordText);
	}

	public void selectPrivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicyField);
	}

	public Boolean isPrivacyPolicySelected() {
		return elementUtilities.isElementSelected(privacyPolicyField);
	}

	public AccountSuccessPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}

	public String getFirstNamePlaceHolderText() {
		return elementUtilities.getElementDomAttribute(firstNameField, "placeholder");
	}

	public String getLasttNamePlaceHolderText() {
		return elementUtilities.getElementDomAttribute(lastNameField, "placeholder");
	}

	public String getEmailPlaceHolderText() {
		return elementUtilities.getElementDomAttribute(emailField, "placeholder");
	}

	public String getTelephonePlaceHolderText() {
		return elementUtilities.getElementDomAttribute(telephoneField, "placeholder");
	}

	public String getPasswordDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(passwordField, attributeName);
	}

	public String getConfirmPasswordDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(passwordConfirmField, attributeName);
	}

}
