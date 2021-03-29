package Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.SearchResultsPage;

public class VerifyCalculatorTest extends TestBase{

	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchResultsPageObject; //Declare Object from Search results page
	
	@Test
	//Verify results of google calculator method definition
	public void VerifyCalculatorResults() throws InterruptedException {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String Keyword = "2+2"; //Assign value to input keyword
		HomePageObject.SearchMethod(Keyword); //Call text input method
		Thread.sleep(2000); //Wait 2 seconds
		HomePageObject.SelectSuggestion(); //Call method to select first suggestion
		
		SearchResultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver
		String Result = SearchResultsPageObject.CalculatorResult.getText(); //Assign value in calculator to string result
		
		//Check if calculator result is correct
		Assert.assertEquals(SearchResultsPageObject.CalculatorResult.getText(), "4"); 
		System.out.println("Results is " + Result); //Print result
	}
	
	@AfterMethod
	//Take screenshot method declaration
	public void TakeScreenshotOnFailure(ITestResult Result) throws IOException {
		if(ITestResult.FAILURE == Result.getStatus()) {

			TakesScreenshot ts = (TakesScreenshot)Driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + Result.getName() + ".png"));
		}
	}
}
