package vinayakshettypractice.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayakshettypractice.TestComponents.BaseTest;
import vinayakshettypractice.pageobjects.CartPage;
import vinayakshettypractice.pageobjects.CheckOutPage;
import vinayakshettypractice.pageobjects.ConfirmationPage;
import vinayakshettypractice.pageobjects.LandingPage;
import vinayakshettypractice.pageobjects.OrderPage;
import vinayakshettypractice.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	public String productName = "ADIDAS ORIGINAL";

	@Test (dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {

		
		ProductCatalogue productCatalogue = landingP.loginApplication(input.get("email"), input.get("password"));

		// Iterate all item and select 1 item

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("productName"));

		CartPage cartP = productCatalogue.goToCartPage();

		boolean match = cartP.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutP = cartP.goToCheckout();
		checkoutP.selectCountry("India");
		ConfirmationPage confirmationP = checkoutP.submitOrder();
		String confirmMessage = confirmationP.verifyConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	// To verify 'Adidas original is displaying in orders

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingP.loginApplication("vinayak01@gmail.com", "Vinayak@12");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		boolean matchFound = ordersPage.verifyOrderDisplay(productName);
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		System.out.println(productName + " matched");
	}
	
	
	
	//Extent Reports
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "vinayak01@gmail.com");
//		map.put("password", "Vinayak@12");
//		map.put("productName", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "vinayak01@gmail.com");
//		map1.put("password", "Vinayak@12");
//		map1.put("productName", "ZARA COAT 3");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/vinayakshettypractice/data/PurchaseOrder.json");
		
		return new Object [][] {{data.get(0)}, {data.get(1)}};
		
//		return new Object[][] {{map}, {map1}};
	}
	
//	@Dataprovider
//	public Object[][] getData(){
//		return new Object[][] {{"vinayak01@gmail.com", "Vinayak@12", "ADIDAS ORIGINAL"}, {"vinayak01@gmail.com", "Vinayak@12", "ZARA COAT 3"}};
//	}
	
	

}
