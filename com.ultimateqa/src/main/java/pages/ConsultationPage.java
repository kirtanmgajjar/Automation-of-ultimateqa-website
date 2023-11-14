package pages;

 

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClass.BaseClass;

public class ConsultationPage extends BaseClass {

    // Webpage elements of the current page used for running the test
    @FindBy(xpath = "(//*[@id='menu-home-page-menu']//a)[1]")
    WebElement services;
    @FindBy(xpath = "(//*[@class='header-content']/a)[1]")
    WebElement consultationBtn;
    @FindBy(className = "cu-form__container")
    WebElement pageContainer;
    
    public ConsultationPage() {
        PageFactory.initElements(driver, this);
    }
    public void verifyTitle()
    {
    	String title = driver.getTitle();
    	assertEquals(title, "Homepage - Ultimate QA");
    }
    public void clickFreeConsultation()
    {
    	services.click();
    	consultationBtn.click();
    	String currentUrl = driver.getCurrentUrl();
    	wait.until(ExpectedConditions.visibilityOfAllElements(pageContainer));
    	assertTrue(currentUrl.contains("forms.clickup.com"));
    	

    }

}