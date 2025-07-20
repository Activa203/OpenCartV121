package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() throws InterruptedException {

		logger.info("****   Starting TC001_AccountRegistrationTest   ****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("My acc clicked..");

			hp.clickRegister();
			logger.info("Register clicked...");

			logger.info("Providing data...");
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			regPage.setFirstName(generateRandomString());
			regPage.setLastName(generateRandomString());
			regPage.setEmail(generateRandomString() + "@gmail.com");
			regPage.setPassword("abcd@123");

			regPage.setSubcribe();
			regPage.setPolicy();

			logger.info("validating user...");
			boolean confMsg = regPage.clickContinue();
			Assert.assertEquals(confMsg, true);
		} catch (Exception e) {
			logger.error("Test failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("****   Finish TC001_AccountRegistrationTest   ****");
	}

}
