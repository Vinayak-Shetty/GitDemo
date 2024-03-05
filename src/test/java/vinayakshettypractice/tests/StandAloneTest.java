package vinayakshettypractice.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayakshettypractice.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingP = new LandingPage(driver);
		String productName = "ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement email = driver.findElement(By.id("userEmail"));
		WebElement password = driver.findElement(By.id("userPassword"));
		WebElement loginBtn = driver.findElement(By.id("login"));
		email.sendKeys("vinayak01@gmail.com");
		password.sendKeys("Vinayak@12");
		loginBtn.click();
		
		//Iterate all item and select 1 item
		
		List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='card']/div"));
		for (int i=0; i<productNames.size(); i++) {
			System.out.println(productNames.get(i).getText());
		}
		
		WebElement prod = productNames.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod.getText());

		prod.findElement(By.cssSelector("button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys("India");
		
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//div[@class='form-group']/input")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
