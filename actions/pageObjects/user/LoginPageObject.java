package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageObjects.navigation.PageGeneratorManager;
import pageUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click Login button")
	public CustomerInfoPageObject clickLoginButton() {
		clickToElementByJS(driver, castRestParameter(LoginPageUI.LOGIN_BUTTON));
		//sleepInSecond(0.5);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}
	
	@Step("Get the error registration message")
	public String getNotRegisteredAccountMessage() {
		return getElementText(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);	
	}
}
