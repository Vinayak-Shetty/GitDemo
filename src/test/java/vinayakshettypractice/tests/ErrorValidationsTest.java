package vinayakshettypractice.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayakshettypractice.TestComponents.BaseTest;
import vinayakshettypractice.TestComponents.Retry;
import vinayakshettypractice.pageobjects.CartPage;
import vinayakshettypractice.pageobjects.CheckOutPage;
import vinayakshettypractice.pageobjects.ConfirmationPage;
import vinayakshettypractice.pageobjects.LandingPage;
import vinayakshettypractice.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {

		
		landingP.loginApplication("vinayak01@gmail.com", "Vinayak@1");
		String errorMessage = landingP.getErrorMessage();
		System.out.println(errorMessage);
		Assert.assertEquals("Incorrect email or password.", landingP.getErrorMessage());
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void ProductErrorValidation() throws IOException {

		ProductCatalogue productCatalogue = landingP.loginApplication("vinayak01@gmail.com", "Vinayak@12");

		// Iterate all item and select 1 item

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);

		CartPage cartP = productCatalogue.goToCartPage();

		boolean match = cartP.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
