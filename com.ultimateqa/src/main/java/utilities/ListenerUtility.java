package utilities;



import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.io.Files;

public class ListenerUtility implements ITestListener {
	
	private static final String reportFilePath = "./Report/Report.html";
	private static ExtentReports report = ExtentReportUtility.getReportInstance(reportFilePath);
	private static String passMessage;
	private static ExtentTest test;
	private static WebDriver driver;
	
	@Override
	public void onTestStart(ITestResult result)
	{
	
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		String testName = method.getAnnotation(Test.class).testName();
		String cat = result.getMethod().getRealClass().getSimpleName();
		test = report.createTest(testName);
		test.assignCategory(cat);
		test.info(result.getMethod().getDescription());
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		try {
			driver = (WebDriver) result.getMethod().getTestClass().getRealClass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,new File("./Screenshot/" + result.getMethod().getConstructorOrMethod().getMethod().getName() + ".png"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test.pass(passMessage,MediaEntityBuilder.createScreenCaptureFromPath("../Screenshot/"+result.getMethod().getConstructorOrMethod().getMethod().getName()+".png").build());
		passMessage = "";
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		try {
			driver = (WebDriver) result.getMethod().getTestClass().getRealClass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
				Files.copy(f,new File("./Screenshot/" + result.getMethod().getConstructorOrMethod().getMethod().getName() + ".png"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath("../Screenshot/"+result.getMethod().getConstructorOrMethod().getMethod().getName()+".png").build());
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test.skip(result.getThrowable().getMessage());
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		report.flush();
	}
	
	
	public static void setPassMessage(String msg)
	{
		passMessage = msg;
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){}
}
