package pageObjects.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import io.qameta.allure.Step;
import pageObjects.navigation.PageGeneratorManager;
import pageUIs.user.UserHomePageUI;

public class UserHomePageObject extends BasePage{
	WebDriver driver;
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step ("Click My account link")
	public LoginPageObject clickMyAccountLink() {
		clickToElementByJS(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		sleepInSecond(1);
		return PageGeneratorManager.getLoginPage(driver);
	}
}
