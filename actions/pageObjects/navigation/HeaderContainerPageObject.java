package pageObjects.navigation;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;
import pageUIs.user.HeaderContainerPageUI;

public class HeaderContainerPageObject extends BasePage{
	WebDriver driver;
	public HeaderContainerPageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public RegisterPageObject openRegisterPageObject() {
		waitForElementClickable(driver, HeaderContainerPageUI.REGISTER_LINK);
		clickElement(driver, HeaderContainerPageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	public LoginPageObject openLoginPageObject() {
		waitForElementClickable(driver, HeaderContainerPageUI.LOGIN_LINK);
		clickElement(driver, HeaderContainerPageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
//	public CustomerInfoPageObject openCustomerInfoPageObject() {
//		waitForElementClickable(driver, HeaderContainerPageUI.MY_ACCOUNT_LINK);
//		clickElement(driver, HeaderContainerPageUI.MY_ACCOUNT_LINK);
//		return PageGeneratorManager.getCustomerInfoPage(driver);
//	}
	
}
