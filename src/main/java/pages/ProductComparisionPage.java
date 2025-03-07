package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductComparisionPage extends RootPage{

	WebDriver driver;

	public ProductComparisionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Product Comparison']")
	private WebElement productComparisonPageBreadcrumb;
	
	public boolean didWeNavigateToProductComparisonPage() {
		return elementUtilities.isElementDisplayed(productComparisonPageBreadcrumb);
	}

}
