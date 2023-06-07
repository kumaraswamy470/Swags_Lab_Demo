package com.tutorialninjaqa.testcases;


import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsninja.pages.AccountPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base  {
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	
	public void setUp() {
		
		//loadpropertiesFile();
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOptions();
		
	//	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	//	driver.findElement(By.linkText("Login")).click();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password) {
	LoginPage loginPage=new LoginPage(driver);
	loginPage.enterEmailAddress(email);
	loginPage.enterPassword(password);
	loginPage.clickOnLoginButton();
	
//	driver.findElement(By.id("input-email")).sendKeys(email);
//	driver.findElement(By.id("input-password")).sendKeys(password);
//	driver.findElement(By.xpath("//input[@value='Login']")).click();
	
	AccountPage accountPage=new AccountPage(driver);
	Assert.assertTrue(accountPage.getDisplayStatusOfEditAccountInformationOption(),"Edit your Accpount Informations is not displayed" );
	
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() throws FileNotFoundException {
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInValidCredentials() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterEmailAddress(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();		
	
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
	//	String actualWarningmessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		String actualWarningmessage=loginPage.retriveWarningMessageText();
		String expWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningmessage.contains(expWarningmessage),"Expected warning message is not displayed");
		
	
	}	
		
	@Test(priority=3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterEmailAddress(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();		
	
		
		
	//	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	//	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	//	driver.findElement(By.xpath("//input[@value='Login']")).click();
	//	String actualWarningmessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		String actualWarningmessage=loginPage.retriveWarningMessageText();
		String expWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningmessage.contains(expWarningmessage),"Expected warning message is not displayed");
		
	
	}	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInValidPassword() {
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String actualWarningmessage=loginPage.retriveWarningMessageText();
		String expWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningmessage.contains(expWarningmessage),"Expected warning message is not displayed");
		
		
		
	//	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
	//	driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
	//	driver.findElement(By.xpath("//input[@value='Login']")).click();
	//	String actualWarningmessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	//	String expWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");
	//	Assert.assertTrue(actualWarningmessage.contains(expWarningmessage),"Expected warning message is not displayed");
		
	
	}	
	@Test(priority=5)
	public void verifyLoginWithOutProvidingCredentials() {
		//driver.findElement(By.id("input-email")).sendKeys("");
		//driver.findElement(By.id("input-password")).sendKeys("");
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.clickOnLoginButton();
		String actualWarningmessage=loginPage.retriveWarningMessageText();
		
		
		
	//	driver.findElement(By.xpath("//input[@value='Login']")).click();
	//	String actualWarningmessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningmessage.contains(expWarningmessage),"Expected warning message is not displayed");
		
	
	}	
				
	
	
	
}
