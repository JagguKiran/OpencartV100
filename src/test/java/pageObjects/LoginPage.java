package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageEvents.LoginPageEvent;

public class LoginPage extends BasePage{
	@FindBy(xpath=LoginPageEvent.EMAIL) WebElement txtEmail;
	@FindBy(xpath=LoginPageEvent.PASSWORD) WebElement txtPassword;
	@FindBy(xpath=LoginPageEvent.CLICKLOGIN) WebElement clkLogin;
	@FindBy(xpath=LoginPageEvent.WARNINGMESSAGE) WebElement warningTxt;
	public LoginPage(WebDriver driver){
		super(driver);
	}
	public void setEmail(String str){
		txtEmail.sendKeys(str);
	}
	public void setPassword(String str){
		txtPassword.sendKeys(str);
	}
	public void clickOnLogin(){
		clkLogin.click();
	}
	public boolean isWarningMessageDisplayed(){
		try {
			return warningTxt.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
}
