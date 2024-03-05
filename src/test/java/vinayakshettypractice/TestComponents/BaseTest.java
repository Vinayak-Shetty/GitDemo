package vinayakshettypractice.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayakshettypractice.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingP;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//vinayakshettypractice//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();	
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}	
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver",
					"C://Users//vinay//OneDrive//Documents//Downloads//geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\vinay\\OneDrive\\Documents\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to hashmap Jackson Data Bind
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List <HashMap<String,String>>>(){
			
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot takeSS = (TakesScreenshot)driver;
		File source = takeSS.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {

		WebDriver driver = initializeDriver();
		landingP = new LandingPage(driver);
		landingP.goTo();
		return landingP;
	}
	
	@AfterMethod(alwaysRun=true)
	public void TearDown() throws IOException {

		driver.close();
	}
	
	

}
