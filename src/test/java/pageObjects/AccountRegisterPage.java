package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageEvents.AccountRegisterEvent;

public class AccountRegisterPage extends BasePage {
	@FindBy(xpath=AccountRegisterEvent.FIRSTNAME) WebElement txtFirstName;
	@FindBy(xpath=AccountRegisterEvent.LASTNAME) WebElement txtLastName;
	@FindBy(xpath=AccountRegisterEvent.EMAIL) WebElement txtEmail;
	@FindBy(xpath=AccountRegisterEvent.TELEPHONE) WebElement txtTelephone;
	@FindBy(xpath=AccountRegisterEvent.PASSWORD) WebElement txtPassword;
	@FindBy(xpath=AccountRegisterEvent.CONFIRM) WebElement txtConfirmPassword;
	@FindBy(xpath=AccountRegisterEvent.PRIVACY) WebElement chkPrivacy;
	@FindBy(xpath=AccountRegisterEvent.CONTINUE) WebElement btnContinue;
	@FindBy(xpath=AccountRegisterEvent.MESSAGE) WebElement txtConfirmMessage;
	public AccountRegisterPage(WebDriver driver){
		super(driver);
	}
	public void setFirstName(String s){
		txtFirstName.sendKeys(s);
	}
	public void setLastName(String s){
		txtLastName.sendKeys(s);
	}
	public void setEmail(String s){
		txtEmail.sendKeys(s);
	}
	public void setPassword(String s){
		txtPassword.sendKeys(s);
	}
	public void setPasswordConfirm(String s){
		txtConfirmPassword.sendKeys(s);
	}
	public void setTelephone(String s){
		txtTelephone.sendKeys(s);
	}
	public void checkPolicy(){
		chkPrivacy.click();
	}
	public void clickContinue(){
		btnContinue.click();
	}
	public String getConfirmMessage(){
		try {
			return txtConfirmMessage.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
