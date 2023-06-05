package com.tutorialninjaqa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public RegisterTest() {
		super();
	}
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void Setup() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	    Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	    Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
		
	
	
	}
	@Test(priority=3)
	public void VerifyRegisteringAccountDetailsWithExistingEmailAddress() {
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate email address not displayed");
	   
		
	}
	@Test(priority=4)

public void VerifyRegisteringAccountWithoutFillingAnyDetails() {
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualprivacypolicywarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualprivacypolicywarning.contains(dataProp.getProperty("privacypolicywarning")),"Privacy policy massage not displayed");
		
		String actualFirstnamewarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstnamewarning,dataProp.getProperty("FirstNameWarning"),"First Name Warning message not Displayed");
		
		String actuallastnamewarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actuallastnamewarning,dataProp.getProperty("LatNameWarning"),"last Name Warning message not Displayed");
		
		String actualEmailwarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailwarning,dataProp.getProperty("emailWarning"),"E-Mail Warning message not Displayed");
		
		String actualTelephonewarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephonewarning,dataProp.getProperty("TelephoneWarning"),"Telephone Warning message not Displayed");
		
		String actualPasswordwarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordwarning,dataProp.getProperty("passwordWarning"),"Password Warning message not Displayed");
		
		
		
	
		
	}
}


