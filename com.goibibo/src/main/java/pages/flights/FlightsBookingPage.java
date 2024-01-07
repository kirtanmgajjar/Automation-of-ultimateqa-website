package pages.flights;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;

public class FlightsBookingPage extends BasePage{

	By detailsCardLoc = By.xpath("(//*[contains(@class,'BkFltCrd')])[1]");
	By stateMenuLocator = By.xpath("//*[@class='billing-input-container']/div/div");
	By firstNameTextboxLocator = By.name("givenname");
	By lastNameTextboxLocator = By.name("lastname");
	By emailTextboxLocator = By.name("email");
	By phoneTextboxLocator = By.name("mobile");
	By countryCodeSelectboxLocator = By.xpath("(//*[contains(@class,'CustSelectTrvl')])[2]");
	By genderLoc = By.xpath("(//*[contains(@class,'CustSelectTrvl')])[1]");
	By trpSecLoc = By.xpath("(//*[contains(@class,'dweb-commonstyles__Button')])[1]");
	By proceedPayLoc = By.xpath("(//*[contains(@class,'FltBtn')])[2]");
	By skpPayLoc = By.xpath("//*[contains(@class,'SkipPaymentWrapper')]");
	By modalBoxLoc = By.xpath("//*[contains(@class,'__Modal-sc')]");
	By msgLoc = By.xpath("(//*[contains(@class,'flight-reviewstyles__Container')])[1]");
	By errorMessageLocator = By.className("redText");
	
	
	String stateSelectLocator = "//*[@class='sc-gmPhUn bVtUiH' and text()='%s']";
	
	WebElement modalBox;
	
	String psngrInfo ;

	
	public FlightsBookingPage(WebDriver driver) 
	{
		super(driver);
	}

	@Override
	public void verifyPage() 
	{
		String msg = driver.findElement(msgLoc).getText();
		assertEquals(msg, "Review your booking");
	}

	public void reviewDetails()
	{
		System.out.println("--------------------------------------------------------------------");
		System.out.println(driver.findElement(detailsCardLoc).getText());
		System.out.println("--------------------------------------------------------------------");
	}
	
	public void enterState(String state)
	{
		driver.findElement(stateMenuLocator).click();
		driver.findElement(By.xpath(String.format(stateSelectLocator, state)));
		
	}
	public void selGender(String gen)
	{
		Select select = new Select(driver.findElement(genderLoc ));
		select.selectByValue(gen.toUpperCase());
	}
	
	public void enterFirstName(String fname)
	{
		driver.findElement(firstNameTextboxLocator).sendKeys(fname);
	}
	
	public void enterLastName(String lname)
	{
		driver.findElement(lastNameTextboxLocator).sendKeys(lname);
	}
	
	public void enterEmail(String email)
	{
		driver.findElement(emailTextboxLocator).sendKeys(email);
	}
	
	public void enterCountryCode(String code)
	{
		Select countryCode = new Select(driver.findElement(countryCodeSelectboxLocator));
		countryCode.selectByValue(code);
	}
	
	public void enterMobileNumber(String number)
	{
		driver.findElement(phoneTextboxLocator).sendKeys(number);
	}
	public void clickProceedToPayment()
	{
		driver.findElement(trpSecLoc).click();
		driver.findElement(proceedPayLoc).click();
		try
		{
		wait.until(ExpectedConditions.elementToBeClickable(By.className("iocrTs")));
		driver.findElement(By.className("iocrTs")).click();
		} catch(Exception e)
		{
			
		}
		driver.findElement(proceedPayLoc).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(skpPayLoc)));
		driver.findElement(skpPayLoc).click();
		modalBox = driver.findElement(modalBoxLoc);
		wait.until(ExpectedConditions.visibilityOf(modalBox));
		wait.until(ExpectedConditions.invisibilityOf(modalBox));
		assertEquals(driver.getTitle(), "Goibibo - Best Travel Website. Book Hotels, Flights, Trains, Bus and Cabs with upto 50% off");
	}
	
	public void verifyGrandTotal(int fare) throws InterruptedException
	{
		action.scrollToElement(driver.findElement(By.xpath("(//*[contains(@class,'price-breakupstyles__BreakupTitle-')]/span)[6]"))).perform();
		int grandTotal = 0;
		String fr="";
		Thread.sleep(3000);
		while(fr.equals(""))
		{
			fr = driver.findElement(By.xpath("(//*[contains(@class,'price-breakupstyles__BreakupTitle-')]/span)[6]")).getText().replace("₹","").replace(",","");
			grandTotal = Integer.valueOf(fr);
		}
		int baseFare = Integer.valueOf(driver.findElement(By.xpath("(//*[contains(@class,'price-breakupstyles__BreakupTitle-')]/span)[3]")).getText().replace("₹","").replace(",",""));
		int taxes = Integer.valueOf(driver.findElement(By.xpath("(//*[contains(@class,'price-breakupstyles__BreakupTitle-')]/span)[5]")).getText().replace("₹","").replace(",",""));
		assertEquals(fare,grandTotal);
		assertTrue(grandTotal==(baseFare+taxes));
	}
	
	public void verifyFromCity(String fromCity)
	{
		driver.findElement(By.className("blueBgArr")).click();
		String fromTocity = driver.findElement(By.className("bookingSumry__lob")).getText();
		assertTrue(fromTocity.contains(fromCity));
	}
	
	public void verifyToCity(String toCity)
	{
		String fromTocity = driver.findElement(By.className("bookingSumry__lob")).getText();
		assertTrue(fromTocity.contains(toCity));
	}
	
	public void verifyName(String fname, String lname)
	{
		psngrInfo = driver.findElement(By.className("appendBottom8")).getText();
		assertTrue(psngrInfo.contains(fname+" "+lname));
	}
	
	public void verifyGender(String gen)
	{
		assertTrue(psngrInfo.contains(gen.toUpperCase()));
	}
	
	public void verifyEmail(String mail)
	{
		psngrInfo = driver.findElement(By.xpath("//*[@class='flexOne']/p[2]")).getText();
		assertTrue(psngrInfo.contains(mail));
	}
	
	public void verifyMob(String cCode, String mob)
	{
		assertTrue(psngrInfo.contains("+"+cCode+mob));
	}
	
	public void verifyPaymentTotal(int fare)
	{
		int finalGrandTotal = Integer.parseInt(driver.findElement(By.className("lightGreenText")).getText());
		int convenienceFee = Integer.parseInt(driver.findElement(By.xpath("(//*[contains(@class,'rupees')])[4]")).getText());
		assertTrue(finalGrandTotal==(fare+convenienceFee));
		
	}
	public void getErrorMessage()
	{
		System.out.println("Payment Error Message: " + driver.findElement(errorMessageLocator ).getText());
	}
}
