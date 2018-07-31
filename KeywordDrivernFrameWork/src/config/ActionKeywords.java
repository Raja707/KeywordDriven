package config;

import static executionEngine.DriverScript.OR;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.Log;

public class ActionKeywords {
	
	static WebDriver driver;
	
	public static void openBrowser(String object) {	
		Log.info("Opening Browser Chrome");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void navigateUrl (String object) {	
		Log.info("Navigate to Salesforce URL");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(Constants.url);
	}
	
	public static void input_uname (String object) {
		Log.info("Enter the values for "+object);
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.uname);
	}
	
	public static void input_upass (String object) {
		Log.info("Enter the values for "+object);
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.upass);
	}
	
	public static void click (String object) {
		Log.info("Clicking the values for "+object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void waitSometime (String object) throws InterruptedException {
		Log.info("Waiting for 5 seconds");
		Thread.sleep(5000);
	}
	
	public static void closeBrowser (String object) {
		Log.info("Closing the browser");
		driver.quit();
	}
	
	

}
