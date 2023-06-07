package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText=("Login"))
	private WebElement LoginOption;
	
	@FindBy(linkText=("Register"))
	private WebElement RegisterOption;
	
	@FindBy(name=("search"))
	private WebElement searchBoxField;
	
	@FindBy(xpath=("//div[@id='search']/descendant::button"))
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public void selectLoginOptions() {
		LoginOption.click();
	}
	
	public void selectRegisterOption() {
		RegisterOption.click();
	}
	public void enterProductIntoSearchBoxField(String productText) {
		searchBoxField.sendKeys(productText);;
	}
	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	
	
	
}
