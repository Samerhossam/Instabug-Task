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

public class VerifyResultsTest extends TestBase{

	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchResultsPageObject; //Declare Object from Search results page

	@Test
	//Verify that search engine returns correct search results related to entered keyword
	public void VerifySearchResults() throws InterruptedException {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String Keyword = "Instabug"; //Assign value to correct input keyword
		HomePageObject.SearchMethod(Keyword); //Call text input method
		Thread.sleep(2000); //Wait 2 seconds
		HomePageObject.SelectSuggestion(); //Call click on first suggestion method

		SearchResultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver
		int counter = 0; //Initialise counter

		//Method to loop over the list of results titles in search results page
		for(int i = 0; i<SearchResultsPageObject.Headers.size(); i++)
		{
			//Check if search results contain the entered keyword
			if(SearchResultsPageObject.Headers.get(i).getText().contains(Keyword))
			{
				counter++; //Increment counter
			}
		}

		//Print how many times the entered keyword appeared in search results
		System.out.println(Keyword + " is repeated " + counter + " times out of " + SearchResultsPageObject.Headers.size() + " in search results");
		//Check if search results are more the zero
		Assert.assertNotEquals(counter, 0); 
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
