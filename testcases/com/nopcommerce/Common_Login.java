package com.nopcommerce;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.CustomerInfoPageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.UserHomePageObject;

public class Common_Login extends BaseTest{
	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		loginPage = homePage.clickMyAccountLink();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		loginPage.enterTextboxByID(driver, "Password", password);
		customerInfoPage = loginPage.clickLoginButton();
		
		Assert.assertTrue(customerInfoPage.isPageLoadedSuccess(driver));
		loginCookies = customerInfoPage.getCookies(driver);
		driver.quit();
	}
	
	WebDriver driver;
	UserHomePageObject homePage;
	LoginPageObject loginPage;
	CustomerInfoPageObject customerInfoPage;
	
	String registeredEmail = "cindytest@gmail.com";
	String password= "123456";
	static Set<Cookie> loginCookies;
}
