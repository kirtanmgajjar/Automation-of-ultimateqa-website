package pages.hotels;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;


public class HotelsPage extends BasePage{
	
	By whereInputBoxLocator = By.id("downshift-1-input");
	By checkOutDateLocator = By.xpath("//*[@data-testid='openCheckoutCalendar']");
	By checkInDateLocator = By.xpath("//*[@data-testid='openCheckinCalendar']");
	By currentMonthLocator = By.xpath("//*[@data-testid='currentCalendarMonthName']");
	By nextMonthLocator = By.xpath("//*[@data-testid='nextCalendarMonthName']");
	By calenderRightArrowLocator = By.xpath("//*[@data-testid='calendarRightArrowBtn']");
	By calenderLeftArrowLocator = By.xpath("//*[@data-testid='calendarLeftArrowBtn']");
	By guestDetailsLocator = By.xpath("//*[@data-testid='pax-text-wrapper']//input");
	By guestDetailsPopupLocator = By.xpath("//*[@data-testid='pax-modal-home']");
	By roomAddLocator = By.xpath("//*[@data-testid='button-room-add']");
	By adultAddLocator = By.xpath("//*[@data-testid='button-adult-add']");
	By childrenAddLocator = By.xpath("//*[@data-testid='button-child-add']");
	By roomDecLocator = By.xpath("//*[@data-testid='button-room-dec']");
	By adultDecLocator = By.xpath("//*[@data-testid='button-adult-dec']");
	By childrenDecLocator = By.xpath("//*[@data-testid='button-child-dec']");
	By roomCountLocator = By.xpath("//*[@data-testid='room-count']");
	By adultCountLocator = By.xpath("//*[@data-testid='adult-count']");
	By childrenCountLocator = By.xpath("//*[@data-testid='child-count']");
	By doneButtonLocator = By.xpath("//button[text()='Done']");
	By searchButtonLocator = By.xpath("//*[@data-testid='searchHotelBtn']");
	By childrenAgeSelectBoxLocator = By.xpath("//*[@class='dwebCommonstyles__CenteredSpaceWrap-sc-112ty3f-0 PaxWidgetstyles__ContentActionWrapperDiv-sc-gv3w6r-5 PaxWidgetstyles__ContentActionDropdownWrapper-sc-gv3w6r-6 eHRHNU lhNBSu kfVCVI']");
	
	String countryTypeLocator = "//input[@name='CountryType']/following-sibling::*[text()='%s']";
	String requiredDateLocator = "//*[@data-testid='date_li_%d_%d_%d']";
	String childrenAgeSelectBoxMenuLocator = "//*[@class='PaxWidgetstyles__ChildDropdownWrap-sc-gv3w6r-9 cuOfFa']/li[text()='%d']";
	
	
	WebElement country;
	WebElement whereInputbox;
	WebElement checkInDate;
	WebElement checkOutDate;
	WebElement currentMonth;
	WebElement nextMonth;
	WebElement calenderRightArrow;
	WebElement calenderLeftArrow;
	WebElement guestDetailsMenu;
	WebElement roomAdd;
	WebElement adultAdd;
	WebElement childrenAdd;
	WebElement roomDec;
	WebElement adultDec;
	WebElement childrenDec;
	WebElement roomCount;
	WebElement adultCount;
	WebElement childrenCount;
	WebElement doneButton;
	WebElement searchButton;
	

	public HotelsPage(WebDriver driver) 
	{
		super(driver);
	}

	@Override
	public void verifyPage() 
	{
		assertEquals(driver.getTitle(), "Online Hotel Booking | Book Cheap, Budget and Luxury Hotels -Goibibo");
	}
	
	public void selectCountryType(String choice)
	{
		country = driver.findElement(By.xpath(String.format(countryTypeLocator, choice)));
		action.scrollToElement(country).perform();
		country.click();
	}
	
	public void enterLocationOfHotel(String location)
	{
		whereInputbox = driver.findElement(whereInputBoxLocator);
		whereInputbox.sendKeys(location);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='downshift-1-menu']/li")));
		List<WebElement> options = driver.findElements(By.xpath("//*[@id='downshift-1-menu']/li"));
		for(WebElement option: options)
		{
			wait.until(ExpectedConditions.visibilityOf(option));
			String s = option.getText();
			if (s.contains(location))
			{
				action.moveToElement(option).click().build().perform();
				break;
			}
		}
	}
	
	public void enterCheckInDate(String data)
	{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(data,dtf);
		
		checkInDate = driver.findElement(checkInDateLocator);
		wait.until(ExpectedConditions.elementToBeClickable(checkInDate));
		action.moveToElement(checkInDate).click().build().perform();
		currentMonth = driver.findElement(currentMonthLocator);
		calenderRightArrow = driver.findElement(calenderRightArrowLocator);
		calenderLeftArrow = driver.findElement(calenderLeftArrowLocator);
		
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		
		while(currentMonth.getText().equals(Month.of(month).toString()+" "+year))	
		{
			calenderRightArrow.click();
		}
		
		driver.findElement(By.xpath(String.format(requiredDateLocator,day,month-1,year))).click();
		
	}
	
	public void enterCheckOutDate(String data)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(data,dtf);
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		driver.findElement(By.xpath(String.format(requiredDateLocator,day,month-1,year))).click();
		
	}
	
	public void enterDate(String checkIn, String checkOut) throws Exception
	{
		checkInDate = driver.findElement(checkInDateLocator);
		wait.until(ExpectedConditions.elementToBeClickable(checkInDate));
		action.moveToElement(checkInDate).click().build().perform();
		currentMonth = driver.findElement(currentMonthLocator);
		nextMonth = driver.findElement(nextMonthLocator);
		calenderRightArrow = driver.findElement(calenderRightArrowLocator);
		calenderLeftArrow = driver.findElement(calenderLeftArrowLocator);

		SimpleDateFormat format1 = new SimpleDateFormat("MMMM yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MM-yyyy");
		Date currentMonthDate;
		Date nextMonthDate;
		Date checkInMonthDate = format2.parse(checkIn.substring(3));
		Date checkOutMonthDate = format2.parse(checkOut.substring(3));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate checkindate = LocalDate.parse(checkIn,dtf);
		int checkinday = checkindate.getDayOfMonth();
		int checkinmonth = checkindate.getMonthValue();
		int checkinyear = checkindate.getYear();
		
		do
		{
			currentMonthDate = format1.parse(currentMonth.getText());
			nextMonthDate = format1.parse(nextMonth.getText());
			if(nextMonthDate.compareTo(checkInMonthDate)<0)
				calenderRightArrow.click();
			else if(currentMonthDate.compareTo(checkInMonthDate)>0)
				calenderLeftArrow.click();
		}
		while(!currentMonthDate.equals(checkInMonthDate)&&!nextMonthDate.equals(checkInMonthDate));	
		
		driver.findElement(By.xpath(String.format(requiredDateLocator,checkinday,checkinmonth-1,checkinyear))).click();
		
		
		LocalDate checkoutdate = LocalDate.parse(checkOut,dtf);
		int checkoutday = checkoutdate.getDayOfMonth();
		int checkoutmonth = checkoutdate.getMonthValue();
		int checkoutyear = checkoutdate.getYear();
		
		do
		{
			currentMonthDate = format1.parse(currentMonth.getText());
			nextMonthDate = format1.parse(nextMonth.getText());
			if(nextMonthDate.compareTo(checkOutMonthDate)<0)
				calenderRightArrow.click();
			else if(currentMonthDate.compareTo(checkOutMonthDate)>0)
				calenderLeftArrow.click();
		}
		while(!currentMonthDate.equals(checkOutMonthDate)&&!nextMonthDate.equals(checkOutMonthDate));	
		
		driver.findElement(By.xpath(String.format(requiredDateLocator,checkoutday,checkoutmonth-1,checkoutyear))).click();
		
	}
	
	
	public void enterRooms(int rooms)
	{
		driver.findElement(guestDetailsLocator).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(guestDetailsPopupLocator)));
		roomCount = driver.findElement(roomCountLocator);
		roomAdd = driver.findElement(roomAddLocator);
		roomDec = driver.findElement(roomDecLocator);
		doneButton = driver.findElement(doneButtonLocator);
		
		int currentRooms = Integer.valueOf(roomCount.getText());
		
		if(currentRooms<rooms)
		{
			for(int i=0;i<rooms-currentRooms;i++)
			{
				roomAdd.click();
			}
		}
		else if(currentRooms>rooms)
		{
			for(int i=0;i<currentRooms-rooms;i++)
			{
				roomDec.click();
			}
		}
		
		doneButton.click();
	}
	
	public void add0Room()
	{
		driver.findElement(guestDetailsLocator).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(guestDetailsPopupLocator)));
		roomCount = driver.findElement(roomCountLocator);
		roomDec = driver.findElement(roomDecLocator);
		doneButton = driver.findElement(doneButtonLocator);
		for(int i=Integer.valueOf(roomCount.getText());i>=1;i--)
		{
			roomDec.click();
		}
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "Minimum 1 is required");
		alert.dismiss();
		doneButton.click();
	}
	
	public void enterAdults(int adults)
	{
		driver.findElement(guestDetailsLocator).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(guestDetailsPopupLocator)));
		adultCount = driver.findElement(adultCountLocator);
		adultAdd = driver.findElement(adultAddLocator);
		adultDec = driver.findElement(adultDecLocator);
		doneButton = driver.findElement(doneButtonLocator);
		
		int currentAdults = Integer.valueOf(adultCount.getText());
		
		if(currentAdults<adults)
		{
			for(int i=0;i<adults-currentAdults;i++)
			{
				adultAdd.click();
			}
		}
		else if(currentAdults>adults)
		{
			for(int i=0;i<currentAdults-adults;i++)
			{
				adultDec.click();
			}
		}
		
		doneButton.click();
	}
	
	public void add0Adult()
	{
		driver.findElement(guestDetailsLocator).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(guestDetailsPopupLocator)));
		adultCount = driver.findElement(adultCountLocator);
		adultDec = driver.findElement(adultDecLocator);
		doneButton = driver.findElement(doneButtonLocator);
		for(int i=Integer.valueOf(adultCount.getText());i>=1;i--)
		{
			adultDec.click();
		}
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "Minimum 1 adult is required");
		alert.dismiss();
		doneButton.click();
	}
	
	public void enterChildren(int children, List<Integer> childrenAge)
	{
		driver.findElement(guestDetailsLocator).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(guestDetailsPopupLocator)));
		childrenCount = driver.findElement(childrenCountLocator);
		childrenAdd = driver.findElement(childrenAddLocator);
		childrenDec = driver.findElement(childrenDecLocator);
		doneButton = driver.findElement(doneButtonLocator);
		
		int currentChildren = Integer.valueOf(childrenCount.getText());
		
		if(currentChildren<children)
		{
			for(int i=0;i<children-currentChildren;i++)
			{
				childrenAdd.click();
			}
		}
		else if(currentChildren>children)
		{
			for(int i=0;i<currentChildren-children;i++)
			{
				childrenDec.click();
			}
		}
		
		List<WebElement> childrenAgeSelect = driver.findElements(childrenAgeSelectBoxLocator);
		for(int i=0;i<Integer.valueOf(children);i++)
		{
			childrenAgeSelect.get(i).click();
			driver.findElement(By.xpath(String.format(childrenAgeSelectBoxMenuLocator, childrenAge.get(i)))).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(doneButton));
		doneButton.click();
	}
	
	public void clickSearchButton()
	{
		searchButton = driver.findElement(searchButtonLocator);
		action.moveToElement(searchButton).click().build().perform();
	}

}
