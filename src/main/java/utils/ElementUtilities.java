package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementUtilities {

	WebDriver driver;
	Actions actions;
	Select select;
	
	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public void selectOptionFromDropdownFieldUsingIndex(WebElement element, String optionIndex)
	{
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			select = new Select(element);
			int index = Integer.parseInt(optionIndex);
			select.selectByIndex(index);
		}
	}
	
	public List<String> getTextOfElements(List<WebElement> items) {
		List<String> itemNames =  new 	ArrayList<>();
		for(WebElement item: items) 
		{
		itemNames.add(getElementText(item));		
		}
		return itemNames;
	}


	public void selectOptionFromDropdownFieldUsingText(WebElement element, String optionText)
	{
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			select = new Select(element);
			select.selectByVisibleText(optionText);
		}
	}
	
	public void clickOnElement(WebElement element) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			element.click();
		}
	}

	public String getElementText(WebElement element) {
		String elementText = "";
		if (isElementDisplayed(element)) {
			elementText = element.getText();
		}
		return elementText;
	}
	
	public int getElementsCount(List<WebElement> elements)
	{
		int count = 0;
//		if(isElementDisplayedOnPage(elements.get(0))) {
//			count= elements.size();
//		}
		try {
			count= elements.size();		
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (Exception e) {
			b = false;
		}
		return b;
	}
	

	public boolean isElementDisplayedOnPage(WebElement element) {
		boolean b = false;
		b = element.isDisplayed();
		return b;
	}

	public String getElementDomAttribute(WebElement element, String attributeName) {
		return element.getDomAttribute(attributeName);
	}

	public String getElementDomProperty(WebElement element, String attributeName) {
		return element.getDomProperty(attributeName);
	}

	public boolean isElementSelected(WebElement element) {
		boolean b = false;
		if (isElementDisplayedOnPage(element)) {
			b = element.isSelected();
		}
		return b;
	}

	public String getElementCSSValue(WebElement element, String cssPropertyName) {
		return element.getCssValue(cssPropertyName);
	}

	public void clearTextfromElement(WebElement element) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			element.clear();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (isElementDisplayedOnPage(element) && element.isEnabled()) {
			clearTextfromElement(element);
			element.sendKeys(text);
			;
		}
	}

	public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}
	
	public void copyTextUsingKeyboardKeys(WebDriver driver) {
		actions = getActions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
	}
	
	
	public void pasteTextUsingKeyboardKeys(WebDriver driver, WebElement element) {
		actions = getActions(driver);
		actions.click(element).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
