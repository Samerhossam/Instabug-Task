package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
	}

	@FindBy(name = "q")
	public WebElement SearchBox; //Search input field element

	@FindBy(className = "gNO89b")
	WebElement SearchButton; //Search Button element

	@FindBy(tagName = "li")
	public List<WebElement> Suggestions; //List of suggestions elements list

	@FindBy(css = "span.lBbtTb.z1asCe.rzyADb")
	WebElement ClearButton; //Clear button element


	//Search method definition
	public void SearchMethod(String SearchKeyword) {
		SearchBox.clear(); //Clear input field function
		SendText(SearchBox, SearchKeyword); //Enter keyword method
	}
	
	//Click on search button method definition
	public void ClickSearch() {
		action.moveToElement(SearchButton).click().build().perform(); //Click on search button method
	}

	//Select first index in search auto-complete suggestion method definition
	public void SelectSuggestion() throws InterruptedException {
		SearchBox.sendKeys(Keys.ARROW_DOWN); //Press arrow down button method
		Thread.sleep(1000); //Wait 1 second
		SearchBox.sendKeys(Keys.ENTER); //Press enter button method
	}

	//Click enter button to search method
	public void SearchByEnter() {
		SearchBox.sendKeys(Keys.ENTER); //Press enter button method
	}

	//Clear search box input field method definition
	public void ClearMethod() {
		ClearButton.click(); //Click on clear button method
	}
}
