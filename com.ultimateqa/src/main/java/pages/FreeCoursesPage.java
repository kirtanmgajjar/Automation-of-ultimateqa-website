package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseClass;

public class FreeCoursesPage extends BasePage
{
	@FindBy(name = "q")
	WebElement searchBox;
	@FindBy(linkText = "Sign In")
	WebElement signInButton;
	
	public FreeCoursesPage(WebDriver driver) 
	{
        super(driver);
    }
	
	public void searchCourse(String course)
	{
		searchBox.sendKeys(course);
		action.sendKeys(Keys.ENTER).perform();
	}
	
	public boolean hasSearchResults()
	{
		
		String result = driver.findElement(By.xpath("//*[@class='products__title']/following-sibling::*")).getAttribute("class");
		
		if(result.equals("products__list-no-results"))
			return false;
		else
			return true;
	}
	
	public void navigateToSignin()
	{
		signInButton.click();
	}

	@Override
	public void verifyPage() 
	{
		
		
	}
}
