package pages.flights;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class FlightsPage extends BasePage {
	
	By fromBoxLocator = By.className("fswFld");
	By cityBoxLocator = By.xpath("//input[@type='text']");
	By dropdownListLoc = By.xpath("//*[@id='autoSuggest-list']/li");
	By fromTextLocator = By.xpath("(//*[contains(@class,'fswWidgetTitle')])[1]");
	By toTextLocator = By.xpath("(//*[contains(@class,'fswWidgetTitle')])[2]");
	By monthLocator = By.className("DayPicker-Caption");
	By calenderRightArrowLocator = By.className("DayPicker-NavButton--next");
	By calenderLeftArrowLocator = By.className("DayPicker-NavButton--prev");
	By dateDoneLoc = By.className("fswTrvl__done");
	By fare = By.xpath("//*[@class='fsw__price ']");
	By randDtFare = By.xpath("//*[@aria-selected='true']/p[2]");
	By dte = By.xpath("//*[@aria-selected='true']/p[1]");
	By disDteLoc = By.xpath("(//p[contains(@class,'fswWidgetTitle')])[3]");
	By trvlDoneLoc = By.linkText("Done");
	By cls = By.xpath("(//p[contains(@class,'fswWidgetTitle')]/following-sibling::*)[4]");
	By parentLoc = By.className("bTdRNA");
	By child = By.xpath("following-sibling::*/span");
	By ieframe = By.id("sec-cpt-if");
	By modal = By.className("modal");
	By dateLoc = By.xpath("(//*[contains(@class,'fswFld')])[3]");
	
	
	String randomDateLoc = "//*[contains(@aria-label,'%s')]/p[1]";
	String calFares = "//*[contains(@aria-label,'%s')]/p[2]";
	
	WebElement calenderLeftArrow, calenderRightArrow;
	WebElement randomDate,dateDone;
	WebElement trvlDone;
	WebElement parent;
	
	public static List<WebElement> textBoxes, months ;
	
	public FlightsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void verifyPage() {
		assertEquals(driver.getTitle(), "Flight Tickets, Flights Booking at Lowest Airfare, Book Air Tickets-Goibibo");
	}
		
	public void verifyFromBox()
	{
		textBoxes = driver.findElements(fromBoxLocator);
		assertTrue(textBoxes.get(0).isEnabled());
		textBoxes.get(0).click();
	}
	
	public void selectCity(String city)
	{
		driver.findElement(cityBoxLocator).sendKeys(city);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(dropdownListLoc)));
		List<WebElement> dropDownList = driver.findElements(dropdownListLoc);
		for (WebElement child : dropDownList) 
		{
			String text = child.getText();
			if(text.contains(city))
			{
				child.click();
				break;
			}
		}
	}
	
	public void verifyFromCity(String city)
	{
		String fromCity = driver.findElement(fromTextLocator).getText();
		assertEquals(fromCity,city);
	}
	
	public void verifyToBox(){
		assertTrue(textBoxes.get(1).isEnabled());
	}

	public void verifyToCity(String city)
	{
		String toCity = driver.findElement(toTextLocator).getText();
		assertEquals(toCity,city);
	}
	
	public void verifyDateBtn()
	{
		assertTrue(textBoxes.get(2).isEnabled());
	}
	
	public void selectMonth(String month) throws ParseException
	{
		calenderRightArrow = driver.findElement(calenderRightArrowLocator);
		calenderLeftArrow = driver.findElement(calenderLeftArrowLocator);
		months = driver.findElements(monthLocator);
		SimpleDateFormat dtf = new SimpleDateFormat("MMMM yyyy");
		Date userMonth = dtf.parse(month);
		List<Date> displayedMonths;
		do 	
		{
			displayedMonths = months.stream().map(k -> {
															try {
																return dtf.parse(k.getText());
															} catch (ParseException e) {
																e.printStackTrace();
															}
															return null;
														}).toList();
			wait.until(ExpectedConditions.presenceOfElementLocated(fare));
			if(displayedMonths.get(1).compareTo(userMonth)<0)
				calenderRightArrow.click();
			else if(displayedMonths.get(0).compareTo(userMonth)>0)
				calenderLeftArrow.click();

		}while(!displayedMonths.contains(userMonth));
		assertTrue(displayedMonths.contains(userMonth));
	}
	
	public void randomDateSel(String mon)
	{
		randomDate = driver.findElement(By.xpath(String.format(randomDateLoc, mon.substring(0,3))));
		randomDate.click();
		dateDone = driver.findElement(dateDoneLoc);
		dateDone.click();
	}
	
	
	public void lowestFareCheck(int chpFare)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(dateLoc));
		driver.findElement(dateLoc).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(fare));
		assertEquals(chpFare, Integer.parseInt(driver.findElement(randDtFare).getText().replace(",", "")));
	}
	
	public void selectDate(String month)
	{
		List<WebElement> monFares = driver.findElements(By.xpath(String.format(calFares, month.substring(0, 3))));
		List<Integer> mFares = monFares.stream().map(k->{
			return Integer.parseInt(k.getText().replace(",", ""));
		}).toList();
		
		for (WebElement dt : monFares) {
			if(Integer.parseInt(dt.getText().replace(",", ""))==(Collections.min(mFares)))
			{
				dt.click();
				break;
			}
		}
		String checkSelDt = driver.findElement(dte).getText();
		dateDone = driver.findElement(dateDoneLoc);
		dateDone.click();
		assertTrue(driver.findElement(disDteLoc).getText().contains(checkSelDt));
	}
	
	public void travellerDetails(String clas)
	{
		trvlDone = driver.findElement(trvlDoneLoc);
		trvlDone.click();
		String text = driver.findElement(cls).getText();
		assertEquals(text, clas);
	}

	public void searchFlightBtn()
	{
		parent = driver.findElement(parentLoc);
		parent.findElement(child).click();
		if(!driver.findElements(ieframe).isEmpty()) {
		driver.switchTo().frame(driver.findElement(ieframe));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(modal)));
		}
	}
}
