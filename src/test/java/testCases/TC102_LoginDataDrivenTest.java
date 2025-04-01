package testCases;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseTest;
import utilities.DataProviderUtil;

public class TC102_LoginDataDrivenTest extends BaseTest{
	@Test(dataProvider="fetchUserNamePassword",dataProviderClass=DataProviderUtil.class,groups= {"DataDriven"})
	public void validate_Login_Data_Driven(List<String> al){
		try {
				logger.info("Login Data Driven Test is Started");
				
				logger.info("Home Page Instantiation");
				HomePage homePage=new HomePage(driver);
				homePage.clickMyAccount();
				homePage.clickOnLogin();
			
				logger.info("Login Page Instantiation");
				LoginPage loginPage=new LoginPage(driver);
			
				loginPage.setEmail(al.get(0));
				logger.info("Providing Email "+al.get(0));
				
				logger.info("Providing Password "+al.get(1));
			
				loginPage.setPassword(al.get(1));
			
				loginPage.clickOnLogin();
				AccountPage accountPage=new AccountPage(driver);
				
				boolean target=accountPage.isDisplayedMyAccountLabel();
				
				if(target){
					if(al.get(2).trim().equalsIgnoreCase("valid")){
						accountPage.clickMyAccountBtn();
						accountPage.clickLogoutBtn();
						logger.info("Positive Validation->Valid Credentials");
						System.out.println("POSITIVE VALIDATION");
						Assert.assertTrue(true);
					}else {
						accountPage.clickMyAccountBtn();
						accountPage.clickLogoutBtn();
						System.out.println("POSITIVE INVALIDATION");
						logger.info("Invalid Credentials");
						Assert.assertTrue(false);
					}
				}else {
					if(al.get(2).trim().equalsIgnoreCase("invalid")){
						logger.info("Negative Validation->Invalid Credentials");
						System.out.println("NEGATIVE VALIDATION");
						Assert.assertTrue(true);
					}else {
						logger.info("Status Shouldn't be Valid");
						System.out.println("STATUS SHOULDN'T BE VALID");
						Assert.assertTrue(false);
					}
				}
		}catch(Exception e){
			logger.error(e.getMessage());
			Assert.fail();
		}
	}
	
}
