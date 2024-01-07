package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSetup {
	
	private static WebDriver driver;
	
	public static WebDriver setupDriver(String choice)
	{
		if (choice.equalsIgnoreCase("Chrome"))
		{
			
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions co = new ChromeOptions();
			co.addArguments("start-maximized","--disable-notifications");
			
			driver = new ChromeDriver(co);
			
		}
		
		
		else if (choice.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxOptions fo = new FirefoxOptions();
			FirefoxBinary fb = new FirefoxBinary();
			fo.setBinary(fb);
			fo.addPreference("geo.enabled", false);
			
			driver = new FirefoxDriver(fo);
			
			driver.manage().window().maximize();
		}
		
		else
		{
			WebDriverManager.edgedriver().setup();
			
			EdgeOptions eo = new EdgeOptions();
			eo.addArguments("start-maximized","--disable-notifications");
			
			driver = new EdgeDriver(eo);
		}
		
		return driver;
	}
	
}
