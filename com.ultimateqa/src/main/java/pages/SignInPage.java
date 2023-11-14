package pages;

import org.openqa.selenium.WebElement;
import static utilities.ListenerUtility.setPassMessage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClass.BaseClass;

public class SignInPage extends BaseClass {
	
	@FindBy(id = "user[email]")
	WebElement email;
	@FindBy(id = "user[password]")
	WebElement pswd;
	@FindBy(className = "button-primary")
	WebElement submit;
	@FindBy(id = "notice")
	WebElement errorMsg;
	
	public SignInPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void sendEmail()
	{
		email.sendKeys("test123@gmail.com");
	}
	
	public void sendPassword()
	{
		pswd.sendKeys("Test@123");
	}
	
	public void clickSubmit() throws InterruptedException
	{
		submit.click();
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		String errorMsg2;
		errorMsg2 = errorMsg.getText();
		System.out.println("-----------------------------------------");
		System.out.println("Error message displayed is :\n"+errorMsg2);
		System.out.println("-----------------------------------------");
		setPassMessage("Error message is "+errorMsg2);
	}
}
