package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPass;
	@FindBy(xpath = "//input[@id='input-newsletter']")
	WebElement chkSubscribe;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement alertMsg;

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPassword(String pass) {
		txtPass.sendKeys(pass);
	}

	public void setSubcribe() {
		// chkSubscribe.click();

		Actions actions = new Actions(driver);
		actions.moveToElement(chkSubscribe).click().perform();
	}

	public void setPolicy() {
		// chkPolicy.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(chkPolicy).click().perform();
	}

	/*
	 * public boolean clickContinue() { try { btnContinue.click();
	 * Thread.sleep(500);
	 * 
	 * // Check if error alert is displayed List<WebElement> alerts = driver
	 * .findElements(By.xpath("//div[@class='alert alert-danger alert-dismissible']"
	 * )); if (alerts.size() > 0 && alerts.get(0).isDisplayed()) { // Alert present
	 * -> return false return false; }
	 * 
	 * // No alert -> return true return true;
	 * 
	 * } catch (Exception e) { // On any exception, consider click failed return
	 * false; } }
	 */
	public boolean clickContinue() {
	    try {
	        btnContinue.click();

	        Thread.sleep(500);  

	        try {
	            if (alertMsg.isDisplayed()) {
	                return false;  
	            }
	        } catch (Exception e) {
	            
	        }

	        return true; 
	    } catch (Exception e) {
	        return false;  
	    }
	}

}
