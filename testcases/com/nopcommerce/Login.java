package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.LoginPageObject;
import pageObjects.user.UserHomePageObject;

public class Login extends BaseTest{
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	//@Test ()
	public void TC_01_Login_Empty_Data() {
		loginPage = homePage.openLoginPageObject();
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getInlineErrorMessageTextByID(driver, "Email-error"), "Please enter your email");
	}
	
	//@Test
	public void TC_02_Login_Invalid_Email() {
		loginPage = homePage.openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", invalidEmail);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getInlineErrorMessageTextByID(driver, "Email-error"), "Wrong email");
	}
	
	//@Test
	public void TC_03_Login_Not_Registered_Email() {
		loginPage = homePage.openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", notRegisteredEmail);
		loginPage.enterTextboxByID(driver, "Password", password);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getNotRegisteredAccountMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found");
	}

	//@Test
	public void TC_04_Login_Registered_Email_No_Password() {
		loginPage = homePage.openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		loginPage.enterTextboxByID(driver, "Password", "");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getNotRegisteredAccountMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	//@Test
	public void TC_05_Login_Registered_Email_Incorrect_Password() {
loginPage = homePage.openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		loginPage.enterTextboxByID(driver, "Password", incorrectPassword);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getNotRegisteredAccountMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Valid_Account() {
		loginPage = homePage.openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		loginPage.enterTextboxByID(driver, "Password", password);
		loginPage.clickLoginButton();
		
		Assert.assertTrue(homePage.isPageLoadedSuccess(driver));
		homePage = PageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
	}
	
	WebDriver driver;
	UserHomePageObject homePage;
	LoginPageObject loginPage;
	String invalidEmail = "123@123.";
	String notRegisteredEmail = getRandomNumber() + "@gmail.com";
	
	String registeredEmail = "cindytest@gmail.com";
	String password= "123456";
	String incorrectPassword = "123";
}
