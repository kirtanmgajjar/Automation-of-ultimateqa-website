package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import static utilities.ListenerUtility.setPassMessage;

public class SubmitFormTest extends BaseClass {
		
	@Test(testName = "Free Consultation Button",
			description = "To validate the functionality of \"Free Consultation Button\"")
	public void clickConsultation() throws InterruptedException
	{
		
		cp.verifyTitle();
		cp.clickFreeConsultation();
		setPassMessage("Form submission page is successfully opened");
	}
	@Test(testName = "Submit form",
			description = "To validate submit functioality by filling details",
			dependsOnMethods = {"clickConsultation"},
			dataProvider = "filldetails")
	public void fillForm(String data[])
	{
		sf.fillDetails(data);
		setPassMessage("Details are submitted successfully");
		
	}
	
	@Test(testName = "Navigate to homepage",
			description = "To validate the navigation to homepage",
			dependsOnMethods = {"fillForm"})
	public void navigateToHomepage() throws InterruptedException
	{
		hp.navigateHomePage();
		setPassMessage("Navigated to homepage successfully");
	}
	
	@Test(testName = "Navigate to SignIn page",
			description = "To validate page by navigating to Signin page",
			dependsOnMethods = "navigateToHomepage")
	public void navigateToSignin()
	{
		hp.hoverLearningMenu();
		hp.clickFreeCourse();
		hp.navigateToSignin();
		setPassMessage("Navigated to SignIn page successfully");
	}
	
	@Test(testName = "Error Message",
			description = "To validate by sigining with wrong credentials and retreiving the error message",
			dependsOnMethods = "navigateToSignin")
	public void signIn() throws InterruptedException
	{
		sp.sendEmail();
		sp.sendPassword();
		sp.clickSubmit();
	}
	@DataProvider(name = "filldetails")
	public String[][] getData()
	{
		dataExcel.openSheet("Data");
		String[][] data = new String[dataExcel.getRowCount()][dataExcel.getColumnCount()+1];
		for(int i =0; i<dataExcel.getRowCount();i++)
		{
			for(int j=0;j<dataExcel.getColumnCount()+1;j++)
			{
				data[i][j] = dataExcel.getCellData(i+1, j);
			}
		}
		return data;
	}
}
