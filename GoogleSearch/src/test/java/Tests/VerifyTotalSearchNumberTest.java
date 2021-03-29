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

public class VerifyTotalSearchNumberTest extends TestBase{
	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchresultsPageObject; //Declare Object from Search results page

	@Test
	//Verify that search engine returns the total number of results 
	public void VerifyTotalSearchNumber() throws InterruptedException {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String Keyword = "Instabug"; //Assign value to correct input keyword
		HomePageObject.SearchMethod(Keyword); //Call text input method
		Thread.sleep(2000); //Wait 2 seconds
		HomePageObject.SearchByEnter(); //Call search by enter button method

		SearchresultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver

		//Check if total results message section is displayed
		try {
			Assert.assertTrue(SearchresultsPageObject.TotalResults.isDisplayed());
			SearchresultsPageObject.GetTotalResults(); //Print total results message
		}

		catch(AssertionError ae) {
			ae.printStackTrace();
		}
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
