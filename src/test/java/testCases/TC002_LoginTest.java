package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void verifyLogin() {
		logger.info("*****Started Login Page Test *****");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean isExist = myacc.isMyAccountPageExists();
		
		Assert.assertTrue(isExist);
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*****Started Login Page Test *****");
		
	}

}
