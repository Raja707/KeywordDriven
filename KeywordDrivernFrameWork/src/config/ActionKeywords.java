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
	
	//Action Keyword - openBrowser
	//object - comes from Page Object from TestSteps sheet and redirected to OR file
	//data = comes from Data Set from TestSteps sheet
	//data and object can be blank
	public static void openBrowser(String object, String data) {	
		try {
			Log.info("Opening Browser "+data);
			
			if (data.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if (data.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
				driver = new ChromeDriver();
			}
			else if (data.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
				driver = new ChromeDriver();
			}
			
			driver.manage().window().maximize();
		} 
		catch (Exception e) {
			Log.error("Not able to open browser chrome "+e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	//Action Keyword - navigateUrl
	public static void navigateUrl (String object, String data) {	
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
	
	//Action Keyword - input
	public static void input (String object, String data) {
		try {
			Log.info("Enter the values for "+object+" "+data);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		} 
		catch (Exception e) {
			Log.error("Not able to input the values "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
						/*public static void input_upass (String object, String data) {
							try {
								Log.info("Enter the values for "+object);
								driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.upass);
							} 
							catch (Exception e) {
								Log.error("Not able to input the values "+e.getMessage());
								DriverScript.bResult=false;
							}
						}*/
	
	//Action Keyword - click
	public static void click (String object, String data) {
		try {
			Log.info("Clicking the values for "+object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} 
		catch (Exception e) {
			Log.error("Not able to click the button "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	//Action Keyword - waitSometime
	public static void waitSometime (String object, String data) {
		try {
			Log.info("Waiting for 5 seconds");
			Thread.sleep(5000);
		} 
		catch (Exception e) {
			Log.error("Not waited for 5 seconds "+e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	//Action Keyword - closeBrowser
	public static void closeBrowser (String object, String data) {
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
