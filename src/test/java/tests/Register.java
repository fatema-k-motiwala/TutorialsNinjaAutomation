package tests;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.HeaderOptions;
import utils.CommonUtilities;

public class Register extends Base {

	WebDriver driver;

	
	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApp1icationPageURL();

		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		registerPage = headerOptions.selectRegisterOption();

	}

	@Test(priority = 1)
	public void verifyRegisteringAccountUsingMandatoryFields() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

		String properDetailsOne = "Your Account Has Been Created!";
		String properDetailsTwo = "Congratulations! Your new account has been successfully created!";
		String properDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";

		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsOne));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsTwo));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsThree));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsFour));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsFive));

		myAccountPage = accountSuccessPage.clickOnContinueButton();

		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 2)
	public void verifyThankYourConfirmationEmailOnSuccessfulRegistration() throws InterruptedException {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		String emailText = CommonUtilities.generateBrandNewEmail();
		registerPage.enterEmail(emailText);
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		String email = emailText;
		String appPasscode = "dbmm vncw rtja ewoo";

		Thread.sleep(2000);

		// Gmail IMAP configuration
		String host = "imap.gmail.com";
		String port = "993";
		String username = email; // Your Gmail address
		String appPassword = appPasscode; // Your app password
		String expectedSubject = "Welcome To TutorialNinja";
		String expectedFromEmail = "tutorialsninja<account-update@tn.in>";
		String expectedBodyContent = "Your account has been successfully created.";

		try {
			// Mail server connection properties
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "imaps");
			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", port);
			properties.put("mail.imap.ssl.enable", "true");

			// Connect to the mail server
			Session emailSession = Session.getDefaultInstance(properties);
			Store store = emailSession.getStore("imaps");
			store.connect(host, username, appPassword); // replace email password with App password

			// Open the inbox folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Search for unread emails
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			boolean found = false;
			for (int i = messages.length - 1; i >= 0; i--) {

				Message message = messages[i];

				if (message.getSubject().contains(expectedSubject)) {
					found = true;
					Assert.assertEquals(message.getSubject(), expectedSubject);
					Assert.assertEquals(message.getFrom()[0].toString(), expectedFromEmail);
					Assert.assertTrue(CommonUtilities.getTextFromMessage(message).contains(expectedBodyContent));
					break;
				}
			}

			if (!found) {
				System.out.println("No confirmation email found.");
			}

			// Close the store and folder objects
			inbox.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 3)
	public void verifyRegistringAccountUsingAllFields() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

		String properDetailsOne = "Your Account Has Been Created!";
		String properDetailsTwo = "Congratulations! Your new account has been successfully created!";
		String properDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";

		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsOne));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsTwo));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsThree));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsFour));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetailsFive));

		myAccountPage = accountSuccessPage.clickOnContinueButton();

		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());

	}

	@Test(priority = 4)
	public void verifyWarningMessageOfMandatoryFieldsInRegisterAccountPage() {

		registerPage.clickOnContinueButton();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(registerPage.getfirstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getlastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.getemailWarning(), expectedEmailWarning);
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		Assert.assertEquals(registerPage.getPageLevelWarning(), expectedPrivacyPolicyWarning);

	}

	@Test(priority = 5)
	public void verifyRegisteringAccountBySubscribingToNewsletter() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		myAccountPage = accountSuccessPage.clickOnContinueButton();
		newsLetterPage = myAccountPage.clickOnSubscribeUnsubscribeToNews1etterOption();
		newsLetterPage.isyesNewsletterOptionSelected();

		Assert.assertTrue(newsLetterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsLetterPage.isyesNewsletterOptionSelected());

	}

	@Test(priority = 6)
	public void verifyRegisteringAccountByNotSubscribingToNewsletter() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectNoNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		myAccountPage = accountSuccessPage.clickOnContinueButton();
		newsLetterPage = myAccountPage.clickOnSubscribeUnsubscribeToNews1etterOption();
		Assert.assertTrue(newsLetterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsLetterPage.isNoNewsletterOptionSelected());

	}

	@Test(priority = 7)
	public void verifyDifferentWaysOfNavigatingToRegisterAccountPage() {

		Assert.assertTrue(registerPage.didWeNavigatetoRegisterpage());
		headerOptions = registerPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		registerPage = loginPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigatetoRegisterpage());
		headerOptions = registerPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		rightColumnOptions = loginPage.getRightColumnOptions();
		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigatetoRegisterpage());

	}

	@Test(priority = 8)
	public void verifyRegisteringAccountByProvidingMismatchedPasswords() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("mismatchingPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String expectedWarning = "Password confirmation does not match password!";
		Assert.assertEquals(registerPage.getPasswordConfirmationWarning(), expectedWarning);

	}

	@Test(priority = 9)
	public void verifyRegisterAccountWithExistingEmailAddress() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(prop.getProperty("exisitingEmail"));
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String expectedWarning = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(registerPage.getPageLevelWarning(), expectedWarning);

	}

	@Test(priority = 10)
	public void verifyRegisteringAccountUsingInvalidEmail() throws IOException, InterruptedException {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(prop.getProperty("invalidEmailOne"));
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			String expectedWarningMessageOne = "Please include an '@' in the email address. 'amotoori' is missing an '@'.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailTwo"));
		accountSuccessPage = registerPage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			String expectedWarningMessageTwo = "Please enter a part following '@'. 'amotoori@' is incomplete.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageTwo);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailThree"));
		accountSuccessPage = registerPage.clickOnContinueButton();

		String expectedWarningMessageThree = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(registerPage.getemailWarning(), expectedWarningMessageThree);

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailFour"));
		accountSuccessPage = registerPage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			String expectedWarningMessageFour = "'.' is used at a wrong position in 'gmail.'.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageFour);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}

	}

	@Test(priority = 11)
	public void verifyRegisteringAccountUsingInvalidTelephoneNumber() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("invalidTelephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		String expectedWarningMessage = "Telephone number entered by you is invalid!";
		boolean b = false;
		try {
			if (registerPage.gettelephoneWarning().equals(expectedWarningMessage)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}
		Assert.assertTrue(b);

		Assert.assertFalse(accountSuccessPage.didWeNavigateToAccountSuccessPage());

	}

	@Test(priority = 12)
	public void verifyRegisteringAccountUsingKeyboardKeys() {

//		Actions actions = new Actions(driver);
//
//		for (int i = 1; i <= 23; i++) {
//			actions.sendKeys(Keys.TAB).perform();
//		}
//
//		actions.sendKeys(prop.getProperty("firstname")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("lastname"))
//				.sendKeys(Keys.TAB).sendKeys(CommonUtilities.generateBrandNewEmail()).sendKeys(Keys.TAB)
//				.sendKeys(prop.getProperty("telephoneNumber")).sendKeys(Keys.TAB)
//				.sendKeys(prop.getProperty("validPassword")).sendKeys(Keys.TAB)
//				.sendKeys(prop.getProperty("validPassword")).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_LEFT)
//				.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER)
//				.build().perform();

		actions = clickKeyboradKeyMu1tip1eTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, prop.getProperty("firstname"));
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("lastname"));
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, CommonUtilities.generateBrandNewEmail());
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("telephoneNumber"));
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.ARROW_LEFT, 1);
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.SPACE, 1);
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMu1tip1eTimes(actions, Keys.ENTER, 1);

		rightColumnOptions = registerPage.getRightColumnOptions();

		Assert.assertTrue(rightColumnOptions.didWeGetLoggedIn());
		accountSuccessPage = rightColumnOptions.getAccountSuccessPage();
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

	}

	@Test(priority = 13)
	public void verifyRegisterAccountPagePlaceholders() {

		Assert.assertEquals(registerPage.getFirstNamePlaceHolderText(), "First Name");
		Assert.assertEquals(registerPage.getLasttNamePlaceHolderText(), "Last Name");
		Assert.assertEquals(registerPage.getEmailPlaceHolderText(), "E-Mail");
		Assert.assertEquals(registerPage.getTelephonePlaceHolderText(), "Telephone");
		Assert.assertEquals(registerPage.getPasswordDomAttribute("placeholder"), "Password");
		Assert.assertEquals(registerPage.getConfirmPasswordDomAttribute("placeholder"), "Password Confirm");

	}

	@Test(priority = 14)
	public void verifyMandatoryFieldsInRegisterAccountPage() {

		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String fistNameLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getFirstNameLabel());
		Assert.assertEquals(fistNameLabelContent, expectedContent);
		String fistNameLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getFirstNameLabel());
		Assert.assertEquals(fistNameLabelColor, expectedColor);

		String lastNameLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getLastNameLabel());
		Assert.assertEquals(lastNameLabelContent, expectedContent);
		String lastNameLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getLastNameLabel());
		Assert.assertEquals(lastNameLabelColor, expectedColor);

		String emailLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getEmailLabel());
		Assert.assertEquals(emailLabelContent, expectedContent);
		String emailLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getEmailLabel());
		Assert.assertEquals(emailLabelColor, expectedColor);

		String telephoneLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getTelephoneLabel());
		Assert.assertEquals(telephoneLabelContent, expectedContent);
		String telephoneLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getTelephoneLabel());
		Assert.assertEquals(telephoneLabelColor, expectedColor);

		String passwordLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordLabel());
		Assert.assertEquals(passwordLabelContent, expectedContent);
		String passwordLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPasswordLabel());
		Assert.assertEquals(passwordLabelColor, expectedColor);

		String passwordConfirmLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordConfirmLabel());
		Assert.assertEquals(passwordConfirmLabelContent, expectedContent);
		String passwordConfirmLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPasswordConfirmLabel());
		Assert.assertEquals(passwordConfirmLabelColor, expectedColor);

		String privacyPolicyLabelContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPrivacyPolicyLabel());
		Assert.assertEquals(privacyPolicyLabelContent, expectedContent);
		String privacyPolicyLabelColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPrivacyPolicyLabel());
		Assert.assertEquals(privacyPolicyLabelColor, expectedColor);

	}

	@Test(priority = 15, enabled = false)
	public void verifyDataBaseTestingForRegisterAccount() {

		String enteredFirstNameData = prop.getProperty("firstname");
		registerPage.enterFirstName(enteredFirstNameData);

		String enteredLastNameData = prop.getProperty("lastname");
		registerPage.enterFirstName(enteredLastNameData);

		String enteredEmailData = CommonUtilities.generateBrandNewEmail();
		registerPage.enterFirstName(enteredEmailData);

		String enteredPasswordData = prop.getProperty("validPassword");
		registerPage.enterFirstName(enteredPasswordData);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("input-newsletter")));

		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		// Database credentials
		String jdbcURL = "jdbc:mysql://localhost:3306/opencart_db";
		String dbUser = "root";
		String dbPassword = "";

		// SQL query
		String sqlQuery = "SELECT * FROM oc_customer";

		// JDBC objects
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		int newsletter = 0;

		try {
			// Step 1: Register JDBC driver (optional in newer versions)

			// Step 2: Open a connection
			connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
			System.out.println("Connected to the database!");

			// Step 3: Create a statement
			statement = connection.createStatement();

			// Step 4: Execute the query
			resultSet = statement.executeQuery(sqlQuery);

			// Step 5: Process the ResultSet
			while (resultSet.next()) {
				firstName = resultSet.getString("firstname"); // Replace with your column name
				lastName = resultSet.getString("lastname"); // Replace with your column name
				email = resultSet.getString("email");
				newsletter = resultSet.getInt("newsletter");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Step 6: Close resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		Assert.assertEquals(firstName, enteredFirstNameData);
		Assert.assertEquals(lastName, enteredLastNameData);
		Assert.assertEquals(email, enteredEmailData);
		Assert.assertEquals(newsletter, 1);

	}

	@Test(priority = 16)
	public void verifyRegisteringAccountByEnteringOnlySpaces() {

		registerPage.enterFirstName("     ");
		registerPage.enterLastName("     ");
		registerPage.enterEmail("     ");
		registerPage.enterTelephone("     ");
		registerPage.enterPassword("     ");
		registerPage.enterConfirmationPassword("     ");
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone does not appear to be valid!";

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			Assert.assertEquals(registerPage.getfirstNameWarning(), expectedFirstNameWarning);
			Assert.assertEquals(registerPage.getlastNameWarning(), expectedLastNameWarning);
			Assert.assertEquals(registerPage.getemailWarning(), expectedEmailWarning);
			Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		} else if (browserName.equals("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}

	}

	@Test(priority = 17, dataProvider = "passwordSupplier")
	public void verifyRegisteringAccountUsingPasswordsWhichAreNotFollowingPasswordComplexityStandards(
			String passwordText) {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		String expectedWarning = "Enter password which follows Password Complexity Standard!";

		boolean b = false;
		try {
			if (registerPage.getpasswordWarning().equals(expectedWarning)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}

		Assert.assertTrue(b);
	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] supplyPasswords() {

		Object[][] data = { { "12345" }, { "abcdefghi" }, { "abcd1234" }, { "abcd123$" }, { "ABCD456#" } };
		return data;

	}

	@Test(priority = 18)
	public void verifyHeightWidthNumberOfCharacters() throws IOException {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		// First Name Field check
		Assert.assertEquals(registerPage.getFirstNameCSSValue("height"), expectedHeight);
		;
		Assert.assertEquals(registerPage.getFirstNameCSSValue("width"), expectedWidth);

		String exptectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getfirstNameWarning(), exptectedFirstNameWarning);
		registerPage.enterFirstName("a");
		registerPage.clickOnContinueButton();
		boolean firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("bcdeabcdeabcdeabcdeabcdeabcdeaba");
		registerPage.clickOnContinueButton();
		firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdeabcdeabcdeabcdeabcdeabcdeabc");

		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getfirstNameWarning(), exptectedFirstNameWarning);

		// Last Name Field check
		Assert.assertEquals(registerPage.getLastNameCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getLastNameCSSValue("width"), expectedWidth);

		String exptectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getlastNameWarning(), exptectedLastNameWarning);
		registerPage.enterLastName("a");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		boolean lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = registerPage.isLastNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		registerPage.clearlastNameField();
		registerPage.enterLastName("abcdeabcdeabcdeabcdeabcdeabcdeab");
		registerPage.clickOnContinueButton();
		lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = registerPage.isLastNameWarningDisplayed();
		} catch (NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		registerPage.clearEmailField();
		registerPage.enterLastName("abcdeabcdeabcdeabcdeabcdeabcdeabc");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getlastNameWarning(), exptectedLastNameWarning);

		// Email Field check
		Assert.assertEquals(registerPage.getEmailCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getEmailCSSValue("width"), expectedWidth);
		registerPage.enterEmail("adfdsfasdfadfdsssssafasdfasdfasdfadsfasdf@email.com");
		registerPage.clickOnContinueButton();
		boolean emailWarningStatus = false;
		try {
			emailWarningStatus = registerPage.isEmailWarningDisplayed();
		} catch (NoSuchElementException e) {
			emailWarningStatus = false;
		}
		Assert.assertFalse(emailWarningStatus);

		// Telephone Field check
		Assert.assertEquals(registerPage.getTelephoneCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getTelephoneCSSValue("width"), expectedWidth);

		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("1");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("12");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("123");
		registerPage.clickOnContinueButton();
		boolean telephoneWarningStatus = false;
		try {
			telephoneWarningStatus = registerPage.isTelephoneWarningDisplayed();
		} catch (NoSuchElementException e) {
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("12345678901234567890123456789012");
		registerPage.clickOnContinueButton();
		telephoneWarningStatus = false;
		try {
			telephoneWarningStatus = registerPage.isTelephoneWarningDisplayed();
		} catch (NoSuchElementException e) {
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("123456789012345678901234567890123");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.gettelephoneWarning(), expectedTelephoneWarning);

		// Password Field check
		Assert.assertEquals(registerPage.getPasswordCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getPasswordCSSValue("width"), expectedWidth);
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("1");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("12");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("123");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getpasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("1234");
		registerPage.clickOnContinueButton();
		boolean passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);
		registerPage.clearPasswordField();
		registerPage.enterPassword("12345678901234567890");
		registerPage.clickOnContinueButton();
		passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);
		registerPage.clearPasswordField();
		registerPage.enterPassword("123456789012345678901");
		registerPage.clickOnContinueButton();
		passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertTrue(passwordWarningStatus);

		// Password Confirm Field check
		Assert.assertEquals(registerPage.getPasswordConfirmCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getPasswordConfirmCSSValue("width"), expectedWidth);

		// Continue Button
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("color"), "rgba(255, 255, 255, 1)");
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("background-color"), "rgba(34, 154, 200, 1)");
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("font-size"), "12px");

		headerOptions = registerPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		headerOptions.selectRegisterOption();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenshot,
					new File(System.getProperty("user.dir") + "\\Screenshots\\AcutalRAPageAligment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\Screenshots\\AcutalRAPageAligment.png",
				System.getProperty("user.dir") + "\\Screenshots\\ExpectedRAPageAligment.png"));

	}

	@Test(priority = 19)
	public void verifyRegisterAccountUsingLeadingAndTrailingSpaces() {

		SoftAssert softAssert = new SoftAssert();
		registerPage.enterFirstName("     " + prop.getProperty("firstname") + "     ");
		registerPage.enterLastName("     " + prop.getProperty("lastname") + "     ");
		String emailText = "     " + CommonUtilities.generateBrandNewEmail() + "     ";
		registerPage.enterEmail(emailText);
		registerPage.enterTelephone("     " + prop.getProperty("telephoneNumber") + "     ");
		registerPage.enterPassword("     " + prop.getProperty("validPassword") + "     ");
		registerPage.enterConfirmationPassword("     " + prop.getProperty("validPassword") + "     ");
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			myAccountPage = accountSuccessPage.clickOnContinueButton();
			myAccountInformationPage = myAccountPage.clickOnEditYourAccountInformation();
			softAssert.assertEquals(myAccountInformationPage.getFirstNameDomAttribute("value"),
					prop.getProperty("firstname"));
			softAssert.assertEquals(myAccountInformationPage.getLastnameDomAttribute("value"),
					prop.getProperty("lastname"));
			softAssert.assertEquals(myAccountInformationPage.getEmailDomAttribute("value"), emailText.trim());
			softAssert.assertEquals(myAccountInformationPage.getTelephoneDomAttribute("value"),
					prop.getProperty("telephoneNumber"));
			softAssert.assertAll();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			Assert.assertEquals(myAccountInformationPage.getEmailDomProperty("validationMessage"),
					"Please enter an email address.");
		}

	}

	@Test(priority = 20)
	public void verifyRegisterAccountPrivacyPolicyField() {

		Assert.assertFalse(registerPage.isPrivacyPolicySelected());

	}

	@Test(priority = 21)
	public void verifyRegisteringAccountWithoutSelectingPrivacyPolicyCheckboxField() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.clickOnContinueButton();

		Assert.assertEquals(registerPage.getPageLevelWarning(), "Warning: You must agree to the Privacy Policy!");

	}

	@Test(priority = 22)
	public void verifyRegisteringAccountPasswordFieldsForSecurity() {

		Assert.assertEquals(registerPage.getPasswordDomAttribute("type"), "password");
		Assert.assertEquals(registerPage.getConfirmPasswordDomAttribute("type"), "password");

	}

	@Test(priority = 23)
	public void verifyRegisterAccountPageNavigations() {

		headerOptions = registerPage.getHeaderOptions();
		contactUsPage = headerOptions.selectPhoneIcon();
		Assert.assertTrue(getPageTitle(contactUsPage.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectHeartIcon();
		Assert.assertEquals(getPageTitle(headerOptions.getDriver()), "Account Login");
		navigateBackInBrowser(headerOptions.getDriver());

		loginPage = headerOptions.selectWishList();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

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
		navigateBackInBrowser(loginPage.getDriver());

		registerPage = registerPage.selectRegisterPageBreadcrumb();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");

		loginPage = registerPage.selectLoginPageOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		rightColumnOptions = loginPage.getRightColumnOptions();

		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");

		forgotYourPasswordPage = rightColumnOptions.clickOnForgotYourPassword();
		Assert.assertEquals(getPageTitle(forgotYourPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgotYourPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnRewardsPointsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnReturnsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

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
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptionsPage.clickOnOrderHistory();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptionsPage.clickOnWishList();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptionsPage.clickOnNewsletter();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

	}

	@Test(priority = 24)
	public void verifyRegisteringAccountWithoutEnteringConfirmationPassword() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordConfirmationWarning(),
				"Password confirmation does not match password!");

	}

	@Test(priority = 25)
	public void verifyRegisterAccountPageBreadcrumbURLTitleHeading() {

		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");

		Assert.assertEquals(getPageURL(registerPage.getDriver()), prop.getProperty("registerPageURL"));

		Assert.assertTrue(registerPage.didWeNavigatetoRegisterpage());

		Assert.assertEquals(registerPage.getPageHeading(), "Register Account");

	}

	@Test(priority = 26)
	public void verifyRegisterAccountUI() throws IOException {

		if (browserName.equalsIgnoreCase("chrome")){
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualRAPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualRAPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedRAPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxRAPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxRAPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxRAPageUI.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeRAPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeRAPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeRAPageUI.png"));}
		

		
	}

	@Test(priority = 27)
	public void verifyRegisterAccountInAllEnvironments() {

		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(CommonUtilities.generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmationPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

	}

}
