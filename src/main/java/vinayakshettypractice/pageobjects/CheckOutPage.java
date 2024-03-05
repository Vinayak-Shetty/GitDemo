package vinayakshettypractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayakshettypractice.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='form-group']/input")
	WebElement enterCountry;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitBtn;
	
	@FindBy(css=".hero-primary")
	WebElement thankYouText;
	
		
	By countryValues = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		sendKeysAction(enterCountry, countryName);
		waitForElementToAppear(countryValues);
		
		selectCountry.click();
		
		
		
	}
	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}
