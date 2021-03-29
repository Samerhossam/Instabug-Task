package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

	protected WebDriver driver;
	public Actions action;
	
	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this); //Constructor
	}
	
	protected static void SendText(WebElement Text, String Value)
	{
		Text.sendKeys(Value); //Text input method
	}
	
}
