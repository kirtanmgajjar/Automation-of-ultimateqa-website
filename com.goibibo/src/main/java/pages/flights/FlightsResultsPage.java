package pages.flights;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class FlightsResultsPage extends BasePage{

	By filAppLoc = By.xpath("(//div[contains(@class,'__SortTab-')])[4]/div[1]/*");
	By disFareLoc = By.xpath("(//div[contains(@class,'uistyles__Price-')])[1]");
	By chpFlightDetails = By.xpath("(//*[contains(@class,'uistyles__CardWrap')])[1]");
	By viewFareLoc = By.xpath("(//*[contains(@class,'BookButton')])[1]");
	By selFareLoc = By.xpath("(//input[@type='button'])[1]");
	By msgLoc = By.xpath("(//*[contains(@class,'flight-reviewstyles__Container')])[1]");
	
	String faresLoc ="(//*[contains(@class,'uistyles__Price-')])[%d]";
	String mainTab;
	String reviewTab;
	int displayedMinFare;
	
	public FlightsResultsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void verifyPage() {
		assertEquals(driver.getTitle(), "Book Cheap Flights, Air Tickets, Hotels, Bus & Holiday Package at Goibibo");
	}
	
	public void fliterApplied()
	{
		assertTrue(driver.findElements(filAppLoc).size()==2);
	}
	
	public void checkFilter(int minFare)
	{
		displayedMinFare = Integer.parseInt(driver.findElement(disFareLoc).getText().replace(",", ""));
		assertEquals(minFare,displayedMinFare);
	}
	
	public void flightBookBtn()
	{
		System.out.println(driver.findElement(chpFlightDetails).getText());
		action.scrollToElement(driver.findElement(viewFareLoc)).perform();
		driver.findElement(viewFareLoc).click();
		driver.findElement(selFareLoc).click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		iter= driver.getWindowHandles().iterator();
		mainTab = iter.next();
		reviewTab = iter.next();
		driver.switchTo().window(reviewTab);
		String msg = driver.findElement(msgLoc).getText();
		assertEquals(msg, "Review your booking");
	}
	
	public int getFare()
	{
		return displayedMinFare;
	}
	
	public Integer getChpFare()
	{
		int i=1;
		List<Integer> fares = new ArrayList<>();
		try {
			while(true)
			{
				action.scrollByAmount(0, 250).perform();
				fares.add(Integer.parseInt(driver.findElement(By.xpath(String.format(faresLoc, i))).getText().replace(",", "")));
				i++;
			}	
		} catch (Exception e) {
			return Collections.min(fares);
		}
	}
}
