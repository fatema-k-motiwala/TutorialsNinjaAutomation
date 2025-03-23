package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ReturnsPage extends RootPage {

	WebDriver driver;

	public ReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadCrumb;
	
	@FindBy(xpath = "//a[@*='View']")
	private WebElement viewOption;

	@FindBy(name = "return_reason_id")
	private WebElement reasonForReturnFirstOption;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	public void clickOnSubmitButton() {
		elementUtilities.clickOnElement(submitButton);
	}

	public void selectFirstReasonForReturn() {
		elementUtilities.clickOnElement(reasonForReturnFirstOption);
	}

	public ReturnInformationPage clickOnViewOption() {
		elementUtilities.clickOnElement(viewOption);
		return new ReturnInformationPage(driver);
	}

	public MyAccountPage clickOnAccountBreadCrumb() {
		elementUtilities.clickOnElement(accountBreadCrumb);
		return new MyAccountPage(driver);
	}

}
