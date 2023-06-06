package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id=("input-firstname"))
	private WebElement firstNameField;
	@FindBy(id=("input-lastname"))
	private WebElement lastNameField;
	@FindBy(id=("input-email"))
	private WebElement emailAddressField;
	@FindBy(id=("input-telephone"))
	private WebElement telephoneField;
	@FindBy(id=("input-password"))
	private WebElement passwordField;
	@FindBy(id=("input-confirm"))
	private WebElement confirmPasswordField;
	@FindBy(name=("agree"))
	private WebElement privacypolicyField;
	@FindBy(xpath=("//input[@value='Continue']"))
	private WebElement continueButton;
	@FindBy(xpath=("//input[@name='newsletter'][@value='1']"))
	private WebElement yesNewsLetterOption;
	@FindBy(xpath=("//div[contains(@class,'alert-dismissible')]"))
	private WebElement duplicateEmailAddresswarning;
	@FindBy(xpath=("//div[contains(@class,'alert-dismissible')]"))
	private WebElement privacyPolicyWarning;
	@FindBy(xpath=("//input[@id='input-firstname']/following-sibling::div"))
	private WebElement firstNameWarning;
	@FindBy(xpath=("//input[@id='input-lastname']/following-sibling::div"))
	private WebElement lastNameWarning;
	@FindBy(xpath=("//input[@id='input-email']/following-sibling::div"))
	private WebElement emailFieldWarning;
	@FindBy(xpath=("//input[@id='input-telephone']/following-sibling::div"))
	private WebElement telephoneFieldWarning;
	@FindBy(xpath=("//input[@id='input-password']/following-sibling::div"))
	private WebElement passwordFieldWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterlastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void confirmPassword(String confirmpasswordText) {
		confirmPasswordField.sendKeys(confirmpasswordText);
	}
	
	public void selectPrivacyPolicyField() {
		privacypolicyField.click();;
	}
	
	public void clickOnContinueButton() {
		continueButton.click();;
	}
	
	public void selectyesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	
	public String retriveDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarningMessage=duplicateEmailAddresswarning.getText();
		return duplicateEmailAddressWarningMessage;
	}
	public String retrivePrivacyPolicyWarning() {
		String privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
		}
	public String retrivefirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
		}
	public String retrivelastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
		}
	public String retriveemailFieldWarning() {
		String emailFieldWarningText=emailFieldWarning.getText();
		return emailFieldWarningText;
		}
	public String retrivetelephoneFieldWarning() {
		String telephoneFieldWarningText=telephoneFieldWarning.getText();
		return telephoneFieldWarningText;
		}
	
	public String retrivepasswordFieldWarning() {
		String passwordFieldWarningText=passwordFieldWarning.getText();
		return passwordFieldWarningText;
		}
}
