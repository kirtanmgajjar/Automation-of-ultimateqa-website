package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	protected WebDriver driver;
	public Actions action;
	public WebDriverWait wait;
	public FluentWait<WebDriver> fwait;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		action = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		fwait = new FluentWait<WebDriver>(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public abstract void verifyPage();
}
