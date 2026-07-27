package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationPage extends BaseClass{
	
	@Test
	public void verifyAccountRegistration() {
		logger.info("****Starting TC001_AccountRegistrationPage****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount");
		hp.clickRegister();
		logger.info("Clicked on Register");
		
		RegisterPage rp = new RegisterPage(driver);
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		
		rp.setTelephone(randomNumber());
		String password = randomAlphanumeric();
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		
		rp.setNewsletter(true);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		logger.info("Validating expected message");
	    String msg= rp.getAccountCreatedMessage();
		Assert.assertEquals( msg,"Your Account Has Been Created!");
		logger.debug("Debug Logs....");
		} catch(Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("****Finished TC001_AccountRegistrationPage***");
	}
	
	
	

}
