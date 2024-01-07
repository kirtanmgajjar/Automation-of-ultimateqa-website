package baseClass;

import java.io.InputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import locatorsUtility.HomepageLocators;
import pages.Homepage;
import pages.flights.FlightsBookingPage;
import pages.flights.FlightsPage;
import pages.flights.FlightsResultsPage;
import pages.hotels.HotelResultsPage;
import pages.hotels.HotelsBookingPage;
import pages.hotels.HotelsPage;
import utilities.DriverSetup;
import utilities.ListenerUtility2;
import utilities.PropertiesFileReader;

@Listeners(ListenerUtility2.class)
public class BaseClass extends HomepageLocators {
	public static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
	
	public static String browser;
	public static String email;
	public static String pass;
	public static String user;
	public static InputStream configFile;
	public static InputStream credFile;
	public static PropertiesFileReader reader;
	public static String url ;
	public static String mobile;
	public static Actions action;
	public Homepage hp;
	public HotelsPage ho;
	public HotelResultsPage hr;
	public HotelsBookingPage hb;
	public FlightsPage fp;
	public FlightsResultsPage fr;
	public FlightsBookingPage fb;
	public WebDriver driver;
	
	
	@BeforeSuite
	public void configurations() throws Exception
	{
		configFile = this.getClass().getResourceAsStream("/config.properties");
		reader = new PropertiesFileReader(configFile);
		browser = reader.getValue("browser");
		url = reader.getValue("baseUrl");
		credFile = this.getClass().getResourceAsStream("/credential.properties");
		reader = new PropertiesFileReader(credFile);
		email = reader.getValue("Email");
		pass = reader.getValue("Password");
		mobile = reader.getValue("Mobile");
		user = reader.getValue("User");
//		driver = DriverSetup.setupDriver(browser);
//		driver.get(url);
		System.out.println(browser+" "+url+" "+mobile+" "+user);
	}
	
	@BeforeTest
	public void configPages()
	{
		driverLocal.set(DriverSetup.setupDriver(browser));
		driverLocal.get().get(url);
		hp = new Homepage(driverLocal.get());
		ho = new HotelsPage(driverLocal.get());
		hr = new HotelResultsPage(driverLocal.get());
		hb = new HotelsBookingPage(driverLocal.get());
		fp = new FlightsPage(driverLocal.get());
		fr = new FlightsResultsPage(driverLocal.get());
		fb = new FlightsBookingPage(driverLocal.get());
	}
	
	@AfterTest
	public void tearDown()
	{
		WebDriver driver1 = driverLocal.get();
		if(driver1!=null)
		{
			driver1.quit();
			driverLocal.set(null);
		}
		System.out.println(browser+" browser is closed successfully.");
	}
}
