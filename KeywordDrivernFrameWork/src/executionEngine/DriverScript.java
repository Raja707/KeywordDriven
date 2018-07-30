package executionEngine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://test.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("rajasingh.nadar@infosys.com.vmstdemo");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Raja@1506$$$$$$");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		driver.quit();		

	}

}
