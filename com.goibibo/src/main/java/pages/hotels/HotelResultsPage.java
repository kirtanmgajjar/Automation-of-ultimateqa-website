package pages.hotels;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class HotelResultsPage extends BasePage{
	
	By locationNameLocator = By.id("downshift-2-input");
	By checkInDateLocator = By.id("search-widget-checkin-input");
	By checkOutDateLocator = By.xpath("//*[@id='search-widget-calendar-element']/following-sibling::div[1]//input");
	By guestDetailsLocator = By.xpath("//*[@id='search-widget-calendar-element']/following-sibling::div[2]//input");
	By sortByOptionsLocator = By.xpath("//*[@class='SortingNewUIstyles__FilterName-sc-15ya9lx-1 gvdYst']");
	By filtersAvailable = By.xpath("//*[@class='CheckBoxListstyles__CheckBoxListText-sc-mib0do-6 eOQgmw']");
	By hotelsLocator = By.xpath("//*[contains(@id,'htl_id_seo')]"); 
	By clearFilterButtonLocator = By.xpath("//*[contains(@class,'Filtersstyles__Clear')]");
	By hotelRatingsLocator = By.xpath("//*[contains(@id,'htl_id_seo')]//*[@class='ReviewAndRatingsstyles__AverageReviewText-sc-1nxmeoo-8 jzSqUD']");
	By tempElementsSearchBackgroundLocator1 = By.xpath("//*[contains(@class,'SearchResultsLoaderstyles__OverlayBackround')]");
	By tempElementsSearchBackgroundLocator2 = By.xpath("//*[contains(text(),'LOADING THE BEST PRICES FOR YOU')]");
	By tempElementsSearchBackgroundLocator3 = By.xpath("//*[contains(@class,'SearchResultsLoaderstyles__SearchResultsLoaderContainer')]");
	By hotelPricesLocator = By.xpath("//*[contains(@id,'htl_id_seo')]//*[@class='HotelCardstyles__CurrentPrice-sc-1s80tyk-32 koAFXT']");
	By hotelParentDivLocator = By.xpath("//*[@class='infinite-scroll-component ']");
	By hotelDivLocator = By.xpath("//*[contains(@id,'htl_id_seo')]");
	
	String filtersLocator = "//*[contains(text(),'%s') and @class='CheckBoxListstyles__StyledDiv-sc-mib0do-4 iwpUbf']/ancestor::*[@class='dwebCommonstyles__CenteredSpaceWrap-sc-112ty3f-0 eHRHNU']/following-sibling::*";
	String hotelIndividualElementLocator = "//*[contains(@id,'htl_id_seo')][%d]";
	String hotelIndividualNamesLocator = "//*[contains(@id,'htl_id_seo')][%d]//*[@class='dwebCommonstyles__SmallSectionHeader-sc-112ty3f-10 kJLbNT']";
	String hotelIndividualPriceLocator = "//*[contains(@id,'htl_id_seo')][%d]//*[@class='ReviewAndRatingsstyles__AverageReviewText-sc-1nxmeoo-8 jzSqUD']";
	String hotelIndividualRatingLocator = "//*[contains(@id,'htl_id_seo')][%d]//*[@class='HotelCardstyles__CurrentPrice-sc-1s80tyk-32 koAFXT']";
	
	WebElement whereInputbox;
	WebElement checkInDateInputbox;
	WebElement checkOutDateInputbox;
	WebElement guestDetailsMenu;
	List<WebElement> sortByOptions;
	WebElement priceRangeFilter;
	WebElement clearFilterButton;
	
	Map<WebElement,Map<String,String>> hotelsData = new LinkedHashMap<>();
	Map<String,WebElement> hotelsWebElements = new LinkedHashMap<>();
	Map<String,Map<String,String>> hotelsDetails = new LinkedHashMap<>();
	

	public HotelResultsPage(WebDriver driver) 
	{
		super(driver);
	}

	@Override
	public void verifyPage() 
	{
		assertEquals(driver.getTitle(), "Results");
	}
	
	
	public void verifyLocation(String location)
	{
		whereInputbox = driver.findElement(locationNameLocator);
		assertEquals(whereInputbox.getAttribute("value"),location);
	}
	
	public void verifyCheckInDate(String checkInDate) throws Exception
	{
		SimpleDateFormat inFormat = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
		checkInDateInputbox = driver.findElement(checkInDateLocator);
		String checkInDateData = outFormat.format(inFormat.parse(checkInDateInputbox.getAttribute("value")));
		assertEquals(checkInDateData,checkInDate);
	}
	
	public void verifyCheckOutDate(String checkOutDate) throws Exception
	{
		SimpleDateFormat inFormat = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
		checkOutDateInputbox = driver.findElement(checkOutDateLocator);
		String checkOutDateData = outFormat.format(inFormat.parse(checkOutDateInputbox.getAttribute("value")));
		assertEquals(checkOutDateData,checkOutDate);
	}
	
	public void verifyGuestDetails(int rooms, int adults, int children)
	{
		guestDetailsMenu = driver.findElement(guestDetailsLocator);	
		List<Integer> guestDetails = Arrays.asList(guestDetailsMenu.getAttribute("value").replaceAll("\\s", "").split("\\.")).stream().map(k->Integer.valueOf(k.substring(0,1))).toList();
		assertEquals(guestDetails.get(2), rooms);
		assertEquals(guestDetails.get(0), adults);
		assertEquals(guestDetails.get(1), children);
	}
	
	public void sortBy(String choice)
	{
		sortByOptions = driver.findElements(sortByOptionsLocator);
		
		switch(choice)
		{
			case "Price(High to Low)":
				sortByOptions.get(2).click();
				break;
			case "Price(Low to High)":
				sortByOptions.get(1).click();
				break;
			case "Customer Ratings":
				sortByOptions.get(3).click();
				break;
			default:
				sortByOptions.get(0).click();
				break;
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelDivLocator));
	}
	
	public void verifySortBy(String sortedBy)
	{
		if(sortedBy.equals("Price(High to Low)"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(hotelPricesLocator));
			List<Integer> hotelPrices = driver.findElements(hotelPricesLocator).stream().map(k->Integer.valueOf(k.getText())).toList();
			List<Integer> sortedHotelPrices = new ArrayList<>(hotelPrices);
			Collections.sort(sortedHotelPrices, Collections.reverseOrder());
			assertEquals(hotelPrices, sortedHotelPrices);
		}
		else if(sortedBy.equals("Price(Low to High)"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(hotelPricesLocator));
			List<Integer> hotelPrices = driver.findElements(hotelPricesLocator).stream().map(k->Integer.valueOf(k.getText())).toList();
			List<Integer> sortedHotelPrices = new ArrayList<>(hotelPrices);
			Collections.sort(sortedHotelPrices);
			assertEquals(hotelPrices, sortedHotelPrices);
		}
		else if(sortedBy.equals("Customer Ratings"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(hotelRatingsLocator));
			List<Float> hotelRatings = driver.findElements(hotelRatingsLocator).stream().map(k->Float.valueOf(k.getText().substring(0,k.getText().length()-2))).toList();
			List<Float> sortedHotelRatings = new ArrayList<>(hotelRatings);
			Collections.sort(sortedHotelRatings, Collections.reverseOrder());
			assertEquals(hotelRatings, sortedHotelRatings);
		}
	}
	
	public void applyCustomerRatingFilter(String filter)
	{
		List<WebElement> ratingFilters = driver.findElements(By.xpath(String.format(filtersLocator, "Customer Ratings")));
		
		for(WebElement ratingFilter: ratingFilters)
		{
			if(ratingFilter.getText().contains(filter))
			{
				ratingFilter.click();
				break;
			}
		}
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(tempElementsSearchBackgroundLocator1)));
	}
	
	public void verifyCustomerRatingFilter(String filter)
	{
		float filterRating = Float.valueOf(filter.substring(0,filter.indexOf("+")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(hotelRatingsLocator));
		List<WebElement> ratings = driver.findElements(hotelRatingsLocator);
		
		for(WebElement i: ratings)
		{
			float rating = Float.valueOf(i.getText().substring(0,i.getText().length()-2));
			if(rating<filterRating)
			{
				fail("Customer Rating filter does not work as per requirement");
			}
			
		}
	}
	
	public void applyPriceRangeFilter(List<String> filters)
	{
		List<WebElement> priceFilters = driver.findElements(By.xpath(String.format(filtersLocator, "Price Range")));
		
		for(WebElement priceFilter: priceFilters)
		{
			if(filters.contains(priceFilter.getText()))
			{
				priceFilter.click();
			}
		}
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(tempElementsSearchBackgroundLocator3)));	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelDivLocator));
	}
	
	public void verifyPriceFilter(List<String> filters)
	{
		
		int lowestPrice = 0;
		int highestPrice = 0;
		if(filters.contains("\u20B96000 +"))
		{
			lowestPrice = 6001;
			highestPrice = Integer.MAX_VALUE;
		}
		else if(filters.contains("\u20B94001 - \u20B96000"))
		{
			lowestPrice = 4001;
			highestPrice = 6000;
		}
		else if(filters.contains("\u20B92001 - \u20B94000"))
		{
			lowestPrice = 2001;
			highestPrice = 4000;
		}
		else if(filters.contains("Upto \u20B92000"))
		{
			lowestPrice = 0;
			highestPrice = 2000;
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelPricesLocator));
		List<WebElement> hotelPrices = driver.findElements(hotelPricesLocator);
		
		for(WebElement i: hotelPrices)
		{
			int price = Integer.valueOf(i.getText());
			if(price<lowestPrice || price>highestPrice) 
			{
				fail("Price filter does not work as per requirement");
			}
		}
		
	}
	
	public void clearFilters()
	{
		clearFilterButton = driver.findElement(clearFilterButtonLocator);
		clearFilterButton.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(hotelParentDivLocator)));
	}
	
	public Map<String,Map<String,String>> getHotelsDetails()
	{
		List<WebElement> hotels = driver.findElements(hotelDivLocator);
		hotelsDetails.clear();
		hotelsWebElements.clear();
		
		
		
		for(int i=1;i<=hotels.size();i++)
		{
			Map<String,String> hotelData = new HashMap<>();
			WebElement hotel = driver.findElement(By.xpath(String.format(hotelIndividualElementLocator, i)));
			WebElement hotelName = driver.findElement(By.xpath(String.format(hotelIndividualNamesLocator,i)));
			WebElement hotelRating = driver.findElement(By.xpath(String.format(hotelIndividualPriceLocator,i)));
			WebElement hotelPrice = driver.findElement(By.xpath(String.format(hotelIndividualRatingLocator,i)));
			
			hotelData.put("Price", hotelPrice.getText());
			hotelData.put("Rating", hotelRating.getText());
			hotelsDetails.put(hotelName.getText(), hotelData);
			hotelsWebElements.put(hotelName.getText(), hotel);
		}
		
		return hotelsDetails;
	}
	
	public Map<String,Map<String,String>> getHotelsDetails(int number)
	{
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelDivLocator));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		hotelsDetails.clear();
		hotelsWebElements.clear();
		int count=0;
		
		while(true&&count!=number)
		{
			try
			{
				Map<String,String> hotelData = new HashMap<>();
				count++;
				action.scrollByAmount(0, 350).perform();
				WebElement hotel = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(hotelIndividualElementLocator, count)))));
				WebElement hotelName = driver.findElement(By.xpath(String.format(hotelIndividualNamesLocator,count)));
				WebElement hotelRating = driver.findElement(By.xpath(String.format(hotelIndividualPriceLocator,count)));
				WebElement hotelPrice = driver.findElement(By.xpath(String.format(hotelIndividualRatingLocator,count)));
				
				hotelData.put("Price", hotelPrice.getText());
				hotelData.put("Rating", hotelRating.getText());
				hotelsDetails.put(hotelName.getText(), hotelData);
				hotelsWebElements.put(hotelName.getText(), hotel);
			}
			catch(NoSuchElementException e)
			{
				break;
			}
			
		}
		
		jse.executeScript("window.scrollTo(0,0)");
		
		return hotelsDetails;
	}
	
	
	public void bookHotel(String name)
	{
		hotelsWebElements.get(name).click();
		iter = driver.getWindowHandles().iterator();
		iter.next();
		driver.switchTo().window(iter.next());
	}
	
	public String bookHotel(int number)
	{
		String hotelBooked = null;
		int count=0;
		for(Entry<String, WebElement> entry :hotelsWebElements.entrySet())
		{
			count++;
			if(count==number)
			{
				entry.getValue().click();
				hotelBooked = entry.getKey();
				break;
			}
		}
		iter = driver.getWindowHandles().iterator();
		iter.next();
		driver.switchTo().window(iter.next());
		return hotelBooked;
	}
	

}
