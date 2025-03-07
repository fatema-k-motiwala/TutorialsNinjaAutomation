package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class NewsLetterPage extends RootPage {

	WebDriver driver;

	public NewsLetterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Newsletter']")
	private WebElement newsletterPageBreadcrumb;
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;

	public boolean didWeNavigateToNewsletterPage() {
		return elementUtilities.isElementDisplayed(newsletterPageBreadcrumb);
	}

	public boolean isNoNewsletterOptionSelected() {
		return elementUtilities.isElementSelected(noNewsletterOption);
	}

	public boolean isyesNewsletterOptionSelected() {
		return elementUtilities.isElementSelected(yesNewsletterOption);
	}

}
