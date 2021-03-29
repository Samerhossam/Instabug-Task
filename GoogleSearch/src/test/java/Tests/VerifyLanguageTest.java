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

public class VerifyLanguageTest extends TestBase{
	HomePage HomePageObject; //Declare Object from Home page
	SearchResultsPage SearchResultsPageObject; //Declare Object from Search results page

	@Test
	//Verify if search engine corrects the user if typing is in another language
	public void VerifyLanguage() throws InterruptedException {
		HomePageObject = new HomePage(Driver); //Assign object to web-driver
		String ArabicKeyword = "هىسفشلاعل"; //Assign value to wrong input keyword
		String EnglishKeyword = "Instabug"; //Assign value to correct input keyword
		HomePageObject.SearchMethod(ArabicKeyword); //Call text input method
		Thread.sleep(2000); //Wait 2 seconds
		HomePageObject.SearchByEnter(); //Call search by enter button method

		SearchResultsPageObject = new SearchResultsPage(Driver); //Assign object to web-driver

		int counter = 0; //Initialise counter

		//Method to loop over list of titles in search results page
		for(int i = 0; i<SearchResultsPageObject.Headers.size(); i++)
		{
			//Check if search results contains the correct keyword
			if(SearchResultsPageObject.Headers.get(i).getText().contains(EnglishKeyword))
			{
				Assert.assertTrue(SearchResultsPageObject.Headers.get(i).getText().contains(EnglishKeyword));
				counter++; //Increment counter
				System.out.println(SearchResultsPageObject.Headers.get(i).getText()); //Print list of titles in search page
			}
		}

		System.out.println(EnglishKeyword + " is found in search " + counter + " times"); //Print number of times the correct keyword appeared in search results
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
