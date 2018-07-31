package config;

import static executionEngine.DriverScript.OR;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import executionEngine.DriverScript;
import utility.Log;

public class ActionKeywords {
	
	static WebDriver driver;
	
	public static void openBrowser(String object) {	
		try {
			Log.info("Opening Browser Chrome");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} 
		catch (Exception e) {
			Log.error("Not able to open browser chrome "+e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void navigateUrl (String object) {	
		try {
			Log.info("Navigate to Salesforce URL");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(Constants.url);
		} 
		catch (Exception e) {
			Log.error("Not able to navigate to right URL "+e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void input_uname (String object) {
		try {
			Log.info("Enter the values for "+object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.uname);
		} 
		catch (Exception e) {
			Log.error("Not able to input the values "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void input_upass (String object) {
		try {
			Log.info("Enter the values for "+object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.upass);
		} 
		catch (Exception e) {
			Log.error("Not able to input the values "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void click (String object) {
		try {
			Log.info("Clicking the values for "+object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} 
		catch (Exception e) {
			Log.error("Not able to click the button "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void waitSometime (String object) throws InterruptedException {
		try {
			Log.info("Waiting for 5 seconds");
			Thread.sleep(5000);
		} 
		catch (Exception e) {
			Log.error("Not waited for 5 seconds "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void closeBrowser (String object) {
		try {
			Log.info("Closing the browser");
			driver.quit();
		} 
		catch (Exception e) {
			Log.error("Not able to close the browser "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	

}
