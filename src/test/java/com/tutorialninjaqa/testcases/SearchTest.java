package com.tutorialninjaqa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.SearchPage;

public class SearchTest extends Base {

	
	
	public SearchTest() {
		super();
	}
	public WebDriver driver;	
	
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
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		SearchPage searchPage=new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusAsValidHpProduct(),"Valid product Hp is not displayed in the search results");
		
	/*	driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product Hp is not displayed in the search results"); */
		
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
	
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();
		SearchPage searchPage=new SearchPage(driver);
		String ActualSearchMessage=searchPage.retriveNoProductMessagetext();
		
	/*	driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String ActualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText(); */
		Assert.assertEquals(ActualSearchMessage, dataProp.getProperty("noProductTextinSearchResults"),"No product message in search results is not displayed");
	}
	
	@Test(priority=3)
	public void verifySearchingWithOutAnyProduct() {
	
		HomePage homePage=new HomePage(driver);
		homePage.clickOnSearchButton();
		SearchPage searchPage=new SearchPage(driver);
		String ActualSearchMessage=searchPage.retriveNoProductMessagetext();
		
	/*	driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String ActualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText(); */
		Assert.assertEquals(ActualSearchMessage, dataProp.getProperty("noProductTextinSearchResults"),"No product message in search results is not displayed");
	}
	
	
	
	
	
}
