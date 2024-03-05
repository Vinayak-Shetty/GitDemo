package vinayakshettypractice.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vinayakshettypractice.pageobjects.CartPage;
import vinayakshettypractice.pageobjects.OrderPage;

public class AbstractComponent {
	
//	public AbstractComponent(WebDriver driver) {
//		this.driver = driver;
//	}
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		System.out.println("waitforElement to appear completed");
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		System.out.println("waitforElement to appear completed");
	}
	
	public CartPage goToCartPage() {
		cartBtn.click();
		CartPage cartP = new CartPage(driver);
		return cartP;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage ordersPage = new OrderPage(driver);
		return ordersPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void sendKeysAction(WebElement enterCountry, String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(enterCountry, countryName).build().perform();
	}
	
	public void validateUserIncorrect() {
		
	}
	
}
