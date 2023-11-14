package baseClass;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ConsultationPage;
import pages.HomePage;
import pages.SignInPage;
import pages.SubmitForm;
import utilities.ExcelUtility;
import utilities.ListenerUtility;
import utilities.PropertiesFileReader;

@Listeners(ListenerUtility.class)
public class BaseClass {
	public static WebDriver driver;
	public static String browser;
	public static String email;
	public static String pass;
	public static WebDriverWait wait;
	public static InputStream configFile;
	public static InputStream credFile;
	public static PropertiesFileReader reader;
	public static String url ;
	public static Actions action;
	public static FluentWait<WebDriver> fwait;
	public static ExcelUtility dataExcel;
	public static ConsultationPage cp;
	public static SubmitForm sf;
	public static HomePage hp;
	public static SignInPage sp;
	@BeforeTest
	public void configurations() throws Exception
	{
		configFile = this.getClass().getResourceAsStream("/config.properties");
		reader = new PropertiesFileReader(configFile);
		browser = reader.getValue("browser");
		url = reader.getValue("baseUrl");
//		reader = new PropertiesFileReader(credFile);
//		email = reader.getValue("Email");
//		pass = reader.getValue("Password");
//		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.out.println("Chrome is being opened");
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			//To start the browser in maximized window
			co.addArguments("start-maximized");
			
			//To disable the notifications
			co.addArguments("--disable-notifications");
			driver = new ChromeDriver(co);
		} else if(browser.equalsIgnoreCase("firefox")) 
		{
			System.out.println("Firefox is being opened");
			
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxBinary fb = new FirefoxBinary();
			FirefoxOptions fo = new FirefoxOptions();
			fo.setBinary(fb);
			
			//To disable the notifications
			fo.addPreference("dom.webnotifications.enable", false);
			
			//To disable the notification for location
			fo.addPreference("geo.enabled", false);
			driver =  new FirefoxDriver(fo);
			driver.manage().window().maximize();
		} else
		{
			System.out.println("Edge is being opened");
			
			WebDriverManager.edgedriver().setup();
			EdgeOptions eo = new EdgeOptions();
			
			//To maximize the browser window
			eo.addArguments("start-maximized");
			
			//To disable the notifications
			eo.addArguments("--disable-notifications");
			
			driver = new EdgeDriver();
		}		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		action = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		fwait = new FluentWait<WebDriver>(driver);
		driver.get(url);
		cp = new ConsultationPage();
		sf = new SubmitForm();
		hp = new HomePage();
		sp = new SignInPage();

	}
	@BeforeTest
	public void openDataExcel() throws IOException
	{
		dataExcel = new ExcelUtility(this.getClass().getClassLoader().getResource("Data.xlsx").openStream());
	}
	
	//@AfterTest
	public void tearDown()
	{
		driver.quit();
		System.out.println(browser+" browser is closed successfully.");
	}
}
