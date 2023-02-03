package pageObjects.user;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageObjects.navigation.FooterContainerPage;
import pageUIs.user.CustomerInfoPageUI;

public class CustomerInfoPageObject extends FooterContainerPage{
	
	WebDriver driver;
	public CustomerInfoPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Select Date of birth (day/ month/ year)")
	public void selectDateOfBirthByDropdownName(String dropdownName, String expectedTextItem) {
		waitForElementClickable(driver, CustomerInfoPageUI.DYNAMIC_DATE_OF_BIRTH_BY_NAME, dropdownName);
		selectItemInCustomDropdown(driver, CustomerInfoPageUI.DYNAMIC_DATE_OF_BIRTH_BY_NAME, castRestParameter(CustomerInfoPageUI.DYNAMIC_EXPECTED_OPTION_IN_DATE_OF_BIRTH_BY_NAME, dropdownName), expectedTextItem, dropdownName);
	}
	
}
