package utilities;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ListenerUtility2 implements ITestListener{
	public static HashMap<String, ExtentTest> testid = new HashMap<>();
	public static HashMap<String,ExtentReports> reportid = new HashMap<>();
	private static final String reportFilePath = "./Report/";
	public static int count = 0;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		
		try {
			
			WebDriver driver = (WebDriver) ((ThreadLocal<WebDriver>) result.getMethod()
							.getTestClass().getRealClass().getSuperclass()
							.getDeclaredField("driverLocal").get(result.getInstance())).get();
			String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
			System.out.println(browser);
			if(reportid.get(browser)==null)
			{
				ExtentReports report = ExtentReportUtility.getReportInstance(reportFilePath+"Report_"+browser+".html");
				reportid.put(browser, report);
			}
			
			Method method = result.getMethod().getConstructorOrMethod().getMethod();
			String testName = method.getAnnotation(Test.class).testName();
			String cat = browser + "_" + result.getMethod().getRealClass().getSimpleName();
			System.out.println(cat);
			testid.put(cat, reportid.get(browser).createTest(testName));
			testid.get(cat).info(result.getMethod().getDescription());
			testid.get(cat).assignCategory(cat);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		try {
			WebDriver driver = (WebDriver) ((ThreadLocal<WebDriver>) result.getMethod().getTestClass().getRealClass().getSuperclass().getDeclaredField("driverLocal").get(result.getInstance())).get();
			String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
			String cat = browser + "_" + result.getMethod().getRealClass().getSimpleName();
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			count++;		
			FileUtils.copyFile(f,new File("./Screenshot/" + count + ".png"));
			testid.get(cat).pass((String) result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).attributes()[0].values()[0],
					MediaEntityBuilder.createScreenCaptureFromPath("../Screenshot/" + count +".png").build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		try {
		WebDriver driver = (WebDriver) ((ThreadLocal<WebDriver>) result.getMethod().getTestClass().getRealClass().getSuperclass().getDeclaredField("driverLocal").get(result.getInstance())).get();
		String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		String cat = browser + "_" + result.getMethod().getRealClass().getSimpleName();
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		count++;			
		FileUtils.copyFile(f,new File("./Screenshot/" + count + ".png"));
		testid.get(cat).fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath("../Screenshot/"+count+".png").build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		try
		{
			WebDriver driver = (WebDriver) ((ThreadLocal<WebDriver>) result.getMethod().getTestClass().getRealClass().getSuperclass().getDeclaredField("driverLocal").get(result.getInstance())).get();
			String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
			String cat = browser + "_" + result.getMethod().getRealClass().getSimpleName();
			testid.get(cat).skip(result.getThrowable().getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		for (Map.Entry<String, ExtentReports> entry: reportid.entrySet())
		{
			entry.getValue().flush();
		}
		System.out.println(testid.size());
	}
	
	
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){}

}
