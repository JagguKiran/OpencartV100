package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageEvents.AccountPageEvent;
public class AccountPage extends BasePage{
	@FindBy(xpath=AccountPageEvent.MY_ACCOUNT_TITLE) WebElement myAccountTitle;
	@FindBy(xpath=AccountPageEvent.MY_ACCOUNT) WebElement myAccountBtn;
	@FindBy(xpath=AccountPageEvent.LOGOUT) WebElement logoutBtn;
	@FindBy(xpath=AccountPageEvent.LOGOUT_TITLE) WebElement logoutTitle;
	public AccountPage(WebDriver driver) {
		super(driver);
	}
	public boolean isDisplayedMyAccountLabel(){
		try {
			return myAccountTitle.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
	public void clickMyAccountBtn(){
		myAccountBtn.click();
	}
	public void clickLogoutBtn() {
		logoutBtn.click();
	}
	public boolean isDisplayedAccountLogoutTitle(){
		try {
			return logoutTitle.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
}
