package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageEvents.HomePageEvent;

public class HomePage extends BasePage{
	@FindBy(xpath=HomePageEvent.MYACCOUNT) WebElement btnMyAccount;
	@FindBy(xpath=HomePageEvent.REGISTER) WebElement btnRegister;
	@FindBy(xpath=HomePageEvent.LOGIN) WebElement btnLogin;
	public HomePage(WebDriver driver){
		super(driver);		
	}
	public void clickMyAccount() {
		btnMyAccount.click();
	}
	public void clickOnLogin() {
		btnLogin.click();
	}
	public void clickOnRegister() {
		btnRegister.click();
	}
}
