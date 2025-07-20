package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//h1[normalize-space()='My Account']") WebElement msgHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogOut;
	
	public boolean isMyAccPageExist() {
		
		try {
			boolean isDisplyed=msgHeading.isDisplayed();
			
			return isDisplyed;
		}catch (Exception e) {
			
			return false;
		}
	}
	
	public void clickLogOut() {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(lnkLogOut).click().perform();
		//lnkLogOut.click();
	}
	
}
