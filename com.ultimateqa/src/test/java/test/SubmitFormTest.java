package test;

import org.testng.annotations.CustomAttribute;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class SubmitFormTest extends BaseClass 
{
	
		
	@Test(testName = "Free Consultation Button",
			description = "To validate the functionality of \"Free Consultation Button\"",
			attributes = {@CustomAttribute(name="passMessage",values={"Form submission page is successfully opened"})})
	public void clickConsultation() throws InterruptedException
	{
		
		cp.verifyTitle();
		cp.clickFreeConsultation();
	}
	
	
	@Test(testName = "Submit form", threadPoolSize = 3, invocationCount = 1,
			description = "To validate submit functioality by filling details",
			dependsOnMethods = {"clickConsultation"},
			dataProvider = "filldetails",
			attributes = {@CustomAttribute(name="passMessage",values= {"Details are submitted successfully"})})
	public void fillForm(String data[])
	{
		sf.fillDetails(data);
		
	}
	
	@Test(testName = "Navigate to homepage",
			description = "To validate the navigation to homepage",
			dependsOnMethods = {"fillForm"},
			attributes = {@CustomAttribute(name="passMessage",values= {"Navigated to homepage successfully"})})
	public void navigateToHomepage() throws InterruptedException
	{
		hp.navigateHomePage();
	}
	
	@Test(testName = "Search courses",
			description = "To validate the search functionality for seaching courses based on the subject",
			dependsOnMethods = "navigateToHomepage",
			attributes = {@CustomAttribute(name="passMessage",values= {"Results Found"})})
	public void validateSearchResults()
	{
		hp.hoverLearningMenu();
		hp.clickFreeCourse();
		fp.searchCourse("Selenium");
//		if(fp.hasSearchResults())
//			setPassMessage("Results Found");
//		else
//			setPassMessage("No Results Found");
		
		assertTrue(fp.hasSearchResults());
	}
	
	@Test(testName = "Navigate to SignIn page",
			description = "To validate page by navigating to Signin page",
			dependsOnMethods = "validateSearchResults",
			attributes = {@CustomAttribute(name="passMessage",values= {"Navigated to SignIn page successfully"})})
	public void navigateToSignin()
	{

		fp.navigateToSignin();
	}
	
	
	
	@Test(testName = "Error Message",
			description = "To validate by sigining with wrong credentials and retreiving the error message",
			dependsOnMethods = "navigateToSignin",
			attributes = {@CustomAttribute(name="passMessage",values= {"Error message successfully displayed"})})
	public void signIn() throws InterruptedException
	{
		sp.sendEmail();
		sp.sendPassword();
		sp.clickSubmit();
	}
	
	@DataProvider(name = "filldetails",parallel = true)
	public String[][] getData()
	{
		return registrationData;
	}
}
