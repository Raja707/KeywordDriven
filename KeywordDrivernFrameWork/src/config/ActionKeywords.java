package config;

import static executionEngine.DriverScript.OR;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionKeywords {
	
	static WebDriver driver;
	
	public static void openBrowser(String object) {	
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void navigateUrl (String object) {		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(Constants.url);
	}
	
	public static void input_uname (String object) {
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.uname);
	}
	
	public static void input_upass (String object) {
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.upass);
	}
	
	public static void click (String object) {
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void waitSometime (String object) throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public static void closeBrowser (String object) {
		driver.quit();
	}
	
	

}
