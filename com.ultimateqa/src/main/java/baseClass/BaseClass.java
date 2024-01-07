package baseClass;

import java.io.InputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import pages.ConsultationPage;
import pages.FreeCoursesPage;
import pages.HomePage;
import pages.SignInPage;
import pages.SubmitForm;
import utilities.DriverSetup;
import utilities.ExcelUtility;
import utilities.ListenerUtility;
import utilities.PropertiesFileReader;

@Listeners(ListenerUtility.class)
public class BaseClass 
{
	public static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
	public static PropertiesFileReader configFileData;
	public static String baseUrl;
	public static String email;
	public static String pass;
	public static InputStream credFile;
	public static ExcelUtility dataExcel;
	public ConsultationPage cp;
	public SubmitForm sf;
	public HomePage hp;
	public SignInPage sp;
	public FreeCoursesPage fp;
	public static String[][] registrationData;
	
	
	
//	@Parameters({"browser"})
//	@BeforeTest
//	public void configurations(String browser) throws Exception
//	{
//		configFile = this.getClass().getResourceAsStream("/config.properties");
//		reader = new PropertiesFileReader(configFile);
//		//browser = reader.getValue("browser");
//		url = reader.getValue("baseUrl");
////		reader = new PropertiesFileReader(credFile);
////		email = reader.getValue("Email");
////		pass = reader.getValue("Password");
////		
//		if(browser.equalsIgnoreCase("chrome"))
//		{
//			System.out.println("Chrome is being opened");
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions co = new ChromeOptions();
//			//To start the browser in maximized window
//			co.addArguments("start-maximized");
//			
//			//To disable the notifications
//			co.addArguments("--disable-notifications");
//			driver = new ChromeDriver(co);
//		} else if(browser.equalsIgnoreCase("firefox")) 
//		{
//			System.out.println("Firefox is being opened");
//			
//			WebDriverManager.firefoxdriver().setup();
//			
//			FirefoxBinary fb = new FirefoxBinary();
//			FirefoxOptions fo = new FirefoxOptions();
//			fo.setBinary(fb);
//			
//			//To disable the notifications
//			fo.addPreference("dom.webnotifications.enable", false);
//			
//			//To disable the notification for location
//			fo.addPreference("geo.enabled", false);
//			driver =  new FirefoxDriver(fo);
//			driver.manage().window().maximize();
//		} else
//		{
//			System.out.println("Edge is being opened");
//			
//			WebDriverManager.edgedriver().setup();
//			EdgeOptions eo = new EdgeOptions();
//			
//			//To maximize the browser window
//			eo.addArguments("start-maximized");
//			
//			//To disable the notifications
//			eo.addArguments("--disable-notifications");
//			
//			driver = new EdgeDriver();
//		}		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//		action = new Actions(driver);
//		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		fwait = new FluentWait<WebDriver>(driver);
//		driver.get(url);
//		cp = new ConsultationPage();
//		sf = new SubmitForm();
//		hp = new HomePage();
//		sp = new SignInPage();
//		fp = new FreeCoursesPage();
//
//	}
	
	@BeforeSuite
	public void openDataExcel() throws Exception
	{
		dataExcel = new ExcelUtility(this.getClass().getClassLoader().getResource("Data.xlsx").openStream());
		dataExcel.openSheet("Data");
		registrationData = new String[dataExcel.getRowCount()][dataExcel.getColumnCount()+1];
		for(int i =0; i<dataExcel.getRowCount();i++)
		{
			for(int j=0;j<dataExcel.getColumnCount()+1;j++)
			{
				registrationData[i][j] = dataExcel.getCellData(i+1, j);
			}
		}
	}
	
	
	
	@BeforeSuite
	public void config1() throws Exception
	{
		configFileData = new PropertiesFileReader(this.getClass().getResourceAsStream("/config.properties"));
		baseUrl = configFileData.getValue("baseUrl");
		
	}
	

	@Parameters({"browser"})
	@BeforeTest
	public void config2(String browser)
	{
		
		WebDriver driver = DriverSetup.setupDriver(browser);
		driverLocal.set(driver);
		hp = new HomePage(driverLocal.get(), baseUrl);
		cp = new ConsultationPage(driverLocal.get());
		sf = new SubmitForm(driverLocal.get());
		sp = new SignInPage(driverLocal.get());
		fp = new FreeCoursesPage(driverLocal.get());
	}
	
	@AfterTest
	public void end()
	{
		WebDriver driver = driverLocal.get();
		if(driver!=null)
		{
			driver.quit();
			driverLocal.set(null);
		}
	}
}
	

