package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "DataDriven") //we specify data provider class explicitly
	public void verify_loginDDT(String email,String pass,String exp) throws InterruptedException {
		
		logger.info("**** Starting TC003_LoginDDT ****");
		
		try {
			
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		Thread.sleep(500);
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.clickLogin();
		
		Thread.sleep(500);
		MyAccountPage mp=new MyAccountPage(driver);
		boolean login= mp.isMyAccPageExist();
		Thread.sleep(500);
		if(exp.equalsIgnoreCase("valid"))
		{
			if(login==true)
			{
				
				mp.clickLogOut();
				
				Assert.assertTrue(true);
				System.out.println("pass 1");
			}
			else
			{
				Assert.assertTrue(false);
				System.out.println("fail 1");
			}
		}
		else if(exp.equalsIgnoreCase("invalid"))
		{
			if(login==true)
			{
				mp.clickLogOut();
				Assert.assertTrue(false);
				System.out.println("fail 2");
				
			}
			else
			{
				Assert.assertTrue(true);
				System.out.println("pass 2");
			}
		}
		} catch (Exception e) {
			Assert.fail();
			logger.info("Error" + e.getMessage());
		}
		
	
		logger.info("**** End TC003_LoginDDT ****");
	}
}
