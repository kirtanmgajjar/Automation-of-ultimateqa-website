package allTest;

import org.testng.annotations.CustomAttribute;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class LoginTest extends BaseClass{
	
	@DataProvider
	public String[] invalidMobileNumbers()
	{
		String[] data = {"1234567890","edf4567890"};
		return data;
	}
	
	
	@Test(testName = "Verify login functionality using invalid mobile number",
			description = "To verify that user is not able to enter invalid mobile number",
			dataProvider = "invalidMobileNumbers",
			attributes = {@CustomAttribute(name="passMessage",values={"Proper error messaged is displayed successfully"})})
	public void invalidSignIn(String mobileNumber)
	{
		hp.enterMobileNumber(mobileNumber);
		hp.getErrorMessage();
	}
	
	
	@Test(testName = "Verify login functionality using valid mobile number",
			description = "To verify that user is able to enter valid mobile number and able to login successfully",
			dependsOnMethods = "invalidSignIn",
			attributes = {@CustomAttribute(name="passMessage",values={"User is logged in successfully"})})
	public void signIn() throws InterruptedException
	{
		hp.enterMobileNumber(mobile);
		hp.verifyValidLogin(user);
	}
	
		
}