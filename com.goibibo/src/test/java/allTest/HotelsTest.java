package allTest;

import static utilities.ListenerUtility.setPassMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.CustomAttribute;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class HotelsTest extends BaseClass
{
	String hotelLocation = "Jamnagar, Gujarat, India";
	String checkInDate = "18-01-2024";
	String checkOutDate = "21-01-2024";
	int rooms1 = 3;
	int rooms2 = 1;
	int adults1 = 3;
	int adults2 = 2;
	int children1 = 3;
	int children2 = 1;
	List<Integer> childrenAges1 = Arrays.asList(3,4,5);
	List<Integer> childrenAges2 = Arrays.asList(3);
	List<String> priceFilters = Arrays.asList("\u20B92001 - \u20B94000");
	String ratingFilter = "4+";
	int hotelBook = 1;
	int roomOptionBook = 2;
	int offerCodeNumber = 3;
	int nights = 3;
	String hotelName;
	String roomOptionName;
	String appliedOfferCode;
	String title = "Mr";
	String firstName = "Abc";
	String lastName = "Efg";
	String email = "abc@gmail.com";
	String countryCode = "91";
	String number = "9876543210";
	String address = "Abc, Efg, Hij";
	String pincode = "654321";
	String state = "Maharashtra";
	Map<String, Integer> priceSummary;
	String paymentOption = "Credit/Debit/ATM Card";
	String cardNumber = "4532016646430897";
	String cardExpiryMonth = "07";
	String cardExpiryYear = "2024";
	String cardCvv = "488";
	String cardHolderName = "Carmine Hammes";
	String billingCountry = "India";
	String billingState = "Maharashtra";
	String billingCity = "Mumbai";
	String billingPincode = "654321";
	String billingAddress = "abc,Efg,Hij";
	
	@Test(testName = "Navigation to Hotel page", description = "To validate if 'Hotel' menu button is displayed and clickable",
			attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to Hotels web page"})})
	public void navigateToHotelsPage()
	{
		hp.closeSigninPopup();
		hp.navigateToHotels();
		ho.verifyPage();
		setPassMessage("User is redirected to Hotels web page");
	}
	
	@Test(testName = "Validation of radio buttons", description = "To validate the working of radio buttons",
			dependsOnMethods = "navigateToHotelsPage",
			attributes = {@CustomAttribute(name="passMessage",values={"India radio button is selected"})})
	public void validationOfRadioButtons()
	{
		ho.selectCountryType("International");
		ho.selectCountryType("India");
		setPassMessage("India radio button is selected");
	}
	
	@Test(testName = "Validation of 'Where' menu", description = "To validate the functionality of 'Where' search box to be able to take input and is able to select the required location",
			dependsOnMethods = "validationOfRadioButtons",
attributes = {@CustomAttribute(name="passMessage",values={"A dropdown is displayed showing locations in Goa and the chosen location is displayed in the 'Where' field"})})
	public void validationOfWhereSearchfield()
	{
		ho.enterLocationOfHotel(hotelLocation);
		setPassMessage("A dropdown is displayed showsing locations in Goa and the chosen location is displayed in the 'Where' field");
	}
	
	@Test(testName = "Validation of the date fields", description = "To validate the functionality of date fields to be able select required dates",
			dependsOnMethods = "validationOfWhereSearchfield",
attributes = {@CustomAttribute(name="passMessage",values={"The calender box appears and the selected 'Check In' and 'Check Out' dates is displayed in the respective fields"})})
	public void validationOfDateFields() throws Exception
	{
		ho.enterDate(checkInDate, checkOutDate);
		setPassMessage("The calender box appears and the selected 'Check In' and 'Check Out' dates is displayed in the respective fields");
	}
	
	@Test(testName="Validation of 'Rooms' field", description = "To verify if user is able to select the required number of rooms", 
			dependsOnMethods = "validationOfDateFields",
attributes = {@CustomAttribute(name="passMessage",values={"The dropdown menu appears and the selected number of rooms is displayed in the field"})})
	public void validationOfRoomsField()
	{
		ho.enterRooms(rooms1);
		setPassMessage("The dropdown menu appears and the selected number of rooms is displayed in the field");
	}
	
	@Test(testName="Validation of 'Adults' field", description = "To verify if user is able to select the required number of adults",
			dependsOnMethods = "validationOfDateFields",
attributes = {@CustomAttribute(name="passMessage",values={"The dropdown menu appears and the selected number of adults is displayed in the field"})})
	public void validationOfAdultsField()
	{
		ho.enterAdults(adults1);
		setPassMessage("The dropdown menu appears and the selected number of adults is displayed in the field");
	}
	
	@Test(testName="Validation of 'Children' fields", description = "To verify if user is able to select the required number of children and should be able to select the age for each of the children added",
			dependsOnMethods = "validationOfAdultsField",
attributes = {@CustomAttribute(name="passMessage",values={"The dropdown menu appears and the selected number of children is added and the age is able to gets selected for each of them"})})
	public void validationOfChildrenField()
	{
		ho.enterChildren(children1, childrenAges1);
		setPassMessage("The dropdown menu appears and the selected number of children is added and the age is able to gets selected for each of them");
	}
	
	@Test(testName="Verify functionality to add 0 rooms", description = "To verify if user is able to enter 0 room in the Rooms field",
			dependsOnMethods = "validationOfChildrenField",
attributes = {@CustomAttribute(name="passMessage",values={"The alert is displayed and 'Minimum 1 is required' message is displayed"})})
	public void verify0RoomAdd()
	{
		ho.add0Room();
		setPassMessage("The alert is displayed and 'Minimum 1 is required' message is displayed");
	}
	
	@Test(testName="Verify functionality to add 0 adults", description = "To verify if user is able to enter 0 adult in the Adults field",
			dependsOnMethods = "verify0RoomAdd",
attributes = {@CustomAttribute(name="passMessage",values={"The alert is displayed and 'Minimum 1 adult is required' message is displayed"})})
	public void verify0AdultAdd()
	{
		ho.add0Adult();
		setPassMessage("The alert is displayed and 'Minimum 1 adult is required' message is displayed");
	}
	
	
	
	@Test(testName="Validation of 'Search' field", description = "To validate the functionality of Search button",
			dependsOnMethods = "verify0AdultAdd",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to hotels list webpage"})})
	public void validationOfSearchField()
	{
		ho.enterRooms(rooms2);
		ho.enterAdults(adults2);
		ho.enterChildren(children2, childrenAges2);
		ho.clickSearchButton();
		hr.getHotelsDetails(30);
		setPassMessage("User is redirected to hotels list webpage");
	}
	
	@Test(testName = "Verification of Hotel search criteria", description = "To verify the hotel search criteria details in the hotel results page",
			dependsOnMethods = "validationOfSearchField",
attributes = {@CustomAttribute(name="passMessage",values={"The details displayed in the hotel results page matches with the details used to search the hotels"})})
	public void verifySearchCriteria() throws Exception
	{
		hr.verifyLocation(hotelLocation);
		hr.verifyCheckInDate(checkInDate);
		hr.verifyCheckOutDate(checkOutDate);
		hr.verifyGuestDetails(rooms2,adults2,children2);
		setPassMessage("The details displayed in the hotel results page matches with the details used to search the hotels");
	}
	
	
	@Test(testName="Verification of sorting options",description = "To verify the functionality of the sorting options",
			dependsOnMethods = "verifySearchCriteria", dataProvider = "sortingOptionsAvailable",
attributes = {@CustomAttribute(name="passMessage",values={"The hotels displayed are sorted correctly"})})
	public void verifySortingOptions(String sortingOption)
	{
		hr.sortBy(sortingOption);
		hr.verifySortBy(sortingOption);
		setPassMessage("The hotels displayed are correctly by "+sortingOption);
	}
	
	
	@Test(testName = "Validation of Price filter", description = "To verify the functionality of Price filters",
			dependsOnMethods = "verifySortingOptions",
attributes = {@CustomAttribute(name="passMessage",values={"The hotel results shows the hotels having amount between 2001 and 4000"})})
	public void verifyPriceFilter()
	{
		hr.sortBy("Popularity");
		hr.applyPriceRangeFilter(priceFilters);
		hr.verifyPriceFilter(priceFilters);
		setPassMessage("The hotel results shows the hotels having amount between 2001 and 4000");
	}
	
	@Test(testName = "Validation of Customer Rating filter", description = "To verify the functionality of Rating filters",
			dependsOnMethods = "verifyPriceFilter",
attributes = {@CustomAttribute(name="passMessage",values={"The hotel results shows the hotels having rating above 4"})})
	public void verifyCustomerRatingFilter()
	{
		hr.applyCustomerRatingFilter(ratingFilter);
		hr.verifyCustomerRatingFilter(ratingFilter);
		setPassMessage("The hotel results shows the hotels having rating above 4");
	}
	
	@Test(testName = "Verify selection of hotel", description = "To verify if user is able to select hotel and is redirected to room booking page",
			dependsOnMethods = "verifyCustomerRatingFilter",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to that specific hotel webpage to choose room type"})})
	public void verifyHotelSelection()
	{
		hr.getHotelsDetails();
		hotelName = hr.bookHotel(hotelBook);
		hb.verifyPage();
		setPassMessage("User is redirected to that specific hotel webpage to choose room type");
	}
	
	@Test(testName = "Verify selection of room options", description = "To verify if user is view the availble room options and is able to book the required room option",
			dependsOnMethods = "verifyHotelSelection",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to Room Info webpage"})})
	public void verifyRoomOptionsSelection()
	{
		hb.viewRoomOptionsAvailable();
		roomOptionName = hb.bookRoom(roomOptionBook);
		hb.verifyHotelNameInHotelInfo(hotelName);
		hb.verifyRoomOptionSelected(rooms2, roomOptionName.substring(0,roomOptionName.indexOf(",")));
		setPassMessage("User is redirected to Room Info webpage");
	}
	
	@Test(testName = "Verification of search criteria", description = "To verify the search criteria details on the hotel info page",
			dependsOnMethods = "verifyRoomOptionsSelection",
attributes = {@CustomAttribute(name="passMessage",values={"The search criteria displayed matches with the search criteria used for the searching the hotel"})})
	public void validateSearchCriteria() throws Exception
	{
		hb.verifyHotelInfoSearchCriteria(checkInDate, checkOutDate, rooms2, adults2, children2);
		setPassMessage("The search criteria displayed matches with the search criteria used for the searching the hotel");
	}
	
	@Test(testName = "Verification of Offer Code selection", description = "To verify if the offer code gets selected and gets applied in the price summary",
			dependsOnMethods = "validateSearchCriteria",
attributes = {@CustomAttribute(name="passMessage",values={"The selected offer code gets selected and highlighted and the offer code is applied in the price summary"})})
	public void verifyOfferCodeSelection()
	{
		hb.getOfferCodeDetails();
		appliedOfferCode = hb.selectOfferCode(offerCodeNumber);
		hb.verifyAppliedOfferCode(appliedOfferCode);
		setPassMessage("The selected offer code gets selected and highlighted and the offer code is applied in the price summary");
		
	}
	
	
	@Test(testName = "Verification of Price Summary", description = "To verify the displayed information of price summary",
			dependsOnMethods = "verifyOfferCodeSelection",
attributes = {@CustomAttribute(name="passMessage",values={"The price summary is correct based on the data displayed in the Room Booking page"})})
	public void verifyPriceSummary()
	{
		priceSummary = hb.getPriceSummary();
		hb.verifyPriceSummary(rooms2, nights, roomOptionName,appliedOfferCode);
		setPassMessage("The price summary is correct based on the data displayed in the Room Booking page");
	}
	
	@Test(testName = "Verification of required fields", description = "To verify if user is able to fill all the required guest details",
			dependsOnMethods = "verifyPriceSummary",
attributes = {@CustomAttribute(name="passMessage",values={"User is able to fill details in the required fields"})})
	public void verifyRequiredFields()
	{
		hb.enterTitle(title);
		hb.enterFirstName(firstName);
		hb.enterLastName(lastName);
		hb.enterEmail(email);
		hb.enterCountryCode(countryCode);
		hb.enterMobileNumber(number);
		hb.enterAddress(address);
		hb.enterPincode(pincode);
		hb.enterState(state);
		setPassMessage("User is able to fill details in the required fields");
	}
	
	@Test(testName = "Verification of 'Proceed To Payment Options' button", description = "To verify the functionality of 'Proceed To Payment Options' button",
			dependsOnMethods = "verifyRequiredFields",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to payment gateway to complete the payment"})})
	public void verifyPaymentButton()
	{
		hb.clickProceedToPayment();
		hb.verifyPaymentWindow();
		setPassMessage("User is redirected to payment gateway to complete the payment");
	}
	
	@Test(testName = "Verfication of details on Payment Gateway", description = "To verify the details on the Payment Gateway page",
			dependsOnMethods = "verifyPaymentButton",
attributes = {@CustomAttribute(name="passMessage",values={"All the details displayed is correct"})})
	public void verifyDetailsOnPaymentGateway() throws Exception
	{
		hb.verifyDetailsOnPaymentsPage(hotelName, checkInDate, checkOutDate, firstName, lastName, email, countryCode, number, priceSummary.get("Total Amount to be paid"));
		setPassMessage("All the details displayed is correct");
	}
	
	@Test(testName = "Verfication of 'Credit/Debit/ATM Card' option", description = "To verify if user is able to choose 'Credit/Debit/ATM Card' option",
			dependsOnMethods = "verifyDetailsOnPaymentGateway",
attributes = {@CustomAttribute(name="passMessage",values={"Enter card details message is displayed"})})
	public void verifyCardPaymentOption()
	{
		hb.selectPaymentOption(paymentOption);
		hb.verifyCardMenu();
		setPassMessage("'Enter card details' message is displayed");
	}
	
	@Test(testName = "Verfication of card details fields", description = "To verify if user is able to enter credit card details",
			dependsOnMethods = "verifyCardPaymentOption",
attributes = {@CustomAttribute(name="passMessage",values={"User is able to enter all required details"})})
	public void verifyCardDetailsFields()
	{
		hb.enterCardNumber(cardNumber);
		hb.enterCardExpiryMonth(cardExpiryMonth);
		hb.enterCardExpiryYear(cardExpiryYear);
		hb.enterCvv(cardCvv);
		hb.enterCardHolderName(cardHolderName);
		hb.enterBillingCountry(billingCountry);
		hb.enterBillingState(billingState);
		hb.enterBillingCity(billingCity);
		hb.enterBillingPincode(billingPincode);
		hb.enterBillingAddress(billingAddress);
		setPassMessage("User is able to enter all required details");
	}
	
	@Test(testName = "Verfication of 'Pay without saving card' option", description = "To verify functionality of 'Pay without saving card' option",
			dependsOnMethods = "verifyCardDetailsFields",
attributes = {@CustomAttribute(name="passMessage",values={"Error message is displayed as 'We are unable to process transactions for this user/card'"})})
	public void verifyPayWithoutSavingCardButton()
	{
		hb.clickPayWithoutSavingCard();
		hb.getErrorMessage();
		setPassMessage("Error message is displayed as 'We are unable to process transactions for this user/card'");
		
	}
	
	@DataProvider
	public String[] sortingOptionsAvailable()
	{
		String[] data = {"Price(High to Low)","Price(Low to High)","Customer Ratings"};
		return data;
	}

}
