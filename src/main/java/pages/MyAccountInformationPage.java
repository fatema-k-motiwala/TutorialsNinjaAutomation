package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountInformationPage extends RootPage {

	WebDriver driver;

	public MyAccountInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastname;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	public String getFirstNameDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(firstName, attributeName);
	}

	public String getEmailDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(email, attributeName);
	}

	public String getEmailDomProperty(String attributeName) {
		return elementUtilities.getElementDomProperty(email, attributeName);

	}

	public String getLastnameDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(lastname, attributeName);
	}

	public String getTelephoneDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(telephone, attributeName);
	}

}
