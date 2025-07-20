package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void login() throws InterruptedException
	{
		try {
			
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		Thread.sleep(500);
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		Thread.sleep(500);
		MyAccountPage mp=new MyAccountPage(driver);
		boolean login= mp.isMyAccPageExist();
		
		Assert.assertEquals(login,true,"Login Failed...");
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
}
