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

public class VerifyNoMatchingResultsTest extends TestBase{
	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchResultsPageObject; //Declare Object from Search results page

	@Test
	//Verify if search engine displays No matching results message if keyword is meaningless
	public void VerifyNoMathcingResults() throws InterruptedException {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String MeaninglessKeyword = "qwgevyuf qrifbqf querfbu"; //Assign value to correct input keyword
		HomePageObject.SearchMethod(MeaninglessKeyword); //Call text input method
		Thread.sleep(2000); //Wait 2 seconds
		HomePageObject.SearchByEnter(); //Call search by enter button method

		SearchResultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver
		
		//Check if the message section is displayed
		try {
			Assert.assertTrue(SearchResultsPageObject.NoMatchingResults.isDisplayed());
			SearchResultsPageObject.GetMessage(); //Prints the displayed message
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

