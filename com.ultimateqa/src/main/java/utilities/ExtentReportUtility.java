package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReportUtility {
	private static ExtentReports report;
	
	//Method to create the instance of the ExtentReports class 
	public static ExtentReports getReportInstance(String filePath)
	{
		if(report== null)
		{
			//Creates the object of the spark reporter using the filepath for the HTML file 
			ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
			spark.config().setDocumentTitle("UI Automation Results");
			spark.config().setReportName("Test Report");
			spark.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			spark.config().setTheme(Theme.DARK);
			spark.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD,
					ViewName.TEST, ViewName.CATEGORY});
			
			//Creates the object of the ExtentReport class
			report = new ExtentReports();
			report.attachReporter(spark);
			
			//Setting the system info of the environment used 
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Browser", "Chrome");
			report.setSystemInfo("Browser", "Edge");
			report.setSystemInfo("Browser", "Firefox");
			
		}

		return report;
	}
	
}
