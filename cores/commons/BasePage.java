package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.navigation.HeaderContainerPageObject;
import pageUIs.user.BasePageUI;

public class BasePage {
	public static BasePage getBasePageInstance() {
		return new BasePage();
	}
	
	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie:cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
		refreshCurrentPage(driver);
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public String castRestParameter(String locator, String... dynamicLocator) {
		locator = String.format(locator, (Object[])dynamicLocator);
		return locator;	
	}

	private By getByLocator(String locator) {
		By by = null;
		
		if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
			by = By.id(locator.substring(3));
		} else if(locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLASS=")) {
			by = By.className(locator.substring(6));
			
		}else if(locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
			by = By.name(locator.substring(5));
			
		}else if(locator.startsWith("css=") || locator.startsWith("Css=") || locator.startsWith("CSS=")) {
			by = By.cssSelector(locator.substring(4));
			
		}else if(locator.startsWith("xpath=") || locator.startsWith("Xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
			by = By.xpath(locator.substring(6));
			
		}else {
			throw new RuntimeException("The locator is not valid");
		}
		
		return by;
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {	
		return driver.findElement(getByLocator(locator));
	}
	

	public void clickElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	
	public void clickElement(WebDriver driver, String locator, String... dynamicLocator) {
		getWebElement(driver, castRestParameter(locator, dynamicLocator)).click();
	}
	
	public HeaderContainerPageObject getHeaderContainerPage(WebDriver driver) {
		return new HeaderContainerPageObject(driver);
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, dynamicLocator))));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));

	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicLocator))));

	}
	
	public boolean isElementDisplayedInDOM(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).isDisplayed();
	}

	
	public void sendKeyToElement(WebDriver driver, String locator, String valueToInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput, String... dynamicLocator) {
		WebElement element = getWebElement(driver, castRestParameter(locator, dynamicLocator));
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public void enterTextboxByID(WebDriver driver, String textboxID, String valueToInput) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, valueToInput, textboxID);	
	}
	
	public void clickRadioButtonByID(WebDriver driver, String elementID) {
		waitForElementVisible(driver, BasePageUI.GENDER_TOGGLE_BY_ID, elementID);
		clickElement(driver, BasePageUI.GENDER_TOGGLE_BY_ID, elementID);
		sleepInSecond(2);
	}
	
	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator, String... dynamicLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, castRestParameter(locator, dynamicLocator)));
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).getText();
	} 
	
	public String getInlineErrorMessageTextByID(WebDriver driver, String valueID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_INLINE_ERROR_MESSAGE_BY_ID, valueID);
		return getElementText(driver, BasePageUI.DYNAMIC_INLINE_ERROR_MESSAGE_BY_ID, valueID);	
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait;
		JavascriptExecutor jsExecutor;
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}	
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.deselectByVisibleText(itemText);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, dynamicLocator)));
		select.deselectByVisibleText(itemText);
	}

	public String getFirstSelectedTextItem(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getFirstSelectedTextItem(WebDriver driver, String locator, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, dynamicLocator)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, dynamicLocator)));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedTextItem) {
		clickElement(driver, parentLocator);
		sleepInSecond(2);

		List<WebElement> childItems = new WebDriverWait(driver, this.longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempElement : childItems) {
			if (tempElement.getText().trim().equals(expectedTextItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", tempElement);
				sleepInSecond(1);
				// jsExecutor.executeScript("arguments[0].click;", tempElement);
				tempElement.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedTextItem, String... dynamicParentLocator) {
		clickElement(driver, castRestParameter(parentLocator, dynamicParentLocator));
		sleepInSecond(2);

		List<WebElement> childItems = new WebDriverWait(driver, this.longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempElement : childItems) {
			if (tempElement.getText().trim().equals(expectedTextItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", tempElement);
				sleepInSecond(1);
				// jsExecutor.executeScript("arguments[0].click;", tempElement);
				tempElement.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	//private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	
}

	
