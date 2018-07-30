package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionKeywords {
	
	static WebDriver driver;
	
	public static void openBrowser() {	
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void navigateUrl () {		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://test.salesforce.com/");
	}
	
	public static void input_uname () {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("rajasingh.nadar@infosys.com.vmstdemo");
	}
	
	public static void input_upass () {
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Raja@1506$$$$$$");
	}
	
	public static void clickButton () {
		driver.findElement(By.xpath("//input[@id='Login']")).click();
	}
	
	public static void waitSometime () throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public static void closeBrowser () {
		driver.quit();
	}
	
	

}
