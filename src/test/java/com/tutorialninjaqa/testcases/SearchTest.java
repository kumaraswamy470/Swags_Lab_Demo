package com.tutorialninjaqa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;

public class SearchTest extends Base {

	
	
	public SearchTest() {
		super();
	}
	WebDriver driver;	
	
	@BeforeMethod
	public void setUp() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
	
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product Hp is not displayed in the search results");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
	
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String ActualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(ActualSearchMessage, dataProp.getProperty("noProductTextinSearchResults"),"No product message in search results is not displayed");
	}
	
	@Test(priority=3)
	public void verifySearchingWithOutAnyProduct() {
	
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String ActualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(ActualSearchMessage, dataProp.getProperty("noProductTextinSearchResults"),"No product message in search results is not displayed");
	}
	
	
	
	
	
}
