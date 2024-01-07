package allTest;

import java.text.ParseException;

import org.testng.annotations.CustomAttribute;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class FlightTest extends BaseClass {
	
	int chpFare;
	String paymentOption = "Credit/Debit/ATM Card";
	String cardNumber = "4532016646430887";
	String cardExpiryMonth = "07";
	String cardExpiryYear = "2024";
	String cardCvv = "123";
	String cardHolderName = "Carmine Hammes";
	String billingCountry = "India";
	String billingState = "Maharashtra";
	String billingCity = "Mumbai";
	String billingPincode = "654321";
	String billingAddress = "abc,Efg,Hij";
	
	
	@Test(testName = "Navigation to Hotel page", description = "To validate if Flight menu button is diaplyed and is clickable",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to flight booking page"})})
	public void navigateToFLightsPage()
	{
		hp.closeSigninPopup();
		hp.navigateToFlights();
		fp.verifyPage();
	}
	
	@Test(testName = "Verification of From textfield", description = "To validate From textbox is enabled",
			dependsOnMethods = "navigateToFLightsPage",
attributes = {@CustomAttribute(name="passMessage",values={"A drop drown is displayed showing the city names list"})})
	public void verifyFromTextBox()
	{
		fp.verifyFromBox();
	}
	
	@Test(testName = "From city", description = "To verify if user is able to click Bengaluru from city dropdown list", 
			dependsOnMethods = "verifyFromTextBox",
attributes = {@CustomAttribute(name="passMessage",values={"The option is clicked"})})
	public void verifyOriginCity()
	{
		fp.selectCity("Bengaluru");
		fp.verifyFromCity("Bengaluru");
	}
	
	@Test(testName = "Verification of To textfield", description = "To validate To textbox is enabled",
			dependsOnMethods = "verifyOriginCity",
attributes = {@CustomAttribute(name="passMessage",values={"A drop drown is displayed showing the city names list"})})
	public void verifyToTextBox()
	{
		fp.verifyToBox();
	}
	
	@Test(testName = "To city", description = "To verify if user is able to click Ranchi from city dropdown list", 
			dependsOnMethods = "verifyToTextBox",
attributes = {@CustomAttribute(name="passMessage",values={"The option is clicked"})})
	public void verifyDestinationCity()
	{
		fp.selectCity("Ranchi");
		fp.verifyToCity("Ranchi");
	}
	
	@Test(testName = "Verification of Date Button", description = "To verify Departure date button is clickable", 
			dependsOnMethods = "verifyDestinationCity",
attributes = {@CustomAttribute(name="passMessage",values={"A drop down calendar is displayed with showing the prices of flights for each day"})})
	public void verifyDateBtn()
	{
		fp.verifyDateBtn();
	}
	
	@Test(testName = "Verification of Cheap Fares", description = "To verify fare displayed is lowest for the day", 
			dependsOnMethods = "verifyDateBtn",
attributes = {@CustomAttribute(name="passMessage",values={"The date is selected and is displayed"})})
	public void validationOfLowestFares() throws ParseException
	{
		fp.selectMonth("March 2024");
		fp.randomDateSel("March 2024");
		fp.travellerDetails("Economy");
		fp.searchFlightBtn();
		chpFare = fr.getChpFare();
		driver.navigate().back();
		fp.lowestFareCheck(chpFare);
	}
	
	@Test(testName = "Selecting lowest fare date", description = "To verify user is able to select date from Calendar for the lowest price", 
			dependsOnMethods = "validationOfLowestFares",
attributes = {@CustomAttribute(name="passMessage",values={"A dropdown is displayed and final details is displayed"})})
	public void lowestDateSelection()
	{
		fp.selectDate("March 2024");
	}
	
	@Test(testName = "Traveller Details", description = "To verify user is able to add details in Travellers and class menu",
			dependsOnMethods = "lowestDateSelection",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to avaible flights webpage"})})
	public void enterTravellerDetails()
	{
		fp.travellerDetails("Economy");
	}
	
	@Test(testName = "Validation of Search button", description = "To verify the functionality of \"Search flights\" button after user click it", 
			dependsOnMethods = "enterTravellerDetails",
attributes = {@CustomAttribute(name="passMessage",values={"Flight details having prices in ascending order is displayed"})})
	public void verifySearchButton()
	{
		fp.searchFlightBtn();
		fr.verifyPage();
	}
	
	@Test(testName = "Validation of price filter", description = "To verify the functionality of Price Filter", 
			dependsOnMethods = "verifySearchButton",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to overview details page"})})
	public void verifyPriceFilter()
	{
		fr.fliterApplied();
		chpFare = fr.getChpFare();
		fr.checkFilter(chpFare);
	}
	
	@Test(testName = "Validation of book flight button", description = "To verify the functionality of \"Book now\" button after user click on cheapest price flight",
			dependsOnMethods = "verifyPriceFilter",
attributes = {@CustomAttribute(name="passMessage",values={"The flight details is displayed"})})
	public void verifyBookBtn()
	{
		fr.flightBookBtn();
		fb.verifyPage();
	}
	
	@Test(testName = "Validation of flight details", description = "To verify the details of flight is displayed at overview page", 
			dependsOnMethods = "verifyBookBtn",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to payment gateway to complete the payment"})})
	public void verifyFlightDetails()
	{
		fb.reviewDetails();
	}
	
	
	@Test(testName = "Filling passenger details", description = "To verify user is able to fill passenger details ", 
			dependsOnMethods = "verifyFlightDetails", dataProvider = "passengerDetails",
attributes = {@CustomAttribute(name="passMessage",values={"An error message is displayed stating 'Card number is not valid'"})})
	public void enterPassengerDetails(String[] details) throws InterruptedException
	{
		hb.enterAddress(details[0]);
		hb.enterPincode(details[1]);
		fb.enterState(details[2]);
		fb.selGender(details[3]);
		fb.enterFirstName(details[4]);
		fb.enterLastName(details[5]);
		fb.enterEmail(details[6]);
		fb.enterCountryCode(details[7]);
		fb.enterMobileNumber(details[8]);
		fb.verifyGrandTotal(chpFare);
		fb.clickProceedToPayment();
		
	}
	
	@Test(testName = "Verify Passenger details", description = "To verify passenger details are displayed correctly",
			dependsOnMethods = "enterPassengerDetails", dataProvider = "passengerDetails",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to flight booking page"})})
	public void verifyPassengerDetails(String[] details)
	{
		fb.verifyFromCity(details[9]);
		fb.verifyToCity(details[10]);
		fb.verifyName(details[4], details[5]);
		fb.verifyGender(details[3]);
		fb.verifyEmail(details[6]);
		fb.verifyMob(details[7], details[8]);
		fb.verifyPaymentTotal(chpFare);
	}
	
	@Test(testName = "Verfication of 'Credit/Debit/ATM Card' option", description = "To verify if user is able to choose 'Credit/Debit/ATM Card' option",
			dependsOnMethods = "verifyPassengerDetails",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to flight booking page"})})
	public void verifyCardPaymentOption()
	{
		hb.selectPaymentOption(paymentOption);
		hb.verifyCardMenu();
	}
	
	@Test(testName = "Verfication of card details fields", description = "To verify if user is able to enter credit card details",
			dependsOnMethods = "verifyCardPaymentOption",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to flight booking page"})})
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
	}
	
	@Test(testName = "Verfication of 'Pay without saving card' option", description = "To verify functionality of 'Pay without saving card' option",
			dependsOnMethods = "verifyCardDetailsFields",
attributes = {@CustomAttribute(name="passMessage",values={"User is redirected to flight booking page"})})
	public void verifyPayWithoutSavingCardButton()
	{
		hb.clickPayWithoutSavingCard();
		fb.getErrorMessage();
		
	}
	
	@DataProvider
	public String[][] passengerDetails()
	{
		String[][] data = {{"sdsds","560078","Odisha","MALE","Ram","Kumar","boy3452@gmail.com","91","7867389223","Bengaluru","Ranchi"}};
		return data;
	}

}
