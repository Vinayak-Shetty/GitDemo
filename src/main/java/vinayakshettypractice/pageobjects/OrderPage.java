package vinayakshettypractice.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayakshettypractice.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement email = driver.findElement(By.id("userEmail"));
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;


	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckOutPage goToCheckout() {
		checkOutBtn.click();
		CheckOutPage checkOutP = new CheckOutPage(driver);
		return checkOutP;
	}
	

}
