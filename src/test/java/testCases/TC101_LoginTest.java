package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseTest;
import utilities.GeneralUtil;

public class TC101_LoginTest extends BaseTest {
	
	@Test(groups={"Sanity","Master"})
	public void validate_Login_Test(){
		try {
		logger.info("Login Test is Started");
		
		logger.info("Home Page Instantiation");
		HomePage homePage=new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickOnLogin();
		
		logger.info("Login Page Instantiation");
		LoginPage loginPage=new LoginPage(driver);
		String email=GeneralUtil.getProperty("username");
		loginPage.setEmail(email);
		logger.info("Providing Email "+email);
		String password=GeneralUtil.getProperty("password");
		logger.info("Providing Password "+password);
		
		loginPage.setPassword(password);
		
		loginPage.clickOnLogin();
		AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.isDisplayedMyAccountLabel());
		}catch(Exception e) {
			logger.info("Failed...");
			logger.info(e.getMessage());
			Assert.fail();
		}
		logger.info("End of Login Test");
	}
}
