package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class) //getting data providers from different class
	public void verifyLoginDDT(String email, String pwd, String exp) {
		
		logger.info("*****Started Login Page Test *****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage macc  = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		/* 
		 * Data is Valid - Login Successful - logout - test passes
		 * Data is Valid - login failed - test fails
		 * 
		 * Data is Invalid - login failed - test passes
		 * Data is Invalid - login successful - test fails
		 * 
		 * */
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*****Finished Login Page Test *****");
	}
}
