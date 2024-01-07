package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
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
	public Iterator<String> iter;
	public Set<String> windowHandels;
	public JavascriptExecutor jse;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		action = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		fwait = new FluentWait<WebDriver>(driver);
		jse = (JavascriptExecutor) driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public abstract void verifyPage();
}
