package pageObjects.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import io.qameta.allure.Step;
import pageObjects.navigation.PageGeneratorManager;
import pageUIs.user.HeaderContainerPageUI;
import pageUIs.user.UserHomePageUI;

public class UserHomePageObject extends BasePage{
	WebDriver driver;
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public RegisterPageObject openRegisterPageObject() {
		waitForElementClickable(driver,UserHomePageUI.REGISTER_LINK);
		clickElement(driver, UserHomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	public LoginPageObject openLoginPageObject() {
		waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
		clickElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	@Step ("Click My account link")
	public LoginPageObject clickMyAccountLink() {
		clickToElementByJS(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		sleepInSecond(1);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	@Step ("Verify the Logout link is displayed or not")
	public boolean isLogoutLinkDisplayed() {		
		return isElementDisplayed(driver, UserHomePageUI.LOGOUT_LINK);
	}
}
