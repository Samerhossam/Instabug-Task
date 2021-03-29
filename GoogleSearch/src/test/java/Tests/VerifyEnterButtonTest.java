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

public class VerifyEnterButtonTest extends TestBase{
	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchResultsPageObject; //Declare Object from Search results page

	@Test
	//Verify if pressing enter button goes to search results page
	public void VerifySearchByEnterButton() throws InterruptedException	{
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String Keyword = "Instabug"; //Assign value to input keyword
		HomePageObject.SearchMethod(Keyword); //Call text input method
		Thread.sleep(2000); //Wait 2 seconds
		HomePageObject.SearchByEnter(); //Call search by enter button method
		
		SearchResultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver

		//method to loop over list of title in search results page
		for(int i = 0; i<SearchResultsPageObject.Headers.size(); i++)
		{
			//Check if number of titles is bigger than zero
			If(SearchResultsPageObject.Headers.size() > 0);
			{
				Assert.assertNotEquals(SearchResultsPageObject.Headers.size(), 0);
			}
		}

		System.out.println(SearchResultsPageObject.Headers.size() + " results appeared"); //Print number of results
	}

	private void If(boolean b) {
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
