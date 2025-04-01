package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseTest;
import utilities.GeneralUtil;
public class TC100_AccountRegisterTest extends BaseTest{

	@Test(groups={"Regression","Master"})
	public void create_Account_Registration() {
		try {
			
			logger.info("Started Registering Account...");
			logger.info("Home Page Instantiation");
			HomePage homePage=new HomePage(driver);
			logger.info("Clicked on My Account");
			homePage.clickMyAccount();
			logger.info("Clicked on Register");
			homePage.clickOnRegister();
			
			logger.info("Account Register Page Instantiation");
			AccountRegisterPage accountRegister=new AccountRegisterPage(driver);
			
			logger.info("Providing First Name");
			accountRegister.setFirstName(faker.name().firstName());
			logger.info("Providing Last Name");
			accountRegister.setLastName(faker.name().lastName());
			
			logger.info("Providing Email...");
			String email=faker.name().firstName()+"@gmail.com";
			logger.info("Email = "+email);
			GeneralUtil.setProperty("username1",email);
			System.out.println(GeneralUtil.getProperty("username1"));
			accountRegister.setEmail(email);
			logger.info("Providing Password..");
			String password=faker.internet().password(5,10);
			
			
			GeneralUtil.setProperty("password1",password);
			System.out.println(GeneralUtil.getProperty("password1"));
			logger.info("Password "+password);
			accountRegister.setPassword(password);
			logger.info("Confirming Password..");
			accountRegister.setPasswordConfirm(password);
			logger.info("Providing Telephone Number");
			accountRegister.setTelephone(faker.number().digits(10));
			
			logger.info("Accepting Privacy...");
			accountRegister.checkPolicy();
			
			logger.info("Clicking on Continue button");
			accountRegister.clickContinue();
	
			String msg=accountRegister.getConfirmMessage();
			logger.info("Confirmation Message "+msg);
			Assert.assertEquals(msg,"Your Account Has Been Created!");
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.info("End of Registering Account");
	}
}
