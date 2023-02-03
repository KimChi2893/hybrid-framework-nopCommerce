package com.nopcommerce;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.LoginPageObject;
import pageObjects.user.CustomerInfoPageObject;
import pageObjects.user.UserHomePageObject;

public class My_Account extends BaseTest{
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = homePage.clickMyAccountLink();
		
		
		loginPage.setCookies(driver, Common_Login.loginCookies);
		customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
		/*
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		loginPage = homePage.clickMyAccountLink();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		loginPage.enterTextboxByID(driver, "Password", password);
		customerInfoPage = loginPage.clickLoginButton();
		
		Assert.assertTrue(customerInfoPage.isPageLoadedSuccess(driver));
		*/
	}

	@Test ()
	public void TC_01_Update_Customer_Information() {
		customerInfoPage.clickRadioButtonByID(driver, "gender-female");
		customerInfoPage.enterTextboxByID(driver, "FirstName", firstName);
		customerInfoPage.enterTextboxByID(driver, "LastName", lastName);
		customerInfoPage.enterTextboxByID(driver, "Email", email);
		customerInfoPage.enterTextboxByID(driver, "Company", company);
		
		//DateOfBirthDay
		customerInfoPage.selectDateOfBirthByDropdownName("DateOfBirthDay", dayOfBirth);
		customerInfoPage.selectDateOfBirthByDropdownName("DateOfBirthMonth", monthOfBirth);
		customerInfoPage.selectDateOfBirthByDropdownName("DateOfBirthYear", yearOfBirth);
	}
	
	//@Test
	public void TC_02_Login_Invalid_Email() {
		loginPage = homePage.getHeaderContainerPage(driver).openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", "");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getInlineErrorMessageTextByID(driver, "Email-error"), "Wrong email");
	}
	
	//@Test
	public void TC_03_Login_Not_Registered_Email() {
		loginPage = homePage.getHeaderContainerPage(driver).openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", "");
		loginPage.enterTextboxByID(driver, "Password", password);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getNotRegisteredAccountMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found");
	}

	//@Test
	public void TC_04_Login_Registered_Email_No_Password() {
		loginPage = homePage.getHeaderContainerPage(driver).openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		loginPage.enterTextboxByID(driver, "Password", "");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getNotRegisteredAccountMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	//@Test
	public void TC_05_Login_Registered_Email_Incorrect_Password() {
loginPage = homePage.getHeaderContainerPage(driver).openLoginPageObject();
		
		loginPage.enterTextboxByID(driver, "Email", registeredEmail);
		
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getNotRegisteredAccountMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	//@Test
	public void TC_06_Login_Valid_Account() {
		
	}
	
	WebDriver driver;
	UserHomePageObject homePage;
	LoginPageObject loginPage;
	CustomerInfoPageObject customerInfoPage;
	
	String firstName = "Automation", lastName = "FC", email = "automationfc.vn@gmail.com", company = "AutomationFC";
	String dayOfBirth = "2";
	String monthOfBirth = "10";
	String yearOfBirth = "1997";
	String registeredEmail = "cindytest@gmail.com";
	String password= "123456";
	
	Set<Cookie> cookies;
}
