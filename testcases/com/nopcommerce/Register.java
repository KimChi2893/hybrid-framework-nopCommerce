package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserHomePageObject;

public class Register extends BaseTest{
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	//@Test ()
	public void TC_01_Register_Empty_Data() {
		registerPage = homePage.openRegisterPageObject();
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "FirstName-error"), "First name is required.");
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "LastName-error"), "Last name is required.");
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "Email-error"), "Email is required.");
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "Password-error"), "Password is required.");
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "ConfirmPassword-error"), "Password is required.");
	}
	
	//@Test
	public void TC_02_Register_Invalid_Email() {
		registerPage = homePage.openRegisterPageObject();
		
		registerPage.clickRadioButtonByID(driver, "gender-female");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);
		registerPage.enterTextboxByID(driver, "LastName", lastName);
		registerPage.enterTextboxByID(driver, "Email", invalidEmail);
		registerPage.enterTextboxByID(driver, "Password", password);
		registerPage.enterTextboxByID(driver, "ConfirmPassword", password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "Email-error"), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Valid_Data() {
		registerPage = homePage.openRegisterPageObject();
		
		//registerPage.clickRadioButtonByID(driver, "gender-female");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);
		registerPage.enterTextboxByID(driver, "LastName", lastName);
		registerPage.enterTextboxByID(driver, "Email", validEmail);
		registerPage.enterTextboxByID(driver, "Password", password);
		registerPage.enterTextboxByID(driver, "ConfirmPassword", password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getSuccessRegistrationMessage(), "Your registration completed");
	}
	
	//@Test
	public void TC_04_Register_Already_Email() {
		registerPage = homePage.openRegisterPageObject();
		
		registerPage.clickRadioButtonByID(driver, "gender-female");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);
		registerPage.enterTextboxByID(driver, "LastName", lastName);
		registerPage.enterTextboxByID(driver, "Email", alreadyEmail);
		registerPage.enterTextboxByID(driver, "Password", password);
		registerPage.enterTextboxByID(driver, "ConfirmPassword", password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorRegistrationMessage(), "The specified email already exists");
	}
	
	//@Test
	public void TC_05_Register_Less_6_Characters_Password() {
		registerPage = homePage.openRegisterPageObject();
		
		registerPage.clickRadioButtonByID(driver, "gender-female");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);
		registerPage.enterTextboxByID(driver, "LastName", lastName);
		registerPage.enterTextboxByID(driver, "Email", validEmail);
		registerPage.enterTextboxByID(driver, "Password", less6CharactersPassword);
		registerPage.enterTextboxByID(driver, "ConfirmPassword", password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "Password-error"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	//@Test
	public void TC_06_Register_Incorrect_Confirm_Password() {
		registerPage = homePage.openRegisterPageObject();
		
		registerPage.clickRadioButtonByID(driver, "gender-female");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);
		registerPage.enterTextboxByID(driver, "LastName", lastName);
		registerPage.enterTextboxByID(driver, "Email", validEmail);
		registerPage.enterTextboxByID(driver, "Password", password);
		registerPage.enterTextboxByID(driver, "ConfirmPassword", less6CharactersPassword);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getInlineErrorMessageTextByID(driver, "ConfirmPassword-error"), "The password and confirmation password do not match.");
	}
	
	WebDriver driver;
	UserHomePageObject homePage;
	RegisterPageObject registerPage;
	String firstName = "Cindy";
	String lastName = "Test";
	String invalidEmail = "123@123.";
	String alreadyEmail = "cindytest@gmail.com";
	//String validEmail = "cindytest" + getRandomNumber() + "@gmail.com";
	String validEmail = "cindytest@gmail.com";
	String password= "123456";
	String less6CharactersPassword = "1";
}
