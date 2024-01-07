package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homepage extends BasePage
{
	
	By hotelsLocator = By.linkText("Hotels");
	By flightsLocator = By.linkText("Flights");
	By holidayLocator = By.linkText("Holidays");
	By trainsLocator = By.linkText("Trains");
	By signInLinkLocator = By.id("get_sign_in");
	By mobileTextboxLocator = By.name("phone");
	By continueButtonLocator = By.cssSelector("button[type='submit']");
	By signInMessageLocator = By.xpath("//*[contains(@class,'sc-1we57hl-0')]");
	By userProfileLocator = By.xpath("//*[@class='sc-gEvEer fWAnMN gr-grey-text--dark username-highlight']"); 
	By closeButtonLocator = By.xpath("//*[@class='logSprite icClose']");
	By errorMessageLocator = By.xpath("//*[@class='sc-jlZhew dSoaQL']");
	By userProfileMenuLocator = By.xpath("//*[contains(@class,'gr-flex-one ')]");
	By logoutButtonLocator = By.xpath("//*[contains(@class,'sc-11civud-0')]");
	By confirmLogoutButtonLocator = By.xpath("//*[contains(@class,'sc-sypgwv-6')]");
	By loginTabLocator = By.xpath("//*[@class='login__tab_wrapper']");
	
	WebElement hotelsMenu, flightsMenu, holidayMenu, trainsMenu;
	WebElement signinMenu;
	WebElement mobileTextbox;
	WebElement continueButton;
	WebElement userProfile;
	WebElement signInMessage;
	WebElement closeButton;
	WebElement errorMessage;
	WebElement userProfileMenu;
	WebElement logoutButton;
	WebElement confirmLogoutButton;
	WebElement loginTab;
	
	

	public Homepage(WebDriver driver) 
	{
		super(driver);
	}

	@Override
	public void verifyPage() 
	{
		assertEquals(driver.getTitle(), "Goibibo - Best Travel Website. Book Hotels, Flights, Trains, Bus and Cabs with upto 50% off");
	}
	
	public void navigateToHotels()
	{
		hotelsMenu = driver.findElement(hotelsLocator);
		hotelsMenu.click();
	}
	public void navigateToFlights()
	{
		flightsMenu = driver.findElement(flightsLocator);
		assertTrue(flightsMenu.isDisplayed());
		flightsMenu.click();
	}
	
	public void navigateToHoliday()
	{
		holidayMenu = driver.findElement(holidayLocator);
		assertTrue(holidayMenu.isDisplayed());
		holidayMenu.click();
	}
	
	public void navigateToTrains()
	{
		trainsMenu = driver.findElement(trainsLocator);
		assertTrue(trainsMenu.isDisplayed());
		trainsMenu.click();
	}
	
	
	public void openSignInPopup()
	{
		signinMenu = driver.findElement(signInLinkLocator);
		signinMenu.click();
	}
	
	public void enterMobileNumber(String mobileNumber)
	{
		mobileTextbox = wait.until(ExpectedConditions.visibilityOf(driver.findElement(mobileTextboxLocator)));
		mobileTextbox.clear();
		mobileTextbox.sendKeys(mobileNumber);
	}
	
	public String getErrorMessage()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMessageLocator)));
		errorMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMessageLocator)));
		System.out.println("Error Message: " + errorMessage.getText());
		return errorMessage.getText();
	}
	
	public void verifyErrorMessage(String expectedMessage)
	{
		assertEquals(getErrorMessage(), expectedMessage);
	}
	
	public void clickContinueButton()
	{
		continueButton = driver.findElement(continueButtonLocator);
		continueButton.click();
	}
	
	public void verifyValidLogin(String user)
	{
		signInMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(signInMessageLocator)));
		System.out.println("Success message: " + signInMessage.getText());
		wait.until(ExpectedConditions.invisibilityOf(signInMessage));
		userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(userProfileLocator));
		String userData = wait.until(ExpectedConditions.presenceOfElementLocated(userProfileLocator)).getText();
		assertEquals(userData, "Hey "+user);
	}
	
	public void closeSigninPopup()
	{
		closeButton =  driver.findElement(closeButtonLocator);
		closeButton.click();
	}
	
	public void logOut()
	{
		loginTab = wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginTabLocator)));
		action.moveToElement(loginTab).perform();
		userProfileMenu = wait.until(ExpectedConditions.visibilityOf(driver.findElement(userProfileMenuLocator)));
		userProfileMenu.click();
		logoutButton = driver.findElement(logoutButtonLocator);
		logoutButton.click();
		confirmLogoutButton = driver.findElement(confirmLogoutButtonLocator);
		confirmLogoutButton.click();
	}
	
}
