package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
public class TestBase {

	public static WebDriver Driver;

	@BeforeClass
	public void StartDriver() {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
		Driver = new ChromeDriver();
		Driver.manage().window().maximize();
		Driver.manage().deleteAllCookies();
		Driver.navigate().to("https://www.google.com/");	
	}

	@AfterClass
	public void StopDriver() {
		Driver.quit();
	}



}
