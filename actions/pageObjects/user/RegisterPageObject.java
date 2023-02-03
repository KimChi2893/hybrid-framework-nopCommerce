package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click Register button")
	public void clickRegisterButton() {
		clickToElementByJS(driver, castRestParameter(RegisterPageUI.REGISTER_BUTTON));
	}
	
	@Step("Get the success registration message")
	public String getSuccessRegistrationMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);	
	}
	
	@Step("Get the error registration message")
	public String getErrorRegistrationMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_ERROR_MESSAGE);	
	}
}
