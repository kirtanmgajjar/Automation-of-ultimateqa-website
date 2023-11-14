package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseClass;

public class HomePage extends BaseClass{
	
	@FindBy(xpath = "//*[@class = 'et_pb_menu__logo']/img")
	WebElement homePageImg;
	@FindBy(xpath = "//*[@id='menu-home-page-menu']/li[3]")
	WebElement hoverLearning ;
	@FindBy(xpath = "(//*[contains(@class,'et_pb_menu_page_id-216155')]/a)[1]")
	WebElement freeCourse;
	@FindBy(className = "header__nav-sign-in")
	WebElement signinButton;
	@FindBy(className = "page__heading")
	WebElement check;
	@FindBy(className = "formkit-close")
	WebElement close;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	public void navigateHomePage() throws InterruptedException
	{
//		driver.navigate().back();
//		driver.navigate().back();
		driver.get(url);
		assertEquals(driver.getTitle(), "Homepage - Ultimate QA");
	}
	
	public void hoverLearningMenu()
	{
		fwait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1))
		.ignoring(StaleElementReferenceException.class).until( driver -> {
			action.moveToElement(hoverLearning).perform();
			return freeCourse.isDisplayed();
		});
		
	}
	
	public void clickFreeCourse()
	{
		freeCourse.click();
	}
	
	public void navigateToSignin()
	{
		signinButton.click();
		assertEquals(check.getText(), "Welcome Back!");
		System.out.println(check.getText());
	}
}
