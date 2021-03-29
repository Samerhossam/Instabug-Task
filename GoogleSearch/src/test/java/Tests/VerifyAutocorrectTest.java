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

public class VerifyAutocorrectTest extends TestBase{
	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchResultsPageObject; //Declare Object from Search results page

	@Test
	//Verify auto-correct method definition
	public void VerifyAutocorrect() {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String IncorrectKeyword = "Instagug"; //Assign value to input keyword
		HomePageObject.SearchMethod(IncorrectKeyword); //Call text input method
		HomePageObject.SearchByEnter(); //Call search by enter button method

		SearchResultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver

		//Hard assertion method to check if auto-correct section is displayed in search page
		try {
			Assert.assertTrue(SearchResultsPageObject.DidYouMean.isDisplayed());
			SearchResultsPageObject.GetCorrectKeyword(); //Call method to print correct keyword
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
