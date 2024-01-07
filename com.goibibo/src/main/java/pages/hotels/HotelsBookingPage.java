package pages.hotels;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;

public class HotelsBookingPage extends BasePage
{
	By roomOptionsLocator = By.xpath("//*[@data-testid='detail-roomSelection-room-flavor']");
	By roomTypeLocator = By.xpath("//*[@class='Roomstyles__RoomTypeTextStyled-sc-1vvh1xt-3 vHxaN']");
	By titleSelectboxLocator = By.xpath("//*[@class='PersonalProfilestyles__NameEnterSelect-sc-1t6fe6a-6 WIiFu']");
	By firstNameTextboxLocator = By.xpath("//*[@data-guestdetailsinnerwrapid='first-name']");
	By lastNameTextboxLocator = By.xpath("//*[@data-guestdetailsinnerwrapid='last-name']");
	By emailTextboxLocator = By.xpath("//*[@data-guestdetailsinnerwrapid='guest-details-email']");
	By countryCodeSelectboxLocator = By.xpath("//*[@class='PersonalProfilestyles__CountryCodeWrap-sc-1t6fe6a-10 FwKgr']");
	By phoneTextboxLocator = By.xpath("//*[@data-guestdetailsinnerwrapid='guest-details-phone']");
	By addressTextboxLocator = By.id("Billing Address");
	By pincodeTextboxLocator = By.id("Pincode");
	By stateMenuLocator = By.xpath("//*[contains(@class,'sc-bTDODP')]");
	By roomPaymentOptionsLocator = By.xpath("//*[@for='bookNow']");
	By paymentButtonLocator = By.xpath("//*[@class='dwebCommonstyles__ButtonBase-sc-112ty3f-13 PaymentCustomButtonUIstyle__CustomButton-sc-12ekasb-0 kwEZmw kPPtwa']");
	By cardNumberTextboxLOcator = By.name("cardNumber");
	By expiryMonthTextboxLocator = By.name("expiryMonth");
	By expiryYearTextboxLocator = By.name("expiryYear");
	By cardCvvTextboxLocator = By.name("cardCvv");
	By nameOnCardTextboxLocator = By.name("nameOnCard");
	By billingCountryTextboxLocator = By.name("billingCountry");
	By billingStateTextboxLocator = By.name("billingState");
	By billingCityTextboxLocator = By.name("billingCity");
	By billingPincodeTextboxLocator = By.name("billingPinCode");
	By biilingAddressTextboxLocator = By.name("billingAddress");
	By priceSummaryTypeLocator = By.xpath("//*[contains(@class,'PricingBlockUIstyles__ChargesLeftBlockDiv-sc-1ex6u7d')]");
	By priceSummaryChargeLocator = By.xpath("//*[contains(@class,'PricingBlockUIstyles__ChargesRightBlockDiv-sc-1ex6u7d')]");
	By errorMessageLocator = By.xpath("//*[@class='sprite errorToastIcon']/following-sibling::*");
	By roomBookingButtonsLocator = By.xpath("//*[@data-testid='selectRoomBtn']");
	By hotelInfoNameLocator = By.xpath("//*[contains(@class,'ReviewHotelInfostyles__ReviewHotelNameDiv')]/h4");
	By hotelInfoSearchCriteriaLocator = By.xpath("//*[contains(@class,'DurationBlockstyles__CheckInWrapPara')]");
	By hotelInfoRoomDetailsLocator = By.xpath("//*[contains(@class,'RoomDetailsstyles__MultiRoomHeaderPara')]");
	By hotelInfoRoomOptionLocator = By.xpath("//*[contains(@class,'RoomDetailsstyles__PropertyTypePara')]");
	By couponAppliedLocator = By.xpath("//*[contains(@class,'PricingBlockUIstyles__CouponApplied')]");
	By offerCodeNameLocator = By.xpath("//*[@class='offerCode']");
	By offerCodeDiscountLocator = By.xpath("//*[contains(@class,'ReviewOfferCardUIstyles__OfferCodePrice')]");
	By offerCodeSelectButtonLocator = By.xpath("//*[contains(@class,'ReviewOfferCardUIstyles__CheckWrapper')]");
	By selectedOfferCodeNameLocator = By.xpath("//*[@class='ReviewOfferCardUIstyles__Offer-sc-rr6e0s-3 clIqeY']//*[@class='offerCode']");
	By appliedOfferCodeLocator = By.xpath("//*[contains(@class,'PricingBlockUIstyles__CouponCode')]");
	By paymentPageGoibiboLogoLocator = By.xpath("//*[@class='logoWrap sprite']");
	By paymentPageHotelNameLocator = By.xpath("//*[contains(@class,'bookingSumry__lob')]");
	By paymentPageDatesInfoLocator = By.xpath("//*[contains(@class,'makeFlex hrtlCenter appendRight')]");
	By paymentPageGuestNameLocator = By.xpath("//*[@class='bookingSumry__customerDetail']/p[1]");
	By paymentPageGuestContactLocator = By.xpath("//*[@class='bookingSumry__customerDetail']/p[2]");
	By paymentPageAmountLocator = By.xpath("(//*[contains(@class,'rupees')])[1]");
	By creditCardMenuLocator = By.xpath("//*[contains(@class,'cardSectionBody padding')]");
	By payWithoutSavingCardButton = By.xpath("//*[@class='formBtnStroked flexOne appendLeft16  '][text()='Pay without saving card']");
	
	
	String roomOptionsNameLocator = "//*[@class='Roomstyles__RoomTypeTextStyled-sc-1vvh1xt-3 vHxaN' and text()='%s']/../../../descendant::*[@id='room-options-name']";
	String roomBookingButtonLocator = "//*[@class='Roomstyles__RoomTypeTextStyled-sc-1vvh1xt-3 vHxaN' and text()='%s']/../../../descendant::*[@data-testid='selectRoomBtn']";
	String roomBasePriceLocator = "//*[@class='Roomstyles__RoomTypeTextStyled-sc-1vvh1xt-3 vHxaN' and text()='%s']/../../../descendant::*[contains(@class,'RoomFlavorstyles__StrikeThroughPersuasionPriceTextStyled')]";
	String roomPriceLocator = "//*[@class='Roomstyles__RoomTypeTextStyled-sc-1vvh1xt-3 vHxaN' and text()='%s']/../../../descendant::*[contains(@class,'RoomFlavorstyles__ActualPrice')]";
	String stateSelectLocator = "//*[@class='sc-daBvwG gTaSYg' and text()='%s']";
	String paymentOptionsLocator = "//*[@class='makeFlex column']//*[text()='%s']";
	String optionsSelectboxLOcator = "//*[@class='font16' and text()='%s']";
	
	Map<String,WebElement> roomBoookingWebElement = new LinkedHashMap<>();
	Map<String,Map<String,Integer>> roomPriceInfo = new LinkedHashMap<>();
	Map<String,Integer> priceSummary = new LinkedHashMap<>();
	
	Map<String,WebElement> offerCodeButton = new LinkedHashMap<>();
	Map<String,Integer> offerCodeDiscount = new LinkedHashMap<>();

	public HotelsBookingPage(WebDriver driver) 
	{
		super(driver);
	}

	@Override
	public void verifyPage() 
	{
		assertEquals(driver.getTitle(), "Book Cheap Flights, Air Tickets, Hotels, Bus & Holiday Package at Goibibo");
	}
	
	public Map<String,Map<String,Integer>> viewRoomOptionsAvailable()
	{		
		List<WebElement> roomOptions = driver.findElements(roomTypeLocator);
		
		for(int i=0; i<roomOptions.size();i++)
		{
			String roomType = roomOptions.get(i).getText();
			List<WebElement> roomOptionName = driver.findElements(By.xpath(String.format(roomOptionsNameLocator, roomType)));
			List<WebElement> roomOptionPrice = driver.findElements(By.xpath(String.format(roomPriceLocator, roomType)));
			List<WebElement> roomOptionBookingButton = driver.findElements(By.xpath(String.format(roomBookingButtonLocator, roomType)));
			List<WebElement> roomOptionBasePrice = driver.findElements(By.xpath(String.format(roomBasePriceLocator, roomType)));
			
			for(int j=0; j<roomOptionName.size();j++)
			{
				Map<String,Integer> roomOptionsPrices = new LinkedHashMap<>();
				String roomOption = roomType + "," +roomOptionName.get(j).getText().substring(3);
				
				roomOptionsPrices.put("Base Price", Integer.valueOf(roomOptionBasePrice.get(j).getText().substring(1).strip()));
				roomOptionsPrices.put("Price", Integer.valueOf(roomOptionPrice.get(j).getText()));
				
				roomBoookingWebElement.put(roomOption, roomOptionBookingButton.get(j));
				roomPriceInfo.put(roomOption, roomOptionsPrices);
			}
		}
		return roomPriceInfo;
	}
	
	public void bookRoom(String room)
	{
		roomBoookingWebElement.get(room).click();
	}
	
	public String bookRoom(int optionNumber)
	{
		int count=0;
		for(Entry<String,WebElement> entry: roomBoookingWebElement.entrySet())
		{
			count++;
			if(count==optionNumber)
			{
				entry.getValue().click();
				return entry.getKey();
			}	
		}
		return null;
	}
		
	public void verifyHotelNameInHotelInfo(String expectedHotelName)
	{
		String hotelName = driver.findElement(hotelInfoNameLocator).getText();
		assertEquals(hotelName, expectedHotelName);
	}
	
	public void verifyHotelInfoSearchCriteria(String expectedCheckInDate, String expectedCheckOutDate, int expectedRooms, int expectedAdults, int expectedChildren) throws Exception
	{
		SimpleDateFormat inFormat = new SimpleDateFormat("EEE, dd MMM, yyyy");
		SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<WebElement> info = driver.findElements(hotelInfoSearchCriteriaLocator);
		
		String checkInDate = outFormat.format(inFormat.parse(info.get(0).getText()));
		String checkOutDate = outFormat.format(inFormat.parse(info.get(1).getText()));
		
		String guestDetail = info.get(2).getText();
		String roomDetail = driver.findElement(hotelInfoRoomDetailsLocator).getText();
		
		assertEquals(checkInDate,expectedCheckInDate);
		assertEquals(checkOutDate,expectedCheckOutDate);
		String a = expectedChildren>1 ? "Children" : "Child";
		String b = expectedAdults+expectedChildren>1 ? "Guests" : "Guest";
		String c = expectedRooms>1 ? "Rooms" : "Room";
		String d = expectedAdults>1 ? "Adults" : "Adult"; 
		assertEquals(guestDetail,String.format("%d %s | %d %s",expectedAdults+expectedChildren,b,expectedRooms,c));
		assertEquals(roomDetail,String.format("%d %s for %d %s, %d %s",expectedRooms,c,expectedAdults,d,expectedChildren,a));
		
	}
	
	public void verifyRoomOptionSelected(int expectedRooms, String expectedRoomOption)
	{
		String roomOptionBooked = driver.findElement(hotelInfoRoomOptionLocator).getText();
		assertEquals(roomOptionBooked, String.format("%d x %s", expectedRooms, expectedRoomOption));
	}
	
	public void verifyAppliedOfferCode(String expectedOfferCode)
	{
		WebElement selectedOfferCode = driver.findElement(selectedOfferCodeNameLocator);
		WebElement appliedOfferCode = driver.findElement(appliedOfferCodeLocator);
		
		assertEquals(selectedOfferCode.getText(),expectedOfferCode);
		assertEquals(appliedOfferCode.getText(), expectedOfferCode);
		
	}
	
	public void getOfferCodeDetails()
	{
		List<WebElement> offerCodeNames = driver.findElements(offerCodeNameLocator);
		List<WebElement> offerCodeDiscounts = driver.findElements(offerCodeDiscountLocator);
		List<WebElement> offerCodeButtons = driver.findElements(offerCodeSelectButtonLocator);
		
		for(int i=0; i<offerCodeNames.size();i++)
		{
			offerCodeButton.put(offerCodeNames.get(i).getText(),offerCodeButtons.get(i));
			offerCodeDiscount.put(offerCodeNames.get(i).getText(), Integer.valueOf(offerCodeDiscounts.get(i).getText().substring(2)));
		}
	}
	
	public void selectOfferCode(String offerCode)
	{
		offerCodeButton.get(offerCode).click();
	}
	
	public String selectOfferCode(int number)
	{
		int count=0;
		for(Entry<String,WebElement> entry: offerCodeButton.entrySet())
		{
			count++;
			if(count==number) 
			{
				jse.executeScript("arguments[0].click()", entry.getValue());
				return entry.getKey();
			}
		}
		return null;
	}
	
	
	
	public Map<String,Integer> getPriceSummary()
	{
		List<WebElement> priceType = driver.findElements(priceSummaryTypeLocator);
		List<WebElement> priceCharge = driver.findElements(priceSummaryChargeLocator);
		
		for(int i=0;i<priceType.size();i++)
		{
			if(priceType.get(i).getText().contains("Total Discount"))
			{
				priceSummary.put("Total Discount", Integer.valueOf(priceCharge.get(i).getText().substring(2)));
				continue;
			}
				
			priceSummary.put(priceType.get(i).getText(), Integer.valueOf(priceCharge.get(i).getText().substring(1)));
		}
		
		return priceSummary;
	}
	
	
	public void verifyPriceSummary(int rooms, int nights, String roomOption, String appliedOfferCode)
	{	
		String room = rooms>1 ? "rooms" : "room";
		String night = nights>1 ? "nights" : "night";
		int basePrice = priceSummary.get(String.format("Base Price (%d %s x %d %s)", rooms,room, nights,night));
		int discountedPrice = priceSummary.get("Price after Discount");
		int charges = priceSummary.get("Taxes & Service Fees");
		int totalPrice = priceSummary.get("Total Amount to be paid");
		
		int expectedBasePrice = roomPriceInfo.get(roomOption).get("Base Price")*rooms*nights;
		int expectedTotalPrice = discountedPrice+charges;
		
		assertEquals(basePrice,expectedBasePrice);
		assertEquals(totalPrice,expectedTotalPrice);
	}
	
	public void enterTitle(String choice)
	{
		Select title = new Select(driver.findElement(titleSelectboxLocator));
		title.selectByVisibleText(choice);
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
	
	public void enterAddress(String address)
	{
		driver.findElement(addressTextboxLocator).sendKeys(address);
		driver.findElement(By.xpath("//*[@for='confirm_check']")).click();
	}
	
	public void enterPincode(String pincode)
	{
		driver.findElement(pincodeTextboxLocator).sendKeys(pincode);
	}
	
	public void enterState(String state)
	{
		driver.findElement(stateMenuLocator).click();
		driver.findElement(By.xpath(String.format(stateSelectLocator, state)));
	}
	
	public void clickProceedToPayment()
	{
		driver.findElement(paymentButtonLocator).click();
	}
	
	public void verifyPaymentWindow()
	{
		assertTrue(driver.findElement(paymentPageGoibiboLogoLocator).isDisplayed());
	}
	
	public void verifyDetailsOnPaymentsPage(String expectedHotelName, String expectedCheckInDate, String expectedCheckOutDate, String expectedFirstName, String expectedLastName, String expectedEmail, String expectedCountryCode, String expectedMobileNumber, int expectedTotalPrice) throws ParseException
	{
		WebElement paymentPageAmount = driver.findElement(paymentPageAmountLocator);
		
		WebElement paymentPageHotelName = driver.findElement(paymentPageHotelNameLocator);
		WebElement paymentPageDatesInfo = driver.findElement(paymentPageDatesInfoLocator);
		WebElement paymentPageGuestName = driver.findElement(paymentPageGuestNameLocator);
		WebElement paymentPageGuestContact = driver.findElement(paymentPageGuestContactLocator);
		
		
		SimpleDateFormat outFormat = new SimpleDateFormat("dd MMM yyyy (EEE)");
		SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String checkInDate = outFormat.format(inFormat.parse(expectedCheckInDate));
		String checkOutDate = outFormat.format(inFormat.parse(expectedCheckOutDate));
		String expectedDate = String.format("%s %s", checkInDate, checkOutDate);
		String expectedGuestContactInfo = String.format("%s, +%s%s", expectedEmail,expectedCountryCode,expectedMobileNumber);
		
		
		assertEquals(Integer.valueOf(paymentPageAmount.getText()), expectedTotalPrice);
		assertEquals(paymentPageHotelName.getText(), expectedHotelName);
		assertEquals(paymentPageDatesInfo.getText().replace("\n"," "), expectedDate);
		assertEquals(paymentPageGuestName.getText(), expectedFirstName+" "+expectedLastName);
		assertEquals(paymentPageGuestContact.getText(), expectedGuestContactInfo);
	}
	
	public void selectPaymentOption(String choice)
	{
		WebElement paymentOption = driver.findElement(By.xpath(String.format(paymentOptionsLocator, choice)));
		paymentOption.click();
	}
	
	public void verifyCardMenu()
	{
		assertTrue(driver.findElement(creditCardMenuLocator).isDisplayed());
	}
	
	
	public void enterCardNumber(String number)
	{
		driver.findElement(cardNumberTextboxLOcator).sendKeys(number);
	}
	
	public void enterCardExpiryMonth(String month)
	{
		driver.findElement(expiryMonthTextboxLocator).click();
		driver.findElement(By.xpath(String.format(optionsSelectboxLOcator, month))).click();
	}
	
	public void enterCardExpiryYear(String year)
	{
		driver.findElement(expiryYearTextboxLocator).click();
		driver.findElement(By.xpath(String.format(optionsSelectboxLOcator, year))).click();
	}
	
	public void enterCvv(String cvv)
	{
		driver.findElement(cardCvvTextboxLocator).sendKeys(cvv);
	}
	
	public void enterCardHolderName(String name)
	{
		driver.findElement(nameOnCardTextboxLocator).sendKeys(name);
	}
	
	public void enterBillingCountry(String country)
	{
		driver.findElement(billingCountryTextboxLocator).click();
		driver.findElement(By.xpath(String.format(optionsSelectboxLOcator, country))).click();
	}
	
	public void enterBillingState(String state)
	{
		driver.findElement(billingStateTextboxLocator).sendKeys(state);
	}
	
	public void enterBillingCity(String city)
	{
		driver.findElement(billingCityTextboxLocator).sendKeys(city);
	}
	
	public void enterBillingPincode(String pincode)
	{
		driver.findElement(billingPincodeTextboxLocator).sendKeys(pincode);
	}
	
	public void enterBillingAddress(String address)
	{
		driver.findElement(biilingAddressTextboxLocator).sendKeys(address);
	}
	
	public void clickPayWithoutSavingCard()
	{
		driver.findElement(payWithoutSavingCardButton).click();
	}
	
	public void getErrorMessage()
	{
		System.out.println("Payment Error Message: " + driver.findElement(errorMessageLocator).getText());
	}
}
 