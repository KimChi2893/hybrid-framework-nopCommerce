package pageObjects.navigation;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.FooterContainerPageUI;


public class FooterContainerPage extends BasePage{
	WebDriver driver;

	public FooterContainerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public FooterContainerPage openFooterLinkByPageNames(String pageName) {
		waitForElementClickable(driver, FooterContainerPageUI.DYNAMIC_FOOTER_LINK, pageName);
		clickElement(driver, FooterContainerPageUI.DYNAMIC_FOOTER_LINK, pageName);
		
		if (pageName.equals("My account")) {
			return PageGeneratorManager.getCustomerInfoPage(driver);
		} 
//		else if(pageName.equals("My Orders")) {
//			return PageGeneratorManager.getMyOrderPage(driver);
//		} 
		else {
			return null;
		}
	}
}
