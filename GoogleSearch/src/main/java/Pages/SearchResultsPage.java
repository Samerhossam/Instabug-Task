package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends PageBase{

	public SearchResultsPage(WebDriver driver) {
		super(driver); //Constructor
	}

	@FindBy(tagName="h3")
	public List<WebElement> Headers; //List of search title element list
	
	@FindBy(id = "cwos")
	public WebElement CalculatorResult; //Result number in google calculator element
	
	@FindBy(tagName = "p")
	public WebElement CorrectionTag; //"Showing results for" section element
	
	@FindBy(id = "result-stats")
	public WebElement TotalResults; //Number of total results section element
	
	@FindBy(id="taw")
	public WebElement DidYouMean; //"Did you mean" section element
	
	@FindBy(id = "res")
	public WebElement NoMatchingResults; //No matching results section element
	
	//Print Number of total results method definition
	public void GetTotalResults() {
		String Number = TotalResults.getText();
		System.out.println(Number);
	}
	
	//Print correct keyword message method definition
	public void GetCorrectKeyword() {
		String CorrectKeyword = DidYouMean.getText();
		System.out.println(CorrectKeyword);
	}
	
	//Print No matching results message method definition
	public void GetMessage() {
		String Message = NoMatchingResults.getText();
		System.out.println(Message);
	}
}
